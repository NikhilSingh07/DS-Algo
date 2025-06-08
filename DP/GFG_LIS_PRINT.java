import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GFG_LIS_PRINT {
    
    // Using the 1D tabulation LIS apporach
    // Dp array to store the max len of LIS
    // hash array to keep track of prev elements

    // TC: O(n^2)
    // SC: O(2n)

    public static List< Integer > printingLongestIncreasingSubsequence(int []arr, int x) {
        

        List<Integer> ls = new ArrayList<>();
        int []dp = new int[x];
        int []hash = new int[x];
        int max = 0;
        int lastIndex = 0;

        for(int i=0; i<x; i++) {
            hash[i] = i; // initilaing hash with current index
            for(int prev = 0; prev<i; prev++) {

                if(arr[i] > arr[prev] && dp[prev]+1 > dp[i] ) {

                    // updating the len of LIS till current nndex
                    dp[i] = dp[prev] + 1;
                    hash[i] = prev; // stores the address of prev element
                }
            }
            
            // updaing the lastIndex
            if(dp[i] > max) {
                lastIndex = i;
                max = dp[i];
            }
        }

        ls.add(arr[lastIndex]);

        while(hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            ls.add(arr[lastIndex]);
        }

        Collections.reverse(ls);

        return ls;


    }
}
