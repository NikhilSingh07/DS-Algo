// https://www.geeksforgeeks.org/problems/boolean-parenthesization5610/1
// Boolean Parethesization

// DP with Paritions
/* 
 * 
 * Using a for loop we are partitioning the expression in 2 subproblems and solving the subproblems using recurison to count the total 
 * number of ways it returns true.
 * 
 * We also needs to calculate the number of ways it returns false, as when we apply an operation between 2 subporblems, it can give 
 * me true or fals, depends completely on the operator, : T OR F = T  T & F = F
 * 
 * Hence, in the function, we pass 3 state variables: i, j, isTrue = 1
 * Therefore, the function returns the number of ways expression from index i till j returns true
 * 
*/
public class GFG_Boolean_Parethesization {


        
    // Recursion TC: EXponential
    // SC: O(n)
    
    // Memoization :TC: O(n* n * 2) = O(n^2)
    // SC: O(n *n * 2) + O(n)
    
    // returns the number of ways we can partheesize the expression in the block from index i to j, 
    // such that it returns true
    
    private static int memoWays(int i, int j, int isTrue, String s, int[][][]dp){
    
            // base case: if there's no subproblem left
            if(i > j)  return 0;
            
            // base case: if the sub problem ends with a single symbol : T | F
            if(i == j) {
                
                if(isTrue == 1) return s.charAt(i) == 'T' ? 1: 0;
                else // we are looking for no. of ways it returns false
                  return s.charAt(i) == 'F' ? 1: 0;
            }
            
            if(dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
            
            int ways = 0;
            
            // Try out all possible partitions and subproblem
            for(int ind = i+1; ind<=j-1; ind = ind+2) {
                
                // Number of ways the left subproblem returns true
                int lT = memoWays(i, ind-1, 1, s, dp);
                // Number of ways the left subproblem returns false
                int lF = memoWays(i, ind-1, 0, s, dp);
                
                // Number of ways the right subproblem returns true
                int rT = memoWays(ind+1, j, 1, s, dp);
                // Number of ways the right subproblem returns false
                int rF = memoWays(ind+1, j, 0, s, dp);
                
                
                if(s.charAt(ind) == '&') {
                    
                    if(isTrue == 1) { 
                        ways += lT * rT;
                        
                    } else { // no. of ways it returns a false
                        
                        ways += (lT * rF) + (lF  * rT) + (lF * rF);     
                    }
                }
                
                else if(s.charAt(ind) == '|') {
                    
                    if(isTrue == 1) {
                        
                        ways += (lT * rF) + (lF  * rT) + (lT * rT);
                    
                        
                    } else {
                        ways += lF * rF;
                    }
                }
                
                else if(s.charAt(ind) == '^') {
                    
                    if(isTrue == 1) {
                        
                        ways += (lT * rF) + (lF * rT);
                    } else {
                        
                        ways += (lT * rT) + (lF * rF);
                    }
                }
            }
            
            return dp[i][j][isTrue] = ways;
        
    }
    
    // TC: O(n*n*2) ~ O(n^2)
    // SC: O(n*n*2) ~ O(n^2)
    private static int tabWays(String s, int[][][]dp){
        
        int n = s.length();
        // bottom case
        for(int i = 0; i <n; i++) {

                for(int k = 0; k<=1; k++) {
                        
                   if(k == 1)  dp[i][i][k] = s.charAt(i) == 'T' ? 1: 0 ;
                    
                    else // we are looking for no. of ways it returns false
                               dp[i][i][k] = s.charAt(i) == 'F' ? 1: 0;
                }
        }
        
 
        
        // hanlde the state variables: i, j, isTrue
        for(int i = n-1; i>=0; i--) {
             
            for(int j = i+1; j<=n-1; j++) { // (i == j) was the base case
                
                for(int isTrue = 0; isTrue<=1; isTrue ++) {
                    
                    int ways = 0;
                        for(int ind = i+1; ind<=j-1; ind = ind+2) {
                                
                            int lT = dp[i][ind-1][1];
                            int lF = dp[i][ind-1][0];
                            
                            int rT = dp[ind+1][j][1];
                            int rF = dp[ind+1][j][0];
                            
                            
                            if(s.charAt(ind) == '&') {
                                
                                if(isTrue == 1) { 
                                    ways += lT * rT;
                                
                                    
                                } else { // no. of ways it returns a false
                                    
                                    ways += (lT * rF) + (lF  * rT) + (lF * rF);
                                    
                                }
                            }
                            
                            else if(s.charAt(ind) == '|') {
                                
                                if(isTrue == 1) {
                                    
                                    ways += (lT * rF) + (lF  * rT) + (lT * rT);
                                
                                    
                                } else {
                                    ways += lF * rF;
                                }
                            }
                            
                            else if(s.charAt(ind) == '^') {
                                
                                if(isTrue == 1) {
                                    
                                    ways += (lT * rF) + (lF * rT);
                                } else {
                                    
                                    ways += (lT * rT) + (lF * rF);
                                }
                            }
                        }
                        
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        
        
        return dp[0][n-1][1];
        
    }
    
    static int countWays(String s) {

        // code here
        int n = s.length();
        int [][][]dp = new int[n][n][2];
        
        /*
        for(int [][]row: dp ){
            
            for(int []r: row) {
                Arrays.fill(r, -1);
            }
        }
        return memoWays(0, s.length()-1, 1, s, dp);
        */

        return tabWays(s, dp);
    }
    
}
