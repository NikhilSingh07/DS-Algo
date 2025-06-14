import java.util.Arrays;
// https://leetcode.com/problems/number-of-longest-increasing-subsequence/
// Number of Longest Increasing Subsequence

// DP on LIS: LIS pattern + count array logic

public record LC673() {


    // Using the LIS logic 
    // using a count array to store the the count of subsequences ending at index i
    // compute the total no. of LIS by adding the count of indces where dp[i] = maxLen or LIS

    //   [1 | 3 | 5 | 4 | 7]

    //dp [1 | 2 | 3 | 3 | 4]

    //cnt[1 | 1 | 1 | 1 | 2]

    /**
    
       for ex: at index 2, the number of ways to get an LIS of len 3 is 1 only
       finally, at index 4, the number of ways to get an LIS of len 4 is 2

       In the end, we can traverse the array and see the locations where count is of length 4 (from dp array) and add those values in ans
     */

    // TC: O(n^2)
    // SC: O(2n)
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int []dp = new int[n];
        int []count = new int[n];
        int maxLen = 0;
        int total = 0;

        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for(int i=0; i<n; i++) {
            for(int prev = 0; prev<i; prev++) {

                if(nums[i] > nums[prev] && dp[i] < dp[prev] + 1) {

                    dp[i] = dp[prev] +1;
                    count[i] = count[prev]; // no. of ways to get LIS at this index reamins same 
                } 

                else if(nums[i] > nums[prev] && dp[i] == dp[prev] + 1) { // anotehr way to get the same len LIS
                    count[i]  += count[prev] ;
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        // computing the total count of LI subsequence
        for(int i=0; i<n; i++) {

            if(dp[i] == maxLen) {
                total += count[i];
            }
        }

        return total;
    }
    
}
