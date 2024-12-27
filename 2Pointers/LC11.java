// https://leetcode.com/problems/container-with-most-water/

public class LC11 {
      // Approach 1: try out all the pairs and check if u can store max water between the pairs
    // TC: O(n^2)
    // TLE 57 / 63 
    /*
    public int maxArea(int[] height) {
        
        int maxWater = 0;
        for(int i=0; i<height.length; i++) { 
            for(int j=i+1; j<height.length; j++) {

                int minHeight= Math.min(height[i], height[j]); // u can't store water more than the current min height
                int capacity = minHeight*(j-i);

                maxWater = Math.max(maxWater, capacity);
            }
        }
        return maxWater;
    }*/

    // approach 2: use 2 Pointers
    // l pointer: beg
    // r pointer: end
    // TC: O(n)
    // SC: O(1)
    public int maxArea(int[] height) {

        int l = 0;
        int r= height.length-1;
        int maxWater =0;

        while(l<r) {
            int minHeight = Math.min(height[l], height[r]);
            int capacity = minHeight *(r-l);
            maxWater = Math.max(maxWater, capacity);

            if(height[l] <= height[r]){ // we will keep the taller bar.
                l++;
            } else r--;
        }
        return maxWater;

    }
    
}
