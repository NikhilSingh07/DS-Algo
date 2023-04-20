package Hashing.subArrays_SumEqualsK;
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int curr = 0;
        int count = 0;
         
        for(int i=0; i<nums.length;i++) {   // O(n) time and O(n) space.

            curr+= nums[i];
            
            if(curr == k) count++;

            if(map.containsKey(curr-k)) {
                count += map.get(curr-k);
            }

            map.put(curr, map.getOrDefault(curr,0)+1);
        }
 
        return count;
         
    }
}
