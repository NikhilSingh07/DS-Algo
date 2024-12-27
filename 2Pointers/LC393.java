// https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=leetcode-75

public class LC393 {

    public boolean isSubsequence(String s, String t) {

        int l = 0; // 0th index of s string
        int r= 0; // 0th index of t string

        if(s.length() == 0 ) return true;

        while(r<t.length() && l<s.length()){

            if(s.charAt(l) == t.charAt(r)){
                l++;
             
            } r++;
        }
        if(l == s.length()) return true; // we have found all the s string chars.
        else return false;

    }
    
}
