// DP on subsequences
// 4 Metods: Recursion-> MEMO -> Tabulation -> Space optimisation

public class SubsetSumEqualK{

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

    // at any index if target is 0
    for(int i = 0; i<=n; i++) {
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

public static boolean subsetSumToK(int n, int k, int arr[]){
    /* // Memoization
    int [][]dp = new int[n][k+1];
    for(int []row: dp) {
        Arrays.fill(row, -1);
    }
    return memoSubset(n-1, k, arr, dp);
    */

    /* //Tabulation
    boolean [][] dp  =new boolean[n][k+1];
    return tabSubset(n-1, k, arr, dp);
    */

    // Tabulation with space optimisation
    return optimisedSubset(n-1, k, arr);
}
}