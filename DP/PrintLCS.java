public class PrintLCS {
    
    // DP on strings: Print the LCS: 
    // using the tabualation table

    // Using the tabulation table from LCS length to print the LCS
    // TC: O(n*m) + O(n+m)
    // SC: O(n*m)
    private static int tabLCS(int n, int m, String text1, String text2, int[][]dp) {

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

    public static String findLCS(int n, int m, String s1, String s2){
        // Write your code here.

        int [][]dp = new int[n+1][m+1];
        int len = tabLCS(n,m, s1, s2, dp);

        // i and j are pointing to the last cell of the dp array
        int i = n;
        int j = m;
        StringBuilder sb = new StringBuilder(len);

        for(int a=0; a<len; a++) {
            sb.append('$');
        }
        int ind = len-1;

        while(i>0 && j >0) {

            // if chars are equal, move to i-1, j-1
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.setCharAt(ind, s1.charAt(i-1));
                ind --;
                i = i-1;
                j = j-1;

            } // else move to the cell with max value
            
            else if(dp[i-1][j] >= dp[i][j-1]) {
                i = i-1;
            } else j = j-1;
        }

        return sb.toString();
    }
    
}
