#include "../comon/header.h"

int	pattern = 0xFE6B2840;
int	pmask_len = 32;

int
get_bit(char ch, int bit)
{
	int	mask = (1 >> bit);
	return (ch & mask);
}

int
bitPosition32BitPattern(char *byteArray, int length)
{
	int	smask = 0x10;
	int	indx = 0;

	while (indx < length) {
		cval = byteArray[indx];

		while (((cval & smask) != (pattern & smask)) &&
			smask) {
			smask >>= 1;
		}

		if (smask) {
			/* check for a sub-pattern */
			int	cmask = smask >> 1;
			int	clen = 1;
			int	cindx = indx;
			int	pval = cval;

			while (clen < pmask_len) {
				if (!(pval & cmask)) {
					/* no match, bail out */
					break;
				}

				cmask >>= 1;
				clen++;
				if (!cmask && clen < pmask_len) {
					/* move to the next index */
					if (cindx + 1 >= length) {
						break;
					}
					cindx++;
					cmask = 0x10;
					pval = byteArray[cindx];
				}
			}

			if (clen >= pmask_len) {
				/* match break */
				return indx;
			}
			smask >>= 1;
		}

		if (!smask) {
			indx++;
			smask = 0x10;
		}
	}

	return (-1);
}

int
main(int argc, char *argv[])
{
	char array[] = { 0x0, 0x03, 0xFC, 0xD6, 0x50, 0x80, 0x04, 0x06};

	printf("bit position = %d\n", bitPosition32BitPattern(array, sizeof (array)));
}
