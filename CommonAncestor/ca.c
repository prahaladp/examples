#include <stdio.h>
#include <stdlib.h>

#define	NONE	0
#define	A	0x1
#define	B	0x20
#define	AB	(A | B)

typedef struct tree_s {
	int		val;
	struct tree_s	*right;
	struct tree_s	*left;
} tree_t;

int find_common(tree_t *node, tree_t **ca, int a, int b)
{
	int rval;
	int lval;

	if (node == NULL) {
		*ca = NULL;
		return NONE;
	}
	if (node->val == a) {
		return A;
	}
	if (node->val == b) {
		return B;
	}

	rval = find_common(node->right, ca, a, b);
	lval = find_common(node->left, ca, a, b);

	if (rval == AB) {
		*ca = node->right;
		return rval;
	}
	if (lval == AB) {
		*ca = node->left;
		return lval;
	}
	if (rval == A && lval == B) {
		*ca = node;
	}
	return (rval | lval);
}

int main(int argc, char **argv)
{
	tree_t		*root;
	tree_t		*ca;
	int		val;
	int		a, b;

	val = find_common(root, &ca, a, b);
}
