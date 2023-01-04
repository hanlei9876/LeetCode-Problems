package module_1_array_and_string.array;

public class LC_121_Best_Time_to_Buy_and_Sell_Stock {

    // solution-1 (Time Limit Exceeded): brute force
    // time: O(N^2)
    // space: O(1)
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length - 1; i ++) {
            for (int j = i + 1; j < prices.length; j++) {
                int currDiff = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, currDiff);
            }
        }

        return maxProfit;
    }

    // solution-2: (Special Algorithm) one-pass to maintain minPrice & maxDiff at the same time
    // time: O(N)
    // space: O(1)
    public int maxProfit_2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            // the aim to keep track of minPrice is to maintain the max difference so far
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            int diff = prices[i] - minPrice;
            if (diff > maxProfit) {
                maxProfit = diff;
            }
        }

        return maxProfit;
    }
}
