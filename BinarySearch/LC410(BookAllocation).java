// Book Allocation Problem

// minimum of the largest possible sum is max(arr)->10, if k == n
// maximum of the largest possible sum is summation(arr)-> 32
// our answer lie somewhere between 10 and 32.

// approach 1: Linear search from max to summation. TLE -> TC: O(summtaion - max)*O(n)
// approach 2: Binary Seach: O(nlogn) 
class Solution {

    public boolean canWeSplit(int[] nums, int splitSum, int k){

        int subCount =1;
        int sum =0;

        for(int i=0; i<nums.length; i++) {

            if(subCount > k) break;
            if(nums[i] + sum <= splitSum) {
                sum += nums[i];
            } else {
                subCount++;
                sum  = nums[i];
            }
        }

        if(subCount <= k) return true;
        else return false;
    }

    public static int getMax(int[] nums){
        int max = Integer.MIN_VALUE;

        for(int i=0; i<nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public static int getSummation(int nums[]) {
        int sum = 0;

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }

    public int splitArray(int[] nums, int k) {

        int low = getMax(nums);
        int high = getSummation(nums);
        int ans =-1;

        if(k > nums.length) return -1;

        //approach 1: Linear search from max to summation. TLE -> TC: O(summtaion - max)*O(n)
        /*
        for(int i= low; i<=high; i++) {
            if(canWeSplit(nums, i, k)){
                ans = i;
                break;
            }
        }*/

        // approach 2: Binary Seach: O(nlogn) 
        while(low<=high) {
            int mid = (low+high)/2;
            if(canWeSplit(nums, mid, k)) {
                ans = mid;
                high = mid-1;
            } else low = mid+1;
        }

        return ans;
    }
}