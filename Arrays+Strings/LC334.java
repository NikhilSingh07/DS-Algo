public class LC334 {

    public boolean increasingTriplet(int[] nums) {
        
        // corner case
        if(nums.length < 3) return false;
        int n = nums.length;

        for(int i=0; i<n-2; i++) {

            int j = i+1;
            int k = j+1;

            // 2 | 1 | 5 |0 | 4 | 6
            // i       j  k

            while(k<n && nums[i]>= nums[j]) {
                j++;
                k++;
            }

            while(k<n && nums[j] >= nums[k]) {
                System.out.println(nums[i]+", "+nums[j]+", "+nums[k]);
                k++;
            }

            if(k<n && nums[i] < nums[j] && nums[j] < nums[k]) return true;

        }

        return false;
    }
    
}
