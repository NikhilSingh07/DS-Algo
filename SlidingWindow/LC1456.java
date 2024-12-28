//https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/?envType=study-plan-v2&envId=leetcode-75

// Sliding Window: Pattern 1

public class LC1456 {

        // Brute force: try out all the substring of length k and keep a count on the number of vowels.
    // TC: O(n*k)
    // TLE
    /*
    public int maxVowels(String s, int k) {

        int count =0;
        int maxCount = 0;

        for(int i=0; i<=s.length()-k; i++) {
            count = 0;
            for(int j=i; j<i+k; j++){

                if(s.charAt(j) == 'a' || s.charAt(j) == 'e' || s.charAt(j) == 'i' || s.charAt(j) == 'o' || s.charAt(j) == 'u' ) count++;
                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }*/

    // approach 2: Sliding Window: Pattern 1
    // intially compute the count for first k elements.
    // use window of size k defined by l and r pointers to compute for the remaining elements
    // TC: O(n)
    // SC: O(1)
    public boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c=='a' || c == 'e' || c == 'i' || c== 'o' || c=='u';
    }

    public int maxVowels(String s, int k) {

        int count =0;
        int maxCount = 0;
        int l =0;
        int r = k-1;

        // initially computing for the first k elements.
        for(int i=0; i<k; i++) {
            if(isVowel(s.charAt(i))) count++;
        }

        maxCount = count;

        // computing for the remaining k size window
        while(r<s.length()-1){

            if(isVowel(s.charAt(l))) count --;
            l++;
            r++;
            if(isVowel(s.charAt(r))) count ++;

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
    
}
