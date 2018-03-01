#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct permute_s {
	char	*str;
	int	len;
	char	*result;
	int	frame;
	int	start;
	int	end;	
	int	skip;
} permute_t;

int g_counter = 0;

permute_t *
init_permute()
{
	permute_t	*perm;

	perm = (permute_t *)malloc(sizeof (*perm));
	bzero(perm, sizeof (*perm));
	return (perm);
}

void
swap(char *str, int i1, int i2)
{
	char	tmp;

	tmp = str[i1];
	str[i1] = str[i2];
	str[i2] = tmp;
}

void
permutate(permute_t *perm)
{
	permute_t	*rp;
	int		i;

	/*
	printf("permutate : (%s) (%s) (len = %d) (%d, %d)\n", perm->str,
		perm->result, perm->len, perm->frame, perm->start);
	*/
	if (perm->start == perm->len - 1) {
		/* last character */
		perm->result[perm->len] = '\0';
		printf("Permutation (%d) : %s\n", g_counter++, perm->result);
		return;
	}

	rp = init_permute();
	for (i = perm->start; i < perm->len; i++) {
		swap(perm->result, perm->frame, i);
		rp->len = perm->len;
		rp->str = perm->str;
		rp->result = perm->result;
		rp->frame = perm->frame + 1;
		rp->start = perm->start + 1;
		permutate(rp);
		swap(perm->result, perm->frame, i);
	}
	free(rp);
}

int
main(int argc, char **argv)
{
	char		*str = NULL;
	permute_t	*perm;
	int		len = 0;

	if (argc != 2) {
		printf("Usage : <prog> <string>\n");
		return -1;
	}

	str = argv[1];
	printf("Permutations for %s\n", str);

	len = strlen(str);
	perm = init_permute();
	perm->len = len;
	perm->str = str;
	perm->result = (char *)malloc(len);
	strcpy(perm->result, perm->str);
	perm->start = 0;
	perm->frame = 0;
	permutate(perm);
	free(perm);
}
