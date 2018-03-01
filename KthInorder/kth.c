/*
	Given a node n and an int k,
	write a function to return the kth
	node in an in order traversal.
	Can you do this non recursively
 */
#include <stdio.h>

typedef struct node_s {
	int	val;
	int	deg;
	struct node_s	*left, *right;
} node_t;

node_t *
find_kth(char *root, int k)
{
	node_t		*start = root;
	int		cnt = 0;
	int		left_cnt = 0;
	int		right_cnt = 0;

	while (start) {
		if (start->deg  + cnt < k) {
			return (NULL);
		}

		left_cnt = (start->left) ? start->left->deg : 0;
		right_cnt = (start->right) ? start->right->deg : 0;

		if (k == (cnt + left_cnt + 1) {
			return start;
		}
		if (k > (cnt + left_cnt + 1)) {
			cnt += left_cnt;
			cnt += 1;
			start = start->right;
		} else {
			start = start->left;
		}
	}
	/* we should never hit this path */
	return (NULL);
}
