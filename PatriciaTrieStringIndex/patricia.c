#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define	MAX_ALPHA	26
#define	NODE		0
#define	LEAF		1
#define	ROOT		2

#define	MAX_LEN		80

typedef struct trie_s {
	struct trie_s	*child[MAX_ALPHA];
	char		str[MAX_LEN];
	int		len;
	short		type;
} trie_t;

typedef struct trie_stat_s {
	int		total_lines;
	int		total_nodes;
	int		total_leaf;
	int		total_ignored;
} trie_stat_t;

trie_t		g_root;
trie_stat_t	g_stats;

int
find_longest_match(trie_t *t, char *start, char *end)
{
	int	len = 0;
	char	*ms = t->str;

	while (*ms && *start) {
		if (tolower(*ms) != tolower(*start)) {
			return (len);	
		}
		ms++;
		start++;
		len++;
	}	
	return (len);
}

trie_t *
create_new_node(char *str, int len)
{
	trie_t	*n;

	n = (trie_t *)malloc(sizeof(trie_t));
	bzero(n, sizeof (*n));
	strncpy(n->str, str, len);
	n->str[len] = '\0';
	n->type = LEAF;
	n->len = len;
	printf("created node = %s\n", n->str);

	g_stats.total_nodes++;
	g_stats.total_leaf++;
	return (n);
}

void
insert_word(trie_t *root, char *start, char *end)
{
	trie_t	*curr;
	int	indx;
	int	word_len;
	int	match_len;
	char	next_ch;
	char	temp_ch;

	if (!start) {
		return;
	}

	while (*start == ' ' && start++ < end);
	if (start >= end) {
		return;
	}

	curr = root;
	word_len = end - start + 1;

	match_len = find_longest_match(curr, start, end);
	temp_ch = start[word_len];
	start[word_len] = '\0';
	printf("longest match in %s for %s is %d\n", curr->str, start, match_len);
	start[word_len] = temp_ch;

	/*
	 * split based on the length being matched
	 * match len has to be less than/equal to the curr node string
	 * if remaining in the current node, then
	 *	we need to create a new node, copy the remaining str
	 * 	null terminate
	 * if remaining in the current word, then,
	 *	we need to create a new node, copy the remaining str
	 */

	if (curr->len == 0) {
		next_ch = *(start + match_len);
		indx = tolower(next_ch) - 'a';
		if (indx > MAX_ALPHA) {
			g_stats.total_ignored++;
			return;
		}

		if (curr->child[indx] == NULL) {
			curr->child[indx] = create_new_node(start + match_len,
			    word_len - match_len);
		} else {
			insert_word(curr->child[indx], start + match_len,
			    end);
		}

		return;
	}

	if (match_len < curr->len) {
		next_ch = curr->str[match_len];
		indx = tolower(next_ch) - 'a';
		if (indx > MAX_ALPHA) {
			g_stats.total_ignored++;
			return;
		}
		curr->child[indx] = create_new_node(&curr->str[match_len],
		    curr->len - match_len);		
		if (curr->type == LEAF) {
			g_stats.total_leaf--;
		}
		curr->type = NODE;
	}

	curr->str[match_len] = '\0';
	curr->len = match_len;

	if (match_len == word_len) {
		curr->type = LEAF;
		g_stats.total_leaf++;
	} else {
		next_ch = *(start + match_len);
		indx = tolower(next_ch) - 'a';
		if (indx > MAX_ALPHA) {
			g_stats.total_ignored++;
			return;
		}

		if (curr->child[indx] == NULL) {
			curr->child[indx] = create_new_node(start + match_len,
			    word_len - match_len);
		} else {
			insert_word(curr->child[indx], start + match_len,
			    end);
		}
	}
}

int
read_file(char *filename)
{
	FILE	*fp;
	char	line[512];

	fp = fopen(filename, "r");
	if (!fp) {
		printf("Unable to open file %s\n", filename);
		return (-1);
	}

	while (fgets(line, sizeof (line), fp) != NULL) {
		/* read a character at a time and insert it into the trie */
		char	*ch = line;
		char	*start = NULL;
		char	*end = NULL;

		printf("line = %s\n", line);
		g_stats.total_lines++;
		while (*ch != '\n' && *ch != '\0') {
			if (!isalpha(*ch)) {
				end = ch;
				insert_word(&g_root, start, end);
				start = NULL;
			} else {
				if (start == NULL) {
					start = ch;
				}
			}
			ch++;
		}
	}
	fclose(fp);
	return (0);
}

void
usage()
{
}

void
print_all_words(trie_t *curr, char *prefix)
{
	trie_t	*node;
	int	i;
	int	prefix_len = strlen(prefix);

	/* printf("print_all_words starting at %s, with prefix %s\n", curr->str, prefix); */
	if (curr->type == LEAF) {
		printf("%s%s\n", prefix, curr->str);
	}

	for (i = 0; i < MAX_ALPHA; i++) {
		node = curr->child[i];
		if (node) {
			strcat(prefix, curr->str);
			print_all_words(node, prefix);
			prefix[prefix_len] = '\0';
		}
	}
}

void
search_trie(trie_t *curr, char *word, char *prefix)
{
	int	indx;
	int	match_len = 0;
	int	word_len;
	int	prefix_len;
	char	next_ch;

	if (!curr) {
		return;
	}

	word_len = strlen(word);
	prefix_len = strlen(prefix);

	match_len = find_longest_match(curr, word, word + word_len - 1);
	printf("longest match in %s for %s is %d\n", curr->str, word, match_len);	

	if (match_len == word_len) {
		print_all_words(curr, prefix);
		return;
	}

	if (match_len < word_len) {
		next_ch = word[match_len];
		indx = tolower(next_ch) - 'a';
		strncat(prefix, curr->str, match_len);		
		search_trie(curr->child[indx], word + match_len, prefix);
		return;
	}
}

void
print_trie_stats()
{
	printf("--------------------------\n");
	printf("   lines = %d\n", g_stats.total_lines);
	printf("   nodes = %d\n", g_stats.total_nodes);
	printf("   leaf  = %d\n", g_stats.total_leaf);
	printf("   ignore= %d\n", g_stats.total_ignored);
	printf("--------------------------\n");
}

void
search()
{
	char	readline[80];
	char	prefix[512];

	while (gets(readline)) {
		prefix[0] = '\0';
		search_trie(&g_root, readline, prefix);
		printf(".....\n.....\n");
	}
}

int
main(int argc, char *argv[])
{
	if (argc <= 1) {
		usage();
		exit(1);
	}
	read_file(argv[1]);
	print_trie_stats();
	search();
}
