
// https://leetcode.com/problems/4sum/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC18 {

    // approach 1: use 3 for loops for the first 3 elements and use BS to find the last element in the 
//             remaining part of the array.
// TC: O(n^3 logn) + nlogn
// SC: O(n)
/*
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums); // O(nlogn)

        for(int i=0; i<nums.length; i++) { // o(n^3)
            for(int j=i+1; j<nums.length; j++) {
                for(int k = j+1; k< nums.length; k++) {

                    long d = target - ((long )nums[i] + (long )nums[j] + (long)nums[k]);
                    if(d<Integer.MIN_VALUE || d>Integer.MAX_VALUE) continue;

                    // checking if d is present in the remaining part of the array.
                    if(Arrays.binarySearch(nums, k+1, nums.length,(int) d) >= 0) {

                        List<Integer> temp  = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add((int)d);

                       // Collections.sort(temp); // 
                        // System.out.println(temp);

                        // checking for the duplicate results.
                        if(!set.contains(temp) ) { 
                            ans.add(temp);
                            set.add(temp);    // logn 
                        }
                    } 
                }
            }
        }

        return ans;   
    }*/


    // approach 2: use 2 for loops for the first 2 elements and 2 Pointer for the next 2 elements: this way
//             we can avoid using any extra space.
    // TC: O(n^3) + nlogn
    // SC: O(1)


// sort the array.
// run 2 for loops and 2 pointers
// avoid duplicates in the for loops and inside while loop

    public List<List<Integer>> fourSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {

                int lo = j+1;
                int hi = nums.length-1;

                long sum = (long)target - ((long)nums[i] + (long)nums[j]);
                if(sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) continue;

                // -2,-1,0,0,1,2
                //  i  j l     h  
                while(lo<hi) {

                    if(nums[lo] + nums[hi] == sum) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));

                        // skipping the duplicates
                        while(lo<hi && nums[lo] == nums[lo+1]) lo++;
                        while(lo<hi && nums[hi] == nums[hi-1]) hi--;

                        
                    lo++;
                    hi--;
                    
                    } else if(nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }

                // skipping outer loop duplicates
                while(j+1 < nums.length && nums[j] == nums[j+1]) j++;
            }

            while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
        }

        return ans;
    }
    
}
