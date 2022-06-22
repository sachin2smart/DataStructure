package in.sachinshinde.dp.coinchange;

import java.util.Arrays;

//	https://leetcode.com/problems/coin-change/

/*
 *	Coin Change:
 *		You are given an integer array coins representing coins of different denominations 
 *			and an integer amount representing a total amount of money.
 *		Return the fewest number of coins that you need to make up that amount. 
 *		If that amount of money cannot be made up by any combination of the coins, return -1.
 *		
 *		You may assume that you have an infinite number of each kind of coin. 
 */

public class CoinChange {
	public static int getMinCoinChangeNeeded(int[] coins, int amount) {
        int[] change = new int[amount+1];
        Arrays.fill(change, -1);
        
        change[0] = 0;
        
        for(int nextCoinIndex=1; nextCoinIndex<=amount; nextCoinIndex++) {
            boolean isMinIndexedValueFound = false;
            int minNumCoinsUsed = Integer.MAX_VALUE;
            
            for(int currCoinValue : coins)
                if(nextCoinIndex >= currCoinValue && change[nextCoinIndex - currCoinValue] != -1) {
                	isMinIndexedValueFound = true;
                    minNumCoinsUsed = Math.min(minNumCoinsUsed, change[nextCoinIndex - currCoinValue]);
                }
            
            if(isMinIndexedValueFound)
                change[nextCoinIndex] = 1 + minNumCoinsUsed;
        }
        
        return change[amount];
    }
	
	public int coinChange(int[] coins, int amount) {
	    int[] dp = new int[amount + 1];
	    
	    for (int i=1; i < dp.length; i++) {
	      dp[i] = dp.length;
	      for (int j=0; j < coins.length; j++)
	        if (i >= coins[j]) 
	        	dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
	      
	    }
	    
	    return dp[amount] == dp.length ? -1 : dp[amount];
	 }
	
	public static void main(String[] args) {
		System.out.println(getMinCoinChangeNeeded(new int[]{5,5,1}, 13));	//5 	5+5+1+1+1
	}
	
}
