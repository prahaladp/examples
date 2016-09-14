# include <stdio.h>
# include <string.h>
# include <stdlib.h>

/*
 * Given a string with numbers in sequence, find the missing number in the
 * string, the numbers could be 1 to 6 digits in length 
 *

int
next_num(char *start, int remaining, int length)
{
	int	count = 0;
	char	*temp;
	int	nn = 0;

	printf("debug : string (%s), remaining (%d), length (%d)\n",
		start, remaining, length);

	if (remaining == 0) {
		return (0);
	}

	if (remaining > 0 && remaining < length) {
		printf("remaining problem (%d), length (%d)\n",
			remaining, length);
		return (-1);
	}	

	temp = (char *)malloc(length + 1);
	temp[length] = '\0';
	strncpy(temp, start, length);

	nn = atoi(temp);
	free(temp);
	return (nn);
}

void
print_numbers(char *str, int len)
{
	int	indx = 0;
	int	in_len = strlen(str);
	int	num;

	printf("printing string sequence\n");
	while (indx < in_len) {
		num = next_num(&str[indx], in_len - indx + 1, len);
		printf("%d\n", num);
		indx += len;
	}
}

int
find_missing(char *str)
{
	int	len = 1;
	int	in_len = strlen(str);
	int	nn, prev_num;
	int	indx;
	int	missing = -1;
	int	err;

	while (len <= 6) {
		indx = 0;

		err = 0;
		missing = 0;

		prev_num = next_num(&str[indx], in_len - indx, len);
		while (indx <= (in_len - 1) && prev_num != -1) {
			indx += len;
			if (in_len - indx == 0) {
				break;
			}
			nn = next_num(&str[indx], in_len - indx, len);

			if (nn == -1) {
				printf("terminating search with len %d\n", len);
				err = -1;
				break;
			}

			if ((nn - prev_num) > 2 || (nn - prev_num) < 1) {
				printf("terminating search gap > 2 (%d, %d)\n",
					prev_num, nn);
				err = -1;
				break;
			}

			if (nn - prev_num == 2) {
				missing = nn - 1;
			} else if (nn - prev_num == 1) {
				/* great, keep going */
			}

			prev_num = nn;
		}

		if (!err) {
			if (missing) {
				printf("found missing number %d in %s split (%d)\n",
					missing, str, len);
				print_numbers(str, len);
				return (missing);
			}
		}
		len++;
	}
	return (-1);
}

typedef struct test_val {
	int	missing;
	char	test_str[80];
} test_val_t;

int
main(int argc, char *argv[])
{
	test_val_t	tv[5] = {
		{-1, "1234"},
		{5, "467"},
		{-1, "565566588589"},
		{567, "565566568569"},
		{12, "1011131415"}
	};
	int		i;
	int		val;

	for (i = 0; i < sizeof (tv)/sizeof (test_val_t); i++) {
		val = find_missing(tv[i].test_str);
		printf("Test Case : (%s) (%d), (%d) ",
			tv[i].test_str, tv[i].missing, val);
		if (val != tv[i].missing) {
			printf(" FAIL\n");
		} else {
			printf("SUCCESS\n");
		}
	}	
}
