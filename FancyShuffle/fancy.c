#include <stdio.h>
#include <string.h>

void swap(char *s, int src, int dst)
{
	char	temp = s[src];
	s[src] = s[dst];
	s[dst] = temp;
}

int valid_swap(char *s, int len, int src, int dst)
{
	if (src - 1 >=0 && s[src] == s[src - 1]) {
		return 0;
	}
	if (src + 1 < len && s[src] == s[src + 1]) {
		return 0;
	}
	if (dst - 1 >= 0 && s[dst] == s[dst - 1]) {
		return 0;
	}
	if (dst + 1 < len && s[dst] == s[dst + 1]) {
		return 0;
	}
	return 1;
}

int fancy_shuffle_impl(char *s, int len, int indx)
{
	int	sw;
	int 	retval = 0;

	for (sw = indx; sw < len; sw++) {	
		swap(s, indx, sw);
		if (sw != indx && valid_swap(s, len, indx, sw)) {	
			retval = fancy_shuffle_impl(s, len, indx + 1);
		}
		swap(s, sw, indx);
	}
	printf("fancy_shuffle_impl : %s\n", s);
	return (retval);
}

int fancy_shuffle(char *s)
{
	printf("Fancy shuffle %s = %d\n", s, fancy_shuffle_impl(s, strlen(s), 0));
	return (0);
}

int
main(int argc, char *argv[])
{
	fancy_shuffle(argv[1]);
}
