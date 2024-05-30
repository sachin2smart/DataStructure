package in.sachinshinde.misc.coinchange;

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
    // why greedy approach is wrong





    public static int getMinCoinChangeNeeded(int[] coins, int amount) {
        int[] change = new int[amount+1];
        Arrays.fill(change, -1);
        
        change[0] = 0;
        
        for(int i=1; i<=amount; i++) {
            boolean isFound = false;
            int minCoins = Integer.MAX_VALUE;
            
            for(int currCoin: coins) {
                if (i >= currCoin && change[i - currCoin] != -1) {
                    isFound = true;
                    minCoins = Math.min(minCoins, change[i - currCoin]);
                }
            }
            
            if(isFound) {
                change[i] = 1 + minCoins;
            }
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
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChangeDP(new int[]{5,5,1}, 13));
	}


    public int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j < dp.length; j++) {
                if (dp[j - coin] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }

    public int coinChangeDP2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }
}
