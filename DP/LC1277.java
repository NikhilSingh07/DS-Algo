// https://leetcode.com/problems/count-square-submatrices-with-all-ones/
// Count Square Submatrices with All Ones

// DP on Rectangles | Squares

import java.util.Arrays;

public class LC1277 {

    // logic is to check for each cell, that how many 1x1, 2x2 and so on matrices
    // can be formed with the cell as the top left corner
    // to expand: check for right, down, and rightDown cells

    // Recursion
    // TC: exponential TLE: 22 / 32 testcases passed
    // SC: O(n+m)

    // Memoization
    // Here, each cell will be computed only once
    // TC: O(n*m)
    // SC: O(n*m) + O(n+m)

    private int memoSquares(int i, int j, int [][] matrix, int[][] dp) {

        // base case: if cell contains 0 or we go out of bound
        if(i>= matrix.length || j>=matrix[0].length) return 0;

        if(matrix[i][j] == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        // Expand the submatrices
        int right = memoSquares(i, j+1, matrix, dp);
        int down = memoSquares(i+1, j, matrix, dp);
        int rightDown = memoSquares(i+1, j+1, matrix, dp);

        return dp[i][j] = 1 + Math.min(right, Math.min(down, rightDown));
    }

    private int solution(int [][] matrix){

        int n  = matrix.length;
        int m = matrix[0].length;
        int count = 0;
        int [][]dp = new int[n][m];

        for(int []row: dp) {
            Arrays.fill(row, -1);
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                count += memoSquares(i, j, matrix, dp);
            }
        }

        return count;
    }

    // Tabulation  | Bottom up approach
    // TC: O(n*m)
    // SC: O(n*m)

    private int tabSquares(int [][] matrix) {

        int n  = matrix.length;
        int m = matrix[0].length;
        int count = 0;
        int [][]dp = new int[n+1][m+1];

        for(int i = n-1; i>=0 ;i--) {
            for(int j = m-1; j>=0; j--) {

                if(matrix[i][j] == 0) continue; // base case

                int right = dp[i][j+1];
                int down = dp[i+1][j];
                int rightDown = dp[i+1][j+1];

                dp[i][j] = 1 + Math.min(right, Math.min(down, rightDown));
                count += dp[i][j];

            }
        }

        return count;
    }

    public int countSquares(int[][] matrix) {
        
        //return solution(matrix);
        return tabSquares(matrix);
    }
    
}
