class Solution {
    public int countPrimes(int n) {
        if(n<2) return 0;
        boolean[] p = new boolean[n];
        for(int i=2;i*i<n;i++){
            if(!p[i]){
                for(int j=i;j*i<n;j++)
                    p[j*i]=true;
            }
        }
        int count=0;
        for(boolean b : p)
            if(!b) count++;
        return count-2; //eliminate 0 and 1
    }
}
