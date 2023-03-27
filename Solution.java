import java.util.*;

public class Solution {
    
    static double f = 0.1;
    static int rate = -1;
    static int pindex = -1;

    public static double maxVal(double[][] arr, int i, int j)
    {
        if (i >= arr.length || i < 0 || j >= arr[i].length || j < 0) return 0;
        double right = maxVal(arr, i, j + 1);
        double bottom = maxVal(arr, i + 1, j);
        return arr[i][j] + Math.max(right, bottom);
    }

    static double Profit(double arr[][], double ans[][], int m ,int n) {

        double profit = 0;
        double rate = 0;

       // System.out.println("Profit called");

         if(n<=0)  {
           // System.out.println("n==0:return");
            return 0;
         }

         if(n==1) {

            rate = arr[m][n]/ arr[m][n-1];
            ans[m][n] = rate;
         }

        // for(int j=n; j>=0; j--) {
         
            for(int i =0 ; i<=m; i++) {

                profit= Profit(arr, ans, i, n-1);

                if(i == pindex) {
                    
                    rate = arr[i][n]/ arr[i][n-1];
                    ans[i][n] = rate;

                } else {
    
                    rate = arr[i][n]/ arr[i][n-1];
                    ans[i][n] = (1-f)*rate;
                }

                profit+= maxVal(ans,i ,n);

            }
       
            System.out.println("for n = "+n+ ", profit: "+profit);

       //  }


         return profit;
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

      System.out.println(Profit(arr, ans, m-1, n-1)); 
    
    }
}
