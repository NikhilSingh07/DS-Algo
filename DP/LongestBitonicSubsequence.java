import java.util.Arrays;
//https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
// GFG
// DP ON LIS

// LIS PATTERN: 
// MOST OPTIMAL WAY is to compute LIS from from using dp array from LIS pattern logic
//                  > compute LIS from the back usng another dp array
//                  > compute the max len using both the arrays


public class LongestBitonicSubsequence {



        // > compute the LIS ending at the current index
    // > compute the LDS starting from the current index
    
    // here, prev is on the left side alwasus
    private static int LISBitonic(int ind, int prev, int endingIndex, int[] nums, int[][]dp){ //return the LIS ending at current index
        
        if(ind > endingIndex) {
            
            return 0;
        }
        
        if(dp[ind][prev+1] !=-1) return dp[ind][prev+1];
        // 2 choices: pick | not pick
        int np = 0 + LISBitonic(ind+1, prev, endingIndex, nums, dp);
        
        int p = 0;
        if(prev == -1 || nums[ind] > nums[prev]) {
            p  = 1 + LISBitonic(ind+1, ind, endingIndex, nums, dp);
        }
        
        return dp[ind][prev+1] = Math.max(np, p);
    }
    
    
    // returns the len of LDS starting from the ind
    private static int LDSBitonic(int ind, int prev, int nums[], int[][]dp) {
        
        if(ind == nums.length) return 0;
        
        if(dp[ind][prev+1]!=-1) return dp[ind][prev+1];
        
        int np = 0 + LDSBitonic(ind+1, prev, nums, dp);
        
        int p=0;
        if(prev == -1 || nums[ind] < nums[prev]) {
            p = 1 + LDSBitonic(ind+1, ind, nums, dp);
        }
        
        return dp[ind][prev+1] = Math.max(np, p);
        
    }
    
    // TC: O(n^2)
    // SC: O(n*n)
    
    private static int LISBitonicTab(int end, int nums[]){
        int n = end + 1;
        int offset = 1;
        int dp[][] = new int[n + 1][n + 1];
    
        // base case: when ind == n (i.e., out of bounds)
        for (int j = -1; j < n; j++) {
            dp[n][j + offset] = 0;
        }
    
        for (int i = n - 1; i >= 0; i--) {
            for (int pi = i - 1; pi >= -1; pi--) {
                int not_pick = dp[i + 1][pi + offset];
                int pick = 0;
                if (pi == -1 || nums[i] > nums[pi]) {
                    pick = 1 + dp[i + 1][i + offset];
                }
                dp[i][pi + offset] = Math.max(not_pick, pick);
            }
        }
    
        return dp[0][offset - 1]; // prev = -1
        
    }
    
    // ind is the starting index
    private static int LDSBitonicTab(int s, int nums[]){
        
          
        int n = nums.length;
        int len = n - s;
        int offset = 1;
        int dp[][] = new int[len + 1][n + 1]; // size based on s to n-1
    
        // base case
        for (int j = -1; j < n; j++) {
            dp[len][j + offset] = 0;
        }
    
        for (int i = n - 1; i >= s; i--) {
            for (int pi = n - 1; pi >=  -1; pi--) {
                int not_pick = dp[i - s + 1][pi + offset];
                int pick = 0;
                if (pi == -1 || nums[i] < nums[pi]) { // fix: LDS condition
                    pick = 1 + dp[i - s + 1][i + offset];
                }
                dp[i - s][pi + offset] = Math.max(not_pick, pick);
            }
        }
    
        return dp[0][offset - 1];
        
    }
    
    private static int LBitSeq(int n, int[] nums){
        
        int maxLen = 0;
        
        for(int i=0; i<n; i++) {
            
            int LIS = LISBitonicTab(i, nums);// ending index and nums array
            int LDS = LDSBitonicTab(i, nums); // starting index and nums array
        
            if(LIS == 1 || LDS == 1) {
                LIS = 0;
                LDS = 0;
            }
            
            maxLen = Math.max(maxLen, LIS+LDS-1);

        }
        
        return maxLen;
    }
    
    
    public static int LongestBitonicSequence(int n, int[] nums) {
        // code here
        /*
        
        int maxLen = 0;
        int [][]dp1 = new int[n][n+1];
        int [][]dp2 = new int[n][n+1];
        
        for(int[]row: dp1) {Arrays.fill(row, -1);}
        for(int []row: dp2) {Arrays.fill(row, -1);}
        

        for(int i=0; i<n; i++) {
            
            int LIS = LISBitonic(0, -1, i, nums, dp1);// starting index is always 0
            int LDS = LDSBitonic(i, -1, nums, dp2);
            
            if(LIS == 1 || LDS == 1) {
                LIS = 0;
                LDS = 0;
            }

            maxLen = Math.max(maxLen, LIS+LDS-1);
        }
        
        return maxLen;
        
*/
        //return LBitSeq(n,  nums);
        
        
        //USING THE LIS PATTERN LOGIC
        // TC: O(n^2) + O(n^2)
        // SC: O(2n)
        int longestBitonic = 0;
        
        int dp1[] = new int[n];
        int dp2[] = new int[n];
        int maxDp1 = 0;
        int maxDp2 = 0;
        
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        
        // computing LIS from the front
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                
                if(nums[i] > nums[j] && dp1[j]+1 > dp1[i]) {
                    
                    dp1[i] = dp1[j] +1;
                }
            }
        }
        
        // compute the LIS from the end
        for(int i= n-1; i>=0; i--) {
            for(int j =n-1; j>i; j--) {
                
                if(nums[i] > nums[j] && dp2[j]+1 > dp2[i]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        } 
        
        
        // computing the max len using both the dp arrays
        // Only count positions that are true bitonic peaks
        for(int i = 0; i < n; i++) {
            if(dp1[i] > 1 && dp2[i] > 1) {
                int len = dp1[i] + dp2[i] - 1;
                longestBitonic = Math.max(longestBitonic, len);
            }
        }
        
        return longestBitonic;
        
        
    }
    
}
