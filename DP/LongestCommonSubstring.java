public class LongestCommonSubstring {

    // DP: on Strings -> Tabulation method
    // Longest Common Substring --> contiguous potion of a string

    // naive iterative approach
    // Try out all the pairs

    // TC: O(m*n * min(m,n))
    // SC: O(1)
    private static int naive(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int max = 0;

        for(int i=0; i<n; i++) {

            for(int j=0; j<m; j++) {

                // Trying all the pairs from s1 and s2
                // if the chars are equal, keep comparing using a while loop

                int curr = 0;
                while( (i+curr <n) && (j+curr < m) && s1.charAt(i+curr) == s2.charAt(j+curr)) {
                    curr++;
                }

                max = Math.max(max, curr);
            }
        }
        return max;
    }

    // Tabulation | Bottom up approach
    // Small improvisation over Longest commoon subsequence 
    // TC: O(m*n)
    // SC: O(m*n)
    private static int tabLCS(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int [][]dp = new int[n+1][m+1];
        int max = 0;

        // filling 1st row and col with 0
        for(int i=0; i<=n; i++) dp[i][0] = 0;
        for(int j=0; j<=m; j++) dp[0][j] = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {

                if(s1.charAt(i-1) == s2.charAt(j-1)) {

                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max, dp[i][j]);
                }
                else dp[i][j] = 0;

            }
        }

        return max;
    } 


    // Tabulation with space optimisation
    // TC: O(n*m)
    // SC: O(m)
    private static int tabSpaceOptimised(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int [] prev = new int[m+1];

        int max = 0;

        // filling 1st row and col with 0
        for(int i=0; i<=n; i++) prev[0] = 0;

        for(int i=1; i<=n; i++) {

            int [] curr = new int[m+1];
            for(int j=1; j<=m; j++) {

                if(s1.charAt(i-1) == s2.charAt(j-1)) {

                    curr[j] = 1 + prev[j-1];
                    max = Math.max(max, curr[j]);
                }
                else curr[j] = 0;

            }
            prev = curr;
        }

        return max;
    }

    public static int lcs(String str1, String str2){
        // Write your code here.

        //return naive(str1, str2);
       // return tabLCS(str1, str2);
       return tabSpaceOptimised(str1, str2);
    }
    
}
