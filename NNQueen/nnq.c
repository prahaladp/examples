#include <stdio.h>
#include <stdlib.h>

#define	MAX_N	1024
int board[MAX_N][MAX_N];
void print_board(int b[][MAX_N], int n);

void clear_board(int b[][MAX_N], int n)
{
	int x, y;
	for (x = 0; x < n; x++) {
		for (y = 0; y < n; y++) {
			b[x][y] = 0;
		}
	}
}

void mark_upper_left(int b[][MAX_N], int n, int x, int y, int depth)
{
	while (x >= 0 && y >= 0) {
		if (b[x][y] == 0) {
			b[x][y] = depth;
		}
		x--; y--;
	}
}

void mark_upper_right(int b[][MAX_N], int n, int x, int y, int depth)
{
        while (x < n && y >= 0) {
		if (b[x][y] == 0) {
                	b[x][y] = depth;
		}
		x++; y--;
        }
}

void mark_lower_left(int b[][MAX_N], int n, int x, int y, int depth)
{
	while (x >= 0 && y < n) {
		if (b[x][y] == 0) {
			b[x][y] = depth;
		}
		x--; y++;
	}
}

void mark_lower_right(int b[][MAX_N], int n, int x, int y, int depth)
{
	while (x < n && y < n) {
		if (b[x][y] == 0) {
			b[x][y] = depth;
		}
		x++; y++;
	}
}

void mark_row(int b[][MAX_N], int n, int x, int depth)
{
	int	y = 0;

	while (y < n) {
		if (b[x][y] == 0) {
			b[x][y] = depth;
		}
		y++;
	}
}

void mark_col(int b[][MAX_N], int n, int y, int depth)
{
	int	x = 0;

	while (x < n) {
		if (b[x][y] == 0) {
			b[x][y] = depth;
		}
		x++;
	}
}

void mark_q(int  b[][MAX_N], int n, int x, int y, int depth)
{
	b[x][y] = depth;
}

void clear_x_y(int b[][MAX_N], int n, int x, int y, int depth)
{
	for (x = 0; x < n; x++) {
		for (y = 0; y < n; y++) {
			if (b[x][y] == depth) {
				b[x][y] = 0;
			}
		}
	}
}
		
void mark(int b[][MAX_N], int n, int x, int y, int depth)
{
	mark_upper_left(b, n, x - 1, y -1, depth);
	mark_upper_right(b, n, x + 1, y - 1, depth);
	mark_lower_left(b, n, x -1, y + 1, depth);
	mark_lower_right(b, n, x + 1, y + 1, depth);
	mark_row(b, n, x, depth);
	mark_col(b, n, y, depth);
	mark_q(b, n, x, y, depth);
	//print_board(b, n);
}

void print_board(int b[][MAX_N], int n)
{
	int	x, y;

	printf("-----------------------------------\n");
	for (x = 0; x < n; x++) {
		for (y = 0; y < n; y++) {
			printf("%d ", b[x][y]);
		}
		printf("\n");
	}
	printf("-----------------------------------\n");
}

void find_row_combinations(int b[][MAX_N], int n, int x, int y, int depth)
{	
	printf("find_row_combinations : (%d, %d) (n = %d) (depth = %d)\n",
		x, y, n, depth);
	if (x >= n) {
//		print_board(b, n);
		return;
	}

	for (; y < n; y++) {
		if (b[x][y] != 0) {
			continue;
		}

		/* mark x, y */
		mark(b, n, x, y, depth);

		/* goto next row */
		find_row_combinations(b, n, x + 1, 0, depth + 1);

		/* unmark x, y */
		clear_x_y(b, n, x, y, depth + 1);
	}
}

void find_combinations(int b[][MAX_N], int n)
{
	int	x = 0, y;
	int	depth = 0;

	while (depth < n) {
		depth = 1;
		for (y = 0; y < n; y++) {
			/* free location */
			find_row_combinations(b, n, x, y, depth);
			clear_board(b, n);
		}
		depth++;
	}
}

int main(int argc, char *argv[])
{
	int	n = 0;

	if (argc < 2) {
		printf("usage : prog <n>\n");
		return (0);
	}
	n = atoi(argv[1]);
	
	clear_board(board, n);
	find_combinations(board, n);
}
