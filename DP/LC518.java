import java.util.Arrays;
// https://leetcode.com/problems/coin-change-ii/
// DP: Memoization + Tabulation
// Count
// MEDIUM

public class LC518 {

    
    // Recursion: Top-Bottom Appraoch
    // TC: O(2^n)
    // SC: O(amount)

    // Top-Bottom + Memoization
    // TC: O(n*amount)
    // SC: O(n*amount) + O(amount) aux space
    /*
    private int countCombinations(int ind, int amount, int []coins, int [][] dp) {

        // base case
        if(ind ==0 ){
            if(amount % coins[0] == 0){
                return 1;
            }
            return 0;
        }

        // returning the cached result: avoiding going into further recursion
        if(dp[ind][amount]!=-1) {
            return dp[ind][amount];
        }
        
        // explore recursion: either I pick a coint or I don't pick
        //picking 
        int countLeft = 0;
        if(coins[ind] <= amount) {
             countLeft = countCombinations(ind, amount-coins[ind], coins, dp);
        }
        // not pick
        int countRight = countCombinations(ind-1, amount, coins, dp);

        return dp[ind][amount] = countLeft+countRight;
    }

    public int change(int amount, int[] coins) {

        int n =  coins.length;
        int [][] dp = new int[n][amount +1 ];

        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        return countCombinations(n-1, amount, coins, dp);

    }*/

    // Bottom - up or Tabulation approach
    // TC: O(n*amount)
    // SC: O(n*amount)
    public int change(int amount, int[] coins) {

        int n =  coins.length;
        int [][] dp = new int[n][amount +1 ];

        for(int []row: dp) {
            Arrays.fill(row, 0);
        }

        // step 1. converting base case
        for(int t=0; t<=amount; t++) {
            if(t%coins[0] == 0) {
                dp[0][t]++;
            }
        }

        // step 2. handling the changing variables from top bottom
        for(int ind = 1; ind<n; ind++){
            for(int t=0; t<=amount; t++) {

                // step 3. copy recurrence from top to bottom
                //picking 
                int countLeft = 0;
                if(coins[ind] <= t) {
                    countLeft = dp[ind][t-coins[ind]];
                }
                // not pick
                int countRight = dp[ind-1][t];
                dp[ind][t] = countLeft+countRight;
            }
        }

        return dp[n-1][amount];
    }
    
}
