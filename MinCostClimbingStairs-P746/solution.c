int minCCimpl(int *cost, int *cc, int ind, int n) {
    if (ind >= n) {
        return 0;
    }
    if (cc[ind] != -1) {
        return cc[ind];
    }
    if (ind == n - 1) {
        cc[ind] = cost[ind];
        return cost[ind];
    }
    int c1 = INT_MAX;
    int c2 = INT_MAX;
    {
        c1 = cost[ind] + minCCimpl(cost, cc, ind + 1, n);
    }
    {
        c2 = cost[ind] + minCCimpl(cost, cc, ind + 2, n);
    }
    cc[ind] = (c1 < c2) ? c1 : c2;
    return cc[ind];
}

int minCostClimbingStairs(int* cost, int costSize){
    int *cc = (int *) malloc(sizeof (int) * costSize);
    int i = 0;
    
    for (i = 0; i < costSize; i++) {
        cc[i] = -1;
    }
    int c1 = minCCimpl(cost, cc, 1, costSize);
    int c0 = minCCimpl(cost, cc, 0, costSize);
    if (c1 < c0) {
        return c1;
    }
    return c0;
}
