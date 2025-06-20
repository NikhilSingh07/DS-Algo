// https://leetcode.com/problems/burst-balloons/
// Balloon Burst

// DP on Partitions

public class LC312 {

    
    // If we partition the problem based on from finding the first possible balloon to be burst to finding the last possible baloon,
    // the divided subproblems ends up depending on each other
    // (s1) (s2)
    // Both subproblems will depend on each other to either get the left balloon or right balloon.

    // To overcome, this we rather try to find the last possible balloon to be burst and we go up from there.
    // This way we found that the subsproblems are independent to each other, and, hence, the problem can be solved using this approach.

    // Recursion: TC: Exponential
    // SC: O(n)

    // Memoization: TC: O(n^3)
    // SC: O(n^2) + O(n)


    private static int memoBalloons(int i, int j, List<Integer> nums, int [][]dp){

        // Base case: we can't divide the problems into subproblems the moment i crosses j
        if(i>j) return 0;

        if(dp[i][j] !=-1) return dp[i][j];

        // Try out all possible ways + partition to burst the balloons going from bursting the last balloon
        int maxi = - (int) 1e9;
        for(int ind = i; ind<=j; ind++) {

            // 1, [3, 1, 5, 8], 1
            // 1, [5], 1 
            int cost = nums.get(i-1) * nums.get(ind) * nums.get(j+1)  + //
                                    memoBalloons(i, ind-1, nums, dp) + 
                                    memoBalloons(ind+1, j, nums, dp);

            maxi = Math.max(maxi, cost);
        }

        return dp[i][j] = maxi;
    }  

    // Tabulation | Bottom up
    // TC: O(n^3)
    // SC: O(n^2)
    private int tabBalloons(List<Integer> nums, int [][]dp) {

        // bottom case: will gets covered by initialising the dp array with 0s
        int m = nums.size()-2;

        for(int i = m; i>=1; i--) {
            for(int j = i; j<=m; j++) {

                // Try out all possible ways + partition to burst the balloons going from bursting the last balloon
                int maxi = - (int) 1e9;
                for(int ind = i; ind<=j; ind++) {

                    // 1, [3, 1, 5, 8], 1
                    // 1, [5], 1 
                    int cost = nums.get(i-1) * nums.get(ind) * nums.get(j+1)  + //
                                            dp[i] [ind-1] + 
                                            dp[ind+1][j];

                    maxi = Math.max(maxi, cost);
                }

                dp[i][j] = maxi;
            }
        }

        return dp[1][m];
    }

    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Step1: Appending 1 at the begining and at the end
        List<Integer> ls = new ArrayList<>();
        ls.add(1);

        for(int e: nums) {
            ls.add(e);
        }
        ls.add(1);

        int [][]dp = new int[n+2][n+2];
        /*
        for(int []row: dp){
            Arrays.fill(row, -1);
        }
        return memoBalloons(1, n, ls, dp);*/
        return tabBalloons(ls, dp);
    }
    
}
