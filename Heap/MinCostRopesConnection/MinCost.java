package Heap.MinCostRopesConnection;

import java.util.PriorityQueue;


// https://practice.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1

// Minimum Cost of ropes


// TC: O(nlogn)
// AS: O(n)


public class MinCost {
    
        //Function to return the minimum cost of connecting the ropes.
    private static long minCost(long arr[], int n) 
    {
        // your code here
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long ans = 0;
        
        for(int i=0; i<n; i++) {  // O(n)
            
             pq.add( arr[i]);      //(logn)   
        }
        
        
        while(pq.size()>1) {    // O(n)
            
            
            long first = pq.poll();   // O(logn)
            long second = pq.poll();      // O(logn)
            long sum = first +second;
            
            ans += sum;
            
            pq.add(sum);        // O(logn)
        }
        
        return ans;
        
    }


    public static void main(String[] args) {


        long arr[] = {4, 3, 2, 6};
        int n = 4;

        System.out.println(minCost(arr, n));
    }
}
