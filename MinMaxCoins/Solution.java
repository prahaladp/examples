import java.util.*;

public class Solution {
	public static int pickUpCoins(List<Integer> coins) {
		return computeMaximumRevenueForRange(coins, 0, coins.size() - 1,
				new int[coins.size()][coins.size()]);
	}
	
	private static int computeMaximumRevenueForRange(List<Integer> coins, int a, int b,
			int[][] maximumRevenueForRange) {
		if (a > b) {
			return 0;
		}
		
		if (maximumRevenueForRange[a][b] != 0) {
			return maximumRevenueForRange[a][b];
		}
		
		int maxRA = coins.get(a) + 
				Math.min(
						computeMaximumRevenueForRange(coins, a+2, b, maximumRevenueForRange),
						computeMaximumRevenueForRange(coins, a+1, b-1, maximumRevenueForRange));
		int maxRB = coins.get(b) +
				Math.min(
						computeMaximumRevenueForRange(coins, a+1, b-1, maximumRevenueForRange),
						computeMaximumRevenueForRange(coins, a, b-2, maximumRevenueForRange));
		
		maximumRevenueForRange[a][b] = Math.max(maxRA, maxRB);
		return maximumRevenueForRange[a][b];
	}
}
