#include "ipoption.h"

void
set_error(error_t *err, int ecode, char *err_msg)
{
	err->ecode = ecode;
	strncpy(err->emsg, err_msg, sizeof (err->emsg));
}

uint16_t
ip_checksum(ip_t *hdr)
{
	uint16_t	*ptr = (uint16_t *)hdr;
	uint32_t	sum = 0;
	uint16_t	check_sum;
	uint16_t	len = ntohs(hdr->ip_len);

	hdr->ip_sum = 0;

	while (len > 1) {
		sum += *ptr;
		ptr++;
		len -= 2;
        }

	if (len) {
		uint8_t	*val;
		val = (uint8_t *)ptr;

		sum += *val;
	}

	sum = (sum >> 16) + (sum & 0xffff);
	sum = sum + (sum >> 16);
	check_sum = ~sum;
	return (check_sum);
}

void
ip_set_hdr_len(pkt_t *pkt, int new_hdr_len)
{
	ip_t	*new_ip;

	new_ip = (ip_t *)pkt->msg;
	new_ip->ip_hl = (new_hdr_len >> 2);
}

void
ip_set_len(pkt_t *pkt, int new_pkt_len)
{
	ip_t	*new_ip;

	new_ip = (ip_t *)pkt->msg;
	new_ip->ip_len = htons(new_pkt_len);
	pkt->len = new_pkt_len;
}

/*
 * appends the custom IP option to a IP packet
 */
void
ip_append_options(pkt_t *pkt, int opt_off, int padding, custom_opt_t *opt)
{
	uint8_t		*off;
	ip_opt_t	*new_ip_opt;

	off = (uint8_t *)pkt->msg;
	off += opt_off;

	/* set the offset */
	new_ip_opt = (ip_opt_t *)off;

	/*
	 * copied flag is set to 0
	 * option class is set to 0 (control)
	 */
	bzero(new_ip_opt, sizeof (*new_ip_opt));
	new_ip_opt->code = opt->code;
	/*
	 * note we need to add +1 to include the custom option
	 * as well as the option length field
	 */
	new_ip_opt->len = opt->len + 1;
	memcpy((new_ip_opt + 1), opt->opt, opt->len);
	off += opt->len + 2;

	if (padding) {
		/*
		 * padding implies that we need to add the EOL option
		 * according to RFC
		 */
		ip_opt_t	*eol_opt;

		eol_opt = (ip_opt_t *)off;
		eol_opt->code = 0;

		padding--;
		off++;
		/* bzero rest of padded space */
		while (padding--) {
			*off++ = 0;
		}
	}
}

void
pkt_realloc(pkt_t *pkt, int new_pkt_len)
{
	void	*new_msg;

	/* need to check for fragment */
	new_msg = (void *)malloc(new_pkt_len);
	if (!new_msg) {
		return;
	}
	bzero(new_msg, new_pkt_len);

	pkt->msg = new_msg;
	pkt->len = new_pkt_len;
}

void
ip_copy_header_options(pkt_t *pkt, ip_t *old_ip)
{
	ip_t	*new_ip;
	uint8_t	*off;
	int	old_hdr_len;

	old_hdr_len = old_ip->ip_hl * 4;

	new_ip = (ip_t *)pkt->msg;
	off = (uint8_t *)new_ip;

	/* copy the old header first */
	memcpy(off, old_ip, old_hdr_len);
}

/*
 * copies the payload when a new IP packet structure is allocated
 * when inserting the IP option
 */
void
ip_copy_payload(pkt_t *pkt, ip_t *old_ip, int payload_len)
{
	ip_t	*new_ip;
	uint8_t	*src;
	uint8_t	*dst;
	int	old_hdr_len;
	int	new_hdr_len;

	old_hdr_len = old_ip->ip_hl * 4;

	new_ip = (ip_t *)pkt->msg;
	new_hdr_len = new_ip->ip_hl * 4;

	/* copy the old header first */
	src = (uint8_t *)old_ip;
	src += old_hdr_len;

	dst = (uint8_t *)new_ip;
	dst += new_hdr_len;
	memcpy(dst, src, payload_len);

}

/*
 * Updates the IP packet
 */
void
ip_update_checksum(pkt_t *pkt)
{
	ip_t	*new_ip;

	new_ip = (ip_t *)pkt->msg;
        new_ip->ip_sum = htons(ip_checksum(new_ip));
}

