import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/continuous-subarray-sum/
// Prefix sum: good problem
// storing the remainder of the prefix sum in the map
// TC: O(n)
// SC: O(n)

public class LC523 {


        public boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>(); // to store remainder and index
        
        map.put(0,-1); // if remainder of prefix sum is 0 and subarray starts at 0 index.
        // 0 | 1 | 2
        // For subrray(0,1) len would be 1 (1-0) for index 0. That's why -1

        int prefixSum = 0;
        int remainder = 0;

        // 23 | 2 |4 | 6 | 7

        // 23 % 6 = 5
        // 25 % 6 = 1
        // 29 % 6 = 5 

        // we added a multiple of k. Thats why we get the same remainder. [2,4] = 6 % 6 = 0
        // even if we added 2 0's instead of 2 and 4. That would also be correct answer [0 , 0]

        // Therefore, if get the remainder again. It means we have a multiple of k inbetween.

        for(int i=0; i<nums.length; i++) {

            prefixSum += nums[i];
            remainder = prefixSum % k;

            if(map.containsKey(remainder)) {

                // calculate the len of the subarray
                int len = i - map.get(remainder);

                if(len >=2) return true;
            } else map.put(remainder, i);
        }

        return false;


    }
    
}
