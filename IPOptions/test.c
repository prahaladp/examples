#include <stdio.h>
#include "ipoption.h"

#define	LOG_ERR	printf
#define	LOG	printf

#define REGULAR_PKT_SIZE	35

void
create_basic_ip_hdr(ip_t *ip)
{
	char	src_addr[] = "1.1.1.1";
	char	dst_addr[] = "2.2.2.2";

	bzero(ip, sizeof (*ip));
	ip->ip_hl = 5;
	ip->ip_v = 4;
	ip->ip_p = IPPROTO_TCP;

	ip->ip_src.s_addr = 0x01010101;
	ip->ip_dst.s_addr = 0x02020202;
}

void
seq_fill(void *buf, int sz)
{
	int	i = 0;
	uint8_t	*ptr = (uint8_t *)buf;

	while (i < sz) {
		*ptr++ = (i % 10);
		i++;
	}
}

int
compare_payload(uint8_t *pay1, uint8_t *pay2, int sz)
{
	return (memcmp(pay1, pay2, sz));
}

/*
 * assumes opt_size is aligned on 4 byte boundary
 */
pkt_t *
create_pkt(int pkt_size, uint8_t *opt, int opt_size)
{
	pkt_t	*pkt;
	ip_t	*ip;
	uint8_t	*off;

	pkt = (pkt_t *)malloc(sizeof (pkt_t));
	if (!pkt) {
		return (NULL);
	}

	pkt->len = pkt_size + MIN_IP_SIZE + opt_size;
	pkt->msg = malloc(pkt->len);
	if (!pkt->msg) {
		free(pkt);
		return (NULL);
	}

	off = (uint8_t *)pkt->msg;

	ip = (ip_t *)pkt->msg;
	ip->ip_id = 1;

	create_basic_ip_hdr(ip);
	if (opt) {
		memcpy(off + MIN_IP_SIZE, opt, opt_size);
		ip->ip_hl = (MIN_IP_SIZE + opt_size) >> 2;
	}

	seq_fill(off + (MIN_IP_SIZE + opt_size), pkt_size);

	ip->ip_len = htons(pkt->len);
	ip->ip_sum = htons(ip_checksum(ip));
	return (pkt);
}

pkt_t *
copy_pkt(pkt_t *p)
{
	pkt_t	*new_pkt;

	new_pkt = (pkt_t *)malloc(sizeof (pkt_t));
	new_pkt->len = p->len;
	new_pkt->msg = malloc(p->len);

	memcpy(new_pkt->msg, p->msg, new_pkt->len);
	return (new_pkt);
}

void
test_case(uint16_t payload_sz, uint8_t *ip_opt, int ip_opt_size, custom_opt_t *opt,
	uint16_t expected_opt_len)
{
	pkt_t		*pkt;
	error_t		err;
	ip_t		*new_ip;
	ip_t		*ip;
	uint16_t	new_hdr_len;
	int		res;
	uint16_t	old_csum;
	uint16_t	old_len;
	uint16_t	old_hdr_len;
	uint16_t	new_len;
	pkt_t		*copy;
	uint8_t		*pay1, *pay2;

	LOG("Test case : creating packet, appending IP option\n");
	pkt = create_pkt(payload_sz, ip_opt, ip_opt_size);
	ip = (ip_t *)pkt->msg;
	old_hdr_len = ip->ip_hl * 4;
	old_csum = ip->ip_sum;
	old_len = ntohs(ip->ip_len);
	copy = copy_pkt(pkt);

	res = ip_add_custom_option(pkt, opt, &err);
	if (res) {
		LOG_ERR("Test Case failed with %d\n", res);
		free(pkt);
		return;
	}

	LOG("Test case : checking new header len\n");
	new_ip = (ip_t *)pkt->msg;
	new_hdr_len = new_ip->ip_hl * 4;
	LOG("New hdr len : %d\n", new_hdr_len);
	if (new_hdr_len <= MIN_IP_SIZE) {
		LOG_ERR("Test Case failed, expecting bigger header size");
		LOG_ERR("Recieved header size %d\n", new_hdr_len);
		free(pkt);
		return;
	}	

	LOG("Test case : testing new packet len\n");
	new_len = payload_sz + MIN_IP_SIZE + expected_opt_len;
	if (ntohs(new_ip->ip_len) != new_len) {
		LOG_ERR("Test case failed, incorrect packet len\n");
		LOG_ERR("Expected %d, Recvd %d\n", new_len, ntohs(new_ip->ip_len));
		free(pkt);
		return;
	}

	LOG("Test case : checking ip checksum\n");
	if (old_csum == new_ip->ip_sum) {
		LOG_ERR("Test case failed, expecting a different checksum\n");
		free(pkt);
		return;
	}

	LOG("Test case : comparing payload\n");
	pay1 = (uint8_t *)copy->msg + MIN_IP_SIZE + ip_opt_size;
	pay2 = (uint8_t *)new_ip + new_hdr_len;
	if (compare_payload(pay1, pay2, payload_sz)) {
		LOG_ERR("Test case failed, payloads didn't match\n");
		free(pkt);
		return;
	}

	LOG("Test case : remove option\n");
	res = ip_remove_custom_option(pkt, opt, &err);
	if (res) {
		LOG_ERR("Test case failed, remove option failed with %d\n", res);
		free(pkt);
		return;
	}

	LOG("Test case : comparing packet len after removing options\n");
	new_ip = (ip_t *)pkt->msg;
	new_hdr_len = new_ip->ip_hl * 4;
	if (ntohs(new_ip->ip_len) != copy->len) {
		LOG_ERR("Test case failed, recvd len (%d), expected (%d)\n",
		    pkt->len, copy->len);
		free(pkt);
		return;
	}

	LOG("Test case : comparing packet contents after removing options\n");
	pay1 = (uint8_t *)copy->msg + MIN_IP_SIZE + ip_opt_size;
	pay2 = (uint8_t *)new_ip + new_hdr_len;
	if (memcmp(pay1, pay2, payload_sz)) {
		LOG_ERR("Test case failed, contents didn't match\n");
		free(pkt);
		return;
	}

	free(pkt);
}

int
main(int argc, char *argv[])
{
	uint8_t 	opt_arr1[] = { 0x1, 0x2, 0x1, 0x0 };
	uint8_t 	opt_arr2[] = { 0x1, 0x4, 0x1, 0x1, 0x0, 0x0, 0x0, 0x0 };
	custom_opt_t	opt;
	uint16_t	expected_opt_size;
	uint16_t	payload_sz;

	opt.code = CUSTOM_IP_OPT;
	opt.len = 3;
	opt.opt[0] = 'X';
	opt.opt[1] = 'Y';
	opt.opt[2] = 'Z';

	expected_opt_size = 8;
	payload_sz = 5;
	test_case(payload_sz, NULL, 0, &opt, expected_opt_size);

	expected_opt_size = 8;
	payload_sz = 1000;
	test_case(payload_sz, opt_arr1, sizeof (opt_arr1), &opt, expected_opt_size);

	expected_opt_size = 24;
	payload_sz = 1000;
	strncpy(opt.opt, "Special Option", sizeof (opt.opt));
	opt.len = strlen("Special Option");
	test_case(payload_sz, opt_arr2, sizeof (opt_arr2), &opt, expected_opt_size);
} 
