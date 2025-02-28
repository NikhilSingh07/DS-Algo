import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/frog-jump/
// HARD
public class LC403 {


        // Recursion: O(3^n)
    // SC: O(n)

    // memoization: O(n^2): computing all the combintaions of ind and jump (can become as large as n-1): total no of unique states-> O(n^2)
    // SC: O(n) + O(n^2)
    private boolean isPossible(int ind, int prev_jump, Map<Integer, Integer> map, int []stones, Boolean [][]dp) {

        if(ind == stones.length-1) { // base case
            return true;
        }
        //edge case
        if(stones[1] != 1) return false; // first stone is not 1.

        if(dp[ind][prev_jump]!= null){

            return dp[ind][prev_jump];
        }
        // current jump could be pj-1, pj, pj+1
        for(int cj = prev_jump-1; cj<= prev_jump+1; cj++) {
            if(cj > 0) { // otherwise frog will not move forward

                if(map.containsKey(stones[ind] + cj)) {
                     if (isPossible(map.get(stones[ind]+cj), cj, map, stones, dp )) {
                        
                        return dp[ind][cj] = true;
                     }
                } 
            }
        }

        return dp[ind][prev_jump] = false;
    }

    // we have 3 choices of jump to make at each stone: try out all possible combinaions
    public boolean canCross(int[] stones) {
        
        int n = stones.length;
        Map<Integer, Integer> map = new HashMap<>();
        Boolean [][]dp = new Boolean[n][n]; // it can make a jump of n-1 index

        for(int i=0; i<n; i++) { // storing values and their indices
            map.put(stones[i], i);
        }

        return isPossible(0, 0, map, stones, dp);
    }


    
}