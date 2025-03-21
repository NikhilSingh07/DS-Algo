public class CountSubsetsSumWithZeros {

    
// Recursion
// TC: O(2^n)
// SC: O(n)


// Memo: TC: O(n*target)
// SC: O(n*target) + O(n)
private static int memoSubsets2(int n, int t, int[] arr, int [][] dp){

    int MOD=(int)(1e9+7);

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

    return dp[n][t] = (not_pick + pick) % MOD;
}

// TC: O(n*tar)
// SC: O(n*tar)
private static int tabSubsets(int n, int t, int []arr, int [][]dp) {
    int MOD = (int)(1e9 + 7);

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

            dp[ind][tar] = (pick+ not_pick)% MOD;
        }
    }

    return dp[n][t];
}


// Tab with space optimisation
// TC: O(n*tar)
// SC: O(t)
private static int optimisedSubsets(int n, int t, int[] arr) {

    int [] prev = new int[t+1];
    int MOD = (int)(1e9 + 7);

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

            temp[tar] = (pick+ not_pick)% MOD;
        }
        prev = temp;
    }

    return prev[t];
}


public static int findWays(int num[], int tar) {
    // Write your code here.
    int n = num.length;
    int [][]dp = new int[n][tar+1];

    // for(int []row: dp){
    //     Arrays.fill(row,-1);
    // }
   //return memoSubsets2(n-1, tar, num, dp);

   //return tabSubsets(n-1, tar, num, dp);
   return  optimisedSubsets(n-1, tar, num);

}
    
}
