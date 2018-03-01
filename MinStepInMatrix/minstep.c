#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define	MAX_ROW	6
#define	MAX_COL	6

#define	NO_PATH	-1
#define	FALSE	0
#define	TRUE	1

#define	MAX_INT	(25000)

int	matrix[MAX_ROW][MAX_COL];

int
min_sum(int matrix[MAX_ROW][MAX_COL],
	int *accum,
	int *min,
	int row,
	int col) {

	if ((matrix[row][col] + *accum) < *min) {
		*accum = matrix[row][col] + *accum;
		return TRUE;
	}
	return FALSE;
}

int
valid(int matrix[MAX_ROW][MAX_COL], char path[MAX_ROW][MAX_COL],
	int row, int col) {
	return (matrix[row][col] && path[row][col] != 'X');
}

void
init_path_matrix(char path[MAX_ROW][MAX_COL], int row, int col) {
	int	i, j;

	for (i = 0; i < row; i++) {
		for (j = 0; j < col; j++) {
			path[i][j] = ' ';
		}
	}
}

int
bounded(int param, int add, int minp, int maxp) {
	return ((param + add) >= minp) && ((param + add) <= maxp);
}

void
print_matrix(int matrix[MAX_ROW][MAX_COL]) {
	int	i, j;

	for (i = 0; i < MAX_ROW; i++) {
		for (j = 0; j < MAX_COL; j++) {
			if (matrix[i][j]) {
				printf("%3d ", matrix[i][j]);
			} else {
				printf("    ");
			}
		}
		printf("\n");
	}
}

void
print_char_matrix(char path[MAX_ROW][MAX_COL]) {
	int	i, j;

	for (i = 0; i < MAX_ROW; i++) {
		for (j = 0; j < MAX_COL; j++) {
			printf("%3c ", path[i][j]);
		}
		printf("\n");
	}

}

int
push_down(int matrix[MAX_ROW][MAX_COL],
	int start_row,
	int start_col,
	int max_row,
	int max_col,
	int *accum,
	int *min,
	char path[MAX_ROW][MAX_COL]) {

	int	xpaths[] = { 0,  1 , 1, 1, 0 };
	int	ypaths[] = { -1, -1, 0, 1, 1 };
	int	i, res;
	int	no_path = TRUE;

	printf("Debug : evaluating [%d, %d] (min = %d, accum = %d) (%d %d)\n",
	    start_row, start_col, *min, *accum, max_row, max_col);

	path[start_row][start_col] = 'X';

	if (start_row == max_row) {
		/* we have one path already */
		*min = *accum;

		printf("End of path\n");

		/* assuming positive values, we can return */
		return TRUE;
	}

	for (i = 0; i < sizeof (xpaths)/sizeof (int); i++) {
		printf("Debug : checking %d,%d\n",
		    start_row + xpaths[i], start_col + ypaths[i]);

		if (!valid(matrix,
		    path,
		    start_row + xpaths[i],
		    start_col + ypaths[i])) {
			printf("Debug : invalid\n");
			continue;
		}

		if (!bounded(start_col, ypaths[i], 0, max_col) ||
		    !bounded(start_row, xpaths[i], 0, max_row)) {
			printf("Debug : Unbounded\n");
			continue;
		}

		if (min_sum(matrix, accum, min,
		    start_row + xpaths[i],
		    start_col + ypaths[i]) == FALSE) {
			printf("Debug : not min path\n");
			continue;
		}


		no_path = FALSE;

		/* this fits the criteria, so continue */
		res = push_down(matrix,
			start_row + xpaths[i],
			start_col + ypaths[i],
			max_row,
			max_col,
			accum,
			min,
			path);
		if (res == TRUE) {
			if (start_row + xpaths[i] == max_row) {
				printf("found min_path %d\n", *accum); 
				/* update min */
				*min = *accum;
				print_char_matrix(path);
			}
		}

		/* rollback, nothing to do, currently */
		*accum -=
		    matrix[start_row + xpaths[i]][start_col + ypaths[i]];
		path[start_row + xpaths[i]][start_col + ypaths[i]] = ' ';
	}
	return (no_path) ? NO_PATH : TRUE;
}

void
testcase1() {
	int	matrix[MAX_ROW][MAX_COL];
	char	path[MAX_ROW][MAX_COL];
	int	res;
	int	accum = 0;
	int	min = MAX_INT;

	bzero(matrix, sizeof (int) * MAX_ROW * MAX_COL);
	matrix[0][0] = 91;
	matrix[1][0] = 22;
	matrix[2][0] = 21;
	matrix[2][1] = 12;
	matrix[3][0] = 2;

	init_path_matrix(path, MAX_ROW, MAX_COL);

	print_matrix(matrix);

	accum = matrix[0][0];
	res = push_down(matrix, 0, 0, 3, 3, &accum, &min, path);
	if (res == NO_PATH) {
		printf("no_path found\n");
	}
	printf("min_path = %d\n", accum);
}

void
testcase2() {
	int	matrix[MAX_ROW][MAX_COL];
	char	path[MAX_ROW][MAX_COL];
	int	res;
	int	accum = 0;
	int	min = MAX_INT;

	/*
			 91             
		 22  13      35         
		  6   1                 
		 14       5             
		  9       6             

	 */

	bzero(matrix, sizeof (int) * MAX_ROW * MAX_COL);
	matrix[0][2] = 91;
	matrix[1][0] = 22;
	matrix[1][1] = 13;
	matrix[1][3] = 35;
	matrix[2][1] = 1;
	matrix[2][0] = 6;
	matrix[3][0] = 14;
	matrix[3][2] = 5;
	matrix[4][2] = 6;
	matrix[4][0] = 9;

	init_path_matrix(path, MAX_ROW, MAX_COL);

	print_matrix(matrix);

	accum = matrix[0][2];
	res = push_down(matrix, 0, 2, 4, 4, &accum, &min, path);
	if (res == NO_PATH) {
		printf("no_path found\n");
	}
	printf("min_path = %d\n", accum);
}

int
main(int argc, char *argv[]) {
	testcase1();
	testcase2();
}
