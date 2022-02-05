package in.sachinshinde.dp.state_machine;

import java.util.Arrays;

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

/*
 *	 -------------------
 *	|	Buy Sell III	|
 * 	 -------------------
 * 
 * 	You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * 	Find the maximum profit you can achieve. 
 * 	You may complete at most two transactions. --IMP--
 * 
 * 	Note: You may not engage in multiple transactions simultaneously 
 * 	(i.e., you must sell the stock before you buy again).
 */

/*
 * 	Example 1:
	---------
		Input: prices = [3,3,5,0,0,3,1,4]
		Output: 6
		Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
		Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
	
	Example 2:
	---------
		Input: prices = [1,2,3,4,5]
		Output: 4
		Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
		Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

	Example 3:
	---------
		Input: prices = [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

public class BuySell3 {
	
	// DP 2D Approach 
	public int maxProfit(int[] prices) {
		if (prices.length == 0) 
			return 0;
		
        int[][] dp = new int[3][prices.length];
        
        for (int k = 1; k <= 2; k++)  {
            for (int i = 1; i < prices.length; i++) {
        
            	int min = prices[0];
                
            	for (int j = 1; j <= i; j++)
                    min = Math.min(min, prices[j] - dp[k-1][j-1]);
            	
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }

        return dp[2][prices.length - 1];
    }
	
	//	DP 1D Approach
	public int MaxProfitDpCompact2(int[] prices) {
        if (prices.length == 0) 
        	return 0;
        int[] dp = new int[3];
        int[] min = new int[3];
        Arrays.fill(min, prices[0]);
        for (int i = 1; i < prices.length; i++)  {
            for (int k = 1; k <= 2; k++) {
                min[k] = Math.min( min[k] , prices[i] - dp[k-1] );
                dp[k]  = Math.max( dp[k]  , prices[i] - min[k]  );
            }
        }
        return dp[2];
    }
	
	//	Simple Approach : "State Machine"
	public int MaxProfitDpCompactFinal(int[] prices)  {
		int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int sell1 = 0; 
        int sell2 = 0;

        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        
        return sell2;
    }
	
	public static void main(String[] args) {
		BuySell3 buySell = new BuySell3();
		
		System.out.println();
		System.out.println(buySell.maxProfit(new int[] {3,3,5,0,0,3,1,4}));		//	6
		System.out.println(buySell.maxProfit(new int[] {1,2,3,4,5}));			//	4
		System.out.println(buySell.maxProfit(new int[] {7,6,4,3,1}));			//	0
		
		System.out.println();
		System.out.println(buySell.MaxProfitDpCompact2(new int[] {3,3,5,0,0,3,1,4}));	//	6
		System.out.println(buySell.MaxProfitDpCompact2(new int[] {1,2,3,4,5}));			//	4
		System.out.println(buySell.MaxProfitDpCompact2(new int[] {7,6,4,3,1}));			//	0
		
		System.out.println();
		System.out.println(buySell.MaxProfitDpCompactFinal(new int[] {3,3,5,0,0,3,1,4}));	//	6
		System.out.println(buySell.MaxProfitDpCompactFinal(new int[] {1,2,3,4,5}));			//	4
		System.out.println(buySell.MaxProfitDpCompactFinal(new int[] {7,6,4,3,1}));			//	0

	}
}

/*
 *									DP 1D 
 *-------------------------------------------------------------------------
 *					0	1	2	3	4	5	6	7	8	9	<-- prices p[n]
 *-------------------------------------------------------------------------
 *  	min[0]	  p[0]
 *  	min[1]	  p[0]	t1
 *  	min[2]    p[0]	t2
 *  	------------------
 *  	dp[0]		0	
 *  	dp[1]		0	s1
 *  	dp[2]		0	s2
 *  ------------------------------------------------------------------------
 *  	t1	= 	min[1]	, 	p[1]-dp[0]		->	get min		-> memoize min
 *  	s1	=	dp[1]	,	p[1]-min[1]		->	get max		-> memoize max
 *  
 *  	t2	= 	min[2]	, 	p[1]-dp[1]		->	get min		-> memoize min
 *  	s2	=	dp[2]	,	p[1]-min[2]		->	get max		-> memoize max
 *  -------------------------------------------------------------------------
 *  	ans	=> dp[2] 
 *  -------------------------------------------------------------------------
 */

/*
 * 		State Machine
 * ------------------------
 * To find the profit each transaction has 2 states

		State 1 -> Buying
		State 2 -> Selling
		
	When we buying, we use the profit. 
		So profit = profit - stock price
	When we selling, we add the earning into profit. 
		So profit = profit + stock price
		
	Using the above idea we create a state machine to find profit 
		from at most 2 transactions (4 states).
	-------------------------------------------------------------	
		State machine will look like:
		
		transaction1 :- buy1 = max(buy1, 0 - stock) // for the 1st buy profit is 0
						sell1 = max(sell1, buy1 + stock)
		For 2nd transaction we use the profit accumulated from 1st transaction
		
		transaction2 :- buy2 = max(buy2, sell1 - stock)
						sell2 = max(sell2, buy2 + stock)
 */

/*
 * 	State machine : example
 * ------------------------------------------------------------
 * 	buy1	=  INF		min		p[i]			-- memoize min
 * 	sell1	=  0		max		p[i] - buy1		-- memoize max
 * 	buy2	=  INF		min		p[i] - sell1	-- memoize min
 * 	sell2	=  0		max		p[i] - buy2		-- memoize max
 * ------------------------------------------------------------
		prices[] = {1,2,3,4,5}
		----------------------
		buy1	1	
		buy2	1	
		sell1	0	
		sell2	0	
		-----------
		buy1	1	
		buy2	1	
		sell1	1	
		sell2	1	
		-----------
		buy1	1	
		buy2	1	
		sell1	2	
		sell2	2	
		-----------
		buy1	1	
		buy2	1	
		sell1	3	
		sell2	3	
		-----------
		buy1	1	
		buy2	1	
		sell1	4	
		sell2	4	
		-----------
		
		sell2 = 4  <----answer
-------------------------------------------------------------------
 */
