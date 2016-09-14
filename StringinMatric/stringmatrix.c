#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define	boolean_t	int

#define	TRUE	0
#define	FALSE	1

typedef struct search_info_s {
	char	*m;
	int	x, y;
	int	szx, szy;
} search_info_t;

struct adjacent{
	int	x, y;
} adj[] = {
	{ -1, -1},
	{ -1, 0},
	{ -1, +1},
	{ 0, -1},
	{ 0, +1},
	{ 1, -1},
	{ 1, 0},
	{ 1, 1}
};

boolean_t legal_coordinates(search_info_t *sm, int adjx, int adjy)
{
	boolean_t	val;

	val = ((sm->x + adjx >= 0 && sm->x + adjx < sm->szx) &&
	    (sm->y + adjy >= 0 && sm->y + adjy < sm->szy));
	return (val);
}

boolean_t search_str(search_info_t *sm, char *str, int pos)
{
	search_info_t	new_sm;
	int		i;
	int		newx, newy;
	boolean_t	val;
	char		*m;

	if (pos >= strlen(str)) {
		return (TRUE);
	}

	for (i = 0; i < sizeof (adj); i++) {
		if (legal_coordinates(sm, adj[i].x, adj[i].y)) {
			newx = sm->x + adj[i].x;
			newy = sm->y + adj[i].y;
			m = sm->m;
			printf("comparing %c with %c at (%d, %d)\n",
			    *(m + newx * sm->szx + newy),
			    str[pos + 1], newx, newy);
			if (*(m + newy * sm->szy + newx) == str[pos + 1]) {
				printf("matched %c at (%d, %d)\n",
				    str[pos+1], newx, newy);
				memcpy(&new_sm, sm, sizeof (new_sm));
				new_sm.x = newx;
				new_sm.y = newy;
				val = search_str(&new_sm, str, pos + 1);
				if (val == TRUE) {
					return (val);
				}
			}
		}
	}
	return (FALSE);
}

boolean_t string_in_matrix(char *matrix[], char *str, int smx, int smy)
{
	search_info_t	sm;
	int		x, y;
	int		pos = 0;
	boolean_t	val = FALSE;

	sm.m = &matrix[0][0];
	sm.szx = smx;
	sm.szy = smy;
	for (x = 0; x < smx; x++) {
		for (y = 0; y < smy; y++) {
			sm.x = x;
			sm.y = y;
			if (matrix[x][y] == str[pos]) {
				val = search_str(&sm, str, pos);
				printf("matched %c at (%d, %d)\n", str[pos],
				    x, y);
				if (val == TRUE) {
					printf("success = string at (%d, %d)\n", x, y);
					return (val);
				}
			}
		}
	}
	return (FALSE);
}

void print_matrix(char *m[], int smx, int smy)
{
	int	x, y;

        for (x = 0; x < smx; x++) {
                for (y = 0; y < smy; y++) {
			char	*ch = &m[0][0];
			printf("%c", *(ch + x * smx + smy));
		}
		printf("\n");
	}
}

int main(int argc, char *argv[])
{
	char	*m[] = {
		"abc",
		"def",
		"geh"
	};
	print_matrix(m, 3, 3);
	string_in_matrix(m, "de", 3, 3);
}
