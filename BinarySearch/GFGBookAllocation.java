//https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1

// Initally, max number of pages a student can hold must be atleast max of array?
// [12, 34, 67, 90], 1 student out of 4 can have a max of 90 pages given that each student have atleat 1 book?
// for the given array, ans would lie between 90 (4 students) to 202 (only 1 student).

// we know that our ans lie between 90 and 202.
// we can run a search from 90 and onwards on for how many student we can distribute the pages.
// the moment we get students == k. It will be our min ans where k number of students having max pages.

class GFGBookAllocation {
    
    public static boolean canWeDistribute(int [] arr, int pages, int k) {
        
        int studentCount =1;
        int pageCount = 0;
        
        for(int i=0; i<arr.length; i++){
            
            if(studentCount > k) break;
            // current student can hold this much pages
            if(arr[i]+pageCount <= pages) {
                pageCount += arr[i];
            } else {
                studentCount++;
                pageCount = arr[i];
            }
        }
       // System.out.println(studentCount+", "+pages);
        return (studentCount <= k)?true: false;
    }
    
    public static int getMaximum(int [] arr) {
        
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i<arr.length; i++) {
            
            max = Math.max(arr[i], max);
        }
        
        return max;
    }
    
    
    public static int getSummation(int[] arr) {
        
        int sum = 0;
        
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
        }
        
        return sum;
    }
    
    
    public static int findPages(int[] arr, int k) {
        
        int low = getMaximum(arr);
        int high = getSummation(arr);
        int ans= -1;
        
        if(k>arr.length) return -1;
        
        /*
        for(int pages = low; pages <=high; pages++) {
            
          if(canWeDistribute(arr, pages, k)) {
              ans = pages;
              break;
          }

        }*/
        
        while(low<=high) {
            int mid = (low+high)/2;
            
            if(canWeDistribute(arr, mid, k)) {
                ans = mid;
                high = mid-1;
            } else low = mid+1;
            
        }
        
        return ans;
        
    }
}