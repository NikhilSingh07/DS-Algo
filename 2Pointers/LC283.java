// https://leetcode.com/problems/move-zeroes/?envType=study-plan-v2&envId=leetcode-75

public class LC283 {
    
    // approach 1: use another array to store the non zero elements and maintain a count to fill the reminaing of the array with 0's
    // TC: O(2n)
    // SC: O(n)
   /* public void moveZeroes(int[] nums) {

        int[] temp = new int[nums.length];
        int count =0;

        // storing all the non zero elements in the aux array
        for(int i=0; i<nums.length; i++) { // O(n)

            if(nums[i]!=0) {
                temp[count] = nums[i];
                count++;
            }
        }

        // filling the rem array with 0's
        while(count < nums.length) {
            temp[count] = 0;
            count++;
        }
        // updating the original array
        for(int i=0; i<nums.length; i++) { // O(n)
            nums[i] = temp[i];
        }
    }*/

    // approach 2: using 2 pointers
    // l pointer will point to the zero element and r pointer will point to the non zero element..
    // TC: O(n)
    // SC: O(1)

    public void swap(int l, int r, int [] nums){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;

    }

    public void moveZeroes(int[] nums) {

        int l = 0;
        int r = 0;

        while(r<nums.length){

            if(nums[r]!=0) {
                swap(l,r, nums);
                l++;
                r++;
            }else r++;
        }
    }
}
