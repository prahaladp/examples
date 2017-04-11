# include "../common/header.h"

int
get_length(ll_node_t *l) {
	int	i = 0;

	while (l) {
		i++;
		l = l->next;
	}

	return i;
}

ll_node_t *
skip(ll_node_t *l, int count) {
	int	i = 0;

	while (i < count && l) {
		i++;
		l = l->next;
	}

	if (i == count) {
		return l;
	}
	return NULL;
}

int
get_pow(int p) {
	int	mf = 1;

	while (p--) {
		mf *= 10;
	}

	return mf;
}

int
add_lists(ll_node_t *a, ll_node_t *b, int *overflow, int multi, int count) {
	int		sum = 0;
	ll_node_t	*anext = (a) ? a->next : NULL;
	ll_node_t	*bnext = (b) ? b->next : NULL;
	int		aval = (a) ? a->val : 0;
	int		bval = (b) ? b->val : 0;

	printf("add_list : [a,b] = [%d, %d], [o=%d][multi=%d][count=%d]\n",
		aval, bval, *overflow, multi, count);

	if (count > 1) {
		sum = add_lists(anext, bnext, overflow, multi - 1, count - 1);
	}

	sum += ((aval + bval + *overflow) % 10) * get_pow(multi - 1);
	if ((aval + bval + *overflow) >= 10) {
		*overflow = 1;
	}

	printf("sum = %d\n", sum);
	return sum;
}

int
add_linked(ll_node_t *a, ll_node_t *b) {
	int	a_count = get_length(a);
	int	b_count = get_length(b);
	int	count = 0;
	int	overflow = 0;
	int	multi = 0;
	ll_node_t	*as = NULL;
	ll_node_t	*bs = NULL;
	int		sum;

	as = a;
	bs = b;

	if (a_count > b_count) {
		as = skip(a, a_count - b_count);
		count = b_count;
	} else if (b_count > a_count) {
		bs = skip(b, b_count - a_count);
		count = a_count;
	} else {
		count = a_count;
	}

	multi = count;
	sum = add_lists(as, bs, &overflow, multi, count);

	if (a_count > b_count) {
		sum += add_lists(a, NULL, &overflow, a_count, a_count - b_count);
	} else if (b_count > a_count) {
		sum += add_lists(b, NULL, &overflow, b_count, b_count - a_count);
	}

	return sum;
}

ll_node_t *
create_list(int num) {
	ll_node_t	*head = NULL;
	ll_node_t	*lnode = NULL;
	
	while (num) {
		lnode = (ll_node_t *)malloc(sizeof (ll_node_t));
		bzero(lnode, sizeof (ll_node_t));

		lnode->val = num % 10;
		if (head) {
			lnode->next = head;
		}
		head = lnode;

		num /= 10;
	}

	printf("Returing new list = %p\n", head);
	return head;
}

void
testcase_exec(int num1, int num2, int sum) {
	ll_node_t	*l1 = create_list(num1);
	ll_node_t	*l2 = create_list(num2);

	printf("\t\tSum = %d, Expected = %d\n", add_linked(l1, l2), sum);
}

void
testcase() {
	int	i = 0;
	int	num1[] = { 4324, 245, 4567, 12345,    5 };
	int	num2[] = { 2345, 367,  454, 0    , 1234};

	for (i = 0; i < sizeof (num1) /sizeof (int); i++) {
		testcase_exec(num1[i], num2[i], num1[i] + num2[i]);
	}
	
}

int
main(int argc, char *argv[]) {
	testcase();
}
