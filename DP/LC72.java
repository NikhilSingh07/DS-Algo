// https://leetcode.com/problems/edit-distance/

// Edit Distance
// DP on strings: Match | not match logic

public class LC72 {
    
    // 1. Recustion: 
    // TC: O(3^ min (m,n))
    // SC: O(n+m)

    // 2. Memoization
    // TC: O(n*m)
    // SC: O(n+m) + O(n*m)
    private int memoEdit(int i, int j, String s, String t, int[][]dp) {

        // base cases: 
        if(j < 0) return i + 1;  // now delete all the rem chars from s
        if(i < 0) return j + 1 ;  // delete all the chars from s and insert all feom t

        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i) == t.charAt(j)) {

            return dp[i][j] = 0 + memoEdit(i-1, j-1, s, t, dp);
        } else {

            // delete
            int op1 = 1 + memoEdit(i-1, j, s, t, dp);
            
            // replace
            int op2 = 1 + memoEdit(i-1, j-1, s, t, dp);

            // insert
            int op3 = 1 + memoEdit(i, j-1, s, t, dp);

            return dp[i][j] = Math.min(op1, Math.min(op2, op3));
        }
    }

    // 3. Tabulation: need to shift the indices by 1, as the bottom case has index -1
    // TC: O(n*m)
    // SC: O(n*m)
    
    private int tabEdit(int n, int m, String s, String t, int[][]dp) {

        // bottom cases: when j == 0 or i == 0
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = i;
        }

        for(int j = 0; j<dp[0].length; j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<dp.length; i++) {

            for(int j=1; j<dp[0].length; j++) {

                if(s.charAt(i-1) == t.charAt(j-1))  {
                    dp[i][j] =  0 + dp[i-1][j-1];
                } else {

                    // insert
                    int op1 = 1 + dp[i][j-1];
                    
                    // delete
                    int op2 = 1 + dp[i-1][j];
                    
                    // replace
                    int op3 = 1 + dp[i-1][j-1];

                    dp[i][j] = Math.min(op1, Math.min(op2, op3));
                }
            }
        }

        return dp[n][m];
    }

    // Tabulation with space optimiation:
    // 2 1D arrays and toggling between them
    // TC: O(n*m)
    // SC: O(2m)
    private int tabWithSpaceOptEdit(int n, int m, String s, String t) {

        int []prev = new int[m+1];


        // bottom cases: when j == 0 or i == 0

        for(int j = 0; j<=m; j++) {
            prev[j] = j;
        }

        for(int i=1; i<=n; i++) {
            int curr[] = new int[m+1];
            curr[0] = i;
            for(int j=1; j<=m; j++) {

                if(s.charAt(i-1) == t.charAt(j-1))  {
                    curr[j] =  0 + prev[j-1];
                } else {

                    // insert
                    int op1 = 1 + curr[j-1];
                    
                    // delete
                    int op2 = 1 + prev[j];
                    
                    // replace
                    int op3 = 1 + prev[j-1];

                    curr[j] = Math.min(op1, Math.min(op2, op3));
                }

            }
            prev = curr;
        }

        return prev[m];
    }

    public int minDistance(String word1, String word2) {
        
        int n = word1.length();
        int m = word2.length();

        // memoization
        /*
        int [][]dp = new int[n][m];

        for(int row[]: dp) {
            Arrays.fill(row, -1);
        }

        return memoEdit(n-1, m-1, word1, word2, dp);
        */

        // Tabulation
        //int [][]dp = new int[n+1][m+1];

        //return tabEdit(n, m, word1, word2, dp);

        return  tabWithSpaceOptEdit(n,m,word1, word2);

    }
}
