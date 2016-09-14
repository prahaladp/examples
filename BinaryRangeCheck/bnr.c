#include <stdio.h>

typedef struct node_s {
	int	val;
	struct node_s	*right, *left;
} node_t;

typedef enum {
	left_and_right = 0,
	left = 1,
	right = 2
} search_options;

int
binary_range_check(node_t *r, int x, int y, int count)
{
	search_options	search;

	if (!r) {
		return (count);
	}

	if (x < r->val && y > r->val) {
		search = left_and_right;
	} else if (x == r->val) {
		count++;
		if (y > r->val) {
			search = right;
		}
	} else if (y == r->val) {
		count++;
		if (x < r->val) {
			search = left;
		}
	}

	switch (search) {
		case left_and_right:
			count = binary_range_check(r->left, x, y, count);
			count = binary_range_check(r->right, x, y, count);
			break;
		case left:
			count = binary_range_check(r->left, x, y, count);
			break;
		case right:
			count = binary_range_check(r->right, x, y, count);
			break;
	}
	return (count);
}

int
main(int argc, char *argv[])
{
}
