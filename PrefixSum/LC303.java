class NumArray {

    private int prefixSum[];
    private int sum;
    
    public NumArray(int[] nums) {
        this.prefixSum = new int[nums.length];
        /*
        for(int i=0; i<nums.length; i++) {
            ans[i] = nums[i];
        }*/
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            prefixSum[i] =sum;
        }


    }
    

    // TC: O(n) per Query
    /*
    public int sumRange(int left, int right) {
        
        int sum =0; 
        for(int i=left; i<=right; i++) {
            sum += ans[i];
        }

        return sum;
    }*/

    // TC: O(1) pre query
    // using prefix sum
    public int sumRange(int left, int right){

        // right-> total sun till right index
        if(left ==0) return prefixSum[right];
        return prefixSum[right] - prefixSum[left-1];

    }

}