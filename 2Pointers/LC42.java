
// https://leetcode.com/problems/trapping-rain-water/ - H

public class LC42{


// 1. Brute force: for each index we need to find the maxL and maxR.
//                 The water that can be stored at i, would be min(maxL, maxR) - arr[i].  
// TC: O(n^2)
// TLE: 323/324  
/*
    public int trap(int[] height) {
        
        int maxL = 0;
        int maxR = 0;
        int res = 0;

        for(int i=0; i<height.length; i++) {
             maxL = 0;
             maxR = 0;

            // finding the maxL
            for(int j=i; j>=0; j--) {
                maxL = Math.max(maxL, height[j]);
            }

            // finding the maxR
            for(int j=i; j<height.length; j++) {
                maxR = Math.max(maxR, height[j]);
            }

            int min = Math.min(maxL, maxR);
            if(height[i] < min) {
                res += min- height[i];
            }
        }

        return res;
    }*/

// 2. Better approach: use prefix and suffix arrays to store prefix max and suffix max.
                    // to avoid nested loops
// TC: O(n+n+n) = O(3n)
// SC: O(2n) - can be improved.  

/*

    public int trap(int[] height){

        int pm[] = new int[height.length];
        int prem = 0;
        int sm[] = new int[height.length];
        int suffm = 0;
        int res = 0;

        for(int i=0; i<height.length; i++) {
            
            prem = Math.max(prem, height[i]);
            pm[i] = prem;
        }

        for(int i=height.length-1; i>=0; i--) {
            suffm = Math.max(suffm, height[i]);
            sm[i] = suffm;
        }

        for(int i=0; i<height.length; i++) {

            int min = Math.min(pm[i], sm[i]);
            
            if(height[i] < min) {
                res += min - height[i];
            }
        }
        return res;
    }     */

// 3. Most optimal solution: 2 Pointers.

// TC: O(n)
// SC: O(1)

public int trap(int[] height){
        
    int l =0;
    int r = height.length-1;

    // keeping track of max buildings on respective sides
    int Lmax = 0;
    int Rmax = 0;

    int res = 0;

    while(l<r) {

        // it means that there is a building on right, which is taller or atleast of equal height.
        if(height[l] <= height [r]) {

            // current building is tallest on the left side, so can't store any water
            if(height[l] > Lmax) {
                Lmax = height[l];
            } else {
                res += Lmax - height[l];
            }
            l++;
        
        } else {

            if(height[r] > Rmax) {
                Rmax = height[r];
            } else {
                res += Rmax - height[r];
            }

            r--;
        } 
    }
    return res;
}

}