public class GFGFrogJumpClimbStairs {

    // GFG: https://www.geeksforgeeks.org/problems/geek-jump/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
        
    // Recursion: TC: O(2^n)
    // SC: O(n)
    
    // Memoization:  TC: O(n)
    // SC: O(n) + O(n) [aux recursion stack space]
    private int memoFrog(int n, int []arr, int[] dp) {
        
        // base case: if at 0th index we can't make any further jump
        if(n ==0 )return 0;
        
        if(dp[n] != -1) {
            return dp[n];
        }
        
        int left = memoFrog(n-1, arr, dp) + Math.abs(arr[n-1] - arr[n]);
        int right = Integer.MAX_VALUE;
        if(n > 1) {
            
            right = memoFrog(n-2, arr, dp) + Math.abs(arr[n-2] - arr[n]); 
        }
        
        return dp[n] = Math.min(left, right);

    }
    
    // TC: O(n)
    // SC: O(n)
    private int tabFrog(int n, int []arr, int[] dp){
        
        dp[0] = 0;
        
        for(int i=1; i<=n; i++) {
            
            int left = dp[i-1] + Math.abs(arr[i-1] - arr[i]);
            int right = Integer.MAX_VALUE;
            if(i > 1) {
                
                right = dp[i-2] + Math.abs(arr[i-2] - arr[i]); 
            }
            dp[i] = Math.min(left, right);
        }
        
        return dp[n];
    }
    
    // TC: O(n)
    // SC: (1)
    private int optimisedFrog(int n, int []arr){
        
        int prev = 0;
        int prev2 = 0;
        
        for(int i=1; i<=n; i++) {
            
            int left = prev + Math.abs(arr[i-1] - arr[i]);
            int right = Integer.MAX_VALUE;
            if(i > 1) {
                
                right = prev2 + Math.abs(arr[i-2] - arr[i]); 
            }
            int curr = Math.min(left, right);
            
            prev2 = prev;
            prev = curr;
        }
        
        return prev;
    }
    
    
    int minCost(int[] height) {
        // code here
        int n = height.length;
        int []dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = -1;
        }
        //return memoFrog(n-1, height, dp);
        //return tabFrog(n-1, height, dp);
        return optimisedFrog(n-1, height);
    }
    
}
