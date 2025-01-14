public class LC33 {
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    // Medium
    class Solution {

        // Binary Seach: for rotated array. identify the sorted half. check if element lie there -> otherwise eleminate the space.
        // TC: O(logn)
        public int search(int[] nums, int target) {
            // -> 4 | 5 | 6 | 7 | 0 | 1 | 2
    
            int l = 0;
            int h = nums.length-1;
    
            while(l<=h) {
    
                int m = l + (h-l)/2;
    
                if(nums[m] == target) return m;
    
                // identifying the sorted half
                // left half is sorted
                if(nums[l] <= nums[m]) {
    
                    // check if element lie in the left half
                    if(nums[l] <= target && nums[m] >= target) {
                        h = m-1;
                    } else l =  m+1;
    
                } else { // right half is sorted
    
                    // checking if element lie in the right half  
                    if(nums[m] <= target && nums[h] >= target) {
                        l = m+1;
                    } else h = m-1;  
                }
            }
    
            return -1;
        }
    }
    
}
