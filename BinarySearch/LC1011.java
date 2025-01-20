public class LC1011 {

// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
// MEDIUM: BS- find min pattern    


        // i/p-> weights = [1,2,3,4,5,6,7,8,9,10], days = 5
    // Minimum possible capacity to ship atleast 1 package a day-> max(arr) = 10
    // Maximum possible capacity to ship in 1 day: summation(arr) = 55
    // Our ans lie in the range from max to summation

    // Appraoch 1: Linear search
    // Linear search on the range we have
    // TC: O(summation- max)*O(n) ~ O(n^2)
    // TLE

    // Approach 2: Binary Search on the given range
    // TC: O(log (sum -max)* O(n)) ~ O(nlogn)
    public boolean canWeShip(int []weights, int cap, int days) {
        int dayCount = 1;
        int load = 0;

        for(int i=0; i<weights.length; i++) {

            if(weights[i]+load <= cap) {
                load += weights[i];
            } else {
                dayCount++;
                load=0;
                load += weights[i];
            }
        }

        if(dayCount <= days) {
            return true;
        } else return false;
    }

    public int getMax(int[] weights) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<weights.length; i++) {
            max = Math.max(max, weights[i]);
        }

        return max;
    }

    public int getSummation(int [] weights) {
        int sum = 0;

        for(int i=0; i<weights.length; i++) {
            sum += weights[i];
        }

        return sum;
    }

    public int shipWithinDays(int[] weights, int days) {

        int max = getMax(weights);
        int summation = getSummation(weights);

        // Iterate from max to summation and check if we can ship packages with given min capacity
        /*
        for(int cap =max; cap<=summation; cap++) {
            if(canWeShip(weights, cap, days)) return cap;
        }

        return -1;*/

        // 10 | 11 | 12 |......| 55
        // l           m          h

        int  l =max;
        int h = summation;
        int ans = -1;
        

        while(l<=h) {

            int m = l + (h-l)/2;

            if(canWeShip(weights, m, days)) {
                ans = m;
                h = m-1;
            } else {
                l = m+1;
            }
        }

        return ans;
    }
    
}
