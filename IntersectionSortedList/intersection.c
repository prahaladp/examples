#include <stdio.h>
#include <stdlib.h>

typdef struct node_s {
	int val;
	struct node_s *next;
} node_t;

node_t *
intersect(node_t *l1, node_t *l2)
{
	node_t	*c1 = l1;
	node_t	*c2 = l2;
	node_t	*nh = NULL;
	node_t	*nt = NULL;

	while (c1 && c2) {
		if (c1->val == c2->val) {
			nn = (node_t *)malloc(sizeof (node_t));
			nn->val = c1->val;
			c1 = c1->next;
			c2 = c2->next;

			if (nh == NULL) {
				nh = nt = nn;
			} else {
				nt->next = nn;
				nt = nn;
			}
		} else if (c1->val < c2->val) {
			c1 = c1->next;
		} else {
			c2 = c2->next;
		}
	}
	return (nh);
}

int
main(int argc, char *argv[])
{
	node_t	*nh = NULL;

	nh = intersect(l1, l2);
}
