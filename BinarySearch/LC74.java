public class LC74 {

//https://leetcode.com/problems/search-a-2d-matrix/

// Binary Search: 2D array - hypothetically flattening it to 1D
// TC: O(log n*m)

    // approach 1: Traverse all the elements of each row and check if arr[i][j] == target
    //             TC: O(n * m)

    // approach 2: Since rows are sorted,
    //             Before traversing a row check if target lie within the range
    //             if(target >= arr[i][0] && target <= arr[i][m-1])
    //             Linear Search: O(m)
    //             TC: O(n) + O(m)

    // approach 3: Using Binary Search on above
    //             if(target >= arr[i][0] && target <= arr[i][m-1])
    //                     return BS(arr[i], target)
    //             TC: O(n) + O(logm)

    // approach 4: Hypotheticaly flattening the 2D array into 1D array and peforming a BS operation.
    // [1 3 5 7 10 11 16 20 23 30 34 60]
    //  0 1 2 3  4  5  6  7  8  9 10 11
    //  l           m                 h
    // convert the 1D index to 2d index -?> (ind/m, ind%m) 
    //            TC: O(log m*n)

    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0;  // pointing to the first hypothetical index
        int h = n*m-1; // pointing to the last hypothetical index

        // performing BS on hypothetically flattened array
        while(l<=h) {

            int mid = (l+h) >> 1;
            int row = mid/m;
            int col = mid%m;

            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] > target) { // elimiate the right half
                h = mid-1;
            } else { // eliminate the left half
                l = mid+1;
            }
        }

        return false;
        
    }
    
}
