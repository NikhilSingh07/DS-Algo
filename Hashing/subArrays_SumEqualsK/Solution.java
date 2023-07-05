package Hashing.subArrays_SumEqualsK;
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int curr = 0;
        int count = 0;
         
        for(int i=0; i<nums.length;i++) {   // O(n) time and O(n) space.

            curr+= nums[i];
            
            if(curr == k) count++;

            if(map.containsKey(curr-k)) {
                count += map.get(curr-k);
            }

            map.put(curr, map.getOrDefault(curr,0)+1);
        }
 
        return count;
         
    }


    /*  USING BRUTE FORCE APPROACH, TC: O(n2), AS: O(1)   

    public static void main(String[] args) {
        
        int arr[] = new int[] {10, 15, -5, 15, -10, 5};
        int currsum = 0;
        int sum = 5;
        int sindex = -1;
        int eindex = -1;
        
        for(int i=0; i<arr.length-1; i++) {
            
            currsum = arr[i];
            
            for(int j = i+1; j<arr.length; j++) {
                
                currsum = currsum + arr[j];
                
                if(currsum == sum) {
                    sindex = i;
                    eindex = j;
                    break;
                } 
   
            }
            
            if(eindex!=-1) break;
        }
        
        System.out.println(sindex+" "+eindex);
        
    }   */


    /*    
    
    HASHMAP: TC: O(n), AS: O(n)

    public static void main(String[] args) {
        
        int arr[] = new int[] {10, 15, -5, 15, -10, 5};
        int sum =20;
        int currsum = 0;
        int sindex = -1;
        int eindex = -1;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<arr.length; i++) {   // TC: O(n), AS: O(n)
            
            currsum = currsum + arr[i];
            map.put(currsum, i);
            
            if((currsum - sum) ==0 ) {
                sindex = 0;
                eindex = i;
                break;
            }
        
            if(map.containsKey(currsum-sum)) {
                
                sindex = map.get(currsum-sum) + 1;
                eindex = i;
                break;
            }
        }
        
        if(eindex!=-1) {
            System.out.println(sindex+" "+eindex);
        } else {
            System.out.println("subarray not found!!");
        }
        
        
    } */
}
