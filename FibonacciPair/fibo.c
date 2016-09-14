/*

Assume we only take the least significant digit of each value in fibonacci sequence, and form the sequence of digits into pairs. In those pairs, the first value of one pair is the same as second value of its predecessor.

As we know the fibonacci sequence is 1, 1, 2, 3, 5, 8, 13, 21...
so the pair sequence is:
[1, 1], [1, 2], [2, 3], [3, 5], [5, 8], [8, 3], [3, 1] ...

 */
#include <stdio.h>
#include <strings.h>
#include <stdlib.h>

#define	int64	unsigned long

void OutputPairs(int n)
{
	int64 fib_start = 1;
	int64 fib_next = 1;

	while (n) {
		int64	temp;

		printf("[%ld %ld] ", fib_start, fib_next);
		temp = fib_next;
		fib_next = fib_start + fib_next;
		fib_start = temp;

		n--;
	}
	printf("\n");
}

int main(int argc, char *argv[])
{
	OutputPairs(atoi(argv[1]));
}
