import java.util.Arrays;

// 2D DP problem: Ninja's Training | Geeks training 
// MEDIUM
//https://www.naukri.com/code360/problems/ninja%E2%80%99s-training_3621003?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM

public class NinjaTraining {

    
    // Recursion: TC: O(3^n)
    // SC: O(n)

    // Memoization: TC: O(n*4) (number of states)
    // SC: O(n*4*3) + O(n)
    private static int memoPoints(int n, int last, int [][]arr, int [][]dp) {
        
        if(n == 0) {
            int maxPoints = 0;
            for(int task = 0; task <3; task++) {
                if(task!=last) {
                    int points = arr[n][task];
                    maxPoints = Math.max(maxPoints, points);
                }
            }
            return maxPoints;
        }
        
        if(dp[n][last]!=-1) {
            return dp[n][last];
        }
        int maxPoints = 0;
        
        for(int task = 0; task<3; task++) {
            
            if(task!=last) {
                
                int points = arr[n][task] + memoPoints(n-1, task, arr, dp);
                maxPoints = Math.max(maxPoints, points);
            }
        }
        
        return dp[n][last] = maxPoints;
    }

    // TC: O(n*4*3)
    // SC: O(n*4)
    private static int tabPoints(int n, int [][]arr, int [][]dp) {

        // when n == 0
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        // n: 1 until n-1
        // for each state (combinations) trying out all the tasks
        for(int day = 1; day<=n; day++) {
            for(int last = 0; last<4; last++) {
                int maxPoints = 0;
                for(int task = 0; task<3; task++) {
                
                    if(task!=last) {
                        
                        int points = arr[day][task] + dp[day-1][task];
                        maxPoints = Math.max(maxPoints, points);
                    }
                }
                dp[day][last] = maxPoints;
            }
        }

        return dp[n][3];

    }

    // TC: O(n*4*3)
    // SC: O(4)
    private static int optimisePoints(int n, int [][]arr) {

        int prev[] = new int[4];
        // when n == 0
        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        // n: 1 until n-1
        // for each state (combinations) trying out all the tasks
        for(int day = 1; day<=n; day++) {

            int temp[] = new int[4];

            for(int last = 0; last<4; last++) {
                int maxPoints = 0;
                for(int task = 0; task<3; task++) {
                
                    if(task!=last) {
                        
                        int points = arr[day][task] + prev[task];
                        maxPoints = Math.max(maxPoints, points);
                    }
                }
                temp[last] = maxPoints;
            }
            // after computing for all the states on the given day.
            // Copy the result in dp array to use it for next day.
            prev = temp;
        }

        return prev[3];

    }
    
    public static int ninjaTraining(int n, int points[][]) {

        // Write your code here..
                
            // int [][]dp = new int[n][4];

            // for(int [] row: dp) {
            //     Arrays.fill(row,-1);
            // }

       // return tabPoints(n-1, points, dp);
       return optimisePoints(n-1, points);
        
    }

}