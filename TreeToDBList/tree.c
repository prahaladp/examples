#include<stdio.h>
#include<stdlib.h>
#include<strings.h>

typedef struct node_s {
	int			val;
	struct node_s		*left;
	struct node_s		*right;
} node_t;

node_t *
create_node(int val)
{
	node_t		*n;

	n = (node_t *)malloc(sizeof (*n));
	bzero(n, sizeof (*n));
	n->val = val;
	return (n);	
}

void
tree_to_list(node_t *node, node_t **min, node_t **max)
{
	node_t		*nmin = NULL;
	node_t		*nmax = NULL;
	int		min_val = -1;
	int		max_val = -1;

	*min = *max = NULL;
	if (!node) {
		return;
	}

	min_val = node->val;
	*min = node;
	*max = node;
	max_val = node->val;

	tree_to_list(node->left, &nmin, &nmax);
	if (nmax) {
		node->left = nmax;
		nmax->right = node;
	}
	if (nmin) {
		min_val = nmin->val;
		*min = nmin;
	}
	tree_to_list(node->right, &nmin, &nmax);
	if (nmin) {
		node->right = nmin;
	}
	if (nmax) {
		*max = nmax;
		max_val = nmax->val;
	}
	printf("Node = %d, nmin = %d, nmax = %d\n", node->val, min_val, max_val);	
	return;
}

void
print_list(node_t *start)
{
	node_t		*c;

	c = start;
	while (c) {
		printf("%d->", c->val);
		c = c->right;
	}
	printf("\n");
}

int
main(int argc, char *argv[])
{
	node_t		*root = NULL;
	node_t		*n = NULL;
	node_t		*nmax = NULL;
	node_t		*nmin = NULL;

	n = create_node(5);
	root = n;
	n->left = create_node(3);
	n->left->right = create_node(4);
	n->right = create_node(10);
	n->right->left = create_node(8);
	n->right->right = create_node(12);

	tree_to_list(root, &nmin, &nmax);
	print_list(nmin);
}
