package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/?envType=study-plan-v2&envId=leetcode-75
// MEDIUM: Letter combinations of a phone number
public class LC17 { 

  // TC: 3^n, worst case 4^n: total no. of combinations
 // SC: O(n): depth of the recurison tree.
    public void findCombinations(String digits,Map<Character, String> map,StringBuilder sb, List<String>ans, int ind) {

        // base case: 
        // ind repesents the index of the digits
        if(ind == digits.length()){
            ans.add(sb.toString());
            return;
        }

        String letters = map.get(digits.charAt(ind));

        // i represents the index of the letters
        for(int i=0; i<letters.length(); i++) {

            sb.append(letters.charAt(i));
            // recursion call
            findCombinations(digits, map, sb, ans, ind+1);
            // backtrack
            sb.deleteCharAt(sb.length()-1);
        }
    }
    public List<String> letterCombinations(String digits) {

        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList();
        int ind = 0;

        if(digits.length()==0) {
            return ans;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");



        findCombinations(digits, map, sb, ans, 0);
        return ans;
    }
    
}
