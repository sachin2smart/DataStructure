package in.sachinshinde.dp.coinchange;

//	Coin Change 2

/*
 * 	You are given an integer array coins representing coins of different denominations and 
 * 	an integer amount representing a total amount of money.
 * 
 * 	Return the number of combinations that make up that amount. 
 * 	If that amount of money cannot be made up by any combination of the coins, return 0.
 * 
 * 	You may assume that you have an infinite number of each kind of coin.
 */


/*
 * 		Strategy:- For each coin: 
 * 						- starts with coin value to amount, 
 * 						- update i'th value as dp[i] = dp[i] + dp[i-c]
 */

public class CoinChange2 {
	 public static int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                dp[i] += dp[i - c];
            }
        }

        return dp[amount];
    }
	 
	public static void main(String[] args) {
		System.out.println(change(5, new int[] {1,2,5,1}));	//4
		/*
		 * Explanation: there are four ways to make up the amount:
			5=5
			5=2+2+1
			5=2+1+1+1
			5=1+1+1+1+1
		 */
	}
}
