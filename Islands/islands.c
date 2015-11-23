#include <stdio.h>

int matrix[4][5] = {
	{ 0, 1, 1, 0, 1},
	{ 1, 1, 1, 0, 0},
	{ 0, 0, 0, 1, 1},
	{ 1, 0, 0, 1, 0}
};

int island = 0;

void
mark_adj(int x, int y, int mx, int my)
{
	if (x < 0 || x >= mx) {
		return;
	}
	if (y < 0 || y >= my) {
		return;
	}
	if (matrix[x][y] == -1) {
		return;
	}
	if (matrix[x][y] == 0) {
		return;
	}
	matrix[x][y] = -1;
	mark_adj(x - 1, y, mx, my);
	mark_adj(x, y - 1, mx, my);
	mark_adj(x, y + 1, mx, my);
	mark_adj(x + 1, y, mx, my);
}

void
remark(int mx, int my)
{
	int	ii, jj;

	for (ii = 0; ii < mx; ii++) {
		for (jj = 0; jj < my; jj++) {
			if (matrix[ii][jj] == -1) {
				matrix[ii][jj] = 1;
			}
		}
	}	
}

void
get_islands(int mx, int my)
{
	int	ii, jj;

	for (ii = 0; ii < mx; ii++) {
		for (jj = 0; jj < my; jj++) {
			if (matrix[ii][jj] == 1) {
				mark_adj(ii, jj, mx, my);
				island++;
			}
		}
	}
	remark(mx, my);
}

int
main(int argc, char *argv[])
{
	get_islands(4, 5);
	printf("Number of islands = %d\n", island);
}
