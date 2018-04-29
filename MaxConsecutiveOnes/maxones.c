#include<stdio.h>
#include<stdlib.h>

int findMaxConsecutiveOnes(int* nums, int numSize) {
    int i = 0;
    int maxO = 0;
    int cMaxO = 0;

    if (!nums) {
        return 0;
    }
    for (; i < numSize; i++) {
        if (nums[i] == 1) {
            cMaxO++;
        } else {
            maxO = (cMaxO > maxO) ? cMaxO : maxO;
            cMaxO = 0;
        }
    }
    return maxO;
}

int main(int argc, char *argv[]) {
    int nums[] = {1,1,0,1,1,1};
    printf("maxO = %d\n", findMaxConsecutiveOnes(nums, sizeof (nums)));
}
