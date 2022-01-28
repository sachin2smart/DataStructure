package in.sachinshinde.dp.distribute_candies;

//	https://leetcode.ca/all/1692.html
//	https://leetcode.ca/2020-07-18-1692-Count-Ways-to-Distribute-Candies/

/*
 *	There are n unique candies (labeled 1 through n) and k bags. 
 *	You are asked to distribute all the candies into the bags such that every bag has at least one candy.

	There can be multiple ways to distribute the candies. 
	Two ways are considered different if the candies in one bag in the first way 
		are not all in the same bag in the second way. 
	The order of the bags and the order of the candies within each bag do not matter. 
 */

/*
 * 	For example, (1), (2,3) and (2), (1,3) are considered different 
 * 		because candies 2 and 3 in the bag (2,3) in the first way are not in the same bag in the second way 
 * 		(they are split between the bags (2) and (1,3)). 
 * 	However, (1), (2,3) and (3,2), (1) are considered the same 
 * 		because the candies in each bag are all in the same bags in both ways.
 */

/*
 * 	Given two integers, n and k, return the number of different ways to distribute the candies. 
 * 	As the answer may be too large, return it modulo 10^9 + 7
 */

public class DistributeCandies {
	//	------------------------
	//	### SOLUTION 1 : DP Way
	//	------------------------
	public static int waysToDistribute(int n, int k) {
        final int MODULO = 1000000007;// 
        long[][] dp = new long[k][n];
        for (int j = 0; j < n; j++)
            dp[0][j] = 1;
        for (int i = 1; i < k; i++) {
            for (int j = i; j < n; j++)
                dp[i][j] = (dp[i][j - 1] * (i + 1) + dp[i - 1][j - 1]) % MODULO;
        }
        return (int) dp[k - 1][n - 1];
    }
	/*
	 * Use dynamic programming. Create a 2D array dp with k rows and n columns, where 
	 * 		dp[i][j] represents the number of ways to distribute j + 1 candies into i + 1 bags.

		Obviously, 
			dp[i][j] = 0 when i > j. 
			dp[0][j] = 1 when there is only one bag, there is only one way to destribute the candies for all 0 <= j < n.

		For 1 <= i < k and i <= j < n, there is 
			dp[i][j] = dp[i][j - 1] * (i - 1) + dp[i - 1][j - 1].

		Finally, return dp[k - 1][n - 1].
	 */
	
	
	//	------------------------
	//	### SOLUTION 2 : DP Way 
	//	------------------------
		public static int waysToDistribute_DP1(int n, int k) {
		    final long kMod = (long) 1e9 + 7;
	
		    long[][] dp = new long[n + 1][k + 1];
	
		    for (int i = 0; i <= k; ++i)
		      dp[i][i] = 1;
	
		    for (int i = 1; i <= n; ++i)
		      for (int j = 1; j <= k; ++j)
		        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] * j) % kMod;
	
		    return (int) dp[n][k];
		}
	  
	  /*
	   * 	dp[i][j] := # of ways to distribute i candies to j bags
	     	dp[i][j] = 0, if i < j
	              	 = 1, if i == j
	              	 = dp[i - 1][j - 1]       (the new candy occupies a bag)
	              	   + dp[i - 1][j] * j     (the new candy is in any of j bags) 
	   */
	  
		
		//	------------------------------------------------
		//	### SOLUTION 3 : DP Way  - Dimensions optimized
		//	------------------------------------------------
		public static int waysToDistribute_DP2(int n, int k) {
		    final long kMod = (long) 1e9 + 7;
	
		    long[][] dp = new long[k + 1][n + 1];
	
		    for (int i = 0; i <= k; ++i)
		      dp[i][i] = 1;
	
		    for (int i = 1; i <= k; ++i)
		      for (int j = i + 1; j <= n; ++j)
		        dp[i][j] = (dp[i - 1][j - 1] + i * dp[i][j - 1]) % kMod;
	
		    return (int) dp[k][n];
		}

		//	------------------------------------------------
		//	### SOLUTION 4 : DP Way  - 1D array
		//	------------------------------------------------		
		public static int waysToDistribute_DP_1D(int n, int k) {
			final long MOD = (long) 1e9 +7;
			long[] dp = new long[k+1];
			
			dp[0] = 1;
			
			for(int i=0; i!=n ; i++) {	// for each new candie
				long[] prev = dp;
				dp = new long[k+1];
				
				for(int j=0; j<=k ; j++) {	// for previous bags
					//	join existing bag
					dp[j] =	(dp[j] + prev[j] *j) % MOD; 
					//	open a new bag - if possible
					if(j != k)
						dp[j+1] = (dp[j+1] + prev[j]) % MOD;
				}
			}
			
			return (int) dp[k];
		}

		public static void main(String[] args) {
			System.out.println(waysToDistribute(3,2));	//3
			System.out.println(waysToDistribute(4,2));	//7
			System.out.println(waysToDistribute(20,5));	//206085257
			
			System.out.println();
			System.out.println(waysToDistribute_DP1(3,2));	//3
			System.out.println(waysToDistribute_DP1(4,2));	//7
			System.out.println(waysToDistribute_DP1(20,5));	//206085257
			
			System.out.println();
			System.out.println(waysToDistribute_DP2(3,2));	//3
			System.out.println(waysToDistribute_DP2(4,2));	//7
			System.out.println(waysToDistribute_DP2(20,5));	//206085257
			
			System.out.println();
			System.out.println(waysToDistribute_DP_1D(3,2));	//3
			System.out.println(waysToDistribute_DP_1D(4,2));	//7
			System.out.println(waysToDistribute_DP_1D(20,5));	//206085257
			
		}
}
