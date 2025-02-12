package Backtracking;

import java.util.ArrayList;
import java.util.List;


// https://leetcode.com/problems/combination-sum-iii/?envType=study-plan-v2&envId=leetcode-75
// Backtracking: Combination Sum 3
public class LC216 {

    // TC: O(2^9)
    // SC: O(k): Depth of the recursion tree

    public void findCombinations(int k, int n, List<List<Integer>> ans, List<Integer> ds, int ind) {

        //base case: 
        if(ds.size() == k) {
        //    System.out.println(ds);
            if(n == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
    
    // Either we pick an element or not pick it.    
        for(int i = ind; i<=9; i++) {
            if(i > n ) break; // not possible to get combinations
            
            // recurion call
            ds.add(i);
            findCombinations(k, n-i, ans, ds , i+1);
            // backtrack
            ds.remove(ds.size()-1);  
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int ind = 1;
        findCombinations(k, n, ans, ds, ind);

        return ans;
    }
    
}
