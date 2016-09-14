#ifndef IP_OPTION
#define IP_OPTION
#include <stdio.h>
#include <strings.h>
#include <stdlib.h>
#include <netinet/in.h>

#define	MIN_IP_SIZE	20
#define	MAX_IP_SIZE	60
#define	CUSTOM_IP_OPT	10

#define	E_INVALID_SIZE	201
#define	E_INVALID_PARAM	202
#define	E_INVALID_HDR_SIZE	203
#define	E_NOMEM		204
#define	E_MAXSIZE	205

#define	IP_HDR_BOUNDARY		4
#define	IP_HDR_BOUNDARY_MASK	(IP_HDR_BOUNDARY - 1)
#define IPOPT_COPIED(o)         ((o)&0x80)
#define IPOPT_CLASS(o)          ((o)&0x60)
#define IPOPT_NUMBER(o)         ((o)&0x1f)


/* copied from netinet/ip.h */

typedef struct ip_s {
        uint8_t		ip_v:4,                 /* version */
			ip_hl:4;                /* header length */
        uint8_t		ip_tos;                 /* type of service */
        uint16_t	ip_len;                 /* total length */
        uint16_t	ip_id;                  /* identification */
        uint16_t	ip_off;                 /* fragment offset field */
#define IP_RF 0x8000                    	/* reserved fragment flag */
#define IP_DF 0x4000                    	/* dont fragment flag */
#define IP_MF 0x2000                    	/* more fragments flag */
#define IP_OFFMASK 0x1fff              		/* mask for fragmenting bits */
        uint8_t		ip_ttl;                 /* time to live */
        uint8_t		ip_p;                   /* protocol */
        uint16_t	ip_sum;                 /* checksum */
        struct in_addr	ip_src, ip_dst;		/* source and dest address */
} ip_t;

typedef struct ip_opt_s {
	uint8_t	code;               /* IPOPT_TS */
	uint8_t	len;                /* size of structure (variable) */
	/* variable size data */
} ip_opt_t;


typedef struct pkt_s {
	void	*msg;
	int	len;
} pkt_t;

typedef struct error_s {
	char	emsg[80];
	int	ecode;
} error_t;

typedef struct custom_opt_s {
	int	code;
        int     len;
        char    opt[32];
} custom_opt_t;

int ip_add_custom_option(pkt_t *pkt, custom_opt_t *opt, error_t *err);
int ip_remove_custom_option(pkt_t *pkt, custom_opt_t *opt, error_t *err);
uint16_t ip_checksum(ip_t *hdr);

#endif
