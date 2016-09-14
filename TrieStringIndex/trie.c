#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define	MAX_ALPHA	26
#define	NODE		0
#define	LEAF		1
#define	ROOT		2

typedef struct trie_s {
	struct trie_s	*child[MAX_ALPHA];
	char		ch;
	short		type;
} trie_t;

typedef struct trie_stat_s {
	int		total_lines;
	int		total_nodes;
	int		total_leaf;
} trie_stat_t;

trie_t		g_root;
trie_stat_t	g_stats;

void
insert_word(char *start, char *end)
{
	trie_t	*curr;
	int	indx;

	if (!start) {
		return;
	}

	curr = &g_root;

	while (start && start != end) {
/*		printf("%c", *start++); */
		indx = tolower(*start) - 'a';
		if (indx >= MAX_ALPHA) {
			printf("----- Error - ignoring indx %d\n", indx);
			return;
		}

		if (curr->child[indx] == NULL) {
			curr->child[indx] = (trie_t *)malloc(sizeof(trie_t));
			g_stats.total_nodes++;
			bzero(curr->child[indx], sizeof (trie_t));
			curr->child[indx]->type = NODE;
			curr->child[indx]->ch = *start;
		}

		curr = curr->child[indx];
		start++;
	}

	if (curr != &g_root) {
		curr->type = LEAF;
		g_stats.total_leaf++;
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
				insert_word(start, end);
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
print_all_words(trie_t *curr, char *word, int len, int max_len)
{
	trie_t	*node;
	int	indx;
	int	i;

	if (curr->type == LEAF) {
		printf("%s\n", word);
	}

	for (i = 0; i < MAX_ALPHA; i++) {
		node = curr->child[i];
		if (node) {
			word[len] = node->ch;
			word[len + 1] = '\0';
			print_all_words(node, word, len + 1, max_len);
			word[len] = '\0';			
		}
	}
}

void
search_trie(char *word)
{
	char	*w = word;
	trie_t	*curr;
	char	temp_word[512];
	int	indx;

	strncpy(temp_word, word, sizeof (temp_word));
	curr = &g_root;
	while (*w) {
		if (!isalpha(*w)) {
			printf("no match found\n");
			return;
		}

		indx = tolower(*w) - 'a';
		if (!curr->child[indx]) {
			printf("no match found for %s\n", word);
			return;
		}
		curr = curr->child[indx];
		w++;
	}
	print_all_words(curr, temp_word, strlen(word), sizeof (temp_word));
}

void
print_trie_stats()
{
	printf("--------------------------\n");
	printf("   lines = %d\n", g_stats.total_lines);
	printf("   nodes = %d\n", g_stats.total_nodes);
	printf("   leaf  = %d\n", g_stats.total_leaf);
	printf("--------------------------\n");
}

void
search()
{
	char	readline[80];

	while (gets(readline)) {
		search_trie(readline);
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
