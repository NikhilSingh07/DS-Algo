public class LC1143 {

    

    // Match | NOT MATCH 
    // use recursion to try out all the subsequences: contigous | non- contiguous portion of the array but in-order

    // if there's a match -> add 1 to the length and move both strings to the prev indices
    //                    -> [make the recursion call for the remaining string]
    // if not match -> add 0 and make 2 recusive call, 1 for [n-1][m] and second for [n][m-1] and return the max of both calls

    // Recursion
    // TC: O(2^n * 2*m)
    // SC: O(n+m)

    // Memoization: 
    // TC: O(n*m)
    // SC: O(n+m) + O(n*m)
    private int memoLCS(int n, int m, String text1, String text2, int [][]dp) {

        // base case: when we go out of string (n<0) || (m<0)
        if(n < 0 || m < 0) return 0;

        if(dp[n][m]!=-1) return dp[n][m];
        // Match
        if(text1.charAt(n) == text2.charAt(m))
        return dp[n][m] = 1 + memoLCS(n-1, m-1, text1, text2, dp);

        // not match
        else
        return dp[n][m] = Math.max(memoLCS(n-1,m, text1, text2, dp), memoLCS(n, m-1, text1, text2, dp));

    }

    // To convert this in tabulation, we do shifting of indices by 1 as index -1 cannot be stored in the dp array [for the base case from memo] 

    // Tabulation |  Bottom up approach 
    // TC: O(n*m)
    // SC: O(n*m)
    private int tabLCS(int n, int m, String text1, String text2, int[][]dp) {

        // handling the bottom case: by shiting all the indices by 1
        // if n ==0, m can be of range 0 to m
        for(int j = 0; j<=m; j++) {
            dp[0][j] = 0;
        }

        // similarly for m = 0, n can be in the range 0 to n
        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int i=1; i<=n; i++) {
            for(int j = 1; j<=m; j++) {

                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 0 + Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }


    // Tabulation with space optimisation
    // TC: O(n*m)
    // SC: O(m)
    private int tabLCS(int n, int m, String text1, String text2) {


        int [] prev = new int[m+1];

        // handling the bottom case: by shiting all the indices by 1
        // if n ==0, m can be of range 0 to m
        for(int j = 0; j<=m; j++) {
            prev[j] = 0;
        }

        for(int i=1; i<=n; i++) {
            int curr[] = new int [m+1];
            for(int j = 1; j<=m; j++) {

                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    curr[j] = 1 + prev[j-1];
                } else {
                    curr[j] = 0 + Math.max(prev[j], curr[j-1]);
                }
            }

            prev = curr;
        }

        return prev[m];
    }


    public int longestCommonSubsequence(String text1, String text2) {
        
        int n = text1.length();
        int m = text2.length();

        /*
        int [][]dp = new int[n][m];
        for(int []row: dp) Arrays.fill(row, -1);
        return memoLCS(n-1, m-1, text1, text2, dp);
        */

        int [][]dp = new int[n+1][m+1];// for shifted indices
        return tabLCS(n, m, text1, text2, dp);
    }
    
}
