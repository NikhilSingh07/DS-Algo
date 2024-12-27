import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/max-number-of-k-sum-pairs/?envType=study-plan-v2&envId=leetcode-75
public class LC1679 {

     // approach 1: try of all the pairs satisfying the condition.
    //            //update the valid pairs and change their values to Integer.MIN_VALUE
    /*
    public int maxOperations(int[] nums, int k) {

        int count =0;
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i]> k || nums[j]>k) continue;

                int sum = nums[i]+nums[j];
                if(sum == k) {

                    nums[i] = Integer.MIN_VALUE;
                    nums[j]= Integer.MIN_VALUE;
                    count++;
                }
            }
        }

        return count;  
    }*/

    // approach 2: sort the list and use 2 pointers
    // if sum is > k; we need a smaller ans so we decreement the r pointer
    // eles if sum < k;  we need a bigger ans amd we increement the l pointer

    // TC: O(nlogn)
    // SC: O(1)
    /*
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int l = 0;
        int r = nums.length-1;
        int count =0;

        while(l<r) {

            if(nums[l]+nums[r] == k) {
                count++;
                l++;
                r--;
            
            } else if (nums[l]+nums[r] > k) {
                r--;
            } else l++;
        } 
        return count;
    }*/

    // approach 3: use a hashMap and check if k-a exits; a+b = k -> b = k-a

    // TC: O(nlogn) logn - hashmap worst case time.
    public int maxOperations(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int count=0;

        for(int i=0; i<nums.length; i++) {

            int b = k - nums[i];

            if(map.containsKey(b) && map.get(b)>0) { // we have a pair
                count++;
                
                map.put(b, map.get(b)-1);
                //if(map.get(b) == 0) map.remove(b);
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            }   
        }
        return count;
    }
    
}
