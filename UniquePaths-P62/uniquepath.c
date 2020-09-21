#include<stdio.h>
#include<stdlib.h>
#include<strings.h>

int
uniquePathsImpl(int *arr, int *res,
        int m, int n,
        int cm, int cn) {
    if (cm >= m || cn >= n) {
        return 0;
    }
    if (cm == (m-1) && cn == (n-1)) {
        return 1;
    }

    if (*(res + (cm * n) + cn) != 0) {
        return *(res + (cm * n) + cn);
    }

    int down = uniquePathsImpl(arr, res, m, n, cm, cn + 1);
    int right = uniquePathsImpl(arr, res, m, n, cm + 1, cn);
    *(res + (cm * n) + cn) = down + right;
    return down + right;
}

int
uniquePaths(int m, int n) {
    int *arr = (int *)malloc(sizeof (int) * m * n);
    int *res = (int *)malloc(sizeof (int) * m * n);

    bzero(arr, sizeof (int) * m * n);
    bzero(res, sizeof (int) * m * n);

    return uniquePathsImpl(arr, res, m, n, 0, 0);
}

int
main(int argc, char *argv[]) {
    printf("UniquePaths (3, 3) = %d\n", uniquePaths(3, 3));
    printf("UniquePaths (13, 13) = %d\n", uniquePaths(13, 13));
}
