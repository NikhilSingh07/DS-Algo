// https://leetcode.com/problems/guess-number-higher-or-lower/
// Binary Search: EASY

public class LC374 {

    public int guessNumber(int n) {

        
        int l = 1;
        int h = n;

        while(l<=h) {
            int m = l + (h-l)/2;
            int num = guess(m);
            if(num == 0) {
                return m;
            } else if(num == -1) {
                h = m-1;
            } else l = m+1;
        }

        return 0;
    }
    
}
