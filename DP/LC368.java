//https://leetcode.com/problems/largest-divisible-subset/
//Largest Divisible Subset

// DP on LIS: Tabulation + hash array to store the prev element indices for tracing it back to print

public class LC368 {


        // USING THE LIS PATTERN LOGIC
    // As in subsets, order doesnot matter
    // we can sort it
    // The problem becomes Longest Divisible subsequenc


    // TC: O(n^2) + O(nlogn) + O(n)
    // SC: O(2n)

    public List<Integer> largestDivisibleSubset(int[] nums) {
        
        int n = nums.length;
        int LIS = 0;
        int LISindex = 0;
        int []dp = new int[n];
        int []parent = new int[n];
        List<Integer> ls = new ArrayList<>();

        Arrays.sort(nums);
        Arrays.fill(dp, 1);

        for(int i=0; i<n; i++) {
            parent[i] = i;
            for(int j=0; j<i; j++) {

                if(nums[i] % nums[j] == 0) {

                    if(dp[j]+1 > dp[i]) { // if the len of max
                        dp[i] = dp[j]+1;
                        parent[i] = j; // storing the address of prev element
                    }    
                }
            }

            if(LIS < dp[i]) {
                LIS =  dp[i];
                LISindex = i;
            }
        }

        ls.add(nums[LISindex]);

        while(parent[LISindex]!= LISindex) {

            LISindex = parent[LISindex];   
            ls.add(nums[LISindex]);
        }

        return ls;
    }
    
}
