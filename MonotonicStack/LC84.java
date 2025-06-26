// https://leetcode.com/problems/largest-rectangle-in-histogram/
// 84. Largest Rectangle in Histogram

// Monotonic Stack problem: All the elements following a specific order in the stack

import java.util.ArrayDeque;
import java.util.Deque;

public class LC84 {

        private int onePass(int []heights) {

        int n = heights.length;
        Deque<Integer> st = new ArrayDeque<>();

        int maxi = - (int) 1e9;

        // using pse logic and computing area in a single pass
        for(int i=0; i<n; i++) {
            
            // computing area for the building we popping out, as we have information about left and right for this building
            while(!st.isEmpty() && st.peek() >= heights[i]) {

                int element = st.peek();  // building on which area can be computed as we have information about smaller building on the left
                // and smaller building on the right.

                st.pop();

                int leftBoundary = st.isEmpty()? 0:st.peek() + 1;
                int rightBoundary = i-1;
                int width = rightBoundary - leftBoundary + 1;

                int area = width * heights[element];
                maxi = Math.max(maxi, area);
                //System.out.println(element+", "+ area);
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
            //System.out.println(heights[element]+", " +area);

        }

        return maxi;

    }
    
}
