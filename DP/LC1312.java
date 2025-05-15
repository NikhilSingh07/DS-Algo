public class LC1312 {


    // Minimum Insertion Steps to Make a String Palindrome
    // if we concatinate string s in reverse order, it will be a palindrome
    // number of insertion : max -> len(s)

    // But we just compute the len of longest palindrome subsequence
    // "mbadm" : longest palindrome subsequence -> "mam" (len 3)
    //  n - 3 => 5-3 =2
    //  n - len(longest palindrome subsequence)

    
    // Longest palindrome subsequnce can be computed by reversing the string s and computing the LCS of s and rev(s)

            // approach 1:  naive solution: Generare all the subsequences using powerset method
    //                              iterate through subseqences and check for palindrome
    //                              return the length of longest palindrom 
    // TC: O(2^n * n )
    // SC: O(2^n *n )

    // Reverse the string and compute the longest common subsequence: SAME AS LCS
    // Tabulation with space optimisation
    // TC: O(n*m)
    // SC: O(m)

    private int tabLCS(int n, int m, String text1, String text2) {


        int [] prev = new int[m+1];

        // handling the bottom case: by shiting all the indices by 1
        // if n ==0, m can be of range 0 to m
        for(int j = 0; j<=m; j++) {
            prev[j] = 0;
        }

        for(int i=1; i<=n; i++) {
            int curr[] = new int [m+1];
            for(int j = 1; j<=m; j++) {

                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    curr[j] = 1 + prev[j-1];
                } else {
                    curr[j] = 0 + Math.max(prev[j], curr[j-1]);
                }
            }

            prev = curr;
        }

        return prev[m];
    }


    public int longestPalindromeSubseq(String s) {
        // Reverse the String s to make second string

        int n = s.length();

        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1; i>=0; i--) {
            sb.append(s.charAt(i));
        }

        return tabLCS(n, n, s, sb.toString());
    }


    public int minInsertions(String s) {
        
        int n = s.length();
        return n - longestPalindromeSubseq(s);
    }

    
}
