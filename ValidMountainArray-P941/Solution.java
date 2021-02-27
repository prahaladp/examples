class Solution {
    public boolean validMountainArray(int[] arr) {
        Boolean peakFound = null;
        if (arr.length <= 2) {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                return false;
            }
            if (arr[i] > arr[i-1]) {
                if (peakFound == null && i == arr.length - 1) {
                    return false;
                }
                if (peakFound != null && peakFound == true) {
                    return false;
                }
            } else {
                if (peakFound == null) {
                    if (i == 1) {
                        return false;
                    }
                    peakFound = true;
                } else if (peakFound == false) {
                    return false;
                }
            }
        }
        return true;
    }
}
