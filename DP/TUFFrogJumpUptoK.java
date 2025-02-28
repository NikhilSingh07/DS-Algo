import java.util.Arrays;
// https://www.naukri.com/code360/problems/minimal-cost_8180930?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
// Medium: Frog jump upto k strais with cost of arr[i] - arr[i-j] -> j: 1 to k

public class TUFFrogJumpUptoK {

    
    // Recustion: TC: O(k^n)
    // SC: O(n)

    // memoization: TC: O(n*k)
    // SC:O(n) +(n) 
    private static int memoCost(int n, int k, int [] height, int [] dp){
        //base case
        if(n == 0) return 0; // no further jumps are possible

        // retrieving the cached result. 
        if(dp[n]!=-1) {
            return dp[n];
        }

        int minCost = Integer.MAX_VALUE;

        for(int j=1; j<=k; j++) { //can make upto k jumps 

            if(n-j >=0) {
                int cost = memoCost(n-j, k, height, dp) + Math.abs(height[n-j] - height[n]);
                minCost = Math.min(minCost, cost);
            }

        }
        return dp[n] = minCost;
    }

    // Tabulation: Buttom up
    // TC: O(n*K)
    // SC: O(n) [dp]

    private static int tabCost(int n, int k, int [] height, int [] dp) {

        dp[0] = 0;

        for(int i=1; i<=n; i++) {
            
            int minCost = Integer.MAX_VALUE;

            for(int j=1; j<=k; j++) {
                if(i-j >=0) {
                    int cost = dp[i-j]+ Math.abs(height[i-j] - height[i]);
                    minCost = Math.min(minCost, cost);
                }
            }

            dp[i] = minCost;
        }

        return dp[n];
    }


    public static int minimizeCost(int n, int k, int []height){
        // Write your code here.
        int []dp = new int[n+1];
        Arrays.fill(dp,0);
        //return memoCost(n-1, k , height, dp);
        return tabCost(n-1, k, height, dp);
    }
    
}
