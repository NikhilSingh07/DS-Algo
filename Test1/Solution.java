package Test1;

import java.util.*;

public class Solution {
    
    static double f = 0.1;
    
    static double getMaxValue(double arr[][], int row, int col) {
        double maxValue = Double.MIN_VALUE;
        for(int i=0; i<=row; i++) {
            maxValue = Math.max(maxValue, arr[i][col]);
        }
        return maxValue;
    }
    
    static void calculateProfit(double arr[][], double ans[][], int m, int n) {
        // Calculate profit for the first time period and for each company separately
        for(int i=0; i<m; i++) {
            ans[i][0] = 1.0;
        }
        for(int j=1; j<n; j++) {
            for(int i=0; i<m; i++) {
                double rate = arr[i][j] / arr[i][j-1];
                ans[i][j] = (1-f) * rate + f * getMaxValue(ans, i, j-1);
            }
        }
    }

    public static void main(String[] args) {

        int m = 3;
        int n = 5;
        double arr[][] = new double[][]{{10, 20, 40, 1, 1}, 
                                  {20, 22, 24, 26, 28},
                                  {100, 95, 100, 105, 100}
                                 };

        double ans[][] = new double[m][n];       
        
        for (double[] row: ans)
          Arrays.fill(row, 0);

        calculateProfit(arr, ans, m, n);

        double maxProfit = 1.0;
        for(int i=0; i<m; i++) {
            maxProfit = Math.max(maxProfit, ans[i][n-1]);
        }
        System.out.println(maxProfit);

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    
    }
}