public class LC70 {

    //https://leetcode.com/problems/climbing-stairs/description/
    // DP on 1D array - 4 approaches: from less efficient to most efficient
    //Memo->Tab->Tab+space optimisation

    // Recursion: TLE
    // TC: O(2^n)
    // SC: O(n) Auxiliary stack space


    // Memoization: TC: O(n)
    // SC: (n) + (n) [dp array]
    public int memoStairs(int n, int []dp) {

        //base case 
        if(n == 0) {
            return 1;
        } else if(n == 1 ) {
            return 1;
        }

        if(dp[n]!=-1) {
            return dp[n];
        }
        // climbing the stais
        int step1 = memoStairs(n-1, dp);
        int step2 = memoStairs(n-2, dp);

        // sum of all the steps
        // caching the sum
        return dp[n] = step1 + step2;
    }

    // Tabulation: TC: O(n)
    // SC: (n) [dp array]
    public int tabuStairs(int n, int []dp) {
        
       // 1. converting the base case
            dp[0] = 1; // 1 way to be at the ground step; by not moving at 0;
            dp[1] = 1;

        // 2. handling the changing varaiabes - n
        for(int i=2; i<=n; i++) {

                // 3. Copy recurrence
                int step1 = dp[i-1];
                int step2 = dp[i-2];
                
                // sum of all the steps
                // caching the sum
                dp[i] = step1 + step2;
        }
        return dp[n];
    }

    // TC: Tabulation with space optimsation: O(n)
    // SC: O(1)
    public int tabuOptimsation(int n) {

            int prev2 = 1; 
            int prev = 1;

        // 2. handling the changing varaiabes - n
        for(int i=2; i<=n; i++) {

                int curr = prev+prev2;
                prev2 = prev;
                prev = curr;
        }
        return prev;
    }

    public int climbStairs(int n) {   // calculate and return the total number of ways.

        //int[] dp = new int[n+1];
        //Arrays.fill(dp, -1);
       //return memoStairs(n, dp);
       //Arrays.fill(dp, 0);
       //return tabuStairs(n, dp);

       return tabuOptimsation(n);


    }
    
}
