import java.util.Arrays;
// https://leetcode.com/problems/unique-paths-ii/
// https://excalidraw.com/#json=7eq_kz9LDod4i6_MXMcHD,m6VMYkslW8UPc8JztxUvXQ
public class LC63 {
    

      // same problem as LC 62: Unique Paths 
    // just 1 extra condition, that's
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // 1d array storing prev row

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1)return 0; 

        int [] prev = new int[n];
        Arrays.fill(prev, 0);

        // Step 2: for loops for the state variables
        for(int i=0; i<m; i++) {

            int temp[] = new int[n]; // store the ans of current row
            for(int j=0; j<n; j++) {

                if(i == 0 && j ==0) {temp[j] = 1;} 
                else if(obstacleGrid[i][j] == 1) {temp[j] = 0;}
                else {
                    int up = 0;
                    int left = 0;
                    if(i>0) up = prev[j];
                    if(j>0) left = temp[j-1];

                    temp[j] = up+left;
                }
            } 
            prev = temp;
        }    

        return prev[n-1];
        
    }
}
