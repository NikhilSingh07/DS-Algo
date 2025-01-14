public class LC540 {
    //https://leetcode.com/problems/single-element-in-a-sorted-array/
    // MEDIUM
        // approach 1: iterate the array -> if element is not equal to left or right element -> that's out ans
        // TC: O(n)
        /*
        public int singleNonDuplicate(int[] nums) {
    
            int n = nums.length;
            int ans = 0;
            //edge cases
            if(nums.length == 1) return nums[0];
            if(nums[0]!= nums[1]) return nums[0];
            if(nums[n-1]!= nums[n-2]) return nums[n-1];
    
            for(int i=1; i<n-1; i++) {
    
                if(nums[i]!= nums[i-1] && nums[i]!= nums[i+1]) {
                    ans= nums[i];
                    break;
                }
            }
    
            return ans;
        }*/
    
        // approach 2: Use Binary Search -> Eliminate one of the sides at each step
        //                 -> (even, odd)  indexes -> single element is present on the right side - eliminate the left side
        //                 -> (odd, even)  indexes -> single element is present on the left side - eliminate the right side
        // TC: O(logn)
    
        public int singleNonDuplicate(int[] nums) {
    
            int n = nums.length;
            int ans = 0;
            //edge cases
            if(nums.length == 1) return nums[0];
            if(nums[0]!= nums[1]) return nums[0];
            if(nums[n-1]!= nums[n-2]) return nums[n-1];
    
            int l=1;
            int h= n-1;
    
            while(l<=h) {
                int m = l+(h-l)/2;
    
                if(nums[m]!=nums[m-1] && nums[m]!=nums[m+1]) {
                    ans = nums[m];
                    break;
                }
    
                // (even, odd) -> eliminate the left part
                if((m%2 == 1) && nums[m-1] == nums[m] ||
                   (m%2== 0) && nums[m+1] == nums[m]) {
                    l = m+1;
                } else { // eliminate the left part
                    h = m-1;
                }
            }
    
            return ans;
    
        }

}
