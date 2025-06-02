// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
// Best Time to Buy and Sell Stock 3
// Same as problem with capacity
// Use another state variable to handle the cap


public class LC123 {


    // > This problem is an extenstion of part II 
    // > Here, we have a cap of 2 transactions.
    // > Therefore, we need to use another state variable.

    // ------------------  Apporach 1: Using 3 State Variables -------------------------

    // > The problem says to return the max profit we can generate by making 2 transactions as a whole 
    // > 3 State variables are:  index, buy_status and capacity

    // Recursion: 
    // TC: O(2^n)
    // SC: O(n)
    // TLE

    // Memoization: 
    // TC: O(n*2*3)
    // SC: O(n*2*3) + O(n)
    private int memoProfit(int n, int buy, int cap, int [] prices, int[][][]dp) {

        // base cases: if cap == 0 || n == n

        if(cap == 0) return 0;
        if(n == prices.length) return 0;

        // memoization
        if(dp[n][buy][cap]!=-1) return dp[n][buy][cap];

        if(buy == 1){ // have liberty to buy a stack: as no transaction is going on
            // cap will remain same as the transaction is not finished yet.

            int buy_p = -prices[n] + memoProfit(n+1, 0, cap, prices, dp);
            int not_buy = 0 + memoProfit(n+1, 1, cap, prices, dp);

            return dp[n][buy][cap] = Math.max(buy_p, not_buy);
        
        } else {
            
            int sell = prices[n] + memoProfit(n+1, 1, cap-1, prices, dp); // transaction is finished: cap--
            int not_sell =  0 + memoProfit(n+1, 0, cap, prices, dp);

            return dp[n][buy][cap] = Math.max(sell, not_sell);
        }
    }

    // Tabulation | Bottom Up
    // TC: O(n*2*3)
    // SC: O(n*2*3)
    private int tabProfit(int cap, int []prices, int[][][]dp) {

        // bottom cases: 
        // > 1. cap == 0
        // > 2. n == prices.length;

        // > 1
        for(int i=0; i<=prices.length; i++) {
            for(int b = 0; b<2; b++) {
                dp[i][b][0] = 0;
            }
        }

        // > 2
        for(int b=0; b<2; b++) {
            for(int c = 0; c<3; c++) {
                dp[prices.length][b][c] = 0;
            }
        }

        // Now, comes the state variables

        for(int i = prices.length-1; i>=0; i--) {
            for(int b = 0; b<2; b++) {
                for(int c = 1; c<3; c++) {

                    if(b == 1) {

                        dp[i][b][c] =  Math.max( (-prices[i] + dp[i+1][0][c]), (dp[i+1][1][c]) );
                    } else {

                        dp[i][b][c] =  Math.max( (prices[i] + dp[i+1][1][c-1]), (dp[i+1][0][c]) );
                    }

                }
            }
        }

        return dp[0][1][2];

    }

    // Tabulation with Space optimisation
    // TC: O(n*2*3)
    // SC: O(n*2*3)
    private int tabWithSpaceOptProfit(int []prices) {
        int next[][] = new int[2][3];

        // bottom cases: 
        // > 1. cap == 0
        // > 2. n == prices.length;

        // > 1
        for(int i=0; i<=prices.length; i++) {
            for(int b = 0; b<2; b++) {
                next[b][0] = 0;
            }
        }

        // > 2
        for(int b=0; b<2; b++) {
            for(int c = 0; c<3; c++) {
                next[b][c] = 0;
            }
        }

        // Now, comes the state variables

        for(int i = prices.length-1; i>=0; i--) {

            int curr[][] = new int[2][3];
            for(int b = 0; b<2; b++) {
                for(int c = 1; c<3; c++) {
                    if(b == 1) {

                        curr[b][c] =  Math.max( (-prices[i] + next[0][c]), (next[1][c]) );
                    } else {

                        curr[b][c] =  Math.max( (prices[i] + next[1][c-1]), (next[0][c]) );
                    }

                }
            }
            next = curr;
        }

        return next[1][2];
    }


// Main function     
    public int maxProfit(int[] prices) {

        // Appoach 1: using 3 state variables
        /*

        int cap = 2;

        // 0 ind, have the liberty to buy, cap =2
        //return memoProfit(0, 1, cap, prices);
        int n = prices.length;

        /*
        int dp[][][] = new int[n][2][3];
        for(int [][]twod: dp) {
            for(int []row: twod) {
                Arrays.fill(row, -1);
            }
        }*/

        /*
        int dp[][][] = new int[n+1][2][3];
        return tabProfit(cap,prices,dp); */

        // tabulation with space optimisation
        //return tabWithSpaceOptProfit(prices);

        // Approach 2: Using 2 state variables only

        // Memoization 
        /*
        int n = prices.length;
        int [][]dp = new int[n][4];
        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        return memoProfit2(0, 0, prices, dp);
        */

        // Tabulation
        //return tabProfit2(prices);

        // Tabulation with space optimisation
        return tabWithSpaceOptProfit2(prices);
    }



