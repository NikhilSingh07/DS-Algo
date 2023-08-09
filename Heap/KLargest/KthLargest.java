package Heap.KLargest;

import java.util.PriorityQueue;


// https://leetcode.com/problems/kth-largest-element-in-an-array/description/

public class KthLargest {


    public static int findKthLargest(int[] nums, int k) {
        
       /*

       TC: O(n) + O(nlogn)
       AS: O(n) in JAVA
       
        Integer ans[] = new Integer[nums.length];

        for (int i=0; i<nums.length; i++) {
            ans[i] = Integer.valueOf(nums[i]);
        }

        Arrays.sort(ans, Collections.reverseOrder());

        return ans[k-1];

        */


        // TC: O(nlogn)
        // AS: O(logn) in JAVA // recurive stack // QUICK SORT
        /*
        Arrays.sort(nums);

        return(nums[nums.length-k]);

        */
        
        // TC: O(klogk) + O(n-klogk) = O(nlogk)
        // AS: O(k)
        // FASTEST
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // O(klogk)
    
        for(int i=0; i<k; i++) {

             pq.add(nums[i]);
        }

        // O(n-klogk)
        for(int i=k; i<nums.length; i++) {

            if(pq.peek() < nums[i]) {

                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }
    
    public static void main(String[] args) {


          int arr[] = { 3,2,1,5,6,4 };
          int k = 2;

          System.out.println(findKthLargest(arr, k));

    }
}
