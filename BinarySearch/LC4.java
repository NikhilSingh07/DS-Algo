public class LC4 {

    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    // brute + better + Binary Search

    class Solution {


        // Approach 1: BRUTE FORCE-> using another array to merge elements.
        // TC: O(n+m)
        // SC: O(n+m)
        // Accepted
        /*
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    
            int n1 = nums1.length;
            int n2 = nums2.length;
            int n = n1+n2;
    
            int[] nums = new int[n];
        
            int i=0; // pointers pointing to the 0th element of both arrays
            int j=0;
    
            int pos =0;
    
            // comparing both the arrays and storing the smaller element in the new array.
            while(i<n1 && j <n2) {
    
                if(nums1[i]<=nums2[j]) {
                    nums[pos++] = nums1[i++];
                } else {
                    nums[pos++] = nums2[j++];
                }
            }
    
            while(i<n1) {
                nums[pos++] = nums1[i++];
            }
    
            while(j<n2) {
                nums[pos++] = nums2[j++];
            }
    
            if(n%2 == 1) {
                return (double)nums[n/2];
            } else {
                return ((double) nums[n/2] + (double) nums[(n/2)-1])/2;
            }
        }*/
    
        // Approach 2: Optimization over approach 1: we would not use any extra space.
        // As we already know the value of n = (n1+n2), we can get the median just by using pointers.
        // for even case: n = 10-> el1 = 5 and el2 = 4
        // for odd case: n =9 ->  el1 =4 and just ignore the el2
        // TC: about O(m+n)
        // SC: constant
    
        /*
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    
            int n1 = nums1.length;
            int n2 = nums2.length;
            int n = n1+n2;
    
            int ind1 = n/2;
            int ind2 = (n/2)-1;
    
            int ind1el = -1;
            int ind2el = -1;
    
            int i=0;
            int j=0;
            int pos=0;
    
            while(i<n1 && j<n2) {
    
                if(ind2el!=-1 && ind1el!=-1) break; // optimization
                if(nums1[i]<=nums2[j]) {
                    
                    if(pos == ind1) ind2el = nums1[i]; 
                    if(pos == ind2) ind1el = nums1[i];
                    i++;
                    pos++;
                } else {
                    if(pos == ind1) ind2el = nums2[j]; 
                    if(pos == ind2) ind1el = nums2[j];
                    j++;
                    pos++;
    
                }
            }
    
            while(i<n1) {
                
                   if(ind2el!=-1 && ind1el!=-1) break;
                    if(pos == ind1) ind2el = nums1[i]; 
                    if(pos == ind2) ind1el = nums1[i];
                    i++;
                    pos++;
            }
            
            while(j<n2) {
                    
                    if(ind2el!=-1 && ind1el!=-1) break;
                    if(pos == ind1) ind2el = nums2[j]; 
                    if(pos == ind2) ind1el = nums2[j];
                    j++;
                    pos++;
            }
    
            if(n%2 == 1) {
                return (double) ind2el;
            } else {
                return ( (double)ind2el + (double)ind1el)/2;
            }
        }*/
    
    
        // Approach 3: Binary search: Hypothetically, if we merge arr1 and arr2. There would be a symmetry.
        // half elements would lie on the left half of the symmentry and half on the right half.
        
        // If we determine how many elements we pick from arr1 and arr2 to make a correct left half-> we can potentially have our ans.
    
        // If can do a search operation on one of the arrays to determine number of elements to pick from arr1: remaining would be from arr2 automatically.
        
        // if n = 8 -> left half contains 4 elements
        // [ 0 1 2 3 4] - left half of the hypothetical symmetry.
    
        // we can start picking from 0 elements from arr1 to picking 4 elements from arr1 -> LS OR BS
        // we check when we have a correct symmetry
    
        // So we are doing binary search on one of the arrays to determing how many elements we can pick to form a correct symmetry.
        // TC: min(logm, logn)
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    
            // n1 = [1 2 4 6 7]
            // n2 = [3 5 8]
            // n = even = 8 
            
            int n1= nums1.length;
            int n2= nums2.length;
            int n = n1+n2;
            if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
    
            int left = (n1+n2+1) >> 1;
    
            int l = 0;  // picking 0 elements from arr1 to make left half of the symmetry
            int h = n1; // picking all possible elements to make a left symmetry
    
            // [0 1 2 3 4]
            //  l   m   h
            System.out.println("l: "+l+", h: "+h);
            while(l<=h) {
    
                int mid1 =(l+h) >> 1; // elements to pick from aar1
                int mid2 = left - mid1; // remaining elements from arr2 to fill left half of the symmetry
                System.out.println("m1: "+mid1+", m2: "+mid2);
    
                int l1 = Integer.MIN_VALUE;
                int l2=  Integer.MIN_VALUE;
                int r1= Integer.MAX_VALUE;
                int r2= Integer.MAX_VALUE;
    
                if (mid1< n1) r1 = nums1[mid1];
                if (mid2< n2) r2 = nums2[mid2];
                if (mid1-1 >= 0) l1 = nums1[mid1-1];
                if (mid2-1 >= 0) l2 = nums2[mid2-1];
    
                if(l1<=r2 && l2 <= r1) {
                    
                    // For even There will be an equal symmetry
                    if(n%2 == 0) {
                        System.out.println(l1 + ", "+ l2+", "+r1+ ", "+r2);
                        return ((double)Math.max(l1,l2) + (double)Math.min(r1,r2)) / 2.0;
                    } else {
    
                        // for n == odd;  there will be unequal symmetry
                        // if left = n1+n2 >> 1 -> median will lie on the right half
                        // if left = n1+n2+1 >> 1 -> median will lie on the left half
    
                        return (double) Math.max(l1,l2);
                    }
                }
    
                else if(l2 > r1) { // eliminate the left half and pick more elements from arr1
                    l = mid1+1;
                }else { // elimiate the riht half and pick less elements form arr1
                    h = mid1-1;
                }
            }
    
            return 0;
        }
    
    }
    
}
