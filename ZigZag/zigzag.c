#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node_s {
	int		val;
	struct node_s	*deref;
	struct node_s	*left, *right;
} node_t;

node_t *
new_node(int val)
{
	node_t	*n;

	n =  (node_t *)malloc(sizeof (node_t));
	bzero(n, sizeof (node_t));
	n->val = val;
	n->left = n->right = NULL;
	return n;
}

void
free_db_list(node_t *f)
{
	node_t	*t;

	while (f) {
		t = f;
		f = f->right;
		free(t);	
	}
}

void
add_db_list(node_t **f, node_t **l, node_t *n)
{
	node_t	*node;
	
	if (!n) {
		return;
	}
	//printf("adding %d\n", n->val);

	node = new_node(n->val);
	node->deref = n;
	if (*f == NULL) {
		*f = *l = node;
		return;
	}	

	(*l)->right = node;
	node->left = *l;
	*l = node;
}

node_t *
pop_db_list(node_t **f, node_t **l, int reverse)
{
	node_t	*node;

	if ((*f) == NULL || (*l) == NULL) {
		return NULL;
	}

	if (!reverse) {
		node = (*f)->deref;
		//printf("regular traverse popping %d\n", node->val);
		*f = (*f)->right;
		if (*f) {
			//printf("setting the prev..\n");
			(*f)->left = node;
			return (node);
		}
		*l = *f;
		//printf("returning..\n");
		return (node);
	}

	node = (*l)->deref;
	//printf("reverse traverse popping %d\n", node->val);
	*l = (*l)->left;
	if (*l) {
		(*l)->right = NULL;
		return (node);
	}
	*f = *l;
	return (node);
}

void
bfs_traverse(node_t *root)
{
	node_t	*first = NULL;
	node_t	*last = NULL;
	node_t	*nf = NULL;
	node_t	*nl = NULL;
	node_t	*curr = NULL;
	int	reverse = 1;

	add_db_list(&first, &last, root);
	while (first) {
		//printf("traversing...\n");
		while ((curr = pop_db_list(&first, &last, reverse))) {
			printf("%d ", curr->val);
			if (1) {
				add_db_list(&nf, &nl, curr->left);
				add_db_list(&nf, &nl, curr->right);
			} else {
				add_db_list(&nf, &nl, curr->right);
				add_db_list(&nf, &nl, curr->left);
			}
		}
		printf("\n");
		free_db_list(first);
		first = nf;
		last = nl;
		nf = nl = NULL;
		reverse= (reverse) ? 0 : 1;
	}
}

int
main(int argc, char *argv[])
{
	node_t	*root;

	root = new_node(1);
	root->left = new_node(2);
	root->right = new_node(3);
	root->left->left = new_node(4);
	root->left->right = new_node(5);
	root->right->left = new_node(6);
	root->right->right = new_node(7);

	bfs_traverse(root);
}
