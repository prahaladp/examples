#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <string.h>

#define	MAX_ALPHA	26

typedef struct node_s {
	char		ch;
	char		*string;
	int		count;
	int		len;
	struct node_s	*arr[MAX_ALPHA];
} node_t;

typedef struct tracker_s {
	int	len;
	char	*ptr;
	node_t	*node;
} tracker_t;

node_t *
create_node(node_t *parent, int ch)
{
	node_t	*n;
	int	strl;

	n = (node_t *)malloc(sizeof (node_t));
	bzero(n, sizeof (*n));

	printf("creating new node for %c (parent = %s)\n", ch,
	    (parent) ? parent->string : "");
	n->ch = ch;
	strl = (parent) ? strlen(parent->string) + 2 : 2;
	n->string = (char *)malloc(sizeof (char) * strl);
	if (parent) {
		strcpy(n->string, parent->string);
		n->string[strl - 2] = ch;
		n->string[strl - 1] = '\0'; 
		n->len = strl - 1;
	} else {
		n->string[0] = ch;
		n->string[1] = '\0';
		n->len = 1;
	}
	n->ch = ch;
	printf("new node : (%s, %c)\n", n->string, n->ch);
	return (n);
}

void
max_count_func(node_t *root, int len, int max_count)
{
	int	i;

	if (root->len == len) {
		if (root->count == max_count) {
			printf("max_count : %s  %d\n", root->string, root->count);
		}
		return;
	}

	for (i = 0; i < MAX_ALPHA; i++) {
		if (root->arr[i]) {
			max_count_func(root->arr[i], len, max_count);
		}
	}
}

void
max_substring_count(char *string, char *subs)
{
	int		len = strlen(subs);
	char		*chp;
	tracker_t	*t;
	node_t		root;
	tracker_t	**tr;
	tracker_t	*avail;
	tracker_t	*track_ptr;
	int		max_count = -1;
	int		i;
	int		indx;

	bzero(&root, sizeof (root));
	tr = (tracker_t **)malloc(sizeof (tracker_t*) * len);
	for (i = 0; i < len; i++) {
		tr[i] = (tracker_t *)malloc(sizeof (tracker_t));
		bzero(tr[i], sizeof (tracker_t));
	}

	chp = string;
	printf("string = %s\n", chp);

	while (*chp) {
		/* add it to root */
		avail = NULL;

		indx = *chp - 'a';
		for (i = 0 ; i < len; i++) {
			track_ptr = tr[i];
			if (track_ptr->len) {
				// printf("iterating tracker (%d)\n", track_ptr->len);
				if (track_ptr->len == len) {
				} else {
					// printf("adding to string at indx %d\n", i);
					if (track_ptr->node->arr[indx] == NULL) {
						track_ptr->node->arr[indx] =
						    create_node(track_ptr->node, *chp);
					}
					track_ptr->node = track_ptr->node->arr[indx];
					track_ptr->len++;
					if (track_ptr->len == len) {
						track_ptr->node->count++;
						printf("terminating search for %s, %d\n",
						    track_ptr->node->string, track_ptr->node->count);
						if (track_ptr->node->count > max_count) {
							max_count = track_ptr->node->count;
						}
						bzero(track_ptr, sizeof (track_ptr));
						track_ptr->len = 0;
						avail = (avail == NULL) ? track_ptr : avail;
					}
				}
				
			} else if (avail == NULL) {
				// printf("avail at index %d\n", i);
				avail = track_ptr;
			}
		}

		if (root.arr[indx] == NULL) {
			root.arr[indx] = create_node(NULL, *chp);
		}
		avail->ptr = chp;
		avail->node = root.arr[indx];
		avail->len = 1;

		chp++;
	}

	printf("finding substrings with max count = %d\n", max_count);
	max_count_func(&root, len, max_count);
}

int
main(int argc, char *argv[])
{
	if (argc < 3) {
		printf("usage : <prog> string sub\n");
		exit (1);
	}
	max_substring_count(argv[1], argv[2]);
}
