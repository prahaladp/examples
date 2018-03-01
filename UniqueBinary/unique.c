#include <stdio.h>
#include <stdlib.h>

int numTreesImpl(int *arr, int n) {
    int i;
    int ntree;
    
    printf("numtree : %d\n", n);

    if (arr[n] != -1) {
        return arr[n];
    }

    ntree = 0;
    for (i = 1; i <= n; i++) {
        if (i - 1 == 1 || i - 1 == 0) {
            ntree += numTreesImpl(arr, n - i);
        } else if (n - i == 1 || n - i == 0) {
            ntree += numTreesImpl(arr, i - 1);
        } else {
            ntree += numTreesImpl(arr, i - 1) * numTreesImpl(arr, n - i);
        }
    }
    
    printf("--------\n");
    arr[n] = ntree;
    return ntree;
}

int numTrees(int n) {
    int ntree;
    int i;
    int *arr;
    
    arr = malloc(sizeof(int) * (n + 1));
    
    for (i = 0; i <= n; i++) {
        arr[i] = -1;
    }
    
    arr[0] = 0;
    arr[1] = 1;
    arr[2] = 2;

    return numTreesImpl(arr, n);
}

int
main(int argc, char *argv[]) {
		printf("numtrees(3) = %d\n", numTrees(3));
		printf("numtrees(100) = %d\n", numTrees(100));

}

