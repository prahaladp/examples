/*
 Given two binary trees ( not BST) , return true if both of them have same inorder else return false.
 */

#include <stdio.h>
#include <stdlib.h>

node_t
next_node(stack_t *s, node_t *n)
{
        while (n) {
                push(s, n);
                n = n->left;
        }

	n = pop(s);
	if (n == NULL) {
		return (n);
	}

        push(s, n->right);
	return (n);
}

int
compare_trees(node_t *n1, node_t *n2)
{
	node_t	*curr1;
	node_t	*curr2;

	stack_init(&s1);
	stack_init(&s2);

	while ((curr1 = next_node(s1, curr1)) &&
	    (curr2 = next_node(s2, curr2)) {

		if (curr1->val != curr2->val) {
			return (NOT_SAME_TREE);
		}
		curr1 = pop(s1);
		curr2 = pop(s2);

	}

	if (curr1 != curr2) {
		return (NOT_SAME_TREE);
	}

	return (SAME_TREE);
}
