#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

#define	LEFT	1
#define	RIGHT	2

typedef struct node_s {
	int		val;
	int		flag;
	struct node_s	*left;
	struct node_s	*right;
} node_t;

int
find_nth(node_t *root, int n, int *val)
{
	node_t	**arr;
	int	indx = 0;
	node_t	*curr;
	int	started = 0;	
	int	err = -1;

	arr = (node_t **)malloc(sizeof(struct node_s *) * 100);
	arr[indx++] = root;

	while (indx) {
		curr = arr[--indx];
		if (curr->flag == 0 && curr->left) {
			/* keep going to the left */
			arr[indx++] = curr;
			curr->flag = LEFT;
			arr[indx++] = curr->left;
			continue;
		}
		curr->flag = 0;
		if (!started) {
			/* at the left most node, start counting */
			started = 1;
		}
		if (!--n) {
			/* we hit zero, found the elem */
			*val = curr->val;
			err = 0;
		}
		if (curr->right) {
			arr[indx++] = curr->right;
			continue;
		}
	}
	free(arr);
	return (err);
}

int
main(int argc, char *argv[])
{
	node_t		arr[10];
	int		val;
	int		i;

	bzero(arr, sizeof (arr));
	arr[0].val = 10;
	arr[1].val = 5;
	arr[2].val = 3;
	arr[3].val = 4;
	arr[4].val = 11;
	arr[0].left = &arr[1];
	arr[0].right = &arr[4];
	arr[1].left = &arr[2];
	arr[2].right = &arr[3];

	for (i = 1 ; i < 10; i++) {
		if (find_nth(&arr[0], i, &val) == 0) {
			printf("(%d) smallest element is %d\n", i, val);
		} else {
			printf("(%d) smallest element doesnt exist\n", i);
		}
	}
}
