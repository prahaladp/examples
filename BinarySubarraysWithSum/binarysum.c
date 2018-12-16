#include<stdio.h>
#include<string.h>
#include<stdlib.h>

void printArr(int *sum, int ASize) {
    int i;
    for (i = 0; i < ASize + 1; i++) {
        printf(" %d", sum[i]);
    }
    printf("\n");
}

int numSubarraysWithSum(int* A, int ASize, int S) {
    int *sum = (int *)malloc(sizeof(int) * (ASize + 1));
    int i = 0;
   
    bzero(sum, sizeof(int) * (ASize+1));
    printArr(sum, ASize);
    for (i = 0; i < ASize + 1; i++) {
        if (A[i] == 0) {
            int j;
            sum[0]++;
            for (j = 1; j < ASize + 1; j++) {
                if (sum[j] != 0) {
                    sum[j]++;
                }
            }
        } else {
            int j;
            for (j = ASize; j >=2 ; j--) {
                if (sum[j-1] != 0) {
                    sum[j] = sum[j-1] + 1;
                }
            }
            sum[1]++;
        }
        printArr(sum, ASize);
    }
    return sum[S];
}

int main(int argc, char *argv[]) {
    int A[] = {1, 0, 1, 0, 1};
    printf("numSubarraysWithSum{1, 0, 1, 0, 1} = %d\n", numSubarraysWithSum(A, 5, 2));
}
