package Hashing.countDistinctElements;

import java.util.*;

// count Distinct elements in an array
public class Solution {
    
    public static void main(String[] args){

        int arr[] = new int[]{5,10,5,4,5,10,15,15};

       int count =  countDistinct(arr);   // O(nlogn)

       int size = countDistinctUsingHashing(arr);    // O(n)
       System.out.println(size);
    }

    static int countDistinctUsingHashing(int arr[]) {

        Set<Integer> set = new HashSet<>();

        for(int x: arr) {     //(n)
            set.add(x);      //O(1)
        }

        return set.size();
    }
    static int countDistinct(int arr[]) {

           int count = 0;
           Arrays.sort(arr);  //O(nlogn)

           count =1;

           for(int i=1; i<arr.length; i++) {   //O(n)
             if(arr[i] != arr[i-1]) {
                count++;
             }
           }

           return count;
    }
}
