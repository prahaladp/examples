import java.util.Arrays;

public class Solution {
	private void printArr(Integer[][] arr) {
		System.out.println("-----\n");
		for (int i = 0 ; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(" " + arr[i][j]); 
			}
			System.out.println("\n");
		}
	}
	
	public int longestPalindromeSubseq(String s) {
        Integer[][] arr = new Integer[s.length()][s.length()+1];
        
        System.out.println("length = " + s.length());
        if (s.length() == 0) {
        		return 0;
        }
        
        if (s.length() == 1) {
        		return 1;
        }
        
        for (Integer[] row: arr) {
            Arrays.fill(row, 0);
        }

        for (int i = 0 ; i < s.length(); i++) {
        		arr[i][0] = 0;
        		arr[i][1] = 1;
        }
        //printArr(arr);
        int maxCnt = 1;
        for (int i = s.length() - 1; i >= 0; i--) {    
			int mCount = 1;
			int cCount = 1;
        		for (int j = i + 1; j < s.length(); j++) {
        			cCount = Integer.max(mCount, arr[i+1][j-i]);
        			System.out.println(" " + s.charAt(i) + "," + s.charAt(j));
        			if (s.charAt(i) == s.charAt(j)) {
        				mCount = 2;
        				mCount += arr[i+1][j-i-1];
        				System.out.println("   " + i + "  " + j + " " + mCount);
        				maxCnt = Integer.max(maxCnt, mCount);
        				cCount = Integer.max(mCount, cCount);
        			}
        			arr[i][j-i+1] = cCount;
        		}
        		//printArr(arr);
        }
        return maxCnt;
    }

    public static void main(String args[]) {
    		Solution s = new Solution();
    		//System.out.printf("%s %d\n", "bbbab", s.longestPalindromeSubseq("bbbab"));
    		//System.out.printf("%s %d\n", "bbadccab", s.longestPalindromeSubseq("bbadccab"));
    		System.out.printf("%s \n %d\n", "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew",
    				s.longestPalindromeSubseq("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));
    }
}
