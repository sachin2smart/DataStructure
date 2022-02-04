package in.sachinshinde.dp.minimax;

//	https://leetcode.com/problems/stone-game/

/*
 * 	 ---------------
 * 	|	Stone Game	|
 *	 ---------------
 *
 *	Alice and Bob play a game with piles of stones. 
 *	There are an even number of piles arranged in a row, 
 *		and each pile has a positive integer number of stones piles[i].
 *	The objective of the game is to end with the most stones. 
 *	The total number of stones across all the piles is odd, so there are no ties.
 *
 *	Alice and Bob take turns, with Alice starting first. 
 *	Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. 
 *	This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 *	Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
*/


//	Approach : "Minimax Algorithm"

public class StoneGame {
	
	public boolean stoneGame(int[] piles) {
        if(piles == null)
            return true;
        
        int n = piles.length;
        
        if((n&1) == 0)
            return true;
        
        int[] dp = new int[n];
        
        for(int i=n-1; i>=0 ; i--)
            for(int j=i; j<n ;j++)
                if(i==j)
                    dp[i] = piles[i];
                else
                    dp[j] = Math.max(piles[i]-dp[j] , piles[j]-dp[j-1]);
        
        return dp[n-1]>=0;
    }
	
}

//	https://leetcode.com/problems/predict-the-winner/discuss/96828/JAVA-9-lines-DP-solution-easy-to-understand-with-improvement-to-O(N)-space-complexity.