    // -------------Approach 2: Using 2 State variables only -----------------------

    /*
    // Optimising it to O(n*4) instead
    // Here, we can make atmost 2 transactions
    // 0 1 2 3
    // at indices 0,1 -> we can store the results of 1st transaction
    // 2,3 -> results of 2nd transactions

    // This way, we can avoid using buy state variable: 
    // if even then it means we can buy else sell

    // WE can use 2 state variables only, index = n and transaction no ( 0 to 4)
    // base case: if(n ==n || transaction == 4) return 0;
    // if transaction %2 == 0 buy cases
    // else sell
    // Using this approach, we can write memo, tab, tabwith space optimisation
    // In the end the TC: O(n*4)
    // SC: O(n*4)
    
     */

    // Memoization Technique: 
    // TC: O(n*4)
    // SC: O(n*4) + O(n)
    private int memoProfit2(int n, int op_no, int [] prices, int[][]dp) {

        // base cases: if cap == 0 || n == n

        if(op_no == 4) return 0;
        if(n == prices.length) return 0;

        // memoization
        if(dp[n][op_no]!=-1) return dp[n][op_no];

        if(op_no % 2 == 0){ // have liberty to buy a stack: as no transaction is going on
            // cap will remain same as the transaction is not finished yet.

            int buy_p = -prices[n] + memoProfit2(n+1,op_no+1, prices, dp);
            int not_buy = 0 + memoProfit2(n+1, op_no, prices, dp);

            return dp[n][op_no] = Math.max(buy_p, not_buy);
        
        } else {
            
            int sell = prices[n] + memoProfit2(n+1, op_no+1, prices, dp); // transaction is finished: cap--
            int not_sell =  0 + memoProfit2(n+1, op_no, prices, dp);

            return dp[n][op_no] = Math.max(sell, not_sell);
        }
    }

    // Tabulation code:
    // TC: O(n*5)
    // SC: O(n*5)

    private int tabProfit2(int []prices) {

        int n = prices.length;
        int dp[][] = new int [n+1][5];

        // bottom cases
        // 1. op_no = 4

        for(int i=0; i<=n; i++) {
            dp[i][4] =  0;
        }

        // 2. when ind = n
        for(int j = 0; j<=4; j++) {
            dp[n][j] = 0;
        }

        // Handling the state variables
        for(int i = n-1; i>=0; i--) {
            for(int j = 4; j>=0; j--)  {

                if(j % 2 == 0) { // have liberty to buy

                    int buy = 0;

                    if(j+1 <= 4) buy = - prices[i] + dp[i+1][j+1];
                    int not_buy = 0 + dp[i+1][j];

                    dp[i][j] = Math.max(buy, not_buy);
                
                } else {

                    int sell = 0;

                    if(j+1 <= 4) sell = prices[i] + dp[i+1][j+1];
                    int not_sell = 0 + dp[i+1][j];

                    dp[i][j] = Math.max(sell, not_sell);
                }
            }
        }

        return dp[0][0];
    }

    // Tabulation with space optimisation
    // TC:O(n*5)
    // SC: O(n*5*2)
    private int tabWithSpaceOptProfit2(int []prices) {

        int n = prices.length;
        int next[] = new int[5];

        // bottom cases
        // 1. op_no = 4

        for(int i=0; i<=n; i++) {
            next[4] =  0;
        }

        // 2. when ind = n
        for(int j = 0; j<=4; j++) {
            next[j] = 0;
        }

        // Handling the state variables
        for(int i = n-1; i>=0; i--) {
            int curr[] = new int[5];

            for(int j = 4; j>=0; j--)  {

                if(j % 2 == 0) { // have liberty to buy

                    int buy = 0;

                    if(j+1 <= 4) buy = - prices[i] + next[j+1];
                    int not_buy = 0 + next[j];

                    curr[j] = Math.max(buy, not_buy);
                
                } else {

                    int sell = 0;

                    if(j+1 <= 4) sell = prices[i] + next[j+1];
                    int not_sell = 0 + next[j];

                    curr[j] = Math.max(sell, not_sell);
                }
            }
            next = curr;
        }

        return next[0];
    }
    
}
