// https://leetcode.com/problems/palindrome-partitioning-ii/
// Palindrome Partitioning II

// DP Front Partition

public class LC132{
    
    // Recursion: TC: Exponential
    // SC: O(n) 

    // Memoization: TC: O(n^2)
    // SC: O(n) + O(n)

    // Partioning the string from the front only
    private int memoCut(int i, String s, int[]dp){

        // base case: reaching the end of the string
        if(i == s.length()) return 0;

        if(dp[i]!= -1) return dp[i];

        int mini = (int)1e9;
        // Try out all the partitions starting from index i
        for(int j = i; j<s.length(); j++) {

            if(isPalindrome(i, j, s)) {
                int cost = 1 + memoCut(j+1, s, dp);
                mini = Math.min(mini, cost);
            }
        }

        return dp[i] =  mini;
    }

    // TC: O(n^2)
    // SC: O(n)
    private int tabCuts(String s){
        int n = s.length();
        int []dp = new int[n+1];


        //handling the state variables: i
        for(int i = n-1; i>=0; i--) {
            int mini = (int)1e9;

            // Try out all the partitions starting from index i
            for(int j = i; j<n; j++) {

                if(isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j+1];
                    mini = Math.min(mini, cost);
                }
            }
            dp[i] =  mini;   
        }
        
        return dp[0]-1; // -1 bec it is making a cut at the last index as well
    }


    private boolean isPalindrome(int i, int j, String s) {

        while(i < j) {
            if(s.charAt(i)!= s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }
    public int minCut(String s) {

        /*
        int n = s.length();
        int []dp = new int[n];
        
        Arrays.fill(dp, -1);
        // -1 bec it is making a cut at the last index as well
        // a | a | b |
        // therefore, subtracting the last cut
        return memoCut(0, s, dp) - 1;

        */
        return tabCuts(s);

    }

}
