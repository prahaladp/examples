#include <stdio.h>
#include <string.h>

int bit_string_insert(int n, int m, int i, int j)
{
	int	s;
	int	mask;
	int	nmask;
	int	val;

	/* assumes the size of m */
	/* validate i and j */
	if (i > (sizeof (n) * 8) ||
	    j > (sizeof (n) * 8)) {
		printf("incorrect size parameters\n");
		return n;
	}

	nmask = (1 << (i - 1));

	mask = 0x1;
	for (s = i; s <= j; s++, mask <<= 1, nmask <<= 1) {
		val = 0;
		if (mask & m) {
			val = 1;
		}
		printf("val = %x, %x\n", val, nmask);
		n = (~nmask & n) | val;
	}
	return n;
}

int main(int argc, char *argv[])
{
	int	n = 0x100000;
	int	m = 0x15;

	printf("values are \n\t n = %x\n\t m = %x\n\t f = %x\n\t\n",
	    n, m, bit_string_insert(n, m, 2, 6));

}