void
ip_move_payload(pkt_t *pkt, int old_payload_off, int ip_payload_len)
{
	uint8_t		*src;
	uint8_t		*dst;
	ip_t		*ip;

	src = (uint8_t *)pkt->msg;
	src += old_payload_off;

	dst = (uint8_t *)pkt->msg;
	ip = (ip_t *)pkt->msg;
	dst += (ip->ip_hl * 4);

	memcpy(dst, src, ip_payload_len);
}

int
ip_get_option_len(ip_t *iphdr)
{
	int		hl;
	uint8_t		*ptr;
	ip_opt_t	*ip_opt;
	int		opt_len = 0;
	int		opt_offset = 0;

	hl = iphdr->ip_hl * 4;
	if (hl == MIN_IP_SIZE) {
		/* no options */
		return (opt_len);
	}

	/*
	 * Note, header size has already been validated by the caller
	 * so, no valid checks are required
	 */

	/*
	 * Some ip options are present since the header size is more than
	 * the regular header size. There are two terminating cases for 
	 * option processing :
	 * (1) check for header length
	 * (2) check for End of Options list (0x00)
	 */
	ptr = (uint8_t *)(iphdr + 1);
	opt_offset = MIN_IP_SIZE;
	ip_opt = (ip_opt_t *)ptr;
	while (opt_offset < hl) {
		ptr++;
		if (!IPOPT_NUMBER(ip_opt->code)) {	
			break;
		}
		ptr += ip_opt->len;
		opt_len += ip_opt->len + 1;
		opt_offset += ip_opt->len + 1;
		ip_opt = (ip_opt_t *)ptr;
	}
	return (opt_len);
}

uint8_t *
ip_get_option_offset(pkt_t *pkt, custom_opt_t *opt)
{
	int		hl;
	ip_t		*iphdr;
	uint8_t		*ptr;
	ip_opt_t	*ip_opt;
	int		opt_len = 0;
	int		opt_off = 0;

	iphdr = (ip_t *)pkt->msg;
	hl = iphdr->ip_hl * 4;
	if (hl == MIN_IP_SIZE) {
		/* no options */
		return (NULL);
	}

	/*
	 * Note, header size has already been validated by the caller
	 * so, no valid checks are required
	 */

	/* some ip options are present */
	ptr = (uint8_t *)(iphdr + 1);
	ip_opt = (ip_opt_t *)ptr;
	opt_off = MIN_IP_SIZE;
	while (opt_off < hl) {
		ip_opt = (ip_opt_t *)ptr;
		ptr++;
		opt_len++;
		opt_off++;
		if (!IPOPT_NUMBER(ip_opt->code)) {
			break;
		}
		if (ip_opt->code == opt->code) {
			/* found a match */
			return (uint8_t *)(ip_opt);
		}
		opt_len += ip_opt->len;
		ptr += ip_opt->len;
		opt_off += ip_opt->len;
	}	
	return (NULL);
}

int
ip_validate_pkt(pkt_t *pkt, error_t *err)
{
	ip_t	*ip;
	int	vers;
	int	hdr_len;

	ip = (ip_t *)pkt->msg;
	if (pkt->len < MIN_IP_SIZE) {
		set_error(err, E_INVALID_SIZE, 
		    "Incorrect payload size");
		return (E_INVALID_SIZE);
	}

	/* check version */
	vers = ip->ip_v << 4;
	if (vers != 4) {
		/* no ip packet, don't do anything */
		return (0);
	}

	hdr_len = ip->ip_hl * 4;

	if (pkt->len < hdr_len) {
		set_error(err, E_INVALID_HDR_SIZE,
		    "Incorrect header size");
		return (E_INVALID_HDR_SIZE);
	}

	return (0);
}

