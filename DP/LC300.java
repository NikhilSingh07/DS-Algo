import java.util.Arrays;

public class LC300 {
    
/* 
Approach 1: Most Naive: Compute and save all the subsequences using recursion
                              > check each subsequence for Increasing order
                              > return the length of the longest Increasing Subsequence

                              TC: O(2^n) + O(2^n) * k, where k is the avg len of each subsequence
                              SC: O(2^n) + O(n)


Approach 2: Recursion: Try out all the subsequences based on the given condition.

                            > state variables: ind, prev_ind
                            > func (ind, prev_ind)

                            > 2 choices: pick | not pick
                            > only pick if curr elemt > prev elemt
                            > return the max len

                            > base case: ind == n return 0;


Approach 3: Binary Search way on a temp array
TC: O(nlogn)
SC: O(n)

Binary Search approach:
> Logic: Iterate through the array and store the valid subsequnce in other lists
          > return the length of the longest list

input array: [10 | 9 | 2 | 5 | 3 | 7 | 101 | 18]

ans lists: > [10, 101]
               [9, 101]
               [2, 5, 7, 101] -> Valid longest subsequence
               [3, 7, 101]
               [18]

Since, we only need the length of the LIS. I can use 1 array and replace the values in the array instead
        > if condition is invalid: i can rather insert this value at a valid position using Binary seach

input array: [10 | 9 | 2 | 5 | 3 | 7 | 101 | 18]
add 10 -> replace 9<->10 -> replace 2<->9 ->add 5 -> replace 3<->5 ->add 7 -> add 101 -> replace 18<->101

temp array [2, 3, 7, 18]
len = 4 (final ans for length)




Approach 4: Tabulation with a single 1D array
           > at each index, store the length of LIS ending at that index

 */


class Solution {

    // Recurison: 
    // > TC: O(2^n)
    // > SC: O(n)

    // Memoization
    // > TC: O(n*n)
    // > SC: O(n*n) + O(n)

    private int memoLIS(int ind, int prev_ind, int[] nums, int[][]dp, int offset){

        // base case: 
        if(ind == nums.length) return 0;

        // memo
        if(dp[ind][prev_ind + offset] !=-1) return dp[ind][prev_ind + offset];

        // 2 choices: pick or not pick
        // not pick
        int not_pick = 0 + memoLIS(ind+1, prev_ind, nums, dp, offset);

        // pick
        int pick = 0;

        if(prev_ind == -1 || nums[ind] > nums[prev_ind]) {
            pick = 1 + memoLIS(ind+1, ind, nums, dp, offset);
        }

        return dp[ind][prev_ind + offset] = Math.max(not_pick, pick);

    }


    // Tabulation
    // > TC: O(n*n)
    // > SC: O(n*n)
    private int tabLIS(int[] nums, int [][]dp, int offset) {

        int n = nums.length;
        // bottom case
        for(int j = -1; j<nums.length; j++) {
            dp[n][j+offset] = 0;
        }

        // handling the state variables

        for(int i=n-1; i>=0; i--) {
            for(int pi = i-1; pi>=-1; pi--) {

                int not_pick = 0 + dp[i+1][pi+offset];

                int pick = 0;

                if(pi == -1 || nums[i] > nums[pi]) {
                    pick = 1 + dp[i+1][i+offset];
                }
                dp[i][pi+offset] = Math.max(not_pick, pick);
            }
        }

        return dp[0][-1+offset];

    }


    // Tabulation with space optimistion
    // > TC: O(n*n)
    // > SC: O(n)*2
    private int tabWithSpaceOptLIS(int[] nums, int offset) {


        int n = nums.length;
        int [] ahead =  new int[n+1];

        // bottom case
        for(int j = -1; j<nums.length; j++) {
            ahead[j+offset] = 0;
        }

        // handling the state variables
        for(int i=n-1; i>=0; i--) {
            int []curr = new int[n+1];
            for(int pi = i-1; pi>=-1; pi--) {

                int not_pick = 0 + ahead[pi+offset];

                int pick = 0;

                if(pi == -1 || nums[i] > nums[pi]) {
                    pick = 1 + ahead[i+offset];
                }
                curr[pi+offset] = Math.max(not_pick, pick);
            }
            ahead = curr;
        }

        return ahead[-1+offset];

    }

    // 1 , 3, 7, 8 ,10
    // s      e
    private int lowerBound(int []temp, int indEnd, int a) {

        int s = 0;
        int e = indEnd;
        int ans = e;
        
        while(s < e) {

            int m = (s + e)/2;

            if(temp[m] >= a) {
                ans = m;
                e = m;
            } else {
                s = m + 1;
            }
        }
        return ans;
    }
    // TC: O(nlogn)
    // SC: O(n)

    private int BinarySearchLIS(int [] nums) {

        int n = nums.length;
        int []temp = new int[n];
        int ind = 0;
        temp[ind++] = nums[0];
        for(int i=1; i<n; i++) {

            if(nums[i] > temp[ind-1]) {
                temp[ind++] = nums[i];
            
            } else { 
                // perform BS and replace it with the Upper bound

                int pos = lowerBound(temp, ind, nums[i]);
                temp[pos] = nums[i];
            }
        }

        return ind;
    }

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int offset = 1;
        
        // Memoization
/*
        int [][]dp = new int[n][n + offset];
        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        return memoLIS(0, -1, nums, dp, offset);   
*/

        // Tabulation
        //int [][] dp = new int[n+1][n+offset];
        //return tabLIS(nums, dp, offset);

        // Tabulation with Space Opt
        //return tabWithSpaceOptLIS(nums, offset);

        // return BinarySearchLIS(nums);

        // Tabulation with space optimisation
        // SC: O(n)
        
        int []dp = new int[n];
        int max =0;
        Arrays.fill(dp, 1);

        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {

                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
    }
}
