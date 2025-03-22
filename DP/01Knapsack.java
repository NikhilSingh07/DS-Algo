
//https://www.naukri.com/code360/problems/0-1-knapsack_920542?leftPanelTabValue=PROBLEM
// DP: subsequences: pick | not pick
// 4 methods: Recursion + MEMO + TAB + Space optmisation

public class 01Knapsack {

    
    // Recursion: TC: O(2^n)
    // SC: O(n)


    /// Memo: TC: O(n*weight)
    // SC: O(n*weight) + O(n)
    private static int memoKnapack(int n, int wt, int [] weight, int[] value, int[][]dp){ 

        // base case: when n=0
        if(n ==0 ){

            if(weight[n] <= wt) return value[n];
            else return 0;
        }

        if(dp[n][wt]!=-1) {
            return dp[n][wt];
        }

        int not_pick = 0 + memoKnapack(n-1, wt, weight, value, dp);
        int pick = Integer.MIN_VALUE;

        if(weight[n] <= wt) pick = value[n] + memoKnapack(n-1, wt - weight[n], weight, value, dp);

        return dp[n][wt] = Math.max(pick, not_pick);
    }

    // TC: O(n*weight)
    // SC: O(n*weight)
    private static int tabKnapack(int n, int wt, int [] weight, int[] value, int[][]dp) {

        // bottom case, when n=0
        // for every w >= weight[0]: i can steal it
        for(int w = weight[0]; w<=wt; w++) {
            dp[0][w] = value[0];
        }

        // handling the changing variables
        for(int ind = 1; ind<=n; ind++) {
            for(int w = 0; w<=wt; w++) {

                int not_pick = 0 + dp[ind-1][w];
                int pick =  Integer.MIN_VALUE;

                if(weight[ind] <= w) pick = value[ind] + dp[ind-1][w-weight[ind]];
                dp[ind][w] = Math.max(not_pick, pick);
            }
        }

        return dp[n][wt];

    }

    // TC: O(n*weight)
    // SC: O(wt)

    private static int optimisedKnapack(int n, int wt, int [] weight, int[] value) {

        int [] prev = new int[wt+1];

        // bottom case, when n=0
        // for every w >= weight[0]: i can steal it
        for(int w = weight[0]; w<=wt; w++) {
            prev[w] = value[0];
        }

        // handling the changing variables
        for(int ind = 1; ind<=n; ind++) {

            int [] temp = new int[wt+1];
            for(int w = 0; w<=wt; w++) {

                int not_pick = 0 + prev[w];
                int pick =  Integer.MIN_VALUE;

                if(weight[ind] <= w) pick = value[ind] +prev[w-weight[ind]];
                temp[w] = Math.max(not_pick, pick);
            }
            prev = temp;
        }

        return prev[wt];

    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int dp[][] = new int[n][maxWeight+1];
       /* for(int [] row: dp) {
            Arrays.fill(row, 0);
        }*/
        //return memoKnapack(n-1, maxWeight, weight, value, dp);
       // return tabKnapack(n-1, maxWeight, weight, value, dp);
       return optimisedKnapack(n-1,maxWeight, weight, value);
    }
    
}
