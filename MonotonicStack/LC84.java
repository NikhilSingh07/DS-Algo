// https://leetcode.com/problems/largest-rectangle-in-histogram/
// 84. Largest Rectangle in Histogram

// Monotonic Stack problem: All the elements following a specific order in the stack

import java.util.ArrayDeque;
import java.util.Deque;

public class LC84 {

    
    // find the prev smaller element on the left (PSE) and next smaller element on right (NSE)
    // len of rectanle for the current element =  [ (nextIndex -1) - (prevIndex + 1) + 1 ]
    // compute len for each element and return the max len

    // TC: O(n^2)
    // SC: O(1)
    // TLE: 93 / 99 testcases passed
    private int naive(int[] heights) {

        int n = heights.length;
        int maxi = -(int) 1e9;

        for(int i=0; i<n; i++) {

            int left= 0;
            int right =n-1;
            // finding the first smaller building on the left
            for(int j = i-1; j>=0; j--) {
                if(heights[j] < heights[i]) {
                    left = j+1;
                    break;
                }
            }

            // finding the first smaller building on the right
            for(int j=i+1; j<n; j++) {
                if(heights[j] < heights[i]){
                    right = j-1;
                    break;
                }
            }
            
            int width = right - left + 1;
            int area = heights[i] * (width);
            maxi = Math.max(maxi, area);

            //System.out.println(i+", "+area);
        }
        return maxi;
    } 

    // Using the concept of monotonic stack: all elements are in specific order
    // for Prev smaller element [st will be in increasing order] from bottom to top
    // for next smaller element [st will be in increasing order] from bottom to top

    // TC: O(4n)
    // SC: O(3n)
    private int monotonicArea(int[] heights) {

        int n = heights.length;
        int [] pse = new int[n];
        int [] nse = new int[n];

        Deque<Integer> st = new ArrayDeque<>();

        // computing the pse
        // TC: O(2n)
        for(int i=0; i<n; i++) {

            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();

            if(st.isEmpty()) { // no element is less than current element on the left
                pse[i] = 0;
                st.push(i);
           
            } else{
                int ind = st.peek();
                pse[i] = ind + 1;
                st.push(i);
            }
        }

        while(!st.isEmpty()) st.pop();

        // compute the nse
        // TC: O(2n)
        for(int i = n-1; i>=0; i--) {

            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();

            if(st.isEmpty()) {
                nse[i] = n-1;
                st.push(i);
            } else {

                int ind= st.peek();
                nse[i] = ind -1;
                st.push(i);
            }
        }
        
        int maxi = - (int) 1e9;
        // computing the largest rectangle
        // O(n)
        for(int i=0; i<n; i++) {

            int left = pse[i];
            int right= nse[i];

            int area = (right - left + 1) * heights[i];
            maxi = Math.max(maxi, area);
         //   System.out.println(i+", "+area);
            
        }

        return maxi;
    }

    // TC: O(2n)
    // SC: O(n)
    private int onePass(int []heights) {

        int n = heights.length;
        Deque<Integer> st = new ArrayDeque<>();

        int maxi = - (int) 1e9;

        // using pse logic and computing area in a single pass
        for(int i=0; i<n; i++) {
            
            // computing area for the building we popping out, as we have information about left and right for this building
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) {

                int element = st.peek();  // building on which area can be computed as we have information about smaller building on the left
                // and smaller building on the right.

                st.pop();

                int leftBoundary = st.isEmpty()? 0:st.peek() + 1;
                int rightBoundary = i-1;
                int width = rightBoundary - leftBoundary + 1;

                int area = width * heights[element];
                maxi = Math.max(maxi, area);
               // System.out.println(element+", "+ area);
            }

            st.push(i);
            
        }

        // if the stack is not empty, all the buidling will be in the increasing order
        //  1 | 2 | 3
        while(!st.isEmpty()) {

            int element = st.peek();
            st.pop();

            int rB = n-1;
            int lB = st.isEmpty()? 0:st.peek()+1;
            int width = rB - lB +1;

            int area = width * heights[element];
            maxi = Math.max(maxi, area);
          //  System.out.println(heights[element]+", " +area);

        }

        return maxi;

    }

    public int largestRectangleArea(int[] heights) {
        
        //return naive(heights);
        //return monotonicArea(heights);
        return onePass(heights);
    }
}