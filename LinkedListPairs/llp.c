#include <stdio.h>

typedef struct node_s {
	int	val;
	struct node_s	*next;
} node_t;

node_t	*ll_reverse_pairs(node_t *head)
{
	node_t	*first;
	node_t	*sec;

	first = head;
	while (first) {
		sec = first->next;
		if (!sec) {
			/* done */
			break;
		}

		first->next = sec->next;
		sec->next = first;
		if (first == head) {
			head = sec;
		}
		first = first->next;
	}
	return (head);
}

int main(int argc, char *argv[])
{
}
