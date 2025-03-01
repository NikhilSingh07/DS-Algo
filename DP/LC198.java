// https://leetcode.com/problems/house-robber/description/
// DP: 1D | subsequences
// 4 Methods- > 1. Recursion
            //  2. Memo, 3. Tab, 4. Tab with space optimisation 
public class LC198 {
    
    
    // 1 . Recursion: pick or not pick
    // TC: O(2^n)
    // SC: O(n) {aux stack space}

    // 2. Memoization 
    // TC: O(n)
    // SC: O(n) + O(n) [dp array]
    private int memoRob(int n, int [] nums, int [] dp){  

        if(n == 0) return nums[n]; // if n is 2 and called 0
        if(n<0) return 0;   // n is 1 and called for -1
        
        if(dp[n]!=-1) {
            return dp[n];
        }

        int pick = nums[n] + memoRob(n-2, nums, dp);
        int not_pick = 0 + memoRob(n-1, nums, dp);

        return dp[n] = Math.max(pick, not_pick);
    }

    // 2. Tabulation: Bottom up approach
    // TC: O(n)
    // SC: (n)
    private int tabRob(int n, int [] nums, int [] dp){  

        dp[0] = nums[0];

        for(int i=1; i<=n; i++) {

            int pick = nums[i];
            if(i>1) pick += dp[i-2];

            int not_pick = 0 + dp[i-1];

            dp[i] = Math.max(pick, not_pick);
        }
        return dp[n];
    }

    // 1  | 2 | 3 | 1
    //      ^
    // p1
//  p2
    
    // Tabulation with space optimisation
    // TC: O(n)
    // SC:(1)
    private int spaceOptimisedRob(int n, int [] nums){  

        int prev = nums[0];
        int prev2 = 0;

        for(int i=1; i<=n; i++) {

            int pick = nums[i];
            if(i>1) pick += prev2; // n-2

            int not_pick = 0 + prev; // n-1

            int curr = Math.max(pick, not_pick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        //int []dp = new int[n];
        //Arrays.fill(dp, 0);

        //return memoRob(n-1, nums, dp);
        //return tabRob(n-1, nums, dp);
        return spaceOptimisedRob(n-1, nums);
    }
}
