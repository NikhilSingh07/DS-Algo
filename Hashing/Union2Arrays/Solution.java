package Hashing.Union2Arrays;

import java.util.Arrays;
import java.util.*;

public class Solution {
    
    public static void main(String []  args) {

        int arr1[] = new int[] {5,10,15,5};
        int arr2[] = new int[] {4,10,15,5};

        int count = unionMethod1(arr1, arr2);   // O(nlogn+mlogm)
        int unionCount = unionMethod2(arr1, arr2);   // O(n+m)
        System.out.println(unionCount);

    }

    static int unionMethod2(int arr1[], int arr2[]) {

        Set<Integer> set = new HashSet<>();

        for(int x: arr1) {
            set.add(x);
        }

        for(int x: arr2) {
            set.add(x);
        }

        return set.size();

    }

    static int unionMethod1(int arr1[], int arr2[]) {

        Arrays.sort(arr1);  // O(nlogn)
        Arrays.sort(arr2);    // O(mlogm)

        int i = 0;
        int j=0;
        int prev =-1;
        int count = 0;
    
        if(arr1[0]<arr2[0] ){
            prev  = arr1[0];
            i++;
            count++;
        } else {
            prev  = arr2[0];
            j++;
            count++;
        }
         
        while( (i <arr1.length) && (j< arr2.length)) {   // O(n)+O(m)


            if(arr1[i] <= arr2[j] && (arr1[i]!=prev)) {
                prev = arr1[i];
                count++;
                i++;
            } else if(arr2[j] < arr1[i] && (arr2[j]!=prev)) {
                prev = arr2[j];
                count++;
                j++;
            } else if(arr1[i] == prev) {
                i++;
            } else if(arr2[j] == prev) {
                j++;
            }


        }

        return count;
    }
}
