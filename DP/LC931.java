// 2D Grid with variable starting and ending points
//https://leetcode.com/problems/minimum-falling-path-sum/

/* 4 methods
   1. Recustion: TC: O(3^m)*n 
                 SC: O(m)

   2. Memo: TC: O(m*n)
            SC: O(m*n) + O(m)
            
   3. Tabulation: TC: O(m*n)
                  SC: O(m*n) 
                  
   4. Tabulation with space optimisation: TC: O(m*n)
                                          SC: O(n)               
*/

      

public class LC931 {

    class Solution {
        // Recursion: TC: O(3^m)*n
        // SC: O(m): recursion depth would be the number of rows.
        
        // Memo: O(m*n)
        // SC: O(m*n) + O(m)
            private int memoPath(int m, int n, int [][] matrix, int [][]dp) {
        
                // base case: we reach the 0th row and cover the boundary cases
                if(n < 0 || n >= matrix[0].length) {
                    return (int) 1e9;
                }
        
                if(m == 0) {
                    return matrix[m][n];
                }
        
                if(dp[m][n]!=- (int) 1e9) {
                    return dp[m][n];
                }
        
                int up = matrix[m][n] + memoPath(m-1, n, matrix, dp);
                int ld = matrix[m][n] + memoPath(m-1, n-1, matrix, dp);
                int rd = matrix[m][n] + memoPath(m-1, n+1, matrix, dp);
        
                return dp[m][n] = Math.min(up, Math.min(ld, rd));
            }
        
            // Tabulation: TC: O(m*n) + O(n)[to get the min val in the last row]
            // SC: O(m*n)
            private void tabPath(int m, int n, int [][] matrix, int [][]dp) {
        
                // converting the bottom case, for the 0th row
                for(int j=0; j<=n; j++) {
                    dp[0][j] = matrix[0][j];
                }
        
                // handing the state var, m , n
                for(int i=1; i<=m; i++) {
                    for(int j=0; j<=n; j++) {
        
                        int ld = (int) 1e9;
                        int rd = (int) 1e9;
                        // copy the recurrence
                        int up = matrix[i][j] + dp[i-1][j];
                        if(j > 0)  ld = matrix[i][j] + dp[i-1][j-1];
                        if(j < n)  rd = matrix[i][j] + dp[i-1][j+1];
        
                        dp[i][j] = Math.min(up, Math.min(ld, rd));
                    }
                }
            }
        
            // TC: O(m*n)
            // SC: (n)
            private int optimisedPath(int m, int n, int [][] matrix) {
        
                int [] prev = new int[n+1];
        
                // converting the bottom case, for the 0th row
                for(int j=0; j<=n; j++) {
                    prev[j] = matrix[0][j];
                }
        
                // handing the state var, m , n
                for(int i=1; i<=m; i++) {
        
                    int [] temp = new int[n+1]; 
                    for(int j=0; j<=n; j++) {
        
                        int ld = (int) 1e9;
                        int rd = (int) 1e9;
                        // copy the recurrence
                        int up = matrix[i][j] + prev[j];
                        if(j > 0)  ld = matrix[i][j] + prev[j-1];
                        if(j < n)  rd = matrix[i][j] + prev[j+1];
        
                        temp[j] = Math.min(up, Math.min(ld, rd));
                    }
        
                    prev = temp;
                }
                
                int minV = Integer.MAX_VALUE;
                for(int j=0; j<=n; j++) {
                    minV = Math.min(minV, prev[j]);
                }
                return minV;
            }
        
            public int minFallingPathSum(int[][] matrix) {
                
        
                int m = matrix.length;
                int n = matrix[0].length;
               // int [][]dp = new int[m][n];
        
                // Memo
                /*
                int minV = Integer.MAX_VALUE;
                // we can start from any element of the n-1th row
        
                // -1e9 bec the value can be from -100 t0 100. Therefore we we get -1 as value it will go in recursion
                for(int[] row: dp) {
                    Arrays.fill(row, -(int) 1e9);
                }
                for(int j=0; j<n; j++) {
        
                    int val = memoPath(m-1, j, matrix, dp);
                    minV = Math.min(minV, val);
                }
        
                return minV;*/
        
                // Tabulation
                /*
                tabPath(m-1, n-1, matrix, dp);
                
                int minV = Integer.MAX_VALUE;
                for(int j=0; j<n; j++) {
                    minV = Math.min(minV, dp[m-1][j]);
                }
        
                return minV;*/
        
                // Tab with space optimisation
        
                return optimisedPath(m-1, n-1, matrix);
            }
        }
    
}
