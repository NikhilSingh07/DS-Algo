//https://leetcode.com/problems/maximum-average-subarray-i/?envType=study-plan-v2&envId=leetcode-75

// SW: Pattern 1

public class LC643 {

     // sliding window: Pattern 1
     public double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        double avg = 0;
        double maxAvg = Double.NEGATIVE_INFINITY;;
        int l =0; // left pointer
        int r = k-1; //right pointer with window size k

        // computing the sum of the first k elements.

        for(int i=0; i<k; i++) {
            sum += nums[i];
        }

        avg = (double)sum/k;
        //System.out.println("avg:" +avg);
        maxAvg = Math.max(maxAvg, avg);

        // 1 | 12 | -5 | -6 | 50 | 3
        // l              r
        while(r<nums.length-1){

            sum -= nums[l]; // updating the sum: removing the left most element
            l++;
            r++;
            sum += nums[r]; // updating the sum: adding a new element to the sum

            avg = (double)sum/k;
            //System.out.println("avg:" +avg);
            maxAvg = Math.max(maxAvg, avg);

        }

        return maxAvg;
        
    }
    
}
