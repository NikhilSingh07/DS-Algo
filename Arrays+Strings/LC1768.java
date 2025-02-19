public class LC1768 {

    // https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75   
    // Easy 
    // TC: O(n+m)
    // SC: O(n+m) for the output
    public String mergeAlternately(String word1, String word2) {

        // iterate until I finish one of the strings.

        StringBuilder sb = new StringBuilder();
        int n = word1.length();
        int m = word2.length();

        int i = 0;
        int j=0;
        while(i<n && j<m) {

            sb.append(word1.charAt(i++));
            sb.append(word2.charAt(j++));
        }

        while(i<n) { // append the remaining chars from the word1
            sb.append(word1.charAt(i++));
        }

        while(j<m) {
            sb.append(word2.charAt(j++));
        }

        return sb.toString();
    }
    
}
