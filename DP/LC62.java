// https://leetcode.com/problems/unique-paths/description/
// DP: 2D grid
// https://excalidraw.com/#json=7eq_kz9LDod4i6_MXMcHD,m6VMYkslW8UPc8JztxUvXQ

// 4 methods: Recursion, Memo, Tab, Space optimisation

import java.util.Arrays;

public class LC62{

    
    // Recursion: TC: O(2^ (m*n))
    //            SC: O((m-1) + O(n-1)): Path length
    private int countPaths(int m, int n){

        // base case: we find a path
        if(m == 0 && n==0) return 1;

        // edge cases: we go out of the grid
        else if(m <0  || n<0) return 0;

        int up = countPaths(m-1, n);
        int left = countPaths(m, n-1);

        return up+left;
    }

    // Memoization: TC: O(m*n)
    //              SC: O((m-1)+(n-1)) + O(m*n)
    private int memoPaths(int m, int n, int [][] dp){

        // base case: we find a path
        if(m == 0 && n==0) return 1;

        // edge cases: we go out of the grid
        else if(m <0  || n<0) return 0;

        // returning ans of overlapping subproblem
        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        int up = memoPaths(m-1, n, dp);
        int left = memoPaths(m, n-1, dp);

        // caching the result
        return dp[m][n] = up+left;
    }

    // Tab: TC: O(m*n)
    // SC: O(m*n)
    private int tabPaths(int m, int n, int[][]dp) {

        // Step 1: base case: 
        dp[0][0] = 1;

        // Step 2: for loops for the state variables
        for(int i=0; i<=m; i++) {

            for(int j=0; j<=n; j++) {

                if(i == 0 && j ==0) continue;
                else {
                    int up = 0;
                    int left = 0;
                    if(i>0) up = dp[i-1][j];
                    if(j>0) left = dp[i][j-1];

                    dp[i][j] = up+left;
                }

            }
        }

        return dp[m][n];
    }

    // tab with Space optimsiation: TC: (m*n)
    // SC: O(n)
    private int optimisedPaths(int m, int n) {

        // 1d array storing prev row
        int [] prev = new int[n+1];
        Arrays.fill(prev, 0);

        // Step 2: for loops for the state variables
        for(int i=0; i<=m; i++) {

            int temp[] = new int[n+1]; // store the ans of current row
            for(int j=0; j<=n; j++) {

                if(i == 0 && j ==0) {temp[j] = 1;}
                else {
                    int up = 0;
                    int left = 0;
                    if(i>0) up = prev[j];
                    if(j>0) left = temp[j-1];

                    temp[j] = up+left;
                }
            } 
            prev = temp;
        }    

        return prev[n];
    }


    public int uniquePaths(int m, int n) {

        //int [][] dp = new int[m][n]; // saving results for from 0 to m-1 and 0 to n-1 cells

        /*for(int [] row: dp) {
            Arrays.fill(row, 0);
        }*/

        //return memoPaths(m-1,n-1, dp);
        //return tabPaths(m-1, n-1, dp);
        return optimisedPaths(m-1, n-1);
    }

}