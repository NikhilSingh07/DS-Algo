//https://leetcode.com/problems/koko-eating-bananas/description/
// Medium: Binary Seach: find min pattern

public class LC875 {

    // appraoch 1: use linear search on min bananas per hour ->  it will range from 1 to max(arr)
    // TC: O(max) * O(n) 
    // TLE

    // approach 2: Binary search on bananas per hour- as we know the range: 1 to max(arr).
    // TC: O(log max) * O(n)
    private boolean canSheFinish(int[] piles, int b_per_hour, int h) {
        int totalHours = 0;

        for(int i=0; i<piles.length; i++) {
            totalHours += Math.ceil((double)piles[i]/(double)b_per_hour);
        }

        System.out.println(totalHours);
        if(totalHours <= h) return true;
        else return false;
    }

    private int findMax(int[] piles) {
        int max = Integer.MIN_VALUE;

        for(int i=0; i<piles.length; i++) {
            max =Math.max(max, piles[i]);
        }

        return max;
    }

    public int minEatingSpeed(int[] piles, int h) {
        
        int max = findMax(piles);
        
        /*
        // i is number of bananas koko can eat per hour
        for(int i = 1; i<=max; i++ ){
            if(canSheFinish(piles, i, h)) return i;
        }*/

        int l = 1;
        int high = max;
        // 1 2 3 4 5 .....11
        // np np     p p p p
        // l              h
        // we are moving from possible situation to a non possible situation.
        //final ans would le l: as the polarity will change at the end.
        while(l<=high){

            int m = l +(high-l)/2;

            if(canSheFinish(piles, m, h)) { // find a min ans: so we move left
                high = m-1;
            } else l = m+1;
        }
        
        // intially h is possible. In the end polarities will change h will become not possible and l will become possible and final ans
        return l;

    }
    
}
