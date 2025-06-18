//https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
// Minimum cost to cut a stick

// DP with Partitions

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC1547 {

    /*
     * 1. Append 0 at the beg and n at the end of cuts array to compute the current len of the block in the stick
     * 2. sort the cuts array, so that each block is independent of the other block to correctly compute the len of the current stick block
     * 3. Try out all possible cuts and partitions + return the mini cost
     */
    
    
    // Recursion:  TC: Exponential, worst then O(2^n)
    // SC: O(n)

    // Memoizaton: TC: O(n^3)
    // SC: O(n^2) + O(n)
    private int memoCost(int i, int j, List<Integer> cuts, int [][]dp) {

        // Base case: len of the stick has to be atleast 2 to make a cut possible
        // as we are cal len-> cuts[i-1], cuts[j+1]
        // i == j means there is an element present
        if(i > j) return 0;

        // Try out at all cuts and partitions
        if(dp[i][j] != -1) return dp[i][j];
        int mini = (int) 1e9;
        for(int k = i; k<=j; k++) {

            int cost = cuts.get(j+1) - cuts.get(i-1)  +
                             memoCost(i, k-1, cuts, dp) + 
                             memoCost(k+1, j, cuts, dp);

            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;

    }

    // Tabulation | Bottom up
    // TC: O(n^3)
    // SC: O(n^2)
    private int tabCost(List<Integer> cuts, int [][]dp) {

        for(int i = cuts.size()-2; i>=1; i--) {
            for(int j= i; j<=cuts.size()-2; j++) {

                int mini = (int) 1e9;
                for(int k = i; k<=j; k++) {

                    int cost = cuts.get(j+1) - cuts.get(i-1)  +
                                    dp[i][k-1] + 
                                    dp[k+1][j];

                    mini = Math.min(mini, cost);
                }

                dp[i][j] = mini;
            }    
        }
        return dp[1][cuts.size()-2];
    }

    public int minCost(int n, int[] cuts) {
        
        List<Integer> cutsNew = new ArrayList<>();
        cutsNew.add(0);
        for(int e: cuts) {
            cutsNew.add(e);
        }

        cutsNew.add(n);
        Collections.sort(cutsNew); 

        int [][]dp = new int[cutsNew.size()][cutsNew.size()];
        /*
        for(int[] row:dp) {
            Arrays.fill(row, 0);
        }*/
        //return memoCost(1, cutsNew.size()-2, cutsNew, dp);
        return tabCost(cutsNew, dp);
    }
    
}
