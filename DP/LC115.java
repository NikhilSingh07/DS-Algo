// https://leetcode.com/problems/distinct-subsequences/
// DP on Strings

public class LC115 {

    /*  LOGIC: 

1. Use recursion to compute all subsequences of string s equals to string t
2. use 2 state variables (i, j): i pointing to the last index of s and j pointing to the last index of t
3. if both the chars at indices (i,j) matches: 
         a) check for the remaining of the t string [i-1, j-1]
         b) check if the char appears again somewhere in the string s [i-1, j]
             add both
4. if they don't match, move to the prev index in string s
 */

class Solution {

    // 1. Recursion: TC: O(2^n * 2^m)  = exponential
    //               SC: O(n+m)
    // TLE

    // 2. Memoization: TC: O(n*m)
    // SC: O(n+m) + O(n*m)
    private int memoDistinct(int i, int j, String s, String t, int[][]dp ) {

        /// base case: when t < 0, all chars are found in s
        //             when s < 0, not all chars of t are found

        if(j < 0) return 1;
        if(i < 0) return 0;

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        // if both the char of s and t matches
        // look for the remaining char or look for the same char again in the s string
        if(s.charAt(i) == t.charAt(j)) {

            return dp[i][j] = memoDistinct(i-1, j-1, s, t, dp) + memoDistinct(i-1, j, s, t, dp);
        } else 
            return dp[i][j] = memoDistinct(i-1, j, s, t, dp);
    }

    // Tabulation: will need to do index shifting as the base case contains -ve index
    // TC: O(n*m)
    // SC: O(n*m)

    private int tabDistinct(int n, int m, String s, String t, int [][]dp) {

        //bottom case: when n = 0: no ans
        for(int j = 0; j<=m; j++) {
            dp[0][j] = 0;
        }

        // bottom case: when m = 0: we have an ans
        for(int i = 0; i<=n ; i++) {
            dp[i][0] = 1;
        } 

        for(int i = 1; i<=n; i++) {
            for(int j =1; j<=m; j++) {

                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];
    }


    // Tabulation with Space optimisation: 2D array to 2 1D array
    // TC: O(n*m)
    // SC: O(2m)
    private int tabSpaceOptimisedDistinct(int n, int m, String s, String t) {

        int prev[] = new int[m+1];

        //bottom case: when n = 0: no ans
        for(int j = 0; j<=m; j++) {
            prev[j] = 0;
        }

        // bottom case: when m = 0: we have an ans
        prev[0] = 1;
        for(int i = 1; i<=n; i++) {
            int curr[] = new int[m+1];
            curr[0] = 1;
            for(int j =1; j<=m; j++) {

                if(s.charAt(i-1) == t.charAt(j-1)) {
                    curr[j] = prev[j-1] +prev[j];
                } else 
                    curr[j] = prev[j];    
            }
            prev = curr;
        }

        return prev[m];
    }

    // Tabulation with 1 1D Array space optimisation
    // As we are only fething values from the prev row [j, j-1] column 
    // we don't really need the curr array, we can just use 1 prev array and keep updating the jth col.
    // Nested inner for loop will run from m to 1
    // TC: O(n*m)
    // SC: O(m)
    private int tab1DArraySpaceOptimisedDistinct(int n, int m, String s, String t) {

        int prev[] = new int[m+1];

        //bottom case: when n = 0: no ans
        for(int j = 0; j<=m; j++) {
            prev[j] = 0;
        }

        // bottom case: when m = 0: we have an ans
        prev[0] = 1;

        for(int i = 1; i<=n; i++) {
            for(int j =m; j>=1; j--) {

                if(s.charAt(i-1) == t.charAt(j-1)) {
                    prev[j] = prev[j-1] +prev[j];
                } else 
                    prev[j] = prev[j];    
            }
        }

        return prev[m];
    }

    public int numDistinct(String s, String t) {
        
        int n = s.length();
        int m = t.length();
        /*
        int [][]dp = new int[n][m];

        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        return memoDistinct(n-1, m-1, s, t, dp);*/

        //int [][]dp = new int[n+1][m+1]; // for shifted indices
        //return tabDistinct(n, m, s, t, dp);

        //return tabDistinct(n, m , s, t);
        return tab1DArraySpaceOptimisedDistinct(n, m, s, t);
    }
}
    
}
