// https://leetcode.com/problems/find-a-peak-element-ii/
// Binary Search:  on 2d matrix: find peak
// MEDIUM

// approach 1: Brute force: Traverse all the elements and check if the current element > all 4 neighbors/
//                          TC: O(n*m)

// approach 2: Binary Search.

// If we find max element in each col, then we can convert the problem to a 1 D problem
// 21 | 30 | 32 from ex 2
// we can find peak using BS
// But to find max element in each col, we need to traverse through all the n*m elements.

// Therefore, better way is to find max element on the mid column (BS). That way we only need to find max for logn elements/

// TC: O(n * logm)

public class LC1901 {

    public int getMax(int [][] mat, int mid, int n, int m) {

        int max = Integer.MIN_VALUE;
        int ind = -1;
        for(int i=0; i<n; i++) {

            if(max < mat[i][mid]) {
                max = mat[i][mid];
                ind = i;
            }
        }
        return ind;
    }

    public int[] findPeakGrid(int[][] mat) {

        int n = mat.length;
        int m =  mat[0].length;
        int l = 0;
        int h = m-1;

        while(l<=h) {

            int mid = (l+h)/2;
            int row = getMax(mat, mid, n, m); // we have the max element of the col.

            int left = mid - 1 >= 0 ? mat[row][mid-1]: -1;
            int right = mid + 1 < m ? mat[row][mid+1]: -1;

            if(mat[row][mid] > left && mat[row][mid] > right ) {
                return new int[]{row, mid};
            } 

            else if(mat[row][mid] < left) { // Element is on the left and we can eliminate the right half

               h = mid-1;
            } else {
                l = mid+1;
            }

        }

        return new int[] {-1, -1};
        
    }
}