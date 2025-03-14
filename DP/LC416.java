public class LC416 {

        // Problem is same as subset sum equals K.
    // GFG: https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    // TUF: https://www.naukri.com/code360/problems/subset-sum-equal-to-k_1550954?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

    // if the total sum is odd: we can't partition in 2 equal subsets
    // if even, we can check if a subset with sum equals S/2 exists in the array.


// Recursion: TC: O(2^n)
// SC: O(n)

// Memo: TC: O(n*target)
// SC: O(n*target) + O(n)

private static boolean memoSubset(int n, int t, int []arr, int [][]dp){

    // base cases: if target becomes 0 or if we reach the last element and its equal to rem t
    if(t == 0) return true;
    if(n == 0) {return arr[n] == t;}

    if(dp[n][t]!=-1) return dp[n][t]==1;

    boolean not_pick = memoSubset(n-1, t, arr, dp);
    boolean pick = false;
    if(t >= arr[n]) pick = memoSubset(n-1, t-arr[n], arr, dp);

    dp[n][t] = (not_pick || pick)?1:0;
    return dp[n][t] == 1;

}

// Tabulation: TC: O(n*target)
// SC: O(n*target)
private static boolean tabSubset(int n, int t, int []arr, boolean [][]dp){

    // bottom case: when target is 0
    for(int i=0; i<=n;i++) {
        dp[i][0] = true;
    }

    // when ind == 0  and target is equal to arr[0]
    if(t>= arr[0]) dp[0][arr[0]] = true;

    // handling the state variables
    // already covered case for when ind = 0
    // alreadt covererd case when target =0
    for(int ind = 1; ind<=n; ind++) {
        for(int target = 1; target<=t; target++) {

            boolean not_pick = dp[ind-1][target];
            boolean pick = false;
            if(target >= arr[ind]) pick = dp[ind-1] [target-arr[ind]];

            dp[ind][target] = not_pick || pick;
        }
    }

    return dp[n][t];
    
}

// TC: O(n*target)
// SC: O(target)

private static boolean optimisedSubset(int n, int t, int []arr){

    boolean [] prev = new boolean [t+1];
    // bottom case: when target is 0
    prev[0] = true;

    // when ind == 0  and target is equal to arr[0]
    if(t>= arr[0]) prev[arr[0]] = true;

    // handling the state variables
    // already covered case for when ind = 0
    // alreadt covererd case when target =0
    for(int ind = 1; ind<=n; ind++) {
        boolean [] curr = new boolean [t+1];
        curr[0] = true;
        for(int target = 1; target<=t; target++) {

            boolean not_pick = prev[target];
            boolean pick = false;
            if(target >= arr[ind]) pick = prev[target-arr[ind]];

            curr[target] = not_pick || pick;
        }
        prev = curr;
    }

    return prev[t];
    
}


public boolean canPartition(int[] nums) {
    
    // if total sum is odd: return false
    // if even, check if there's a subset with sum equals Sum/2

    int n = nums.length;
    int sum=0;

    for(int i=0; i<n; i++) {
        sum += nums[i];
    }

    if(sum % 2 == 0){
        return optimisedSubset(n-1, sum/2, nums);
    } else return false;
}
    
}
