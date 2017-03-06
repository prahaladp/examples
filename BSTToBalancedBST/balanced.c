# include<stdio.h>
# include<string.h>
# include<stdlib.h>
# include"../common/header.h"

tree_node_t *
create_new_node(int val)
{
	tree_node_t	*node = (tree_node_t *)malloc(sizeof (tree_node_t));
	bzero(node, sizeof (*node));
	node->val = val;
	return node;
}

void
insert_tree(tree_node_t *root, tree_node_t *node)
{
	if (node->val <= root->val) {
		if (root->left != NULL) {
			insert_tree(root->left, node);
			return;
		}
		root->left = node;
	} else {
		if (root->right != NULL) {
			insert_tree(root->right, node);
			return;
		}
		root->right = node;
	}
}

int
tree_level(tree_node_t *root, int level)
{
	int	left_level;
	int	right_level;

	if (!root) {
		return --level;
	}

	root->lvl = level;
	left_level = tree_level(root->left, level + 1);
	right_level = tree_level(root->right, level + 1);
	return (left_level > right_level ? left_level : right_level);
}

void
traverse_tree(tree_node_t *root, tree_node_t **left, tree_node_t **right)
{
	tree_node_t	*lnl = NULL;
	tree_node_t	*lnr = NULL;
	tree_node_t	*rnl = NULL;
	tree_node_t	*rnr = NULL;

	if (!root) {
		return;
	}

	traverse_tree(root->left, &lnl, &lnr);
	if (lnl == NULL) {
		*left = root;
	} else {
		*left = lnl;
	}
	if (lnr) {
		lnr->right = root;
	}
	(*right) = root;

	traverse_tree(root->right, &rnl, &rnr);
	root->left = NULL;
	if (rnl == NULL) {
		return;
	}

	root->right = rnl;
	*right = rnr;
}

tree_node_t *
tree_to_linked_list(tree_node_t *root)
{
	tree_node_t	*left_most = NULL;
	tree_node_t	*right_most = NULL;

	if (!root) {
		printf("root == NULL\n");
		return root;
	}
	traverse_tree(root, &left_most, &right_most);
	return left_most;
}

void
inorder_traverse(tree_node_t *root, char *str, int sz)
{
	char	temp_str[80];

	temp_str[0] = '\0';
	if (!root) {
		return;
	}
	inorder_traverse(root->left, str, sz);

	snprintf(temp_str, sizeof (temp_str), " [%d/%d]",
	    root->val, root->lvl);
	strncat(str, temp_str, sz);

	inorder_traverse(root->right, str, sz);
}

void
print_by_level(tree_node_t * root, char *str, int sz)
{
}

void
tree_to_str(tree_node_t *tree, char *str, int sz)
{
	int	level = -1;

	level = tree_level(tree, level + 1);
	snprintf(str, sz, "[%p] [level=%d]", tree, level);
	inorder_traverse(tree, str, sz);
}

tree_node_t *
left_balance(tree_node_t *root)
{
	tree_node_t	*new_root = root->right;

	root->right = new_root->left;
	new_root->left = root;
	return new_root;
}

tree_node_t *
right_balance(tree_node_t *root)
{
	tree_node_t	*new_root = root->left;

	root->left = new_root->right;
	new_root->right = root;
	return new_root;
}

tree_node_t *
balance_tree(tree_node_t *root)
{
	int	left_level;
	int	right_level;

	if (!root) {
		return (root);
	}

	left_level = tree_level(root->left, 1);
	right_level = tree_level(root->right, 1);

	if (left_level > right_level) {
		if (left_level - right_level >= 2) {
			return right_balance(root);
		}
		return (root);
	}
	if (right_level - left_level >= 2) {
		return left_balance(root);
	}
	return (root);
}

tree_node_t *
balance_insert_tree(tree_node_t *root, tree_node_t *tmp)
{
	insert_tree(root, tmp);
	return balance_tree(root);
}

tree_node_t *
balance_bst(tree_node_t *root)
{
	tree_node_t	*new_root = NULL;
	char		tree_str[512];

	new_root = tree_to_linked_list(root);

	tree_str[0] = '\0';
	tree_to_str(root, tree_str, sizeof (tree_str));

	root = new_root;
	new_root = new_root->right;
	root->right = NULL;

	while (new_root) {
		tree_node_t	*tmp = new_root;

		new_root = new_root->right;
		tmp->right = NULL;

		root = balance_insert_tree(root, tmp);
	}
	return root;
}

void
testcase_run(int arr[], int sz)
{
	int		i;
	tree_node_t	*root;
	char		tree_str[512];

	printf("running testcase with %d elements\n", sz);
	root = create_new_node(arr[0]);
	for (i = 1; i < sz; i++) {
		insert_tree(root, create_new_node(arr[i]));
	}

	tree_str[0] = '\0';
	tree_to_str(root, tree_str, sizeof (tree_str));
	printf("Tree : %s\n", tree_str);

	root = balance_bst(root);
	tree_str[0] = '\0';
	tree_to_str(root, tree_str, sizeof (tree_str));
	printf("Tree : %s\n", tree_str);
}

void
testcase1()
{
	int	arr[] = { 1, 2, 3, 5, 6, 7};
	testcase_run(arr, sizeof (arr)/sizeof (int));
}

void
testcase2()
{
	int	arr[] = { 1 };
	testcase_run(arr, sizeof (arr)/sizeof (int));
}

void
testcase3()
{
	int	arr[] = { 5, 3, 4, 7, 6};
	testcase_run(arr, sizeof (arr)/sizeof (int));
}

void
testcase4()
{
	int	arr[] = { 5, 3, 4, 2, 1, 7, 6};
	testcase_run(arr, sizeof (arr)/sizeof (int));
}

void
testcases()
{
	typedef void (*test_func_t)(void);
	int	i;

	typedef struct test_cases_s {
		char		desc[80];
		test_func_t	func;
	} test_case_t;
	test_case_t tc[] = {
		{"testcase1", testcase1},
		{"testcase2", testcase2},
		{"testcase3", testcase3},
		{"testcase4", testcase4}
	};

	for (i = 0; i < sizeof (tc)/sizeof (test_case_t); i++) {
		printf("------ START : %s ------\n", tc[i].desc);
		tc[i].func();
		printf("------ END   : %s ------\n", tc[i].desc);
	}
}

int
main(int argc, char *argv[])
{
	testcases();
}
