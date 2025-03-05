public class LC213 {
    

       // House robber 1: code  LC 198
    // Tabulation with space optimisation
    // TC: O(n)
    // SC:(1)
    private int spaceOptimisedRob(int n, int [] nums){  

        int prev = nums[0];
        int prev2 = 0;

        for(int i=1; i<=n; i++) {

            int pick = nums[i];
            if(i>1) pick += prev2; // n-2

            int not_pick = 0 + prev; // n-1

            int curr = Math.max(pick, not_pick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    // House robber 1 logic
    // 1st and last elements can't be together
    // create 2 temp arrays: 1 not having 1st and another not having last and return max(both)
    public int rob(int[] nums) {



        int n = nums.length; 
        if(n == 1) return nums[0];
        int [] temp1 = new int[n-1];
        int [] temp2 = new int[n-1];

        int pos1 = 0;
        int pos2 = 0;
        for(int i=0; i<n; i++) {
            if(i != 0) temp1[pos1++] = nums[i];
            if(i != n-1) temp2[pos2++] = nums[i]; 
        }

        return Math.max(spaceOptimisedRob(temp1.length-1, temp1), spaceOptimisedRob(temp2.length-1, temp2));
    }
}
