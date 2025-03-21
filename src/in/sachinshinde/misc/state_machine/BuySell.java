package in.sachinshinde.misc.state_machine;

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

/*
 * 	-----------------------------------
 * |	Best Time to Buy and Sell Stock |
 * 	-----------------------------------
 * 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock 
 * 	and choosing a different day in the future to sell that stock.

	Return the maximum profit you can achieve from this transaction. 
	If you cannot achieve any profit, return 0.
 * 
 */

public class BuySell {
	//	Naive Approach : TC = O(n)
	public int maxProfit(int[] priceOfDay) {
        int leastPriceSoFar = Integer.MAX_VALUE;
        int maxProfitGainSoFar = 0;

        int profitIfSoldToday;

		for (int currPrice : priceOfDay) {
			if (currPrice < leastPriceSoFar) {
				leastPriceSoFar = currPrice;
			}

			profitIfSoldToday = currPrice - leastPriceSoFar;

			if (maxProfitGainSoFar < profitIfSoldToday) {
				maxProfitGainSoFar = profitIfSoldToday;
			}

		}
        return maxProfitGainSoFar;
    }
	
	//	DP Approach : TC = O(n)
	public int maxProfit2(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
			maxProfit = Math.max(maxProfit, price - minPrice);
		}
	    return maxProfit;
    }
	
	public static void main(String[] args) {
		BuySell buySell = new BuySell();
		System.out.println(buySell.maxProfit(new int[] {7,1,5,3,6,4}));		//	5
		System.out.println(buySell.maxProfit2(new int[] {7,1,5,3,6,4}));	//	5
		System.out.println(buySell.maxProfit(new int[] {7,6,4,3,1}));		//	0
		System.out.println(buySell.maxProfit2(new int[] {7,6,4,3,1}));		//	0
	}
}
