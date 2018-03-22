#include<stdio.h>
#include<stdlib.h>

/*
 * 1,1,2,3,3,4,4,8,8
 * 0    8   mid = 4
 *          nums[4] != nums[5]
 * 0    4   mid = 2
 *          nums[2] != nums[3]
 * 0    2   mid = 1
 *          nums[1] == nums[0]
 * 2    2   
 */
int
singleNonDuplicateImpl(int *nums, int start, int end) {
    int mid = start + (end - start)/2;

    if (start == end) {
        return nums[start];
    }

    if (mid % 2) {
        if (nums[mid] == nums[mid-1]) {
            /* duplicate is present in the lower half */
            return singleNonDuplicateImpl(nums, mid + 1, end);
        }
        return singleNonDuplicateImpl(nums, start, mid - 1);
    }

    if (nums[mid] == nums[mid + 1]) {
        return singleNonDuplicateImpl(nums, mid + 2, end);
    }
    return singleNonDuplicateImpl(nums, start, mid);
}

int
singleNonDuplicate(int* nums, int numsSize) {
       return singleNonDuplicateImpl(nums, 0, numsSize - 1); 
}

int
main(int argc, char *argv[]) {
    int   n1[] = {1,1,2,3,3,4,4,8,8};
    int   n2[] = {3,3,7,7,10,11,11};

    printf("singleNonDuplicate (1,1,2,3,3,4,4,8,8) = %d\n", singleNonDuplicate(n1, sizeof (n1)/sizeof (int)));
    printf("singleNonDuplicate (3,3,7,7,10,11,11) = %d\n", singleNonDuplicate(n2, sizeof (n2)/sizeof (int)));
}
