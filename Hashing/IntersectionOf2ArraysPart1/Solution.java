package Hashing.IntersectionOf2ArraysPart1;
import java.util.*;

//LEETOCODE-> https://leetcode.com/problems/intersection-of-two-arrays/description/


class Solution {
    
    public int[] intersection(int[] nums1, int[] nums2) {
        
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int arr[];

        for(int x: nums1) {
            set.add(x);
        }

        for(int x: nums2) {
            if(set.contains(x)) {               
                set.remove(x);
                ans.add(x);
            }
        }

        arr = new int[ans.size()];

        for(int i=0; i<ans.size(); i++) {

            arr [i] = (int)ans.get(i);
        }
        return arr;
    }

}
