#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

/*
	a cBd LkmY
	cap_offset	small_offset				space_offset
		9	9					
			7
			copy(7, 9)	
			move(5, 6, cap_offset - 1)			7
			copy(other_string, space_offset - 1)
		8
					
 */

int
cap_len(char *str, int off)
{
	int	len = 0;

	while (off >= 0 && isupper(str[off--])) {
		len++;	
	}

	return (len);
}

int
small_len(char *str, int off)
{
	int	len = 0;

	while (off >= 0 && islower(str[off--])) {
		len++;	
	}

	return (len);
}

int
is_space(char *str, int off)
{
	int	len = 0;

	while (off >= 0 && str[off--] == ' ') {
		len++;	
	}

	return (len);
}

void
string_reorder_no_buffer(char *str)
{
	int	len = strlen(str);
	int	upper = 0;
	int	lower = 0;
	int	space = 0;

	while (off < len) {
		if (isupper(str[off]) {
			upper++;
		} else if (islower(str[off])) {
			lower++;
		} else if (str[off] == ' ') {
			space++;
		}
		off++;
	}

	/* now, start moving */
	upoff = len - 1;
	lowoff = 0;
	while (upper && lower) {
		while (lower) {
			if (islower(str[lowoff++])) {
				lower--;
			}
		}
		



	
	}	
}


void
string_reorder(char *str)
{
	int	len = strlen(str);
	char	*buf = NULL;
	int	dco = len - 1;
	int	off = len - 1;
	int	spacelen = 0;

	buf = (char *)malloc(len + 1);

	while (off >= 0) {
		if (isupper(str[off])) {
			buf[dco--] = str[off];
		} else if (str[off] == ' ') {
			spacelen++;
		}
		off--;
	}

	while (spacelen--) {
		buf[dco--] = ' ';
	}

	off = 0;
	dco = 0;
	while (off < len) {
		if (islower(str[off])) {
			buf[dco++] = str[off];
		}
		off++;
	}
	printf("String : %s\n", str);
	printf("Order  : %s\n", buf);
	free(buf);
}

int
main(int argc, char *argv[])
{
	string_reorder(argv[1]);
}
