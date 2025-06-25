//https://leetcode.com/problems/next-greater-element-i/
// Next Greater Element I

public class LC496 {

    // Monotonic Stack

    // Brute force: check every elment present on the right using a for loop and break when u find the first element
    // TC: O(n) * O(m)* O(m)
    // SC: O(1)
    private int[] naive(int[] nums1, int[] nums2){

        int n = nums1.length;
        int m = nums2.length; 
        int ans[] = new int[n];

        Arrays.fill(ans, -1);

        for(int i=0; i<n; i++) {
            for(int j = 0; j<m; j++) { // comparing each pair 

                if(nums1[i] == nums2[j]) {
                    for(int k=j+1; k<m; k++) { // check every elemt on the right

                        if(nums2[k] > nums2[j]) { // found the elemt
                            ans[i] = nums2[k];
                            break;
                        } 
                    }
                }
            }
        }

        return ans;
    }

    // TC: O(2m) +O(n)
    // SC: O(m) + O(m)
    private int[] stackNGE(int []nums1, int[]nums2) {

        Deque<Integer> st = new ArrayDeque<>(); 
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;
        int m = nums2.length;
        int [] res = new int[n];

        for(int i = m-1; i>=0; i--) {

            while(!st.isEmpty() && st.peek() <= nums2[i]) st.pop();

            // no element is greater on the right
            if(st.isEmpty()){
                map.put(nums2[i], -1);
                //ans[i] = -1;
                st.push(nums2[i]);
            } else {
                map.put(nums2[i], st.peek());
                //ans[i] = st.peek();
                st.push(nums2[i]);
            }
        }

        for(int i=0; i<n; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        //return naive(nums1, nums2);
        return stackNGE(nums1, nums2);
    }
    
}
