package Hashing.C_distElements_in_k_window;
import java.util.*;

public class Solution {
    public static void main(String []args) {
       
        int arr[] = {1, 2, 1, 3, 4, 2, 3}, k = 4;
        int n = 8;
        countDist(arr,n,k);
    }

    static void countDist(int arr[], int n, int k) {  //O(n)*k^2

        for(int i=0; i<n-k; i++) {  //O(n)
            
            countUtil(Arrays.copyOfRange(arr, i, k+i), k); 
        }
    }

    static void countUtil(int arr[], int k) {
        
        int distCount = 0;
        for(int i=0; i<k; i++) {       // O(k)
            int j;
            for (j= 0; j<=i; j++) {       //O(k)
               
                if(arr[i] == arr[j])
                break;
            }

            if( i ==j) {
                distCount ++;
            }
          
        }

        System.out.println(distCount);
    }
}
