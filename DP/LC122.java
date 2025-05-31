// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
// Best time to buy and sell stocks 2 
// DP on stocks: buy or not buy + sell or not sell logic

public class LC122 {
/* 
     Logic: 
> state variables: i, Buy
> If I have the liberty to buy, I can make 2 choices at each index: either buy or not buy
> if not, I can make 2 choices as well: either sell or not sell.
*/

        // Recursion: TC: O(2^n)
    // SC: O(n)

    // Memoization: TC: O(n*2)
    // SC: O(n*2) + O(n)
    private int memoProfit(int n, int buy, int[] prices, int[][]dp){

        // base case: 
        if(n == prices.length) return 0;    

        if(dp[n][buy]!=-1) return dp[n][buy];

        if(buy == 1){ // I have the liberty to buy a stock

            // Can make 2 choices: buy or not buy
            int buy_profit = -prices[n] + memoProfit(n+1, 0, prices, dp); // now, can't buy again
            int notBuy_profit = 0 + memoProfit(n+1, 1, prices, dp); 

            return dp[n][buy] = Math.max(buy_profit, notBuy_profit);

        } else { // I already hold a stock, cannot buy again

            // can make 2 choces: sell or not sell
            int sell_profit = prices[n] + memoProfit(n+1, 1, prices, dp);
            int not_sell_profit = 0 + memoProfit(n+1, 0, prices, dp);

            return dp[n][buy] = Math.max(sell_profit, not_sell_profit);
        }
    }

    // Tabulation: Bottom up approach
    // TC: O(n*2)
    // SC: O(n*2)
    private int tabProfit(int[] prices, int[][]dp) {

        int n = prices.length;
        // Bottom cases, when n = n
        dp[n][0] = 0;
        dp[n][1] = 0;

        // handling the state variables in buttom up approach
        // In recursion we go form 0 to n-1: tab is opposite of rec

        for(int i = n-1; i>=0; i--) {
            for(int j = 0; j<2; j++) {

                if(j == 1) { // have the liberty to buy

                    dp[i][j]  = Math.max( (-prices[i] + dp[i+1][0]), (0 + dp[i+1][1]));
                } else {

                    dp[i][j] =  Math.max( (prices[i] + dp[i+1][1]), (0 + dp[i+1][0]) );
                }
            }
        }

        return dp[0][1];

    }

    // Tabulation with space optimisation
    //TC: O(n*2)
    // SC: O(2*2)
    private int tabWithSpaceOptProfit(int[] prices) {

        int []next = new int[2];
        int n = prices.length;
        // Bottom cases, when n = n
        next[0] = 0;
        next[1] = 0;

        // handling the state variables in buttom up approach
        // In recursion we go form 0 to n-1: tab is opposite of rec
        for(int i = n-1; i>=0; i--) {

            int []curr = new int[2];
            for(int j = 0; j<2; j++) {

                if(j == 1) { // have the liberty to buy

                    curr[j]  = Math.max( (-prices[i] + next[0]), (0 + next[1]));
                } else {

                    curr[j] =  Math.max( (prices[i] + next[1]), (0 + next[0]));
                }
            }
            next = curr;
        }
        return next[1];
    }

    public int maxProfit(int[] prices) {

        int n = prices.length;

        /*
        int [][]dp = new int[n][2];
        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        
        return memoProfit(0, 1, prices, dp);
        */
        /*
        int [][]dp = new int[n+1][2];
        return tabProfit(prices, dp);
        */

        return tabWithSpaceOptProfit(prices);
    }
    
}
