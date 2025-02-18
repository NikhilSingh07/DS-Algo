public class LC238 {
    


    
    // calclute the prefix product  and divide the prefix product by the current element.
    // it won't work when we have an element as 0 in the array 
    // pp / 0 - exception

    // approach 2: using multiplication instead of divison
    // maintaing a prefix and suffix product arrays.
    // current element -> prefix[i-1] * sufffix[i+1]

    // TC: O(3n)
    // SC: O(2n)
    public int[] productExceptSelf(int[] nums) {
        
        int n = nums.length;

        int [] pre = new int[n];
        int [] suff = new int[n];
        int [] ans = new int[n];
        int prod = 1;

        // 1 | 2 | 6 | 24
        // calculating prefix product array
        for(int i=0; i<n; i++) {
            prod *= nums[i];
            pre[i] = prod;
        }
        prod = 1;

        // 24 | 24 | 12 | 4
        for(int i= n-1; i>=0; i--) {
            prod *= nums[i];
            suff[i] = prod;
        }

        // corner cases
        ans[0] = suff[1];
        ans[n-1] = pre[n-2];

        for(int i=1; i<n-1; i++) {
            ans[i] = pre[i-1] * suff[i+1];
        }

        return ans;

    }
}
