package Hashing.IntersectionOf2ArraysPart2;
import java.util.*;

//LEETCODE-> https://leetcode.com/problems/intersection-of-two-arrays-ii/description/


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

    /* APPROACH 1:   TC: O(nlogn)  
    // space: O(k): k is the number of common elements


        Arrays.sort(nums1);  // O(nlogn)
        Arrays.sort(nums2);   // O(mlogm)

        ArrayList<Integer> ans = new ArrayList<>();

        int i=0; 
        int j =0;

        while( (i<nums1.length) && (j<nums2.length)) {    // (n+m)

            if(nums1[i] < nums2[j]) {
                i++;
            } else if(nums2[j] < nums1[i]) {
                j++;
            } else if(nums1[i] == nums2[j]) {
                ans.add(nums1[i]);
                i++;
                j++;

            }
        }

        int arr[] = new int[ans.size()];

        for(int x=0; x<ans.size(); x++) {  
            arr[x] = (int)ans.get(x);
        }

        return arr;


       */


        Arrays.sort(nums1);  // O(nlogn)
        Arrays.sort(nums2);   // O(mlogm)

       // ArrayList<Integer> ans = new ArrayList<>();

        int i=0; 
        int j =0;
        int k= 0;

        while( (i<nums1.length) && (j<nums2.length)) {    // (n+m)

            if(nums1[i] < nums2[j]) {
                i++;
            } else if(nums2[j] < nums1[i]) {
                j++;
            } else if(nums1[i] == nums2[j]) {
                nums1[k++] = nums1[i];
                i++;
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);

    }
}