#include <stdio.h>

/*
	Parse the string,
	fill the tree
 */

typedef struct node_s {
	struct node_s	*right;
	struct node_s	*left;
	char		oper;
	int		val;
} node_t;	

int get_number(char *str, int *i)
{
	int	val = 0;

	while (str[*i] && ischar(str[*i])) {
		val = (val * 10) + str[*i] - '0';
		*i++;
	}

	return (val);
}

void skip_others(char *str, int *i)
{
	while (str[*i] && str[*i] == ' ') {
		*i++;
	}
}

tree_t *string_val(char *str)
{
	int	i = 0;
	int	len = strlen(str);
	char	prev_oper = '/0';
	int	stack[100];
	int	indx = 0;

	skip_others(str, &i);
	right_oper = get_number(str, &i);

	for (; i < len; ) {
		oper = str[i++];
		skip_others(str, &i);
		left_oper = get_number(str, &i);

		if (lower_precedence_oper(str[i], prev_oper)) {
		}
	}
}
