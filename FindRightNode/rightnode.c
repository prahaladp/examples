#include <stdio.h>
#include <strings.h>

#define	MAX_NODES	1024

typedef struct node_s {
	struct node_s	*right;
	struct node_s	*left;
	struct node_s	*next_right;
} node_t;

typedef struct q_s {
	int		num;
	node_t		*arr[MAX_NODES];
} q_t;

void q_init(q_t *q)
{
	bzero(q, sizeof (*q));
}

void q_insert(q_t *q, node_t *n)
{
	if (q->num == MAX_NODES) {
		/* exception */
	}
	q->arr[q->num++] = n;
}

node_t *q_remove(q_t *q)
{
	node_t	*n = NULL;

	if (q->num == 0) {
		/* empty */
		return (NULL);
	}
	n = q->arr[q->num - 1];
	q->num--;
	return (n);
}

int q_len(q_t *q) {
	return (q->num);
}

void find_right_node(node_t *root)
{
	node_t	*curr;
	node_t	*prev_r;
	q_t	q;
	int	len;

	q_init(&q);
	q_insert(&q, root);

	len = q_len(&q);
	while (len) {
		prev_r = NULL;
		for (; len; len--) {
			curr = q_remove(&q);
			q_insert(&q, curr->right);
			if (prev_r) {
				prev_r->next_right = curr->right;
			}
			q_insert(&q, curr->left);
		}
		len = q_len(&q);
	}
}

int main(int argc, char *argv[])
{
}
