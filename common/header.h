# include <stdio.h>
# include <stdlib.h>
# include <string.h>

typedef struct tree_node_s {
	struct tree_node_s	*right;
	struct tree_node_s	*left;
	int			val;
} tree_node_t;

typedef struct ll_node_s {
	struct ll_node_s	*next;
	int			val;
} ll_node_t;
