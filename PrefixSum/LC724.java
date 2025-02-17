public class LC724 {

        // approach !: use suffix sum array.
    // TC: O(2n)
    // SC: O(n) -> might need to optmise the space.
    /*
    public int pivotIndex(int[] nums) {
        
        //create a suffix sum array
        int ss[] = new int[nums.length];
        int sum =0;

        for(int i=nums.length-1; i>=0; i--) {
            sum += nums[i];
            ss[i] = sum;
        }

        sum =0; 
        // comparing if same sum element present in one of the sums arrays
        for(int i=0; i<nums.length;i++) {
            sum += nums[i];
            if(sum == ss[i]) {
                return i;
            }
        }
        return -1;
    }*/

    // approach 2: optimising the space compleixy in approach 1
    // calculating prefix leftsum and traverse the array again to check the index where leftsum == rightsum
    // TC: O(2n)
    // SC: O(1)
    public int pivotIndex(int[] nums) {

        int n = nums.length;    
        int Lsum = 0;
        int Rsum = 0;

        // calculating the suffix sum
        // cuzz if we cal the prefix Lsum first and traverse from back to check. if nultile indices gives same result, it will return the later indices
        for(int i=n-1; i>=0; i--) {
            Rsum += nums[i];
        } 

        for(int i = 0; i<n; i++) {
            
            Rsum -= nums[i]; // updating the L sum

            if(Lsum == Rsum) {
                return i;
            }
            Lsum += nums[i];
        }   
        return -1;
    }
    
}
