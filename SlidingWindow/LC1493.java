public class LC1493 {

//https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=study-plan-v2&envId=leetcode-75

// Sliding window: Pattern 2
    
    // approach 1: try out all the subarrays that have atmost 1 0
    // if no zero found then, return maxCount-1;
    // TC: O(n^2), Accepted.

    /*
    public int longestSubarray(int[] nums) {

        int zeros = 0;
        int count = 0;
        int maxCount =0;
    
        // 0 | 1 | 1 | 1 | 0 | 1 | 1 | 0 | 1
        //             i                   j 
        // zeroes = 0
        // count = 0
        // maxCount = 5
        for(int i=0; i<nums.length; i++) {
            count =0;
            zeros = 0;
            for(int j=i; j<nums.length; j++) {

                if(nums[j] == 0)  {
                    zeros++;
                }
 
                if(zeros <= 1) {
                    if(nums[j] == 1) count++;
                    maxCount = Math.max(maxCount, count);
                } else break;

            }
        }

        return maxCount == nums.length? maxCount-1:maxCount;
    }*/

    // approach 2: Sliding window: Pattern 2
    // valid condition: atmost 1 zero - expand the window-> r++
    // invalid condition: zero > 1 - shrink the window -> l++

    public int longestSubarray(int[] nums) {

        int zero = 0;
        int l = 0;
        int r=0;
        int maxCount =0;

        // 0 | 1 | 1 | 1 | 0 | 1 | 1 | 0 | 1
        //                     l 
        //                                    r  
        // zero = 1
        // maxCount = 5

        while(r<nums.length) {
            
            if(nums[r] == 0) zero++;

            // for invalid conition: shrink the window
            while(zero > 1) {
                if(nums[l] == 0) zero--;
                l++;
            }

            // valid condition: expand the window
            maxCount = Math.max(maxCount, (r-l));
            r++;
        }

        return maxCount == nums.length ? maxCount-1: maxCount;
    }
    
}
