#include <stdio.h>

typedef struct node_s {
	int		val;
	struct node_s	*left;
	struct node_s	*right;
} node_t;

/*
				6
		    3	  		9 
		1	2	    8

		1 <> 2 <> 3 <> 6 <> 8 <> 9
 */

node_t *
bst_to_double(node_t *root)
{
}
