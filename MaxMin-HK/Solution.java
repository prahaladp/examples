public class Solution{
    static int maxMin(int k, int[] arr) {
        List<Integer> al = new ArrayList<Integer>();
        
        for (int i = 0; i < arr.length; i++) {
            al.add(new Integer(arr[i]));
        }
        
        int fairness = Integer.MAX_VALUE;
        al.sort(Comparator.naturalOrder());
        for (int i=0, j=k-1; j < al.size(); j++, i++) {
            if ((al.get(j) - al.get(i)) < fairness) {
                fairness = al.get(j) - al.get(i);
            }
        }
        return fairness;
    }
}
