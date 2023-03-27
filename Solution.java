import java.util.*;

public class Solution {

  static double f = 0.1;
  static int rate = -1;
  static int pindex = -1;

  static int getMaxIndex(double arr[][], int col) {

    double maxValue = Double.MIN_VALUE;
    int index = 0;

    for (int i = 0; i < arr[0].length; i++) {

      if ((arr[col][i]) > maxValue) {
        maxValue = arr[col][i];
        index = i;
      }

    }
    return index;

  }

  static double getMaxValue(double arr[][], int col, int row, double[][] memo) {

    double maxValue = Double.MIN_VALUE;
    int index = 0;

    // if(memo[col][row]!=0) {
    // return memo[col][row];
    // }

    for (int i = 0; i < arr[0].length; i++) {

      if ((arr[col][i]) > maxValue) {
        maxValue = arr[col][i];
        index = i;
      }

    }
    // memo[col][row] = maxValue;
    // return memo[col][row];
    return maxValue;
  }

  public static double maxVal(double[][] arr, int i, int j, double[][] memo) {

    if (i >= arr.length || i < 0 || j >= arr[i].length || j < 0)
      return 0;

    double right = maxVal(arr, i, j + 1, memo);
    double bottom = maxVal(arr, i + 1, j, memo);

    // memo[i][j] = arr[i][j] + Math.max(right, bottom);
    // return memo[i][j];

    return arr[i][j] + Math.max(right, bottom);
  }

  static double Profit(double arr[][], double ans[][], int m, int n, double[][] memo) {

    double profit = 1;
    double rate = 0;
    if (n == 0) {
      return 0;
    }

    if (n == 1) {

      rate = arr[m][n] / arr[m][n - 1];
      ans[m][n] = rate;
      pindex = m;

      return profit *= maxVal(ans, m, n, memo);

    }

    for (int j = n; j >= 0; j--) {

      for (int i = 0; i <= m; i++) {

        profit = 1;

        if (memo[i][j] == 0) {
          profit = Profit(arr, ans, i, n - 1, memo);
          if (i == pindex) {

            rate = arr[i][n] / arr[i][n - 1];
            ans[i][n] = rate;

          } else {

            rate = arr[i][n] / arr[i][n - 1];
            ans[i][n] = (1 - f) * rate;
          }

          pindex = getMaxIndex(ans, m);
          profit *= getMaxValue(ans, m, j, memo);
          memo[m][n] = profit;
        }

      }

    }

    return memo[m][n];
  }

  public static void main(String[] args) {

    int m = 3;
    int n = 5;
    double arr[][] = new double[][] { { 10, 20, 40, 1, 1 },
        { 20, 22, 24, 26, 28 },
        { 100, 95, 100, 105, 100 }
    };

    double ans[][] = new double[m][n];
    double memo[][] = new double[m][n];

    for (double[] row : ans)
      Arrays.fill(row, 0);

    for (double[] row : memo)
      Arrays.fill(row, 0);

    System.out.println(Profit(arr, ans, m - 1, n - 1, memo));

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(ans[i][j] + " ");
      }
      System.out.println();
    }

    // for(int i=0; i<m; i++) {
    // for(int j=0; j<n; j++) {
    // System.out.print(memo[i][j]+" ");
    // }
    // System.out.println();
    // }

  }
}
