public class LC1283 {
//    https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
// Binary search: find min Pattern
    
    // Same as koko eating banana problem.

    // Approach 1: Linear search
    // Linear search on the range: 1 to max
    // TLE
    // TC: O(max)*O(n)

    // Approach2 : Binary search on the given range: 1 to max
    // TC: O(max)*O(n)
    public boolean isDivisor(int[] nums, int no, int threshold) {

        int sum = 0;

        for(int i=0; i<nums.length; i++) {
            sum += Math.ceil((double)nums[i]/(double)no);
        }

        if(sum <= threshold) return true;
        else return false;
    }

    public int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;

        for(int i=0; i<nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public int smallestDivisor(int[] nums, int threshold) {

        int max =  getMax(nums);

        // divisor lie between 1 to max(nums)
        /*
        for(int i=1; i<=max; i++) {
            if(isDivisor(nums, i, threshold)) return i;
        }*/

        int l = 1;
        int h = max;
    // int ans = -1;

        while(l<=h) {

            int m = l + (h-l)/2;

            if(isDivisor(nums, m, threshold)) {
            //  ans = m;
                h = m-1;
            } else {
                l =m+1;
            }
        }

        return l;
    }
}
