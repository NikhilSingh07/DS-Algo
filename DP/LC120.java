public class LC120 {

    // Should start the recursion from 0,0 instead of m-1, 0 because ending point is not fixed and we need to
// use for loop for each cell in the m-1th row that will increase the TC by TC*O(n)
/*
    private int memoTotal(int m, int n, List<List<Integer>> triangle, int[][] dp) {

        // boundary cases
        if(n < 0) return (int) 1e9;
        // base case: 0th row
        if(n >= triangle.get(m).size()) n--;

        if(m == 0) return triangle.get(m).get(n);
        
        if(dp[m][n]!=-1) return dp[m][n];

        int up = triangle.get(m).get(n) + memoTotal(m-1, n, triangle, dp);
        int leftD = triangle.get(m).get(n) + memoTotal(m-1, n-1, triangle, dp);

        return dp[m][n] = Math.min(up, leftD); 

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        int minV = Integer.MAX_VALUE;
        int [][] dp = new int[n][n];

        for(int []row: dp) {
            Arrays.fill(row, -1);
        }

        // looping though each cell at the m-1 row
        for(int j=0; j<n; j++) {

            int val = memoTotal(m-1, j , triangle, dp);
            minV = Math.min(minV, val);
        }
        return minV;
    }
    */
// Recursion: TC:  2^(m*n)  
// SC: O(m) 

// Memo: Tc: O(m*n)
// SC: O(m*m) + O(m) 
private int memoTotal(int m, int n, List<List<Integer>> triangle, int[][] dp ) {

    //base case: 
    if(m == triangle.size()-1) return triangle.get(m).get(n);
    // no boundary cases, as base case covers it.

    if(dp[m][n]!=-1){
        return dp[m][n];
    }
    
    int down = triangle.get(m).get(n) + memoTotal(m+1, n, triangle, dp);
    int rightD = triangle.get(m).get(n) + memoTotal(m+1, n+1, triangle, dp);

    return dp[m][n] = Math.min(down, rightD);
}

// Tabultaion: TC: O(m*n)
// SC: O(m*m) 
private int tabTotal(List<List<Integer>> triangle, int[][] dp){

    int m = triangle.size();

    // bottom case storing all the values of m-1 row
    for(int j=0; j<m; j++) {
        dp[m-1][j] =  triangle.get(m-1).get(j);
    }

    for(int i= m-2; i>=0; i--) {
        for(int j=0; j<triangle.get(i).size(); j++) {

            int down = triangle.get(i).get(j) + dp[i+1][j];
            int right = triangle.get(i).get(j) + dp[i+1][j+1];

            dp[i][j] = Math.min(down, right);
        }
    }

    return dp[0][0];
}

// Tabultaion with Space optimisation:
// TC: O(m*n)
// SC: O(m)    
private int optimisedTotal(List<List<Integer>> triangle) {

    int m = triangle.size();
    int [] next = new int[m];

    // bottom case storing all the values of m-1 row
    for(int j=0; j<m; j++) {
        next[j] =  triangle.get(m-1).get(j);
    }

    for(int i= m-2; i>=0; i--) {

        int [] temp = new int[m];
        for(int j=0; j<triangle.get(i).size(); j++) {

            int down = triangle.get(i).get(j) + next[j];
            int right = triangle.get(i).get(j) + next[j+1];
            temp[j] = Math.min(down, right);
        }

        next = temp;
    }

    return next[0];
}

public int minimumTotal(List<List<Integer>> triangle) {
    
    int m = triangle.size();
    int [][]dp = new int[m][m];


    /*  
    for(int[] row: dp) {
        Arrays.fill(row, -1);
    }
    return memoTotal(0,0, triangle, dp);
    */

  //  return tabTotal(triangle, dp);

  return optimisedTotal(triangle);

}
    
}
