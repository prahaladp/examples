#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

typedef struct node_s {
	struct node_s	*right;
	struct node_s	*left;
	int		val;
} tree_t;

int find_split(int start, int end)
{
	int split = start + (end - start + 1)/2;
	return (split);
}

tree_t *create_new_node(int val)
{
	tree_t	*tmp = NULL;

	tmp = (tree_t *)malloc(sizeof (tree_t));
	bzero(tmp, sizeof (tree_t));
	tmp->val = val;
	return (tmp);
}

tree_t *convert_to_bst(int arr[], int start, int end)
{
	tree_t	*n = NULL;
	int	split;

	if (start > end) {
		return (NULL);
	}

	if (start == end) {
		n = create_new_node(arr[start]);
		return (n);
	}

	split = find_split(start, end);
	printf("convert_to_bst (start, end, split) : %d, %d, %d\n",
		start, end, split);
	n = create_new_node(arr[split]);
	n->right = convert_to_bst(arr, start, split - 1);
	n->left = convert_to_bst(arr, split + 1, end);
	return (n);
}

void print(tree_t *root)
{
	if (root->right) {
		print(root->right);
	}
	printf("%d\n", root->val);
	if (root->left) {
		print(root->left);
	}
}

int main(int argc, char *argv[])
{
	int	arr[] = { 1, 4, 5, 7, 9, 11, 13};
	tree_t	*t = NULL;

	t = convert_to_bst(arr, 0, 6);
	print(t);
}
