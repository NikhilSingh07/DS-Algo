import java.util.Arrays;

// https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
// BINARY SEARCH: MEDIUM: BLIND 75

public class LC2300 {
    
        // approach 1:  compute each pair and check if their product is >= success
    // TC: O(n)*O(m)
    // TLE

    //approach 2: Sort the potions array ans USE BINARY SEARCH on it.
    // 1 | 2 | 3 |4 | 5

    // if product >= sucess; then we compute and eliminate the right half -> since result would be same for the right half.
    // if product < success; elimimate the left half without computing 
    // TC: O(mlogm) + O(nlogm)
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        
        int n = spells.length;
        int m = potions.length;

        int [] ans = new int[n];

        int ind = 0;
        int pCount = 0;

        Arrays.sort(potions); // mlogm

        // approach 1:
        /*
        for(int i=0; i<n; i++){
            pCount = 0;

            for(int j=0; j<m; j++) {

                if((long)spells[i] * (long)potions[j] >= success) {
                    pCount++;
                }
            }
            ans[ind++] = pCount;
        }
        */
        for(int i =0; i<n; i++) {
            
            pCount =0;
            int l = 0;
            int h = m-1;

            while(l<=h){
                int mid = l+ (h-l)/2;

                if((long)spells[i] * (long)potions[mid] >= success) {
                    pCount = m-mid;
                    h = mid-1;
                } else l= mid+1;
            }
            ans[ind++] = pCount;
        }
        return ans;
    }
    
}
