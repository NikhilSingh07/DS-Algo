//https://leetcode.com/problems/maximal-rectangle/description/
//85. Maximal Rectangle


// Monotonic Stack + DP on rectangles

// Monotonic Stack problem + DP (as we are using the values from the row just above it to update the values of the current row)
// This problem is an extension of LC 84. Largest Rectangle in Histogram

// Using the same code from LC 84.

// Each row can be treated as building | bar of the histogram with given heights.

import java.util.ArrayDeque;
import java.util.Deque;

public class LC85 {
         
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

    // TC: O(n)* O(m + 2m)
    // SC: O(n) + O(m)
    public int maximalRectangle(char[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[] dp = new int[m];
        int maxi = -(int) 1e9;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {

                if(matrix[i][j] == '1') dp[j]++;
                else dp[j] = 0;
            }

            int areaRow = onePass(dp);
            maxi = Math.max(maxi, areaRow); 
        }

        return maxi;
    }

}
