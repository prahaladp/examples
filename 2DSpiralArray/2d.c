#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int valid_x_y(int *matrix, int x, int y, int n) {
    if ((0 <= x && x < n) && (0 <= y && y < n) &&
        *(matrix + x * n + y) == 0) {
        return 1;
    }
    return 0;
}

int get_next_point(int *matrix, int direction[][2], int *d, int *x,
                   int *y, int n) {
    int next_x = *x + direction[*d][0];
    int next_y = *y + direction[*d][1];
    int count;
    
    if (valid_x_y(matrix, next_x, next_y, n)) {
        *x = next_x;
        *y = next_y;
        printf("selecting (%d, %d)\n", *x, *y);
        return 1;
    }
    
    /* we need to find a new direction */
    for (count = 0; count < 4; count++) {
        *d = *d + 1;
        if (*d >= 4) {
            *d = 0;
        }
        next_x = *x + direction[*d][0];
        next_y = *y + direction[*d][1];
        if (valid_x_y(matrix, next_x, next_y, n)) {
            *x = next_x;
            *y = next_y;
            printf("selecting (%d, %d)\n", *x, *y);
            return 1;
        }
    }
    return 0;
}

void print_matrix(int *matrix, int n) {
    int i;
    int j;
    
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            int *ptr = matrix + i * n + j;
            printf(" %3d", *ptr);
        }
        printf("\n");
    }
}

void twodspiral(int n) {
    int *matrix = (int *) malloc(sizeof (int) * n * n);
    int i = 0;
    int j = 0;
    int direction[][2] = {
        {0, 1},
        {1, 0},
        {-1, 0},
        {0, -1},
    };
    int next = 1;
    int dirindex = 0;
    
    bzero(matrix, sizeof (int) * n * n);
    /* done */
    print_matrix(matrix, n);
    
    while (1) {
        if (*(matrix + i * n +j) == 0) {
            printf("printing %d at (%d, %d)\n", next, i, j);
            *(matrix + i * n +j) = next++;
        }
        /* determine next direction */
        if (get_next_point(matrix, direction, &dirindex, &i, &j, n) == 0) {
            break;
        }
    }
    /* done */
    print_matrix(matrix, n);
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("usage : %s <num>\n", argv[1]);
        exit(1);
    }
    
    char buffer[256];
    twodspiral(atoi(argv[1]));
}
