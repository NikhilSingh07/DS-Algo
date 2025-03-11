// DP: 2D grid: Min path sum
// 4 Methods: Rec + Memp + Tab + Space optimsation


public class LC64 {

    // Recursion: 2^(m*n)
// SC: O(m+n) : length of the path

    // Memo: TC: O(m*n)
    // SC: (m*n) + O(m+n)
    private static int memoPath(int i, int j, int [][] grid, int [][] dp) {

        // base case: reach the 1st cell
        if(i == 0 && j == 0) {
            return grid[i][j];
        }
        // boundary cases
        if(i< 0 || j<0) {
            return (int) 1e9;
        }

        if(dp[i][j]!=-1) {
            return dp[i][j];
        }

        int up = grid[i][j] + memoPath(i-1, j, grid, dp);
        int left = grid[i][j] + memoPath(i, j-1, grid, dp);

        return dp[i][j] = Math.min(up, left);
    }

    // Tabulation: Bottom-up approach
    // TC: O(m*n)
    // SC: O(m*n)
    private static int tabPath(int m, int n, int [][] grid, int [][] dp) {

        // base case: reach the 1st cell
        dp[0][0] = grid[0][0];

        // handle the changing variables
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i ==0 && j==0) continue;

                int up = (int)1e9;
                int left = (int)1e9;

                if(i>0) up = grid[i][j] + dp[i-1][j];
                if(j>0) left = grid[i][j] + dp[i][j-1];

                dp[i][j] = Math.min(up, left);
            }
        }

        return dp[m][n];
    }
    
    // Tab with space optimisation: TC: O(m*n)
    // SC: O(n)
    private static int optimisedPath(int m, int n, int [][] grid) {

        int []prev = new int[n+1];

        // handle the changing variables
        for(int i=0; i<=m; i++) {
            int [] temp = new int[n+1];
            for(int j=0; j<=n; j++) {
                if(i ==0 && j==0) { temp[j] = grid[i][j]; continue;}

                int up = (int)1e9;
                int left = (int)1e9;

                if(i>0) up = grid[i][j] + prev[j];
                if(j>0) left = grid[i][j] + temp[j-1];

                temp[j] = Math.min(up, left);
            }

            prev = temp;
        }
    
        return prev[n];
    }

    public int minPathSum(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        //int [][] dp = new int[m][n];
        /*
        for(int []row: dp) {
            Arrays.fill(row, -1);
        } */

        //return tabPath(m-1, n-1, grid, dp);
        return optimisedPath(m-1, n-1, grid);

    }
    
}
