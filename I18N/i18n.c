/*
 * given a string 'test', generate these
 * t2t
 * te1t
 * t1st
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define	MAX_LEN	256

int	g_cnt = 0;

void
itoa(int num, char *num_str, int base)
{
	int	indx = 0;
	int	left, right;
	int	mod;

	while (num) {
		mod = num % base;
		num_str[indx++] = '0' + mod;
		num = num / 10;
	}
	num_str[indx] = '\0';

	/* swap */
	left = 0;
	right = indx - 1;
	while (left < right) {
		char	temp = num_str[left];
		num_str[left] = num_str[right];
		num_str[right] = temp;
		left++;
		right--;
	}
}

void
gen_pattern(char *str, int start)
{
	int	len = strlen(str);
	int	last = len - 1;
	char	pattern[MAX_LEN];
	char	num_str[MAX_LEN];
	int	diff;

	while (last > (start + 1)) {
		memcpy(pattern, str, (start + 1));
		diff = last - start - 1;
		itoa(diff, num_str, 10);
		memcpy(pattern + start + 1, num_str, (size_t)strlen(num_str));
		memcpy(pattern + start + 1 + strlen(num_str),
		    str + last, (size_t)(len - last));
		pattern[start + 1 + strlen(num_str) + len - last] = '\0';
		printf("Pattern (%d) : %s\n", g_cnt++, pattern);
		last--;
	}
}

void
gen_i18n(char *str)
{
	int	len = strlen(str);
	int	start;

	for (start = 0; start <= (len - 2); start++) {
		gen_pattern(str, start);
	}
}

int
main(int argc, char *argv[])
{
	gen_i18n(argv[1]);
}
