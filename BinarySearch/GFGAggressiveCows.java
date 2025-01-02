package BinarySearch;

import java.util.Arrays;

public class GFGAggressiveCows {

        public static boolean canWePlace(int [] stalls, int dist, int cows) {
        
        int cowCount =1;
        int last = stalls[0];
        
        for(int i=1; i<stalls.length; i++) {
            
            if(cowCount >= cows) break;
            
            // checknig is dist between current and last cow is valid
            if((stalls[i] - last) >= dist) {
                cowCount++;
                last = stalls[i];
            }
        }
        return (cowCount>=cows)?true:false;
    }
    
    public static int aggressiveCows(int[] stalls, int k) {
        
        Arrays.sort(stalls);
        int n = stalls.length;
        int min = stalls[0];
        int max = stalls[n-1];
        

        // [1, 2, 4, 8, 9],
        // min = 1;
        // max = 9; 
        // max possible min distance for only 2 cows: 9-1 = 8;
        
        // linear search solution: TC: O(nlogn) + O(max-min)*O(n) ~ O(n^2)
        /*
        for(int i = 1; i<= (max-min); i++) {
            
            if(canWePlace(stalls, i, k)) continue;
            else return i-1;
        }
        
        return stalls[n-1] - stalls[0];*/
        
        // binary search solution: TC: O(nlogn) + O(nlogn)
        int low = 1;
        int high = max-min;
        int ans = 0;
        
        while(low<=high) {
            int mid = (low+high)/2;
            if(canWePlace(stalls, mid, k) ) {
                ans = mid;
                low = mid+1;
            } else high = mid-1;
        }
        
        return ans;
    }
    
}
