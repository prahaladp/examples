# include "../common/header.h"

void
print_ll(ll_node_t *head)
{
	while (head) {
		printf("%d ", head->val);
		head = head->next;
	}
	printf("\n");
}

void
change_ll(ll_node_t *head)
{
	ll_node_t	*p_list = NULL;
	ll_node_t	*curr = NULL;
	ll_node_t	*curr_l1, *curr_l2;
	ll_node_t	*next_l1;
	ll_node_t	*next_l2;

	curr = head;
	while (curr) {
		if (curr->next) {
			next_l1 = curr->next->next;
			next_l2 = curr->next;

			if (p_list) {
				next_l2->next = p_list;
			} else {
				next_l2->next = NULL;
			}
			p_list = next_l2;
			curr->next = next_l1;
			curr = next_l1;
		} else {
			break;
		}
	}

	print_ll(head);
	print_ll(p_list);

	curr_l1 = head;
	curr_l2 = p_list;

	while (curr_l2) {
		next_l1 = curr_l1->next;
		curr_l1->next = curr_l2;
		curr_l2 = curr_l2->next;
		curr_l2->next = next_l1;
		curr_l1 = next_l1;
	}
}


int
main(int argc, char *argv[])
{
	ll_node_t	arr[6];
	int		i;

	for (i = 0; i < sizeof (arr)/sizeof (ll_node_t); i++) {
		arr[i].val = i + 1;
		arr[i].next = &arr[i + 1];
	}

	arr[5].next = NULL;
	print_ll(&arr[0]);
	change_ll(&arr[0]);
	print_ll(&arr[0]);
}
