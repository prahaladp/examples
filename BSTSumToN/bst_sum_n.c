# include <stdio.h>
# include <stdlib.h>
# include <string.h>

typedef struct tree_s {
	int	val;
	struct tree_s	*right;
	struct tree_s	*left;
} tree_t;

typedef struct ans_s {
	tree_t	*root;
	int	n;
	int 	val1;
	int	val2;
	int	done;
} ans_t;

tree_t *
find_n(tree_t *root, int n)
{
	if (!root) {
		return (root);
	}

	if (root->val == n) {
		return (root);
	}

	if (root->val > n) {
		return (find_n(root->right, n));
	}

	return (find_n(root->left, n));
}

void
find_answer(tree_t *curr, ans_t *ans)
{
	int	diff;
	tree_t	*root;
	tree_t	*node;

	root = ans->root;

	diff = ans->n - curr->val;
	if (diff == 0) {
		return;
	}

	if (diff < 0) {
		return;
	}

	node = find_n(ans->root, diff);
	if (node) {
		ans->val1 = curr->val;
		ans->val2 = node->val;
		ans->done = 1;
		return;
	}
}

void
traverse(tree_t *root, ans_t *ans)
{
	while (root) {
		find_answer(root, ans);
		if (ans->done) {
			return;
		}
		if (root->right) {
			find_answer(root->right, ans);
			if (ans->done) {
				return;
			}
		}
		if (root->left) {
			find_answer(root->left, ans);
			if (ans->done) {
				return;
			}
		}
	}
}

int
main(int argc, char *argv[])
{
	ans_t	ans;
	tree_t	*root = NULL;
	int	n;

	ans.root = root;
	ans.n    = n;
	ans.val1 = ans.val2 = 0;
	ans.done = 0;
	traverse(root, &ans);
}
