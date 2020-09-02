package com.leetcode;

import java.util.Arrays;

public class CountRepetitions {
    int[][] d;
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        StringBuilder S1 = new StringBuilder(s1);
        StringBuilder S2 = new StringBuilder(s2);

        for (int i = 1; i < n1; i++) {
            S1.append(s1);
        }

        for (int i = 1; i < n2; i++) {
            S2.append(s2);
        }

        StringBuilder S3 = new StringBuilder();
        int m = 0;
        for (int i = 1; ; i++) {
            // System.out.println("----------- " + S1.toString());
            S3.append(S2);
            this.d = new int[S1.length()][S3.length()];
            for (int j = 0; j < S1.length(); j++) {
                Arrays.fill(this.d[j], -1);
            }

            if (findMatch(S1.toString().toCharArray(), S1.toString().length()-1,
                S3.toString().toCharArray(), S3.toString().length()-1) == false) {
                m=i-1;
                break;
            }
        }
        return m;
    }

    private boolean findMatch(char[] s1, int i1, char[] s2, int i2) {
        //System.out.println(new String(s1).substring(0,i1+1) + " -> " + new String(s2).substring(0,i2+1));
        if (i2 == -1) {
            return true;
        }
        if (i1 == -1) {
            return false;
        }
        if (this.d[i1][i2] != -1) {
            if (this.d[i1][i2] == 0) {
                return false;
            }
            return true;
        }
        boolean r1 = false;
        if (s1[i1] == s2[i2]) {
            r1 = findMatch(s1, i1-1, s2, i2-1);
        }
        boolean r2 = findMatch(s1, i1-1, s2, i2);
        this.d[i1][i2] = ((r1 | r2) == false) ? 0 : 1;
        return r1 | r2;
    }
}

