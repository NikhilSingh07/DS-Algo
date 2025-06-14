import java.util.Arrays;
import java.util.Comparator;


// https://leetcode.com/problems/longest-string-chain/
// Longest String Chain
// LIS PATTERN

// DP on LIS: LIS PATTERN
// Longest Increasing subsequence with condition
// Solved using brute force -> Memoization -> Tabultion -> LIS pattern tabulation

class LengthComparator implements Comparator<String> {

    public int compare(String s1, String s2) {

        if(s1.length() < s2.length()) return -1;
        else if(s1.length() > s2.length()) return 1;
        else return 0;
    }
}


public class LC1048 {
    
    private boolean isValid(String s, String t) {

        int n = s.length();
        int m = t.length();
        int i=0;
        int j=0;

        if(n + 1 != m) return false;

        while(i < n && j<m){
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            else {
                j++;
            }
        }

        if(i == n) return true; // matched all the chars from s
        else return false;
    }

    // Recutsion
    // TC: O(2^n) * O(k)
    // SC: O(n)

    // Memoization
    // TC: O(n*n*k)
    // SC: O(n*n) + O(n)
    
    private int memoLSC(int n, int next, String[] words, int[][]dp) {

        // base case
        if(n < 0) return 0;

        if(dp[n][next+1] !=-1) return dp[n][next+1]; 
        // Try out all possible combinations
        int np = 0 + memoLSC(n-1, next, words, dp);

        int p = 0;
        if(next == -1 || isValid(words[n], words[next])) {

            p = 1 + memoLSC(n-1, n, words, dp);
        }

        return dp[n][next+1] = Math.max(np, p);
    }

    // Tabulation | Bottom up approach
    // TC: O(n*n*k)
    // SC: O(n*n)

    private int tabLSC(String[] words, int [][]dp) {

        // bottom case
        int n = words.length;
        for(int j = -1; j<n; j++) {
            dp[0][j+1] = 0;
        }

        // hadnling the state variables
        for(int i = 1; i<dp.length; i++) {
            for(int j = -1; j<n; j++) {

                int prevInd = j+1;
                int np = 0 + dp[i-1][prevInd];

                int p =0;
                if(j==-1 || isValid(words[i-1], words[prevInd -1])) {
                    p = 1 + dp[i-1][i];
                }

                dp[i][j+1] = Math.max(np, p);
            }
        }

        // since prev choosen element could be any element from 0 to n-1
        int ans = 0;
        for (int j = 0; j <= n; j++) {
            ans = Math.max(ans, dp[n][j]);
        }
        return ans;   
    }

    // Tabulation with space opt
    // TC: O(n*n*k)
    // SC: O(2n)

    private int tabWithSpaceOptLSC(String[] words) {

        // bottom case
        int n = words.length;
        int [] prev = new int[n+1];

        for(int j = -1; j<n; j++) {
            prev[j+1] = 0;
        }

        // hadnling the state variables
        for(int i = 1; i<=n; i++) {
            int []curr = new int[n+1];

            for(int j = -1; j<n; j++) {

                int prevInd = j+1;
                int np = 0 + prev[prevInd];

                int p =0;
                if(j==-1 || isValid(words[i-1], words[prevInd -1])) {
                    p = 1 + prev[i];
                }

                curr[j+1] = Math.max(np, p);
            }

            prev = curr;
        }

        // since prev choosen element could be any element from 0 to n-1
        int ans = 0;
        for (int j = 0; j <= n; j++) {
            ans = Math.max(ans, prev[j]);
        }
        return ans;   
    }


        // Here, in memo, I went from 0 to n. Therefore, here's the tabulation.  
    private int func(String []words, int[][]dp) {

        int n = words.length;
        // bottom case: when ind == n
        for(int p = -1; p<n; p++) {
            dp[n][p+1] = 0;
        }

        for(int ind = n-1; ind>=0; ind--) {
            for(int p = n-1; p>=-1; p--) {

                // +1 is for the offset
                int np = 0 + dp[ind+1][p+1];

                int pk = 0;
                if(p == -1 || isValid(words[p], words[ind])) {

                    // +1 offset should be applied to all the p indices
                    pk = 1 + dp[ind+1][ind+1];
                }

                dp[ind][p + 1] = Math.max(np, pk);
            }
        }
        
        for(int e: dp[0]) {
            System.out.println(e+", ");
        }
        return dp[0][-1+1]; // return max chain len from the entire word list
    }


    // LIS PATTERN LOGIC
    // TC: O(n*n*k)
    // SC: O(n)
    
    private int LIS_LSC(String[] words) {

        int n = words.length;
        int []dp = new int[n];
        int LIS =0;
        Arrays.fill(dp, 1);

        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {

                if(isValid(words[j], words[i])) {

                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

            LIS  = Math.max(LIS, dp[i]);
        }

        return LIS;
    }

    public int longestStrChain(String[] words) {
        
        int n = words.length;
        Arrays.sort(words, new LengthComparator());
        
        // Memoization
/*
        int [][]dp = new int[n][n+1];
        for(int []row: dp) {
            Arrays.fill(row, -1);
        }

        return memoLSC(n-1, -1, words, dp);
    }
*/

        // Tabulation
        /*
        int[][]dp = new int[n+1][n+1];
        return tabLSC(words, dp);
        */

        //Tabulation with space opt
        /*
        return tabWithSpaceOptLSC(words);*/

        // USING THE LIS PATTERN LOGIC
        return LIS_LSC(words);
    }
    
}
