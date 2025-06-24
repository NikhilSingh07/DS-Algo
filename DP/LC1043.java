// https://leetcode.com/problems/partition-array-for-maximum-sum/description/
// Partition Array for Maximum Sum

// DP with Front Partition

public class LC1043 {

        // Recursion: TC: Exponential
    // SC: O(n)

    // Memoization: TC: O(n) * O(k)
    // SC: O(n) + O(n)

    // Try out all possible paritions of size 3 max
    private int memoFunc(int i, int k, int[] arr, int []dp) {
        
        //base case: i becomes n
        if(i == arr.length) return 0;

        if(dp[i] !=-1) return dp[i];

        int maxi = - (int) 1e9;
        int maxVal = 0; 
        // Try out all possible partitions
        for(int j=i; j < Math.min(arr.length, i+k); j++) {

            maxVal = Math.max(maxVal, arr[j]); // max val in the given partitioned subarray

            int sum = (j-i+1) * maxVal + memoFunc(j+1, k, arr, dp);
            maxi = Math.max(maxi, sum);
        }

        return dp[i] =  maxi;
    }

    // Tabulation | Bottom-up approach
    // TC: O(n) *O(k)
    // SC: O(n)
    
    private int tabFunc(int []arr, int k) {
                
        int n = arr.length;
        int []dp = new int[n+1];

        for(int i = n-1; i>=0; i--) {

            int maxi = - (int) 1e9;
            int maxVal = 0; 
            // Try out all possible partitions
            for(int j=i; j < Math.min(arr.length, i+k); j++) {

                maxVal = Math.max(maxVal, arr[j]); // max val in the given partitioned subarray

                int sum = (j-i+1) * maxVal + dp[j+1];
                maxi = Math.max(maxi, sum);
            }

            dp[i] =  maxi;
        }
        return dp[0];
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        /*
        int n = arr.length;
        int []dp = new int[n];
        Arrays.fill(dp, -1);

        return memoFunc(0, k, arr, dp);
        */

        return tabFunc(arr, k);
    }
    
}
