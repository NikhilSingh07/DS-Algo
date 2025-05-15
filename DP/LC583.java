public class LC583 {

    //minimum number of steps required to make word1 and word2 the same.
    //https://leetcode.com/problems/delete-operation-for-two-strings/description/

    // If we delete all chars from s1 and insert all chars from s2 to s1. We will have our desired string
    // no. of operations = len(n) [delete] + len(m) [insert]
    // "sea" "eat" = 7
    // Therfore, it is conformed that s1 can be converted to s2
    

    // For min operations, logic is WHAT CAN I NOT TOUCH?

    // "sea" "eat"
    // "ea"  is the longest common portion (LCS


    // In String s1, total deletions = n - len(LCS)
    // In String s1, total insertions = m - len(LCS

    // USING LCS CODE HERE :
    

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
        return tabLCS(n, m, text1, text2);
    } 

    public int minDistance(String word1, String word2) {
        
        int n = word1.length();
        int m = word2.length();

        int lcs_len = longestCommonSubsequence(word1, word2);

        int del_operations = n - lcs_len;
        int ins_operations = m - lcs_len;

        return del_operations + ins_operations;
    }
    
}
