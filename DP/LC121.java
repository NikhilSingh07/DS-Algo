//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
// Buy and sell stock

public class LC121 {

        // > if I'm selling a stock on ith day, then I have to buy it between 0 and i-1 day where cost is min
    // TC: O(n)
    // SC: O(1)
    public int maxProfit(int[] prices) {
        
        int buy = prices[0];
        int maxProfit = 0;


        for(int i=1; i<prices.length; i++) {

            int cost = prices[i] - buy;
            maxProfit = Math.max(maxProfit, cost);
            buy = Math.min(buy, prices[i]);
        }

        return maxProfit;
    }
    
}
