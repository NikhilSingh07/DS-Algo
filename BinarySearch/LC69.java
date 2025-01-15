public class LC69 {

    //https://leetcode.com/problems/sqrtx/
    // Easy
    
    // approach 1: Do linear search from 1 to n and check if i*i <= n - > TC: ~O(n)
    // approach 2: Binary Search
    public int mySqrt(int x) {
        
        long l = 1;
        long h = x;

        while(l<=h) {
            long m = l + (h-l)/2;
            if(m *m <= x) {
                 l = m+1;
            } else h = m-1;
        }

        return (int)h;
    }
    
}
