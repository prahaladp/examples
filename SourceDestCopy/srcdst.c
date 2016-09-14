#include <stdio.h>
#include <stdlib.h>

struct Node {
    char         *buffer;
    int         length;
    struct Node *next;
};

/*
 * Test cases:
 *    (1) dst = NULL,    
 *    (2) src = NULL,    (check the original list is intact)
 *    (3) src buffer fits in completely in the dst node
          s = a(1)->null
          d = d(5)->null
      (4) overlapping buffer fit - >two nodes
          s = a(10)->null
          d = b(2)->c(120)->null
      (5) overlapping buffer - span multiple dst nodes
          s = a(10)->null
          d = b(5)->c(2)->d(10)->null
      (6) run out of space on dst nodes
          s = a(10)->null
          d = b(5)->c(2)->null
      (7) variation of (4), (5) and (6) but with src having multiple nodes
      (8) s = a(1)->b(1)->null
          d = c(1)->d(1)->null
 */

void buffer_copy(char *dst_b, char *src_b, int sz)
{
    while (sz--) {
        *dst_b++ = *src_b++;
    }
}

int copy_src_to_dst(struct Node *dst, struct Node *src)
{
    struct Node    *siter = src;
    struct Node    *diter = dst;
    int            dcounter;
    
    if (!diter) {
        /* flag an error */
        return (-1);
    }
    
    dcounter = diter->length;
    while (siter) {
        int    remaining = siter->length;
        int    sz = 0;
        
        /* copy from src till we are done */
        while (remaining) {
            if (dcounter > remaining) {
                /* more space */
                
                sz = remaining;
               
                buffer_copy((diter->buffer + diter->length - dcounter),
                    (siter->buffer + siter->length - remaining), sz);
                dcounter = dcounter - remaining;
                remaining = 0;

            } else {
                sz = dcounter;
               
                /* when the source has more buffer length  */
                buffer_copy((diter->buffer + diter->length - dcounter),
                    (siter->buffer + siter->length - remaining), sz);
                
                diter = diter->next;
                if (!diter) {
                    /* run out of dst space */
                    /* log and return */
                    return (-1);
                }
                dcounter = diter->length;
                remaining -= sz;
            }
        }
        /* go to next src node */
        siter = siter->next;
    }
    return (0);

}

int main(int argc, char **argv)
{
}
