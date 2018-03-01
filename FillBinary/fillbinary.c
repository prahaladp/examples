#include <stdio.h>

void
fill_binary(char *str, int indx)
{
	while (str[indx]) {
		if (str[indx] == '?') {
			str[indx] = '0';
			fill_binary(str, indx + 1);
			str[indx] = '?';
			str[indx] = '1';
			fill_binary(str, indx + 1);
			str[indx] = '?';
			return;
		}
		indx++;
	}
	printf("Completed string = %s\n", str);
}

int main(int argc, char *argv[])
{
	fill_binary(argv[1], 0);
}
