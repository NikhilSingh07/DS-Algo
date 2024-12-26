
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

public class LC26 {
    
     //1. Brute force: use a hashing to store the unique elements
    //   1 | 1 | 2 -> 
    // TC: O(n logn)
    // SC: O(n) - worst case.
    // can optimize the space. 
    /*
    public int removeDuplicates(int[] nums) {

        Set<Integer> set = new LinkedHashSet<>();
        for(int i=0; i<nums.length; i++) { // O(n)
                set.add(nums[i]);  // (log k)- in worst case
            
        }
        int index = 0;
        for(int i : set) {

            nums[index] = i;
            index++;
        }

        return set.size();
    }*/

    public void swap(int l, int r, int[] arr) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    //2. Using 2 Pointers
    // 0 | 0 | 1 | 1 | 1 | 2 | 2 | 3 | 3 | 4
    // l   r
    // TC: O(n)
    // SC: O(1)
    public int removeDuplicates(int[] nums) {

        int l = 0; 
        int r = 0;
       // int count =0;

        if(nums.length == 1) return 1;

        while(r<nums.length) {

            if(nums[l] == nums[r]) r++;
            else {
                l++;
                swap(l, r, nums);
                //count++;
                r++;
            }
        }
        //count +1;
        // OR
        return l+1;
    }
    
}
