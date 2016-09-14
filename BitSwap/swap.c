#include <stdio.h>
#include <strings.h>
#include <stdlib.h>

int bswap(int val)
{
	int num_rounds = sizeof (int) * 8/2;
	int mask = 1;

	for (; num_rounds; num_rounds--, mask <<= 2)
	{
		int lsb = (val & mask) ? 1 :0 ;
		int rsb = (val & (mask << 1)) ? 1 : 0;
		printf("%x, %x, %d, %d\n", val, mask, lsb, rsb);
		if (lsb != rsb) {
			val = val & ~(mask | (mask << 1));
			if (lsb) {
				val |= (mask << 1);
			} else {
				val |= (mask);
			}
		}
	}
	return (val);
}

int main(int argc, char *argv[])
{
	int val = atoi(argv[1]);
	printf("bitswap (%x - > %x)\n", val, bswap(val));
}
