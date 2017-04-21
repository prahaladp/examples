#include <stdio.h>
#include <stdlib.h>

int
power(int x) {
	int	val = 1;

	if (x == 0) {
		return val;
	}
	while (x--) {
		val *= 10;
	}
	return val;
}

int
tidy(int num) {
	int	div = 10;
	int	covered = 0;
	int	last = 100; /* some big number */
	int	iter = 0;
	int	orig = num;

	printf("tidy (%d) \n", num);

	while (num) {
		int	rem = num % div;

		printf("num = %d, rem = %d, iter = %d, covered = %d\n",
			num, rem, iter, covered);
		if (rem <= last) {
			/* we are fine here, continue */
			covered += (rem * power(iter++));
			num /= div;
			last = rem;
			continue;
		}

		/* iter should be >= 1 always */
		orig /= power(iter);
		num = ((orig - 1) * power(iter)) + (power(iter) - 1);
		return tidy(num);
	}

	printf("tidy (%d) \n", covered);
	return covered;
}

int
main(int argc, char *argv[]) {
	if (argc != 2) {
		return 0;
	}

	tidy(atoi(argv[1]));
}
