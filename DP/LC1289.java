//https://leetcode.com/problems/minimum-falling-path-sum-ii/ 
 
 // same as  LC 931. Minimum Falling Path Sum
 // but here we can have m-1 choices at each step// last question has 3 choices at each step
    // we can't choose an element just below it



public class LC1289 {

    


// TC: O(m*n*n-1) ~ O(n^3)
// SC: O(n)
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
            
            int min = Integer.MAX_VALUE;
            for(int a = 0; a<=n; a++) {

                if(a!=j ) { // can't pick the element present just above it
                   
                    int val  = matrix[i][j] + prev[a];
                    min  = Math.min(min, val);
                }
            }
            temp [j] = min; 

        }

        prev = temp;
    }
    
    int minV = Integer.MAX_VALUE;
    for(int j=0; j<=n; j++) {
        minV = Math.min(minV, prev[j]);
    }
    return minV;
}
public int minFallingPathSum(int[][] grid) {

    int m = grid.length;
    int n = grid[0].length;

    return optimisedPath(m-1, m-1, grid);
}
    
}
