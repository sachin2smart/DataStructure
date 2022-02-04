package in.sachinshinde.dp.minimax;

//	https://leetcode.com/problems/predict-the-winner/

/*
 *	 -----------------------
 *	|	Predict the winner	|  
 *	 -----------------------
 *
 *	You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 *	Player 1 and player 2 take turns, with player 1 starting first. 
 *	Both players start the game with a score of 0. 
 *	At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. 
 *	The player adds the chosen number to their score. 
 *	The game ends when there are no more elements in the array.

	Return true if Player 1 can win the game. 
	If the scores of both players are equal, then player 1 is still the winner, and you should also return true. 
	You may assume that both players are playing optimally.
 */

/*
 * 	Example:
 * ---------
	(1)	Input: nums = [1,5,2]
		Output: false
		Explanation: Initially, player 1 can choose between 1 and 2. 
		If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
		So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
		Hence, player 1 will never be the winner and you need to return false.
		
	(2)	Input: nums = [1,5,233,7]
		Output: true
		Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
		Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 */



//	Approach :	"Minimax Algorithm" 


public class PredictWinner {
	//	Using 2D DP array
	public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        
        for(int i=0; i<n; i++)
            dp[i][i] = nums[i];
        
        for(int k=1; k<n; k++)	// k = how far the current block is away from diagonal dp[i][i]
            for(int i=0; i<n-k; i++){
                int j = i + k;
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        
        return dp[0][n-1] >= 0;
    }
	
	/*
	 * 		LINE 55 : if player A picks position i, eventually player A will get nums[i] - dp[i + 1][j] more score than player B after they pick up all numbers. 
	 *  			Similarly, if player A picks position j, player A will earn nums[j] - dp[i][j - 1] more score than player B after they pick up all numbers. 
	 *  			Since A is smart, A will always choose the max
	 *  
	 *  	....i.(i+1).....(j-1).j
	 *  	
	 *  	# Choose i ->   remaining i+1 to j
	 *  	# Choose j ->   remaining i to j-1
	 */
	
	//	Using 1D DP array
	public boolean predictTheWinnerOptimal(int[] nums) {
		if(nums == null)
			return true;
		
		int n = nums.length;
		
		if((n&1) == 0)	//	if n is an even number 
			return true;
		
		int[] dp = new int[n];
		
		for(int i=n-1; i>=0; i--) {
			for(int j=i; j<n; j++) {
				if(i==j)
					dp[i] = nums[i];
				else
					dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j-1]);
			}
		}
		
		return dp[n-1]>=0;
	}
	
	
	
	public static void main(String[] args) {
		PredictWinner winner = new PredictWinner();
		
		System.out.println(winner.predictTheWinner(new int[] {1,5,2}));			// false
		System.out.println(winner.predictTheWinner(new int[] {1,5,233,7}));		// true
		
		System.out.println(winner.predictTheWinnerOptimal(new int[] {1,5,2}));			// false
		System.out.println(winner.predictTheWinnerOptimal(new int[] {1,5,233,7}));		// true
	}
}


//	https://leetcode.com/problems/predict-the-winner/discuss/96828/JAVA-9-lines-DP-solution-easy-to-understand-with-improvement-to-O(N)-space-complexity.