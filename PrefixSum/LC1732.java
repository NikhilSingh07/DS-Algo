public class LC1732 {
    

        // maintain a prefix sum
    
        public int largestAltitude(int[] gain) {

            int maxAlt = 0;
            int sum = 0;
    
            for(int i=0; i<gain.length; i++) {
    
                sum += gain[i];
                maxAlt = Math.max(sum, maxAlt);
            }
    
            return maxAlt;
        }
}
