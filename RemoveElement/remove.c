int removeElement(int* nums, int numsSize, int val){
    int i = 0;
    int ci = 0;
    for (i=0, ci=0; i < numsSize; i++) {
        if (nums[i] == val) {
            continue;
        }
        nums[ci++] = nums[i];
    }
    return ci;
}

