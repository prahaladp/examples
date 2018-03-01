#include <pcap.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <ctype.h>
#include "ipoption.h"

int g_add_option = 0;
int g_remove_option = 0;

/* default snap length (maximum bytes per packet to capture) */
#define SNAP_LEN 1518

/* ethernet headers are always exactly 14 bytes [1] */
#define SIZE_ETHERNET 14

/* Ethernet addresses are 6 bytes */
#define ETHER_ADDR_LEN  6

void
print_hex_ascii_line(const u_char *payload, int len, int offset)
{

        int i;
        int gap;
        const u_char *ch;

        /* offset */
        printf("%05d   ", offset);
        
        /* hex */
        ch = payload;
        for(i = 0; i < len; i++) {
                printf("%02x ", *ch);
                ch++;
                /* print extra space after 8th byte for visual aid */
                if (i == 7)
                        printf(" ");
        }
        /* print space to handle line less than 8 bytes */
        if (len < 8)
                printf(" ");
        
        /* fill hex gap with spaces if not full line */
        if (len < 16) {
                gap = 16 - len;
                for (i = 0; i < gap; i++) {
                        printf("   ");
                }
        }
        printf("   ");
        
        /* ascii (if printable) */
        ch = payload;
        for(i = 0; i < len; i++) {
                if (isprint(*ch))
                        printf("%c", *ch);
                else
                        printf(".");
                ch++;
        }

        printf("\n");

return;
}

/*
 * print packet payload data (avoid printing binary data)
 */
void
print_payload(const u_char *payload, int len)
{

        int len_rem = len;
        int line_width = 16;                    /* number of bytes per line */
        int line_len;
        int offset = 0;                                 /* zero-based offset
counter */
        const u_char *ch = payload;

        if (len <= 0)
                return;

        /* data fits on one line */
        if (len <= line_width) {
                print_hex_ascii_line(ch, len, offset);
                return;
        }

        /* data spans multiple lines */
        for ( ;; ) {
                /* compute current line length */
                line_len = line_width % len_rem;
                /* print line */
                print_hex_ascii_line(ch, line_len, offset);
                /* compute total remaining */
                len_rem = len_rem - line_len;
                /* shift pointer to remaining bytes to print */
                ch = ch + line_len;
                /* add offset */
                offset = offset + line_width;
                /* check if we have line width chars or less */
                if (len_rem <= line_width) {
                        /* print last line and get out */
                        print_hex_ascii_line(ch, len_rem, offset);
                        break;
                }
        }

return;
}


void
got_packet(u_char *args, const struct pcap_pkthdr *header, const u_char
*packet)
{
        pkt_t           *pkt;
        int             res;
        custom_opt_t    opt;
        error_t         err;

        printf("got packet\n");
        pkt = (pkt_t *)malloc(sizeof (pkt_t));
        if (!pkt) {
                printf("unable to allocate pkt_t\n");
                return;
        }

        pkt->msg = malloc(header->len - SIZE_ETHERNET);
        if (!pkt->msg) {
                printf("unable to allocate pkt->msg\n");
                free(pkt);
                return;        
        }
        pkt->len = header->len - SIZE_ETHERNET;

        memcpy(pkt->msg, packet + SIZE_ETHERNET, header->len - SIZE_ETHERNET);
        opt.code = CUSTOM_IP_OPT;
        strncpy(opt.opt, "Special Option", sizeof (opt.opt));
        opt.len = strlen("Special Option");

        if (g_add_option) {
                res = ip_add_custom_option(pkt, &opt, &err);
                if (res) {
                        printf("ip_add_custom_option failed with %d\n", res);
                        free(pkt->msg);
                        free(pkt);
                        return; 
                }
        } else if (g_remove_option) {
                res = ip_remove_custom_option(pkt, &opt, &err);
                if (res) {
                        printf("ip_add_custom_option failed with %d\n", res);
                        free(pkt->msg);
                        free(pkt);
                        return;
                }
        }


        /*
         * at this point pkt->msg contains the IP with the modified header and
         * the new option
         */
        /* call_app(pkt); */
        print_payload(pkt->msg, pkt->len);
        free(pkt->msg);
        free(pkt);
}

int main(int argc, char **argv)
{

        char *dev = NULL;                       /* capture device name */
        char errbuf[PCAP_ERRBUF_SIZE];          /* error buffer */
        pcap_t *handle;                         /* packet capture handle */

        char filter_exp[] = "ip";               /* filter expression [3] */
        struct bpf_program fp;                  /* compiled filter program (expression) */
        bpf_u_int32 mask;                       /* subnet mask */
        bpf_u_int32 net;                        /* ip */
        int num_packets = 100;                   /* number of packets to capture */


        /* check for capture device name on command-line */
        if (argc != 3) {
                fprintf(stderr, "Usage : <pcap> add/remove <device>\n");
                exit(EXIT_FAILURE);
        } 

        if (strcmp(argv[1], "add") == 0) {
                g_add_option = 1;
        } else if (strcmp(argv[1], "remove") == 0) {
                g_remove_option = 1;
        } else {
                fprintf(stderr, "Usage : <pcap> add/remove <device>\n");
                exit(EXIT_FAILURE);
        }

        dev = argv[2];
        
        /* get network number and mask associated with capture device */
        if (pcap_lookupnet(dev, &net, &mask, errbuf) == -1) {
                fprintf(stderr, "Couldn't get netmask for device %s: %s\n",
                    dev, errbuf);
                net = 0;
                mask = 0;
        }

        /* print capture info */
        printf("Device: %s\n", dev);
        printf("Number of packets: %d\n", num_packets);
        printf("Filter expression: %s\n", filter_exp);

        /* open capture device */
        handle = pcap_open_live(dev, SNAP_LEN, 1, 1000, errbuf);
        if (handle == NULL) {
                fprintf(stderr, "Couldn't open device %s: %s\n", dev, errbuf);
                exit(EXIT_FAILURE);
        }

        /* make sure we're capturing on an Ethernet device [2] */
        if (pcap_datalink(handle) != DLT_EN10MB) {
                fprintf(stderr, "%s is not an Ethernet\n", dev);
                exit(EXIT_FAILURE);
        }

        /* compile the filter expression */
        if (pcap_compile(handle, &fp, filter_exp, 0, net) == -1) {
                fprintf(stderr, "Couldn't parse filter %s: %s\n",
                    filter_exp, pcap_geterr(handle));
                exit(EXIT_FAILURE);
        }

        /* apply the compiled filter */
        if (pcap_setfilter(handle, &fp) == -1) {
                fprintf(stderr, "Couldn't install filter %s: %s\n",
                    filter_exp, pcap_geterr(handle));
                exit(EXIT_FAILURE);
        }

        /* now we can set our callback function */
        pcap_loop(handle, num_packets, got_packet, NULL);

        /* cleanup */
        pcap_freecode(&fp);
        pcap_close(handle);

        printf("\nCapture complete.\n");

return 0;
}

