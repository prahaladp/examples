#include <stdio.h>

typedef struct tree_s {
	int val;
	struct tree_s	*right;
	struct tree_s	*left;
} tree_t;

int min_level = 1;
int max_level = 1;

#define	TRUE	1
#define	FALSE	0
typedef int	boolean_t;

boolean_t check_balance(tree_t *root, int lvl)
{
	boolean_t	bval;
	if (lvl > max_level) {
		max_level = lvl;
	}

	if (max_level - min_level > 1) {
		printf("Node at %p, val = %d, incorrect balance "
		    " (min = %d, max = %d)\n", root, root->val,
		    min_level, max_level);
		return FALSE;
	}
	if (root->right) {
		bval = check_balance(root->right, lvl + 1);
		if (bval == FALSE) {
			return (bval);
		}
	}
	if (root->left) {
		bval = check_balance(root->left, lvl + 1);
		if (bval == FALSE) {
			return (bval);
		}
	}

	return TRUE;
}

int main(int argc, char **argv)
{
	tree_t		root;

	/* populate tree */
	check_balance(&root, 1);
}
