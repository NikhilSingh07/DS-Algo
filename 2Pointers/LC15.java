package 2Pointers;

// https://leetcode.com/problems/3sum/ -M 

public class LC15 {
    // 1. brute force: generate all possible triplets using 3 for loops. Use a HashSet DS to avoid duplicacy. 
// TC: O(n3 mlogm)
// SC: O(m)
/*
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> ds = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int k=j+1; k<nums.length; k++) {

                    if(nums[i]+nums[j]+nums[k] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);

                        Collections.sort(temp); // 3log3

                        if(ds.contains(temp)) {
                            continue;
                        }else {
                            ds.add(temp);   // log 3
                            ans.add(temp);
                        }
                    }
                }
            }
        }
        return ans;
        
    }*/

//2. Optimization over appraoch 1: Avoid the 3rd for loop using Hashing.
//  since, (a+b+c) = 0, therefore if c = -(a+b), exits in the hashmap, we have our triplets
// TC: O(n^2 logm)
// SC: O(m) + O(n)
/*
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        // storing the values and their frequencies
        for(int i=0; i<nums.length; i++) {  // O(n)
            map.put(nums[i], map.getOrDefault(nums[i], 0) +1);
        }

        System.out.println(map);

        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.get(nums[i]) -1);

            for(int j=i+1; j<nums.length; j++) {
                map.put(nums[j], map.get(nums[j]) -1);

                int c = -(nums[i]+nums[j]);

                if(map.containsKey(c)){
                    if(map.get(c)>0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(c);

                        Collections.sort(temp);

                        if(!set.contains(temp)) {
                            ans.add(temp);
                            set.add(temp);
                           // System.out.println(ans);
                        }
                    } 
                }

                map.put(nums[j], map.get(nums[j])+1 );
            }
            map.put(nums[i], map.get(nums[i])+1 );
        }

        return ans;
    }*/

//3. Most optimal solution: using 2 pointers technique. 

public List<List<Integer>> threeSum(int[] nums) {  

    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);

    for(int i=0; i<nums.length-2; i++) {

        // avoiding the duplicates
        if( i==0 || (i>0 &&nums[i]!= nums[i-1]) ) {
            
            int lo = i+1;
            int hi = nums.length-1;
            // b+c = -a
            int sum = 0 - nums[i];
            while(lo < hi) {

                if(nums[lo]+nums[hi] == sum) {
                    ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                    // avoiding the duplicates
                    while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                    while(lo < hi && nums[hi] == nums[hi-1]) hi--;

                    lo++;
                    hi--;
                
                } else if (nums[lo] + nums[hi] < sum) lo++;
                else hi--;
            }
        }
    }

    return ans;
}
}
