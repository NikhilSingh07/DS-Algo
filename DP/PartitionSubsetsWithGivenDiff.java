public class PartitionSubsetsWithGivenDiff {

    
	// same problem as Count subsets with sum k (can have 0's)
	//https://www.naukri.com/code360/problems/count-subsets-with-sum-k_3952532?leftPanelTabValue=PROBLEM

	// TC: O(n*tar)
	// SC: O(tar)
	private static int optimisedSubsets(int n, int t, int[] arr) {

        int [] prev = new int[t+1];
        int MOD = (int)(1e9 + 7);

        // bottom case: when n = 0
        for(int tar=0; tar<=t; tar++) {
            prev[tar] = 0;
        }
        // when tar = 0 and arr[0] = 0
        if(arr[0] == 0) {
            prev[0]  =2;
        } else prev[0] = 1;

        // if tar =  arr[0]
        if(t >= arr[0] && arr[0]!=0) {
            prev[arr[0]] = 1;
        }

        /*
        In the for loop, ind starts from 1 as we have considered all tar(0 to t) for ind 0
        tar starts from 0, as in base case we have only considered for ind 0,
        remaining indices are still left to be considered
        for ex: if t = 0 for ind>=1? we will miss out on these cases
        
        */

        // handling the changing variables
        for(int ind = 1; ind<=n; ind++) {
            int temp[] = new int[t+1];
            for(int tar = 0; tar<=t; tar++) {

                int not_pick = prev[tar];
                int pick = 0;
                if(tar >= arr[ind]) {
                    pick = prev[tar-arr[ind]];
                }

                temp[tar] = (pick+ not_pick)% MOD;
            }
            prev = temp;
        }

        return prev[t];
    }


	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.

		// s1 s2
		// count (s1 >= s2) && (s1 -s2) = D

		// s1 + s2  = total sum
		// s2 = total_sum -s1
		// s1 - total_sum +s1 = D
		// 2s1 - total_sum = D
		// 2s1 -D = total_sum
		// s1 = (total_sum +D)/2;

		// Count the subsets where total sum is s1?

		// calculating total sum
		int ts= 0;
		for(int i=0; i<n; i++) {

			ts += arr[i];
		}
		//edge cases: ts- d > =0 && even 
		if(d  > ts || (ts - d) %2!=0) return 0;
		return optimisedSubsets(n-1, (ts+d)/2, arr);


	}
    
}