int
ip_add_custom_option(pkt_t *pkt, custom_opt_t *opt, error_t *err)
{
	ip_t	*ip;
	int	vers;
	int	ip_hdr_len;
	int	ip_len;
	int	ip_payload_len;
	int	ip_opt_len;
	int	new_hdr_len;
	int	new_pkt_len;
	int	padding;
	int	res;

	if (pkt == NULL || err == NULL || opt == NULL) {
		set_error(err, E_INVALID_PARAM,
		    "Null parameters");
		return (E_INVALID_PARAM);
	}

	ip = (ip_t *)pkt->msg;

	if ((res = ip_validate_pkt(pkt, err))) {
		return (res);
	}

	ip_hdr_len = ip->ip_hl * 4;

	/*
	 * for an ip fragment, don't add the option
	 */

	/*
	 * When a new ip option is add, the following needs to change
	 * 	(1) the total length
	 * 	(2) the checksum of the packet
	 *	(3) the payload needs to be reallocated since
	 * 	    new block of data needs to be inserted in the
	 *	    payload
	 */

	/* get the ip length, payload length and option length */
	ip_len = ntohs(ip->ip_len);
	ip_payload_len = ip_len - ip_hdr_len;
	ip_opt_len = ip_get_option_len(ip);

	/* calculate the new packet length */
	new_hdr_len = MIN_IP_SIZE + ip_opt_len + opt->len + sizeof (ip_opt_t);
	padding = 0;
	if (new_hdr_len & IP_HDR_BOUNDARY_MASK) {
		padding = IP_HDR_BOUNDARY - (new_hdr_len & IP_HDR_BOUNDARY_MASK);
	}
	new_hdr_len += padding;
	if (new_hdr_len > MAX_IP_SIZE) {
		set_error(err, E_MAXSIZE,
		    "Option size exceeds maximum IP header size");
		return E_MAXSIZE;
	}

	/* need to check for fragment */
	new_pkt_len = new_hdr_len + ip_payload_len;
	pkt_realloc(pkt, new_pkt_len);
	if (pkt->msg == NULL) {
		set_error(err, E_NOMEM,
		    "No memory available\n");
		return E_NOMEM;
	}	

	ip_copy_header_options(pkt, ip);
	ip_append_options(pkt, MIN_IP_SIZE + ip_opt_len, padding, opt);
	ip_set_hdr_len(pkt, new_hdr_len);
	ip_set_len(pkt, new_pkt_len);
	ip_copy_payload(pkt, ip, ip_payload_len);
	ip_update_checksum(pkt);

	/* delete the old ip which is no longer being used */
	free(ip);
	return (0);	
}

int
ip_remove_custom_option(pkt_t *pkt, custom_opt_t *opt, error_t *err)
{
	ip_t	*ip;
	int	vers;
	int	ip_hdr_len;
	int	ip_len;
	int	ip_payload_len;
	int	ip_opt_len;
	int	new_hdr_len;
	int	new_pkt_len;
	int	padding;
	int	res;
	uint8_t	*opt_buf;
	int	remaining_hdr;
	int	opt_off;

	if (pkt == NULL || err == NULL || opt == NULL) {
		set_error(err, E_INVALID_PARAM,
		    "Null parameters");
		return (E_INVALID_PARAM);
	}

	ip = (ip_t *)pkt->msg;

	if ((res = ip_validate_pkt(pkt, err))) {
		return (res);
	}

	ip_hdr_len = ip->ip_hl * 4;

	/*
	 * When a new ip option is removed, the following needs to change
	 * 	(1) the total length
	 * 	(2) the checksum of the packet
	 *	(3) the payload needs to be reallocated
	 */

	/* get the ip length, payload length and option length */
	ip_len = ntohs(ip->ip_len);
	ip_payload_len = ip_len - ip_hdr_len;
	ip_opt_len = ip_get_option_len(ip);

	if (ip_opt_len == 0) {
		/* nothing to do, since there are no options */
		return (0);
	}

	/* get the offset at which the given option is present */
	opt_buf = ip_get_option_offset(pkt, opt);
	if (opt_buf == NULL) {
		/* nothing to do since the required option is not present */
		return (0);
	}

	/* calculate the new packet length */
	new_hdr_len = MIN_IP_SIZE + ip_opt_len - opt->len - sizeof (ip_opt_t);
	padding = 0;
	if (new_hdr_len & IP_HDR_BOUNDARY_MASK) {
		padding = IP_HDR_BOUNDARY - (new_hdr_len & IP_HDR_BOUNDARY_MASK);
	}
	new_hdr_len += padding;

	new_pkt_len = new_hdr_len + ip_payload_len;

	/* move any remaining options */
	opt_off = (uintptr_t)opt_buf - (uintptr_t)ip;
	remaining_hdr = ip_hdr_len - opt_off - (opt->len + sizeof (ip_opt_t));
	memcpy(opt_buf, opt_buf + (opt->len + sizeof (ip_opt_t)),
	    remaining_hdr);
	opt_buf += remaining_hdr;
	bzero(opt_buf, padding);

	/* set new hdr len and pkt len */
	ip_set_hdr_len(pkt, new_hdr_len);
	ip_set_len(pkt, new_pkt_len);

	/* move the payload */
	ip_move_payload(pkt, ip_hdr_len, ip_payload_len);
	ip_update_checksum(pkt);

	return (0);	
}
