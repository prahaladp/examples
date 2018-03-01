#include <stdio.h>
#include <stdlib.h>
#include "../common/header.h"

#define	RIGHT_SCAN	1
#define	LEFT_SCAN	2

typedef struct dl_node_s {
	void			*data;
	struct dl_node_s	*next;
	struct dl_node_s	*prev;
} dl_node_t;

void
insert_at_end(dl_node_t **head, dl_node_t **tail, void *data) {
	dl_node_t	*elem = (dl_node_t *)malloc(sizeof (dl_node_t));

	bzero(elem, sizeof (*elem));
	elem->data = data;

	if (*tail) {
		(*tail)->next = elem;
	}
	elem->prev = *tail;
	*tail = elem;

	if (*head == NULL) {
		*head = elem;
	}
}

void
add_str_to_str(char *str, int len, char *astr) {
	strncat(str, astr, len);
}

void
add_int_to_str(char *str, int len, int val) {
	char	num_str[10];

	snprintf(num_str, sizeof (num_str), "%d", val);
	strncat(str, num_str, len);
	strncat(str, " ", len);
}

void
print_level(dl_node_t *curr_level, int scan, char *str, int len) {
	dl_node_t	*elem;

	add_str_to_str(str, len, "[");

	while ((elem = curr_level)) {
		tree_node_t	*tr = (tree_node_t *)elem->data;
		add_int_to_str(str, len, tr->val);

		printf("Current elem = %p\n", elem);
		if (scan == RIGHT_SCAN) {
			curr_level = elem->prev;
		} else {
			curr_level = elem->next;
		}
	}
	add_str_to_str(str, len, "]");
}

void
collect_next_level(dl_node_t **next_level_head, dl_node_t **next_level_tail) {
	dl_node_t	*elem = NULL;
	dl_node_t	*curr_level = *next_level_head;

	*next_level_head = NULL;
	*next_level_tail = NULL;

	while ((elem = curr_level)) {
		tree_node_t	*tr = (tree_node_t *)elem->data;

		printf("at item (%d), right (%p), left (%p) [%p, %p]\n",
			tr->val, tr->right, tr->left,
			*next_level_head, *next_level_tail);

		if (tr->left) {
			insert_at_end(next_level_head, next_level_tail,
				(void *)tr->left);
		}
		if (tr->right) {
			insert_at_end(next_level_head, next_level_tail,
				(void *)tr->right);
		}
		free(elem);
		curr_level = curr_level->next;
	}
}

void
set_param_next_level(dl_node_t **curr_level, dl_node_t *next_level_head,
    dl_node_t *next_level_tail, int *scan) {

	if (*scan == RIGHT_SCAN) {
		*curr_level = next_level_head;
		*scan = LEFT_SCAN;
	} else {
		*scan = RIGHT_SCAN;
		*curr_level = next_level_tail;
	}
}

void
reverse_by_level(tree_node_t *root, char *str, int len)
{
	dl_node_t	*next_level_head = NULL;
	dl_node_t	*next_level_tail = NULL;
	dl_node_t	*curr_level = NULL;
	dl_node_t	*elem = NULL;
	int		scan = LEFT_SCAN;

	insert_at_end(&next_level_head, &next_level_tail, (void *)root);
	printf("starting reverse...\n");

	add_str_to_str(str, len, "[");
	curr_level = next_level_head;

	do {
		print_level(curr_level, scan, str, len);
		collect_next_level(&next_level_head, &next_level_tail);
		set_param_next_level(&curr_level, next_level_head,
		    next_level_tail, &scan);

	} while (curr_level);
	add_str_to_str(str, len, "]");
}

void
testcase1() {		
	tree_node_t	tr[9];
	char		str[512];

	bzero(tr, sizeof (tr));
	tr[0].val = 4;
	tr[1].val = 3;
	tr[2].val = 5;
	tr[3].val = 1;
	tr[4].val = 10;
	tr[5].val = -4;
	tr[6].val = 30;
	tr[7].val = 2;
	tr[8].val = 200;

	tr[0].left = &tr[1];
	tr[0].right = &tr[2];
	tr[1].left = &tr[3];
	tr[1].right = &tr[4];
	tr[2].right = &tr[5];
	tr[5].left = &tr[6];
	tr[4].right = &tr[7];
	tr[3].left = &tr[8];

	str[0] = '\0';
	printf("starting testcase 1\n");

	reverse_by_level(&tr[0], str, sizeof (str));
	printf("%s\n", str);
}

int
main(int argc, char *argv[]) {
	testcase1();
}
