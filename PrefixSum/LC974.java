public class LC974 {

    // approach 1: Using prefix sum: storing the remainder and freq in the map
    // TC: O(n)
    // SC: O(k)

    // POINT TO BE NOTED**
    // In arithmetic, the remainder of a mod k is always between 0 and k
    // 0<= r < k
    // if k is 5, remainder can be any pos int from 0 to 4
    // In java if a is neg, it gives neg remainder, which is arithmatically incorrect
    // therefore, we can add K to the remainder, if a is negative

    public int subarraysDivByK(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>(); // to store remainder and freq
        int count = 0;
        int prefixSum = 0;
        map.put(0,1); // if remainder becomes 0

        for(int i=0; i<nums.length; i++) {

            prefixSum += nums[i];
            int remainder = prefixSum % k;
            
            // if remainder if -ve, add k to it to make it valid,
            if(remainder <0) remainder += k; 

            if(map.containsKey(remainder)) {
                count += map.get(remainder);
            }

            // update the map
            map.put(remainder, map.getOrDefault(remainder, 0)+1);
        }

        return count;
        
    }
    
}
