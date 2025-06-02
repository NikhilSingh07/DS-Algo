// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
// DP on stocks
// Best time to buy and sell stock 4
// same as part 3\



public class LC188 {

        // Tabulation with Space optimisation
    // TC: O(n*2*k)
    // SC: O(2*k)
    private int tabWithSpaceOptProfit(int k, int []prices) {
        int next[][] = new int[2][k+1];

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
            for(int c = 0; c<=k; c++) {
                next[b][c] = 0;
            }
        }

        // Now, comes the state variables

        for(int i = prices.length-1; i>=0; i--) {

            int curr[][] = new int[2][k+1];
            for(int b = 0; b<2; b++) {
                for(int c = 1; c<=k; c++) {
                    if(b == 1) {

                        curr[b][c] =  Math.max( (-prices[i] + next[0][c]), (next[1][c]) );
                    } else {

                        curr[b][c] =  Math.max( (prices[i] + next[1][c-1]), (next[0][c]) );
                    }

                }
            }
            next = curr;
        }
        return next[1][k];
    }

    public int maxProfit(int k, int[] prices) {
        
        return tabWithSpaceOptProfit(k, prices);
    }
    
}
