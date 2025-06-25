import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.com/problems/next-greater-element-ii/
// Next Greater Element II: Circular NGE

// Monotonic Stack

public class LC503 {

    
    // Monotonic Stack
    // NGE problem: Conisder a hypothetical exptenstion in the array to handle the circular array elements
    // o  to 2n + 1

    // TC: O(n) * (n)
    // SC: O(1)
    private int[] naive(int[] nums){

        int n = nums.length;
        int ans[] = new int[n];

        Arrays.fill(ans, -1);
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<= i+n-1; j++) { // check every elemt on the right

                if(nums[j % n] > nums[i]) { // found the elemt
                    ans[i] = nums[j % n];
                    break;
                } 
            }
                
        }
        return ans;
    }

    // TC: O(2n) + O(2n)
    // SC: O(2n)
    private int[] stackNGE(int []nums) {

        Deque<Integer> st = new ArrayDeque<>(); 
        int n = nums.length;
        int [] ans = new int[n];


        for(int i = 2*n-1; i>=0; i--) {
            while(!st.isEmpty() && st.peek() <= nums[i % n]) st.pop();

            // no element is greater on the right
            if(st.isEmpty()){

                ans[i % n] = -1;
                st.push(nums[i % n]);
            } else {

                ans[i % n] = st.peek();
                st.push(nums[i % n]);
            }
        }

        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        //return naive(nums);

        return stackNGE(nums);
    }
    
}
