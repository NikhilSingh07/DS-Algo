public class LC1671 {

// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
// Minimum Number of Removals to Make Mountain Array

// DP ON LIS: LIS PATTERN
    
    // > The probem is similar to Finding the longest Bitonic subsequence, where all the elements on the left is smaller and all elemts
    // on the right is greater

    // DP ON LIS PATTERN 
    // > compute the len of the bitonic subsequence and finally subtract it with the len of the array

    // TC: O(2n^2)
    // SC: O(2n)

    public int minimumMountainRemovals(int[] nums) {

        if(nums.length  < 3) return 0;
        
        int n = nums.length;
        int []dp1 = new int[n];
        int []dp2 = new int[n];
        int maxLen = 0;

        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        // Using tabulation method of LIS pattern
        // > computing LIS     

        for(int i=0; i<n; i++) {
            for(int prev = 0; prev <i; prev++) {

                if(nums[i] > nums[prev] && dp1[i] < dp1[prev] + 1) {
                    dp1[i] = dp1[prev] + 1;
                }
            }
        }

        // computing LIS from the back
        for(int i=n-1; i>=0; i--) {
            for(int prev = n-1; prev > i; prev --) {

                if(nums[i] >  nums[prev] && dp2[i] < dp2[prev]  + 1 ){
                    dp2[i] = dp2[prev] + 1;
                }
            }

            // there has to be atleast 1 element in dp1 before i to be smaller
            // and atlesat 1 element in dp2 after i to be smaller to be considered a peak

            if(dp1[i] > 1 && dp2[i] > 1)
            maxLen = Math.max(maxLen, dp1[i] + dp2[i] - 1);
        }

        // computing the totoal len of the longest Bitonic Subsequnce
        /*
        for(int i=0; i<n; i++) {
            if(dp1[i] > 1 && dp2[i] > 1)
            maxLen = Math.max(maxLen, dp1[i] + dp2[i] - 1);
        }*/

        return n- maxLen;
    }
    
}
