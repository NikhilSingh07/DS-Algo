import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/

//2901. Longest Unequal Adjacent Groups Subsequence II

// DP ON LIS: LIS pattern
// LONGEST CONDITION SUBSEQUENCE

public class LC2901 {

    // can't pick a pair, where adjacent elements in the group array are equal
    // words should be equal in length  + distance == 1

    // USING THE LIS print Pattern logic
    // TC: O(n^2)
    // SC: O(2n)
    private static int getHammingDist(String s, String t){ 

        if(s.length()!=t.length()) return 0; // no need to compute
        int n = s.length();

        int i =0;
        int j =0; 
        int dist = 0;

        while(i < n || j <n) {
            if(s.charAt(i) != t.charAt(j)) dist++;

            i++; j++;
        }

        return dist;
    }
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        
        int n = words.length;

        int dp[] = new int[n];
        int parent[] = new int[n];
        List<String> ls = new ArrayList<>();

        int LIS = 0;
        int LISindex = 0;

        Arrays.fill(dp, 1);

        for(int i=0; i<words.length; i++) {
            parent[i] = i;

            for(int j=0; j<i; j++) {

                if (groups[i] != groups[j] &&
                    getHammingDist(words[i], words[j]) == 1 &&
                    words[i].length() == words[j].length()) {

                        if(dp[i] < dp[j]+1) {
                            dp[i] = dp[j] +1; // storing the len of LIS
                            parent[i] = j;  // address of prev element in LIS
                        }
                    }
            }
            if(LIS < dp[i]) {
                LIS = dp[i];
                LISindex = i;
            }
        }

        ls.add(words[LISindex]);

        while(parent[LISindex]!=LISindex) {

            LISindex = parent[LISindex];
            ls.add(words[LISindex]);
        }

        Collections.reverse(ls);
        return ls;
    }
    
}
