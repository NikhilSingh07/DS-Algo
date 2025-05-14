// DP on subsquences: Same as coin change 2 || unbounded knapsack
// PICK | NOT PICK
// TUF: https://www.naukri.com/code360/problems/rod-cutting-problem_800284

public class RodCutting {


// Recursion: TC: O(2^n)
// SC: O(n)
// TLE

// Memoization: TC: O(n*n)
// SC: O(n) + O(n*n)

	private static int memoRod(int n, int len, int [] price, int[][]dp){

			// base case: when rod len becomes 0 || we reach the 0th index
			if(len ==0) return 0;

			if(n == 0) {

				return ((len/(0+1))* price[0]); 
			}

			if(dp[n][len]!=-1) return dp[n][len];

			// pick || not pick

			int np = 0 + memoRod(n-1, len, price, dp);
			int p = 0;

			if(len >= (n+1)) p = price[n] + memoRod(n, len- (n+1), price, dp);

			return dp[n][len] = Math.max(np, p);
	}

// Tabulation | Bottom up approach
// TC: O(n*n)
// SC: O(n*n)

	private static int tabRod(int n, int len, int []price, int[][]dp) {

		// bottom case: when rod is of len 0
		for(int i=0; i<price.length; i++) {
			dp[i][0] = 0;
		}

		// bottom case: when n = 0;
		for(int l = 0; l<=len; l++) {

			dp[0][l] = l*price[0];
		}

		for(int ind = 1; ind < price.length; ind ++) {

			for(int l = 0; l<=len; l++) {

				int np = 0;
				int p = 0;

				np = 0 + dp[ind-1][l];
				if(l >= (ind+1)) p = price[ind] + dp[ind][l - (ind+1)];

				dp[ind][l] = Math.max(np, p);
			}
		}

		return dp[n][len];
	}

// Tabulation with space optimisation
// TC: O(n*n)
// SC: (n)
	private static int tabRodWithSpaceOptimisation(int n, int len, int []price) {

		int []dp = new int[len+1];
		
		// bottom case: when rod is of len 0
		dp[0] = 0;

		// bottom case: when n = 0;
		for(int l = 0; l<=len; l++) {
			dp[l] = l*price[0];
		}

		for(int ind = 1; ind < price.length; ind ++) {

			int temp[] = new int[len+1];

			for(int l = 0; l<=len; l++) {

				int np = 0;
				int p = 0;

				np = 0 + dp[l];
				if(l >= (ind+1)) p = price[ind] + temp[l - (ind+1)];

				temp[l] = Math.max(np, p);
			}
			dp = temp;
		}

		return dp[len];
	}

	public static int cutRod(int price[], int n) {
		// Write your code here.

		//int [][] dp = new int[n][n+1];
		
		//for(int[]row: dp) Arrays.fill(row, 0);
		
		//return memoRod(n-1, n, price, dp);
		//return tabRod(n-1, n, price, dp);

		return tabRodWithSpaceOptimisation(n-1, n, price);
	}
    
}
