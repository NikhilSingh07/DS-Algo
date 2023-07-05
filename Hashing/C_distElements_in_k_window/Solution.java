package Hashing.C_distElements_in_k_window;
import java.util.*;


// https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/

public class Solution {

    public static void main(String[] args) {

        // TC: O(n)
        // AS: O(n)

        int arr[] = new int[] {1, 2, 2, 1, 3, 1, 1, 3};
        int k = 4;

        HashMap<Integer, Integer> map = new HashMap<>();

        int i=0;

        for (i=0; i<k; i++) {

            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        System.out.println(map.size());

        for(i=k; i<arr.length; i++) {

            if( map.get(arr[i-k]) == 1 ) {
               map.remove(arr[i-k]);
           
            } else {
                map.put(arr[i-k], map.getOrDefault(arr[i-k],0)-1);
            }

            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
            System.out.println(map.size());
        }

    }

    /* 
    public static void main(String []args) {
       
        int arr[] = {1, 2, 1, 3, 4, 2, 3}, k = 4;
        int n = 8;
        countDist(arr,n,k); // Naive approach O(N)*K^2 complexity and O(K) space complexity.
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

    */
}


/* 

// O(n-k+1)*klogk
class HelloWorld {
    public static void main(String[] args) {
        
        int arr[] = new int[] {1,2,1,3,4,2,3};
        int n=7;
        int k = 4;
        for(int i=0; i<=n-k; i++) {
            
            System.out.println(countUtil(Arrays.copyOfRange(arr, i, i+k), k));
        }
    }
    
    static int countUtil(int arr[], int k){
        
        int j=0;
        int count =1;
        Arrays.sort(arr);

        for(int i=0; i<arr.length-1; i++) {
            j++;
            if(arr[i]!= arr[j]) count++;
        }
        return count;

    }
}*/