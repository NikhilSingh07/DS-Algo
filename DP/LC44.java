// https://leetcode.com/problems/wildcard-matching/description/
// Wildcard Matching 

// DP on strings: match or skip 

public class LC44 {
    
    
    // logic: need to check if both strings are equal
    // if s[i] == t[j] || t[j] == '?' i--, j--
    // else if t[j] == "*" > 2 options, either match or skip

    // s = "ab*cd"   here, d matches d, c ,matches c, for * i don;t know how many chars should it match: 0 or more
                                                          // > if it matches with ab and 'ab' will left in pattern s
                                                          // therefore, here it should not match with any char         
    // t = 'abcd'

    // return (i--, j) || (i, j--)



    // Recursion
    // TC: O(2^(m+n))
    // SC:O(n+m)


    // Memoization
    // TC: O(n*m)
    // SC: O(n+m) + O(n*m)
    private boolean memoMatch(int i, int j, String s, String p, int [][]dp) {

        // base cases
        // (i<0 and j<0 all chars matched: return true)
        if(i<0 && j<0) return true;

        if(i<0 && j>=0) { // true, if all the rem chars in p contains *

            for(int x = 0; x<=j; x++) {
                if(p.charAt(x)!= '*') return false;
            }
            return true;
        }

        if(j<0 && i>=0) return false;

        if(dp[i][j]!=-1) {
            return dp[i][j] == 1;
        }

        // Match
        if( (s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '?') ) {
            boolean  res =  memoMatch(i-1, j-1, s, p, dp);
            dp[i][j] = res?1:0;
            return res;

        }
        // Match | skip
        else if(p.charAt(j) == '*') {

            // don't know how many chars to match: 0 or more: explore all possibilites
            boolean res = memoMatch(i-1, j, s, p, dp) || memoMatch(i, j-1, s, p, dp);
            dp[i][j]  = res?1:0;

            return res;
        }

        return false;
    }
    

    // Tabulation: Bottom Up
    // Shifting the indices by 1 index to handle the -ve index
    // dp[n+1][m+1]
    // TC: O(n*m)
    // SC: O(n*m)

    private boolean tabMatch(int n, int m, String s, String p, boolean [][]dp) {

        // bottom cases: 
        // i==0 and j==0
        dp[0][0] = true;

        // when i ==0 and j>0
        for(int j=1;j<dp[0].length; j++) {

            if (p.charAt(j-1) == '*' ) {

                dp[0][j] = dp[0][j-1]; // only true if all prev are *
            } 
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {

                if( (s.charAt(i-1) == p.charAt(j-1)) || (p.charAt(j-1) == '?') ) dp[i][j] = dp[i-1][j-1];

                else if (p.charAt(j-1) == '*') {

                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else dp[i][j] = false;
            }
        }

        return dp[n][m];
    }


    // Tabulation with space optimsation
    // using 2 1D arrays and toggling between them
    // TC: O(n*m)
    // SC: O(2m)
    private boolean tabWithSpaceOptMatch(int n, int m, String s, String p) {

        boolean []prev = new boolean[m+1];

        // bottom cases: 
        // i==0 and j==0
        prev[0] = true;

        // when i ==0 and j>0
        for(int j=1;j<=m; j++) {

            if (p.charAt(j-1) == '*' ) {

                prev[j] = prev[j-1]; // only true if all prev are *
            } 
        }

        for(int i=1; i<=n; i++) {

            boolean curr[] = new boolean[m+1];
            for(int j=1; j<=m; j++) {

                if( (s.charAt(i-1) == p.charAt(j-1)) || (p.charAt(j-1) == '?') ) curr[j] = prev[j-1];

                else if (p.charAt(j-1) == '*') {

                    curr[j] = prev[j] || curr[j-1];
                }
                else curr[j] = false;
            }

            prev = curr;
        }

        return prev[m];
    }

    public boolean isMatch(String s, String p) {
        
        int n = s.length();
        int m = p.length(); 

        // Memoization
        /*
        int [][]dp = new int[n][m];

        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        return memoMatch(n-1, m-1, s, p, dp);
        */

        // Tabulation
        /*
        boolean [][]dp = new boolean[n+1][m+1];
        return tabMatch(n, m , s, p, dp);
        */

        // Tabulation with space optimisation
        return tabWithSpaceOptMatch(n, m, s, p);
    }
}
