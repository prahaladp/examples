class Solution {
    public String largestTimeFromDigits(int[] A) {
        int[] copyA = new int[A.length];
        System.arraycopy(A, 0, copyA, 0, A.length);
        String str = pick2(copyA);
        System.out.println("s :  " + str + " " + str.length());
        if (str.length() != 0) {
            return str;
        }
        
        System.arraycopy(A, 0, copyA, 0, A.length);
        str = pickLessThan2(copyA);
        return str;
    }
    
    private String pickLessThan2(int[] A) {
        StringBuilder ns = new StringBuilder();
        int h = 0;
        
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < 2) {
                ns.append(A[i]);
                h = A[i];
                A[i] = -1;
                break;
            }
        }
        if (ns.length() != 1) {
            return "";
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            ns.append(A[i]);
            A[i] = -1;
            break;
        }
        if (ns.length() != 2) {
            return "";
        }

        ns.append(":");
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            if (A[i] <= 5) {
                ns.append(A[i]);
                A[i] = -1;
                break;
            }
        }
        if (ns.length() != 4) {
            return "";
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            ns.append(A[i]);
            break;
        }
        if (ns.length() != 5) {
            return "";
        }

        return ns.toString();
    }
    
    private String pick2(int[] A) {
        StringBuilder ns = new StringBuilder();
        int h = 0;
        
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == 2) {
                ns.append(A[i]);
                h = A[i];
                A[i] = -1;
                break;
            }
        }
        if (ns.length() != 1) {
            return "";
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            if (A[i] <= 3) {
                ns.append(A[i]);
                A[i] = -1;
                break;
            }
        }
        if (ns.length() != 2) {
            return "";
        }

        ns.append(":");
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            if (A[i] <= 5) {
                ns.append(A[i]);
                A[i] = -1;
                break;
            }
        }
        if (ns.length() != 4) {
            return "";
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            ns.append(A[i]);
            break;
        }
        if (ns.length() != 5) {
            return "";
        }

        return ns.toString();
    }
}
