class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = -1;
        int r = arr.length;

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            l=i-1;
            r=i;
            if (x < arr[i]) {
                break;
            }
        }
        while (k-- != 0) {
            int minL = Integer.MAX_VALUE;
            int minR = Integer.MAX_VALUE;
            if (l >= 0) {
                minL = Math.abs(x - arr[l]);
            }
            if (r <= arr.length - 1) {
                minR = Math.abs(x - arr[r]);
            }
            if (minL == minR) {
                res.add(arr[l]);
                l = l-1;
            } else if (minL < minR) {
                res.add(arr[l]);
                l = l-1;
            } else {
                res.add(arr[r]);
                r = r + 1;
            }
        }
        res.sort(Comparator.naturalOrder());
        return res;
    }
}
