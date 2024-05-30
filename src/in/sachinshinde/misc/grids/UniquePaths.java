package in.sachinshinde.misc.grids;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/unique-paths/

/*
 *	There is a robot on an m x n grid.
 *	The robot is initially located at the top-left corner (i.e., grid[0][0]).
 *	The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
 *	The robot can only move either down or right at any point in time.
 *	Given the two integers m and n, return the number of possible unique paths 
 *		that the robot can take to reach the bottom-right corner.
 */

public class UniquePaths {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	public int uniquePaths(int m, int n) {
		if(m==1 || n==1)	//	if we are at rightbottom node's top node, or at rightbottom node's left node
			return 1;

		String key = m +"_"+ n;
		if(map.containsKey(key))	//	utilize memoization
			return map.get(key);

		int downMove = uniquePaths(m-1, n);		//	recursion while down move
		int rightMove = uniquePaths(m, n-1);	//	recursion while right move

		map.put(key, downMove+rightMove);		//	memoize it
		return downMove + rightMove;			//	final calculation
    }
	
	public static void main(String[] args) {
		UniquePaths uniquePaths = new UniquePaths();
		System.out.println(uniquePaths.uniquePaths(3, 7));	//	28
		System.out.println(uniquePaths.uniquePaths(3, 2));	//	3
		
		System.out.println(uniquePaths.uniquePaths2(3, 7));	//	28
		System.out.println(uniquePaths.uniquePaths2(3, 2));	//	3
	}
	
	// Using 2D DP
	public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);	// First Row = 1
        
        for(int i=0; i<m;i++)
            dp[i][0] = 1;	//	First Col = 1
        
        for(int i=1; i<m; i++)
            for(int j=1; j<n;j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            
        return dp[m-1][n-1];
    }
}

/*
	Input: m = 3, n = 7
	Output: 28
	
	Input: m = 3, n = 2
	Output: 3
	Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
		1. Right -> Down -> Down
		2. Down -> Down -> Right
		3. Down -> Right -> Down
*/