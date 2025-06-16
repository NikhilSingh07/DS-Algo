// DP with partitions
// Matrix chain multiplication


public class MCM {  
    // Recusrion TC: eponential, worse than 2^n
    // SC: O(n)
    
    
    // memo: TC: O(n^3)
    // SC: O(n^2)
    
    // returns the min opertions to multiply matrices from index 1 till n-1
    private static int memoMCM(int i, int j, int[]arr, int[][]dp){ 
        
        // base case, when we have a single matrix, 0 operaitons
        if(i == j) return 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        int mini = (int) 1e9;
        // Try out all possible cominations of parititions
        for(int k=i; k<j; k++) {
            
            // formula to multiply 2 matrices and get total multuplicaiton operations
            int operations = arr[i-1] * arr[k] * arr[j] + memoMCM(i, k, arr, dp) + memoMCM(k+1, j, arr, dp);
            mini = Math.min(mini, operations);
        }
        
        return dp[i][j] = mini;
        
        
    }

    // Tabulation | Bottom up approach
    // TC: O(n^3)
    // SC: O(n^2)
    private static int tabMCM(int []arr, int [][]dp){
        
        int n =arr.length;
        
        // bottom case: when i ==j
        for(int i=1; i<n; i++) dp[i][i] = 0;
        
        // handling the state variables
        // i is the first element of the block
        // j is the last element
        
        // i will abways be left of j
        for(int i = n-1; i>=1; i--) {
            for(int j = i+1; j<=n-1; j++) {
                
                int mini = (int) 1e9;
                
                for(int k=i; k<j; k++) {
                    
                    // formula to multiply 2 matrices and get total multuplicaiton operations
                    int operations = arr[i-1] * arr[k] * arr[j] + dp[i][k] + dp[k+1][j];
                    mini = Math.min(mini, operations);
                }
                dp[i][j] = mini;
            }
        }
        
        return dp[1][n-1];
    }
    
    static int matrixMultiplication(int arr[]) {
        // code here
        
        int n = arr.length;
        int i = 1;
        int j = n-1;
        
        if(n <= 2) {
            return 0;
        }
        
        int [][]dp = new int[n][n];
        /*
        for(int []row: dp) {
            Arrays.fill(row, -1);
        }
        return memoMCM(i, j, arr, dp);
        */
        
        return tabMCM(arr, dp);
    }
}
