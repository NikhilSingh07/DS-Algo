import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1207 {

    //https://leetcode.com/problems/unique-number-of-occurrences/

    class Solution {

    // approach 1: -> sort the array to make sure all the elements are in ascending order
    //             -> iterate through the array and count the occurence of elements and store the count in an array 
    // TC: O(2nlogn) + O(n) + O(k)
    // SC: O(n)
    /*
    public boolean uniqueOccurrences(int[] arr) {

        // approach 1:  sort it and do it in single iteration
        Arrays.sort(arr); //O nlogn
        int temp[] = new int[arr.length];
        int count =0;
        int ind = 0;

        // 1 | 1 | 1 | 2 | 2 | 3
        //     i 
        // c = 3
        // 3 | 2 | 1
        for(int i=0; i<arr.length; i++) { // O(n)
            count = 1;

            while(i < arr.length-1 && arr[i+1] == arr[i]) {
                count ++;
                i++;
            }

            temp[ind++] = count;
        }

        Arrays.sort(temp); 
        for(int i=0; i<temp.length-1; i++) {

            if(temp[i] == 0) continue;
            if(temp[i] == temp[i+1]) return false;
        }
        return true;
    }*/

    // approach 2: -> use a hashmap to store the occurences of the elements.
    //             -> store the values in a hashset and check if the size of set == map.
    //             -> if the values are unique, then size of set should be equal to map size.

    // TC: O(2n)
    // SC: O(2n)
    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }

        for(int value: map.values()){
            set.add(value);
        }

        if(set.size() == map.size()) return true;
        else return false;
    }
}
}