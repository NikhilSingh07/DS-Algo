public class LC494 {

// DP on Subsequences: TARGET SUM PROBLEM
//https://leetcode.com/problems/target-sum/

    
// 2 Methods: usual PICK | NOT PICK
//            Using Partition logic: Then PICK | NOT PICK: BETTER: Count subsets sum equals k = (totalSum - Target)/2    
//            https://www.naukri.com/code360/problems/count-subsets-with-sum-k_3952532?leftPanelTabValue=PROBLEM


// -------------------------------------------- METHOD 1: Usual PICK | NOT PICK Approach ---------------------------------------------
// PICK | NOT PICK LOGIC
// either pick + or pick - symbol 


    // Recursion: TC: O(2^n)
    // SC: O(n) stack space
    // Logic: go all the way till index 0 as we need to build an expression out of all elements.


    // Memoization: TC: O(n * 2sum)
    // SC: O(n) + O(n * 2sum)
    private int memoTargetWays(int n, int target, int [] nums, int [][]dp, int offset) {


        if (target + offset < 0 || target + offset >= dp[0].length) return 0; // out of bound fix

        // base case: reaching the 0th index
        if(n == 0) {

                if(target == 0 && nums[n] == 0) return 2; // 2 different expressions: one with +0, another with -0
                if( (target - nums[n] == 0) || (target + nums[n] == 0)) return 1;
                else return 0;
        }

        if(dp[n][target + offset] != -1) {
            return dp[n][target + offset];
        }

        int neg = memoTargetWays(n-1, target - nums[n], nums, dp, offset);
        int pos = memoTargetWays(n-1, target + nums[n], nums, dp, offset);

        return dp[n][target + offset] = neg + pos;    
    }


    // Tabulation  | Bottom up approach
    // TC: O(n*2sum)
    // SC: O(n*2sum)
    private int tabTargetWays(int n, int target, int []nums, int [][]dp, int offset) {

        // hanlding the bottom case first for n == 0
        // target could be in range of [sum, +sum]

        int sum  = offset;
        for(int t = -sum; t<=sum; t++) {
            dp[0][t+ offset] = 0;
        }

        /*
        If arr[0] == 0, then +0 and -0 both result in zero — so there are 2 ways.
        Otherwise, I can either add or subtract the first number, resulting in 1 way each.
        */
        if(nums[0] == 0) {
            dp[0][0 + offset] = 2; 
        } else {
            dp[0][-nums[0] + offset] = 1;
            dp[0][+nums[0] + offset] = 1;
        }

        /*
        -> For every index and every possible target from -sum to +sum, 
           I compute the number of ways to reach that target by either adding or subtracting the current element.

        -> I check the number of ways to reach the current target t by:
           1. subtracting arr[n] from the previous target
           2. adding arr[n] to the previous target

        -> After processing all elements, the final result is the number of ways to reach target using all numbers.   
        */

        for(int ind = 1; ind<=n; ind++) {
            for(int t = -sum; t<=sum; t++) {

                int neg = 0;
                int pos = 0;

                if( (t - nums[ind] + offset >=0) && (t - nums[ind] + offset <= 2*sum)) 
                neg =  dp[ind-1][t - nums[ind] + offset];

                if( (t + nums[ind] + offset >=0) && (t + nums[ind] + offset <= 2*sum)) 
                pos = dp[ind-1][t + nums[ind] + offset];

                dp[ind][t + offset] = neg +  pos;
            }
        }
        return dp[n][target + offset];
    }  


    // Tabulation with space optimsation  | Bottom up approach
    // TC: O(n*2sum)
    // SC: O(2sum)

    // Reducing 2D dp to 1D dp by toggling between 2 rows as we are only using dp[n-1]
    private int tabTargetSpaceOptmisedWays(int n, int target, int []nums, int offset) {
        int sum  = offset;
        int dp[] = new int[2*sum+1];

        for(int t = -sum; t<=sum; t++) {
            dp[t+ offset] = 0;
        }

        if(nums[0] == 0) {
            dp[0 + offset] = 2; 
        } else {
            dp[-nums[0] + offset] = 1;
            dp[+nums[0] + offset] = 1;
        }

        for(int ind = 1; ind<=n; ind++) {

            int temp[] = new int[2*sum+1];
            for(int t = -sum; t<=sum; t++) {

                int neg = 0;
                int pos = 0;

                if( (t - nums[ind] + offset >=0) && (t - nums[ind] + offset <= 2*sum)) 
                neg =  dp[t - nums[ind] + offset];

                if( (t + nums[ind] + offset >=0) && (t + nums[ind] + offset <= 2*sum)) 
                pos = dp[t + nums[ind] + offset];

                temp[t + offset] = neg +  pos;
            }
            dp = temp;
        }
        return dp[target + offset];
    }  

// main function
/*
    public int findTargetSumWays(int[] nums, int target) {
        
        int n = nums.length;
        int sum = 0;

        for(int i=0; i<n; i++) {
            sum += nums[i];
        }

        if(target < -sum || target > sum) return 0;

        // Max possible range of target is [-sum, +sum]
        // to represnt -ve indices in a 0 based array, using an offset sum, so ind 0 in dp[n][0] corresponds to target -sum
        int dp[][] = new int[n][2*sum + 1];

        for(int []row: dp) {
            Arrays.fill(row, 0);
        }

        //return memoTargetWays(n-1, target, nums, dp, sum);
        //return tabTargetWays(n-1, target, nums, dp, sum);
        return tabTargetSpaceOptmisedWays(n-1, target, nums, sum);
    }
*/
// ---------------------------------------------METHOD 2: PARTITION LOGIC: THEN USING PICK | NOT PICK CONCEPT - ----------------------------------
/* 

   Logic: In this problem, the array can be partitioned into 2 subsets
   // Subset 1: only containing + symbols: all numbers having + symbol
   // Subset 2: only containing - symbols: all numbers having - symbol

   S1 - S2 = Target -> equation 1

   S1 + S2  = TotalSum -> equation 2
   -> S1 = TotalSum - S2 -> eq 3

   putting eq3 in eq 1
   TS - S2 - S2 = Target
   2S2 = TS - Target
   S2 = (TS - Target)/2

   Suppose, K = S2
   problem becomes count subsets where sum is equals to K

   Edge cases: 1. (T <= TS) 
               2. (TS - Target) has to be even 
               else return 0;

*/


// Recursion
// TC: O(2^n)
// SC: O(n)


// Memo: TC: O(n*k)
// SC: O(n*k) + O(n)

// K  = (Totalsum - Target)/2;
    private static int memoSubsets2(int n, int t, int[] arr, int [][] dp){

        // base case
        if(n == 0) {

            // if t== 0 && arr[n] == 0 -> 2 ways to get the ans: either we pick or we don't pick
            if(t ==0 && arr[n] == 0) return 2;

            // if t===0  or last element is equal to target: only 1 way to get the ans
            else if(t ==0 || arr[n] == t) return 1;
            
            else return 0;
        }
        
        if(dp[n][t]!=-1) {
            return dp[n][t];
        }

        int not_pick = memoSubsets2(n-1, t, arr, dp);
        int pick = 0;
        if( t>= arr[n]) pick = memoSubsets2(n-1, t-arr[n], arr, dp);

        return dp[n][t] = not_pick + pick;
    }

    // Tabulation
    // TC: O(n*k)
    // SC: O(n*k)
    // K = (Totalsum - Target)/2;
    private static int tabSubsets(int n, int t, int []arr, int [][]dp) {

        // bottom case: when n = 0
        for(int tar=0; tar<=t; tar++) {
            dp[0][tar] = 0;
        }
        // when tar = 0 and arr[0] = 0
        if(arr[0] == 0) {
            dp[0][0]  =2;
        } else dp[0][0] = 1;

        // if tar =  arr[0]
        if(t >= arr[0] && arr[0]!=0) {
            dp[0][arr[0]] = 1;
        }

        /*
        In the for loop, ind starts from 1 as we have considered all tar(0 to t) for ind 0
        tar starts from 0, as in base case we have only considered for ind 0,
        remaining indices are still left to be considered
        for ex: if t = 0 for ind>=1? we will miss out on these cases
        
        */

        // handling the changing variables
        for(int ind = 1; ind<=n; ind++) {
            for(int tar = 0; tar<=t; tar++) {

                int not_pick = dp[ind-1][tar];
                int pick = 0;
                if(tar >= arr[ind]) {
                    pick = dp[ind-1][tar-arr[ind]];
                }

                dp[ind][tar] = pick+ not_pick;
            }
        }

        return dp[n][t];
    }

    
    // Tabulation with space optimisation
    // TC: O(n*k)
    // SC: O(k)

    // K = (Totalsum - Target)/2;
    private static int optimisedSubsets(int n, int t, int[] arr) {

        int [] prev = new int[t+1];

        // bottom case: when n = 0
        for(int tar=0; tar<=t; tar++) {
            prev[tar] = 0;
        }
        // when tar = 0 and arr[0] = 0
        if(arr[0] == 0) {
            prev[0]  =2;
        } else prev[0] = 1;

        // if tar =  arr[0]
        if(t >= arr[0] && arr[0]!=0) {
            prev[arr[0]] = 1;
        }

        /*
        In the for loop, ind starts from 1 as we have considered all tar(0 to t) for ind 0
        tar starts from 0, as in base case we have only considered for ind 0,
        remaining indices are still left to be considered
        for ex: if t = 0 for ind>=1? we will miss out on these cases
        
        */

        // handling the changing variables
        for(int ind = 1; ind<=n; ind++) {
            int temp[] = new int[t+1];
            for(int tar = 0; tar<=t; tar++) {

                int not_pick = prev[tar];
                int pick = 0;
                if(tar >= arr[ind]) {
                    pick = prev[tar-arr[ind]];
                }

                temp[tar] = pick+ not_pick;
            }
            prev = temp;
        }

        return prev[t];
    }

    public int findTargetSumWays(int[] nums, int target) {

        int n = nums.length;
        int totalSum = 0;

        for(int i=0; i<nums.length; i++) {

            totalSum += nums[i];
        }

        if( (target > totalSum) || (totalSum - target) %2 != 0) return 0;

        int k =  (totalSum - target)/2;


        int [][]dp = new int[n][k+1];

        for(int []row: dp) Arrays.fill(row, -1);

        //return memoSubsets2(n-1, k, nums, dp);
        return optimisedSubsets(n-1, k, nums);

    }
    
}
