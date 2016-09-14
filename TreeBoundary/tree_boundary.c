#include <stdio.h>
#include <strings.h>

typedef struct tree_s {
	int		val;
	struct tree_s	*left;
	struct tree_s	*right;
} tree_t;

/*
	(1) Print Root
	(2) Left Boundary Traversal
		(a) Print Node
		(b) Go Left
		(c) Go Right
			in-order traversal
			print leaves only
	(3) Right Boundary Traversal
		(a) At each node, go left
			In-order Traversal
			Print leaves
		(b) Go Right
		(c) Print Node
 */

void
in_order_traverse(tree_t *root)
{
	if (!root) {
		return;
	}

	in_order_traverse(root->left);
	if (root->left == NULL || root->right == NULL) {
		printf("%d  ", root->val);
	}
	in_order_traverse(root->right);
}

void
left_boundary(tree_t *root)
{
	if (!root) {
		return;
	}

	printf("%d  ", root->val);
	left_boundary(root->left);
	in_order_traverse(root->right);
}

void
right_boundary(tree_t *root)
{
	if (!root) {
		return;
	}

	in_order_traverse(root->left);
	right_boundary(root->right);
	printf("%d  ", root->val);
}

int
main(int argc, char *argv[])
{
	tree_t		*root;

	tree_t		test[100];

	bzero(test, sizeof (test));
	test[0].val = 20;
	test[0].left = &test[1];
	test[0].right = &test[2];

	test[1].val = 8;
	test[1].left = &test[3];
	test[1].right = &test[4];

	test[2].val = 22;
	test[2].left = NULL;
	test[2].right = &test[6];

	test[3].val = 4;
	test[4].val = 12;
	test[4].left = &test[9];
	test[4].right = &test[10];

	test[6].val = 25;

	test[9].val = 10;
	test[10].val = 14;

	root = &test[0];
	printf("%d  ", root->val);

	left_boundary(root->left);
	right_boundary(root->right);
}
