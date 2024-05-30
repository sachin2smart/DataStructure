package in.sachinshinde.misc.state_machine;

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

/*
 *	 ---------------------------------------
 *	|	Best Time to Buy and Sell Stock II	|
 *	 ---------------------------------------
 *
 *	You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *	On each day, you may decide to buy and/or sell the stock. 
 *	You can only hold at most one share of the stock at any time. 
 *	However, you can buy it then immediately sell it on the same day.
 *
 *	Find and return the maximum profit you can achieve.
 */

public class BuySell2 {
	
	public int maxProfit(int[] prices) {
		if(prices.length < 2)
            return 0;
        
		int n = prices.length;
        int[][] dp = new int[2][n];
        
        dp[0][0] = 0; 
        dp[1][0] = -prices[0];
        
        int res = 0;
        
        for(int i = 1; i < n; ++i) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i]);
            res = Math.max(res, dp[0][i]);
        }
        
        return res;
    }
	
	public int maxProfit2(int[] prices) {
	    int ret = 0;
	    for (int i = 1 ; i < prices.length ; i++)
	        ret += Math.max(prices[i] - prices[i-1] , 0);
	    return ret;
	}
	
	public static void main(String[] args) {
		BuySell2 buySell = new BuySell2();
		System.out.println(buySell.maxProfit(new int[] {7,1,5,3,6,4}));		//	7
		System.out.println(buySell.maxProfit(new int[] {1,2,3,4,5}));		//	4
		System.out.println(buySell.maxProfit(new int[] {7,6,4,3,1}));		//	0
		
		System.out.println(buySell.maxProfit2(new int[] {7,1,5,3,6,4}));		//	7
		System.out.println(buySell.maxProfit2(new int[] {1,2,3,4,5}));		//	4
		System.out.println(buySell.maxProfit2(new int[] {7,6,4,3,1}));		//	0
	}
	
}

/*
	State Machine Rules:
	-------------------
	If we are in S0, it means we had no stock in past or we had a stock in past and we just sold it.
	If we are in S1, it means we had one stock in past or we had no stock in past and we just bought it.
	
	At beginning, only way to be in S0 is to buy no stock at that time.
	At beginning, only way to be in S1 is to buy a stock at that time.
 */


/* 
 * 			0		1	2	3	4	5	6	7	8	9	10
 * 	dp[0]	0		t
 * 	dp[1]  -p[0]	s
 * 
 * -----------------------------------------------------------
 * 	t = dp[0][0] , dp[1][0]	+ p[1]	-> get max
 * 												-> get max
 * 	s = dp[1][0] , dp[0][0] - p[1]	-> get max 
 * -----------------------------------------------------------
 * 	result = max(t,s)
 */

/* 
 * 	For Second solution : 
 * 		https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/39420/Three-lines-in-C%2B%2B-with-explanation
 * 	
 * 		7	1	5	3	6	4
 * 		  0   4   0   3   0      = 7 
 */
