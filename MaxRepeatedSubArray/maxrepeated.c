#include<stdio.h>
#include<stdlib.h>

int findLength(int* A, int ASize, int* B, int BSize) {
    int maxl = -1;
    int i = 0;
    int j = 0;
    
    if (ASize == 0 || BSize == 0) {
        return 0;
    }
    
    for (i = 0 ; i < ASize; i++) {
        for (j = 0; j < BSize; j++) {
            int matchl = 0;
            int ia = i;
            int jb = j;
            while (ia < ASize && jb < BSize && A[ia] == B[jb]) {
                ia++;
                jb++;
                matchl++;
            }
            
            if (matchl > maxl) {
                maxl = matchl;
            }
        }
    }
    
    return maxl;
}

int
main(int argc, char *argv[]) {
    int A[] = {1,2,3,2,1};
    int B[] = {3,2,1,4,7};
    
    printf("max length = %d\n", findLength(A, 5, B, 5));
}
