public class LC34{
 
    // 5 | 7 | 7 | 8 | 8 | 10
    // 0   1   2   3   4   5

    // for the first position: lower bound 
    // for the last pos: upper bound
    public int findLowerBound(int [] nums, int target) {

        int low =0;
        int high = nums.length-1;
        int ans = nums.length; // if all the elements are less than target, then we will return last indx+1
        
        // 5 | 7 | 7 | 8 | 8 | 10
        // 0   1   2   3   4   5
        //             l   m    h

        while(low <= high) {

            int mid =  (low+high)/2; // (low+high)/2

            if(nums[mid] >= target) {
                ans =  mid;
                high = mid-1;  // we will try to find a smaller ans
            } else low = mid+1;
        }

        return ans;

    }

    public int findUpperBound(int[] nums, int target) {

        int low =0;
        int high = nums.length-1;
        int ans = nums.length; // incase, if all the elements are less than target

        // 5 | 7 | 7 | 8 | 8 | 10
        // 0   1   2   3   4   5
        // l       m           h 
        while(low <= high) {

            int mid = (low+high)/2;

            if(nums[mid] > target) {
                ans = mid;
                high = mid -1;
            } else low = mid+1;
        }
        return ans-1;
    }

    /* 
       5 | 7 | 7 | 8 | 8 | 10
       0   1   2   3   4    5
    */

    public int[] searchRange(int[] nums, int target) {
        
        int[] ans = new int[2];
        int first = findLowerBound(nums, target);

        // handling the edge cases
        // if lb is not present or lb is not the same element as target
        if(first == nums.length || nums[first]!=target ) return new int[]{-1, -1};

        int last = findUpperBound(nums, target);
        ans[0] = first;
        ans[1] = last;
        
        return ans;
    }
    
}