// https://leetcode.com/problems/find-peak-element/description/
// Medium - Binary Search - Eliminate one of the halfs

public class LC162 {

    
    // approach 1: Linear Seach and check if current element is greater than its left and right elements
    //    -> TC: O(n)
    /*
    public int findPeakElement(int[] nums) {

        int n = nums.length;
        // edge cases
        if(nums.length ==1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        for(int i=1; i<n-1; i++) {

            if(nums[i]>nums[i-1] && nums[i] > nums[i+1]) return i;
        }

        return -1;
    }*/

    // approach 2: Binary Search -> Eliminate one of the halfs at each step.
    // if the array contains a single peak -> element on the left will be in increasing order than peak happen and elements on the 
    // right will be in the decreasing order.
    // -> mid could lie on the peak, or on the left half or on the right half.
    // if mid lie on the dec side - we know for sure peak lie on the left side /\m and right side can be eliminated and vice versa. 

    // it will also work for multiple peaks as we only have to return single one and same concept will be used.
    public int findPeakElement(int[] nums) {

        int n = nums.length;
        // edge cases
        if(nums.length ==1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        int l = 1;
        int h = n-2;

        while(l<=h){
            int m = l +(h-l)/2;

            if(nums[m] > nums[m-1] && nums[m] > nums[m+1]) return m;

            else if(nums[m] < nums[m-1] && nums[m] > nums[m+1]) { // dec side -> \m
                 
                 // eliminate the right side
                 h = m-1;
            } else  { // on the inc side -> m/ 

                // eliminate the left side
                l = m+1;
            }
        }

        return -1;
    }
    
}
