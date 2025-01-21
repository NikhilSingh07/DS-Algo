public class LC1539 {

// https://leetcode.com/problems/kth-missing-positive-number/
// Math + Binary Search:  EASY    
        // approach 1: if array is empty, then 5th missing no would be 5.
    //             similarly, if 2 is present in array, 5th missing no. would be 6. it will increse by 1.
    //             therefore, for every smaller or equal value, we will increase the value of k
    // TC: O(n)

    // approach 2: Binary search
    // TC: O(logn)
    public int findKthPositive(int[] arr, int k) {
        /*
                for(int i=0; i<arr.length; i++) {
                    if(arr[i] <= k) k++;
                    else break;
                }
                return k;*/
        
                int l = 0;
                int h = arr.length-1;
                int missing = 0;
        
                while(l<=h) {
                    int m = (l+h)/2;
        
                    missing = arr[m] - (m+1);
                    if(missing < k) {
                        l = m+1;
                    } else 
                      h = m-1;
                }
        
                if(h == -1) {
                    return k;
                } else 
                System.out.println("arr[h]: "+arr[h]+", k: "+k+", missing: "+missing );
                // ( 7 + (5-3)) = 9
                return arr[h] + (k - (arr[h] - (h+1)));
            }
    
}
