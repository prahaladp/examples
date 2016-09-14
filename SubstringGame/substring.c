#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct split_s {
	char		*str;
	struct split_s	*next;
} split_t;

char *dictionary[] = {
	"si"
};

int substring(char *string, char *sub)
{
	int	i, indx, count;

	i = 0;

	while (string[i]) {

		/* match first character */
		if (string[i] == sub[0]) {
			indx = i;
			count = 0;

			printf("first character match at : %d\n", i);
			while (string[indx] && sub[count] && (sub[count] == string[indx])) {
				printf("indx = %d, count = %d\n", indx, count);
				indx++;
				count++;
			}
			if (sub[count] == '\0') {
				/* we found a match here */
				return (i);
			}
		}
		i++;
	}
			
	return (-1);
}

void match_and_split(split_t *head, char *sub)
{
	int	indx;
	int	sublen;
	int	len;
	int	end;
	char	*str;
	split_t	*node = head;
	split_t	*newn = NULL;

	len = strlen(str);
	sublen = strlen(sub);

	while (node) {

		printf("match_and_split : %s, %s\n", node->str, sub);
		str = node->str;
		indx = substring(str, sub);

		if (indx != -1) {
			/* got a match */
			end = indx + sublen;
			str[indx] = '\0';

			printf("match at %d\n", indx);
	
			if (indx == 0) {
				head->str = &str[end];	
			} else if (end >= len) {
				/* nothing to do */
			} else {

				printf("Removing %s\n", sub);

				/* need to split */
				newn = (split_t *)malloc(sizeof (split_t));

				/* check for NULL */
				newn->str = &str[end];
				node->next = newn;
			}

			return;
		}
	
		node = node->next;
	}
}

void print_remaining(split_t *head)
{
	split_t		*node = head;

	printf("Remaining split strings:\n");
	while (node) {
		printf("\t%s\n", node->str);
		node = node->next;
	}
}

int main(int argc, char *argv[])
{
	split_t	m;
	int	i;

	m.str = argv[1];
	m.next = NULL;


	for (i = 0; i < 1; i++) {
		match_and_split(&m, dictionary[i]);
	}

	print_remaining(&m);

}
