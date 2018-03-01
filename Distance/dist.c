/*
 * You have N points on a 2D surface. List K points at a shortest distance to the point (0, 0).
 */
#include<stdio.h>
#include<stdlib.h>

#define	MAX_K	1000

typedef struct 2D_s {
	int	rows;
	int	cols;
	uint8	*matrix;
} 2D_t;

typedef struct K_points_s {
	int	row, col;
} K_point[MAX_K];

int
sweep(2D_t *map, int curr_x, int curr_y, K_point *kp, int *indx)
{
	if (curr_x >= map->rows ||
	    curr_y >= map->cols) {
		return (-1);
	}

	if (matrix[curr_x][curr_y]) {
		kp[*indx].row = curr_x;
		kp[*indx].col = curr_y;
		*indx++;
	}

	sweep(map, curr_x + 1, curr_y, kp, indx);
	sweep(map, curr_x, curr_y + 1, kp, indx);
}

int
main(int argc, char *argv[])
{

} 
