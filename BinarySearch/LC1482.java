// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
// Medium Binary Search: min pattern

public class LC1482 {

    
    // approach 1: Linear Search on the minimum days.
    //              [7,7,7,7,12,7,7] 
    //             -> min possible day = min(arr) -> 7
    //             -> max possible day = max(arr) -> 12   
    // now we have a range from 7 to 12. Our ans will lie in this range.
    // TLE 
    // TC: O(max-min)*O(n)

    // approach 2: use Binary search
    // TC: O(log max-min)*O(n)
    public boolean isPossible(int [] bloomDay, int day, int m, int k) {

        int bouquetCount = 0;
        int flowerCount =0;

        for(int i=0; i<bloomDay.length; i++) {
            if(bloomDay[i] <= day) {
                flowerCount++;
            } else {
                bouquetCount += flowerCount/k;
                flowerCount =0;
            }
        }

        bouquetCount += flowerCount/k;

        if(bouquetCount >= m) return true;
        else return false;
    }

    public int getMin(int[] bloomDay) {

        int min = Integer.MAX_VALUE;
        for(int i=0; i<bloomDay.length; i++) {
            min = Math.min(min,  bloomDay[i]);
        }

        return min;
    }

    
    public int getMax(int[] bloomDay) {

        int max = Integer.MIN_VALUE;
        for(int i=0; i<bloomDay.length; i++) {
            max = Math.max(max,  bloomDay[i]);
        }

        return max;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        
        int min = getMin(bloomDay); 
        int max = getMax(bloomDay);

        // edge case: 
        if(m*k > bloomDay.length) return -1;

        /*
        for(int i=min; i<=max; i++) { // O(max-min)

            if(isPossible(bloomDay, i, m, k)) return i;
        }*/

        int l = min;
        int ans = -1;
        int h = max;

        while(l<=h) {
            int mid = l + (h-l)/2;
            if(isPossible(bloomDay, mid, m, k)) {
                ans = mid;
                h = mid-1;
            } else {
                l = mid+1;
            }
        }

        return ans;
    }
    
}
