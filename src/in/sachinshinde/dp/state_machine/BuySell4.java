package in.sachinshinde.dp.state_machine;

import java.util.Arrays;

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

/*
 * 	 ---------------------------------------
 * 	|	Best Time to Buy and Sell Stock IV	|
 * 	 ---------------------------------------
 * 
 */

public class BuySell4 {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		
		int[] buy  = new int[k+1];	// Decision of having k+1 size
		int[] sell = new int[k+1];

		Arrays.fill(buy,  Integer.MIN_VALUE);
		Arrays.fill(sell, 0);
		
		for(int i=0; i<n; i++)	{	//	for each day	
			for(int j=1; j<=k; j++)	{	//	for every transaction
				buy[j]  = Math.max( buy[j] , sell[j-1] - prices[i]);	//	balance	- at j'th transaction, i'th day (previous sell - current purchase)
				sell[j] = Math.max(sell[j] ,    buy[j] + prices[i]);	//	profit  - at j'th transaction, i'th day (current buy   + current purchase)
			}
		} 
		
		return sell[k];
	}
	
	/*
	 * 	You are given an integer array prices 
	 * 		where prices[i] is the price of a given stock on the ith day, and an integer k.
	 * 
	 * 	Find the maximum profit you can achieve. You may complete at most k transactions.

		Note: You may not engage in multiple transactions simultaneously 
			(i.e., you must sell the stock before you buy again).
 
	 */
	
	public static void main(String[] args) {
		BuySell4 buySell = new BuySell4();
		System.out.println(buySell.maxProfit(2, new int[] {2,4,1}));		//	2
		System.out.println(buySell.maxProfit(2, new int[] {3,2,6,5,0,3}));	//	7
	}
}

/*		
 		k=2				:	Number of transactions  
 		prices= 2,4,1	:	price at day i
 		n=3				:	number of days 
		----------------------------
			i=0	j=1,2	     
		----------------------------
		buy		
			[0]	-2147483648	
			[1]	-2147483648	
			[2]	-2147483648	
		sell		
			[0]	0	
			[1]	0	
			[2]	0
		buy		
			[0]	-2147483648	
			[1]	-2	
			[2]	-2	
		sell		
			[0]	0	
			[1]	0	
			[2]	0	
		----------------------------
			i=1	j=1,2
		----------------------------
		buy		
			[0]	-2147483648	
			[1]	-2	
			[2]	-2	
		sell		
			[0]	0	
			[1]	2	
			[2]	0	
		buy		
			[0]	-2147483648	
			[1]	-2	
			[2]	-2	
		sell		
			[0]	0	
			[1]	2	
			[2]	2	
		----------------------------
			i=2	j=1,2
		----------------------------
		buy		
			[0]	-2147483648	
			[1]	-1	
			[2]	-2	
		sell		
			[0]	0	
			[1]	2	
			[2]	2	
		buy		
			[0]	-2147483648	
			[1]	-1	
			[2]	1	
		sell		
			[0]	0	
			[1]	2	
			[2]	2	
		----------------------------
			ans =>  sell[2] = 2
		----------------------------
 * 
 */
