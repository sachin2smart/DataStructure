package in.sachinshinde.dp.grids;

import java.util.Arrays;

//	https://leetcode.com/problems/dungeon-game

/*
 	The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 	The dungeon consists of m x n rooms laid out in a 2D grid. 
 	Our valiant knight was initially positioned in the top-left room and 
 		must fight his way through dungeon to rescue the princess.

	The knight has an initial health point represented by a positive integer. 
	If at any point his health point drops to 0 or below, he dies immediately.

	Some of the rooms are guarded by demons (represented by negative integers), 
	so the knight loses health upon entering these rooms;
	other rooms are either empty (represented as 0) or contain magic orbs that 
		increase the knight's health (represented by positive integers).

	To reach the princess as quickly as possible, 
	the knight decides to move only rightward or downward in each step.

	Return the knight's minimum initial health so that he can rescue the princess.

	Note that any room can contain threats or power-ups, even the first room the knight enters and 
	the bottom-right room where the princess is imprisoned.


        Example 1:
        ---------
        Input: dungeon = [
        
        [-2,-3,  3],
        [-5,-10, 1],
        [10, 30,-5]]
        
        Output: 7
        Explanation: The initial health of the knight must be at least 7 
        if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN. [-2,-3,3,1,-5]
        
        Example 2:
        ---------
        Input: dungeon = [[0]]
        Output: 1
         
        
        Constraints:
        -----------
            m == dungeon.length
            n == dungeon[i].length
            1 <= m, n <= 200
            -1000 <= dungeon[i][j] <= 1000
 */

public class DungeonGame {
    // Video Solution : https://leetcode.com/problems/dungeon-game/
    public int calculateMinimumHP(int[][] dungeon) {
	int r = dungeon.length;
	int c = dungeon[0].length;
	
	int[][] dp = new int[r][c];
	
	for(int i=r-1; i>=0; i--) {
	    for(int j=c-1; j>=0; j--) {
		if(i==r-1 && j==c-1) {	// Princes Cell : Bottom Right
		    dp[i][j] = Math.min(0,  dungeon[i][j]);
		}
		else if(i==r-1) {  // last row -- move to next column
		    dp[i][j] = Math.min(0, dp[i][j+1] + dungeon[i][j]);
		}
		else if(j==c-1) { // last column -- move to next row
		    dp[i][j] = Math.min(0, dp[i+1][j] + dungeon[i][j]);
		}
		else {
		    dp[i][j] = Math.min(0, Math.max(dp[i][j+1], dp[i+1][j]) + dungeon[i][j]);
		}
	    }
	}
	
	return Math.abs(dp[0][0]) + 1;
    }
    
    public int calculateMinimumHP_2(int[][] dungeon) {
        int r = dungeon.length;
        int c = dungeon[0].length;
	
        int dp[][] = new int[r][c];
        dp[r-1][c-1] = dungeon[r-1][c-1] < 0 ? (dungeon[r-1][c-1] * -1 + 1) : 1;
        
        for (int i = r-1; i >= 0; i--) {
            for (int j=c-1; j >= 0; j--) {
                if (i == r-1 && j == c-1) {
                    continue;
                }
                if (i == r-1) {
                    dp[i][j] = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                }
                else if (j == c-1) {
                    dp[i][j] = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                }
                else {
                    dp[i][j] = Math.max(Math.min(dp[i][j+1], dp[i+1][j]) - dungeon[i][j], 1);
                }
            }
        }
        return dp[0][0];
    }
    
    public int calculateMinimumHP_3(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        
        int dp[][] = new int[m+1][n+1];
        Arrays.fill(dp[m], Integer.MAX_VALUE);
        
        dp[m][n-1] = 1;
        
        for(int i = m-1; i>=0; i--) {
            dp[i][n]=Integer.MAX_VALUE;
            if(i == m-1) 
        	dp[i][n] = 1;
            for(int j = n-1; j>=0; j--)
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1])-dungeon[i][j]);
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
	DungeonGame dungeonGame = new DungeonGame();
	System.out.println(dungeonGame.calculateMinimumHP(new int[][] {
	    {-2, -3, 3},
            {-5,-10, 1},
            {10, 30,-5}
	  }));	// 7
	
	System.out.println(dungeonGame.calculateMinimumHP_2(new int[][] {
	    {-2, -3, 3},
            {-5,-10, 1},
            {10, 30,-5}
	  }));	// 7
	
	System.out.println(dungeonGame.calculateMinimumHP_3(new int[][] {
	    {-2, -3, 3},
            {-5,-10, 1},
            {10, 30,-5}
	  }));	// 7
    }
}