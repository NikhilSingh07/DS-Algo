public class LC35_LowerBound {
    // lower bound problem
    // TC: O(logn)
    public int searchInsert(int[] nums, int target) {

        // 1 | 3 | 5 | 6
       //  0   1   2   3
       //  l   m       h 

       int low = 0;
       int high = nums.length-1;
       int ans = nums.length; 

       while(low<= high) {

            int mid = low + (high-low)/2;

            if(nums[mid] >= target) {
                ans= mid;
                // find a smaller ans as we narrow down the search space
                high = mid-1;
            } else low = mid+1;
       }

       return ans;
    }
}
