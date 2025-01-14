// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// Medium

public class LC153 {

        // Binary Search: -> identify the sorted half-> get min
        //                -> eliminate the sorted half and check on other side
        public int findMin(int[] nums) {
            
    
            int l =0;
            int h = nums.length-1;
            int min = Integer.MAX_VALUE;
    
            while(l<=h) {
    
                int m = l+ (h-l)/2;
    
                // identify the sorted half
                if(nums[l] <= nums[m]) { // left half is sorted
                     
                     min = Math.min(min, nums[l]);
                     // eliminate the left half to search in rigt half.
                     l = m+1;
                } else { // right half is sorted
                     
                     min = Math.min(min, nums[m]);
                     //eliminate the right part to look on the left side
                     h = m-1;
                }
            }
    
            return min;
        }
}
    

