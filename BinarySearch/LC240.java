// https://leetcode.com/problems/search-a-2d-matrix-ii/
// MEDIUM


// approach 1: Brute force: Traverse all the elements of each row and check for the target element.
//             TC: O(m*n)

// approach 2: Use BS on each row
//             TC: O(n * logm)

// approach 3: We start from an element (right upper or left lower) from where all the elements on the left portion is <= and all the 
//             elements below is >= 

// row = 0, col = 4 - > 15
//             if the given element > target - It means target can't lie below and we decreement col count
//             else: element can be the same row so we increement the row count

//             TC: O(n+m) - Optimal 
public class LC240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int row = 0;
        int col = m-1;

        while(row < n && col >= 0) {

            if(matrix[row][col] == target) return true;
            else  if(matrix[row][col] < target) row++;
            else col--;
        } 
        
        return false;
    }
}