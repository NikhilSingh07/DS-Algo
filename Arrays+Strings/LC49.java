// String: Group Anagrams
// https://leetcode.com/problems/group-anagrams/description/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC49 {

        // approach 1: using mapping on stored strings with their anagrams.
    // TC: O(n*mlogm)
    // SC: O(n*m)
    /*
    public List<List<String>> groupAnagrams(String[] strs) {

        // mapping sorted strings to their corresponding anagrams
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        int n = strs.length;

    
        for(int i=0; i<n; i++) {  // O(n*mlogm)

            char[] chars = strs[i].toCharArray(); // m
            Arrays.sort(chars); // mlogm
            String st = new String(chars);

            map.putIfAbsent(st, new ArrayList<>()); 
            map.get(st).add(strs[i]);  // logm

        }

        for(List<String> st: map.values()) { //O(n*m)
             ans.add(st);
        }

        return ans;
    }*/

    // approach 2: avoid sorting using int array of size 26 to store chars frequencies and then iterate to form a word.
    // TC: O(n*m*log(26))
    // SC: O(n*m)
    private String generateWord(String s) { /
        int [] arr = new int[26]; // stoing the lowercase letters
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); i++) { //O(m)
            arr[s.charAt(i) - 'a']++;
        }

        // I have the freq of the letter. 
        // generating the word
        for(int i=0; i<arr.length; i++) { // O(26)*O(m)

            if(arr[i] > 0) {
                sb.append(String.valueOf((char) ('a' + i)).repeat(arr[i]));
            }
        }

        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        int n = strs.length;

        for(int i=0; i<n; i++) {

            // store the freq of letter and form a sorted word using array
            String st = generateWord(strs[i]);

            map.putIfAbsent(st, new ArrayList<>()); 
            map.get(st).add(strs[i]);  // logm
        }

        for(List<String> st: map.values()) { //O(n*m)
             ans.add(st);
        }

        return ans;
    }
    
}
