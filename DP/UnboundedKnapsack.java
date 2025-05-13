// Unbounded Knapsack: 1 item can be picked multiple times
// Same as coin change problem 2
// pick | not pick concept


// https://www.naukri.com/code360/problems/unbounded-knapsack_1215029

import java.util.Arrays;

public class UnboundedKnapsack {

    
// 1. Recursion: TC: O(2^n)
//               SC: O(n)
// TLE

// 2. Memoization: TC: O(n * w)
//                 SC: O(n) + O(n*w)

    private static int memoKnapsack(int n, int w, int[]profit, int[] weight, int[][]dp) {

        // base case: when w ==0 | n ==0

        if(w == 0) return 0;

        if(n == 0){

            if(w >= weight[0]) return (w/weight[0])*profit[0];
            else return 0;
        }

        if(dp[n][w]!=-1) return dp[n][w];

        // pick | not pick
        int np = 0 + memoKnapsack(n-1, w, profit, weight, dp);
        int p = 0 ;

        if(w >= weight[n]) p = profit[n] + memoKnapsack(n, w - weight[n], profit, weight, dp);

        return dp[n][w] = Math.max(np, p);

    }

// Tabulation | Bottom up approach
// TC: O(n*w)
// SC: O(n*w)
    private static int tabKnapsack(int n, int w, int []profit, int[] weight, int [][]dp) {

        // bottom case: if w ==0 or n==0

        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int wt = weight[0]; wt<=w; wt++) {

            dp[0][wt] = (wt/weight[0])*profit[0];
        }

        for(int ind = 1; ind <= n; ind++) {

            for(int wt = 0; wt <= w; wt++) {

                int np =0;
                int p =0;

                np = 0 + dp[ind-1][wt];
                if(wt >= weight[ind]) p = profit[ind] + dp[ind][wt - weight[ind]];

                dp[ind][wt] = Math.max(np, p);
            }
        } 

        return dp[n][w];
    }

// Tabulation with space optimisation 
// TC: O(n*w)
// SC: O(w)

    private static int TabSpaceOptimisedKnapsack(int n, int w, int[]profit, int []weight) {

        int [] dp = new int[w+1];

        // bottom case: if w ==0 or n==0

        dp[0] = 0;
        
        for(int wt = weight[0]; wt<=w; wt++) {

            dp[wt] = (wt/weight[0])*profit[0];
        }

        for(int ind = 1; ind <weight.length; ind++) {
            
            int [] temp = new int[w+1];

            for(int wt = 0; wt <= w; wt++) {

                int np =0;
                int p =0;

                np = 0 + dp[wt];
                if(wt >= weight[ind]) p = profit[ind] + temp[wt - weight[ind]];

                temp[wt] = Math.max(np, p);
            }
            dp = temp;
        } 

        return dp[w];
    }


    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.

        int [][]dp = new int[n][w + 1];
        for(int []row: dp) Arrays.fill(row, 0);

        //return memoKnapsack(n-1, w, profit, weight, dp);
        //return tabKnapsack(n-1, w, profit, weight, dp);

        return TabSpaceOptimisedKnapsack(n-1, w, profit, weight);
    }
    
}
