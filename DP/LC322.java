// https://leetcode.com/problems/coin-change/
// DP: Top Bottom + Bottom up
// MEDIUM

import java.util.Arrays;

public class LC322 {


    
    // Approach 1: using recutsion 
    // TC: O(2^amount)
    // SC: O(amount): recursion depth of the treee
    /*
    public int  getFewestCoins(int []coins, int amount, int cCount, int mCoins, int pos) {

        // base case for the recursive function
        if(amount ==0) {
            return cCount;
        }  else if(amount < 0) {return Integer.MAX_VALUE;}
        
        for(int i=0; i<coins.length; i++) {
            int result = getFewestCoins(coins, amount-coins[i], cCount+1, mCoins, i);
            mCoins = Math.min(mCoins, result);
        }

        return mCoins;

    }*/


    // explore all the combinations
    /*
    public int coinChange(int[] coins, int amount) {
        int cCount = 0;
        int mCoins = Integer.MAX_VALUE;
        int ans = getFewestCoins(coins, amount, cCount, mCoins, 0);

        if(ans != Integer.MAX_VALUE) return ans;
        else return -1;
    }*/

    // Top to Bottom + Memoization: 
    // TC: O(n)*O(amount)-> every subproblem will be solved only once
    // SC: O(n*amount) + O(amount) [recursive stack space] 
    /*
    private int getFewestCoins(int ind, int amount, int []coins, int[][]dp) {

       //4. Base case: reaching the end of array
       if(ind == 0) {
           //think if we have an array of size 1
           if(amount % coins[ind] == 0) {
               // we need Target/coins[ind] number of coins
               return amount/coins[ind];
           } else return Integer.MAX_VALUE;
       }

       if(dp[ind][amount]!=-1) {
        //System.out.println("Cached result: "+dp[ind][amount]);
        return dp[ind][amount];
       }

       // 2. expressing the recursion: 2 possible choices - pick or not pick
       int pick = Integer.MAX_VALUE;
       if(coins[ind] <= amount) {  
           int subResult =   getFewestCoins(ind, amount - coins[ind], coins, dp); // pick the same coin again
           if(subResult!= Integer.MAX_VALUE) {
             pick = 1 +  subResult; // avoiding overflow
           }
           
       }  
       int not_pick = 0 + getFewestCoins(ind-1, amount, coins, dp); // move to the previous index

       //3. Finding the min of all
       dp[ind][amount] = Math.min(pick, not_pick);
       return dp[ind][amount];
    }

    public int coinChange(int[] coins, int amount) {  
        int n = coins.length;
        //dp[i][j] represents the minimum coins required to make amount j using the first i coins.
        int [][]dp = new int[n][amount +1];

        // Initialize the dp array with -1 to indicate that subproblems are not solved yet
        for (int row[] : dp)
            Arrays.fill(row, -1);

        int ans = getFewestCoins(n-1, amount, coins, dp) ; // 1. Declare recurrence: calculate and return the min number of coins

        if(ans == Integer.MAX_VALUE) return -1;
        else return ans;
    }*/

    // Bottom up Technique
    // Converting memoisation to bottom up
    // TC: O(n*amount)
    // SC: O(n*amount)
    // saving the auxiliary space from recursion
    public int coinChange(int[] coins, int amount) {  
        int n = coins.length;
        int [][]dp = new int[n][amount+1]; // 0 to amount

        for(int row[]: dp ){
            Arrays.fill(row, 0);
        }

        // Step1: converting the base case to for loop - when index ==0 
        for(int t= 0; t<=amount; t++) {
            if(t % coins[0] == 0) { 
                dp[0][t] = t/coins[0];
            } else {
                dp[0][t] = (int)Math.pow(10,9); // A large number, avoid overflow risk
            }
        } 

        // Step 2: Handling the changing parameters: ind and amount
        for(int ind = 1; ind<n; ind++) {
            for(int t = 0; t<=amount; t++) {

                // step 3:  copy and paste the recurrence relation
                int pick = Integer.MAX_VALUE;
                if(coins[ind] <= t) {  
                    pick = 1 + dp[ind][t - coins[ind]];
                } 

                int not_pick = dp[ind-1][t]; // move to the0 + d previous index

                dp[ind][t] = Math.min(pick, not_pick);
                
            }
        }
        int ans = dp[n-1][amount];
        return ans >= Math.pow(10,9)?-1:ans;

    }

    
}
