// Shortest common supersequence

// https://leetcode.com/problems/shortest-common-supersequence/
// DP on Strings: Using LCS tabulation table and printing lCS concept

public class LC1092 {
    

    // LCS 
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

    // O(n + m - len(lcs))
    private String reverseSB(StringBuilder sb){

        int i= 0;
        int j = sb.length()-1;

        while(i < j) {

            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }

        return sb.toString();
    }

    // solved using the tabulation table of LCS:
    // prerequisite- >  LCS + printing lcs
    // when match -> incude it in final string only once.
    // when u go up: first string curr char will left out: so u add it in the ans string
    // similarly, when u go leff: second string curr char will left out, so u add ir in the string.
    
    public String shortestCommonSupersequence(String str1, String str2) {
        
        int n = str1.length();
        int m = str2.length();
        int [][]dp = new int[n+1][m+1];

        int len = tabLCS(n, m, str1, str2, dp); // LSC DP table created
        int SCS_len = n + m - len;
        StringBuilder sb = new StringBuilder();

        // O(n+m)
        while(n > 0 && m >0) {

            if(str1.charAt(n-1) == str2.charAt(m-1)) { // include the LSC char only once in the final string

                sb.append(str1.charAt(n-1));
                n--;
                m--;
            
            } else if(dp[n][m-1] >= dp[n-1][m]) { // go left, append the left out char (upward cell)

                sb.append(str2.charAt(m-1));
                m--;

            } else // go up, append the left out char (left one) 
            {
                sb.append(str1.charAt(n-1));
                n--;
            }

        }

        // append the remaining char either from s1 and s2
        while(n>0) {

            sb.append(str1.charAt(n-1));
            n--;
        }

        while(m > 0) {
            sb.append(str2.charAt(m-1));
            m--;
        }

        return reverseSB(sb);

    }
}
