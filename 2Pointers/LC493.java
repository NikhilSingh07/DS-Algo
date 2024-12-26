// https://leetcode.com/problems/reverse-pairs/description/

import java.util.ArrayList;
import java.util.List;

public class LC493 {
        // approach 1: try out all the pairs.
    // TC: O(n^2)
    /*
    public int reversePairs(int[] nums) {

        int count =0;


        for (int i=0;i<nums.length; i++){
            for(int j = i+1; j<nums.length; j++) {
                if(nums[i] > 2* nums[j]) count ++;
            }
        }

        return count;
        
    }*/

    // approach 2: using merge sort
    // just before merging use 2 pointers on the left and right sorted subarrays.-> this way i always < j
    // if the condition is true for a particular j; it will be true for the remaining i (as it is sorted left subarray)

    // 40 | 25 | 19 | 12 | 9 | 6 | 2
    //  0    1   2    3    4   5   6
    
    // l                   r               -> (if nums[l] > 2* nums[r] - it will be true for all remaining l) - no need to compute for 40? right? yes
                                                                        //we move r pointer forward and update count;
    // 25 | 40            12 | 19
  
    public int merge(int [] nums, int l, int mid, int r) {

        // logic
        int j = mid+1;
        int count = 0;
        for(int i=l; i<=mid; i++) {  // iterating through the left sub array -> O(n)
              
              while(j<=r && (long)nums[i]>2*(long)nums[j]) { // if coniditon is true, we move forward in the right subarray
                  count+= mid-i+1; //if the condition is true for a particular j; it will be true for the remaining i (as it is sorted left subarray)
                  j++;
              }
        }
        
        List<Integer> list = new ArrayList<>();
        int left = l;
        int right =mid+1;

        while(left<=mid && right <= r) {  // saving the sorted array until one of the pointer cross the boundaries

            if(nums[left] <= nums[right]){
                list.add(nums[left]);
                left++;
            } else {
                list.add(nums[right]);
                right++;
            }
        }

        while(left<=mid) { // saving the remaining left subarray 
            list.add(nums[left]);
            left++;
        }

        while(right<=r) {
            list.add(nums[right]);
            right++;
        }

        for(int i=l; i<=r; i++) {
            nums[i] = list.get(i-l);
        }

        return count;

    }

    public int mergeSort(int[] nums, int l, int r) {
        // base case: if l == r

        if(l>=r) return 0;

        int mid = (l+r)/2;

        int inv = mergeSort(nums, l, mid); // first half
        inv += mergeSort(nums, mid+1, r);

        inv += merge(nums, l, mid, r);

        return inv;
    }

    public int reversePairs(int[] nums) { // using merge sort

          int count= mergeSort(nums, 0, nums.length-1);
          return count;
    }
}
