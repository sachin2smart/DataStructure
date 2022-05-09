package in.sachinshinde.array.matrix;

//	https://leetcode.com/problems/minimum-path-sum

/*
	Given a m x n grid filled with non-negative numbers, 
	find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
	Note: You can only move either down or right at any point in time.
 */

public class MinPathSum {
	//	Recursive way
	public int getMinPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		return getMinPathSum(grid, m-1, n-1);
	}

	private int getMinPathSum(int[][] grid, int m, int n) {
		if(m==0 && n==0)	
			return grid[m][n];
		
		if(m==0)
			return grid[m][n] + getMinPathSum(grid, m, n-1);
		
		if(n==0)
			return grid[m][n] + getMinPathSum(grid, m-1, n);
		
		return grid[m][n] + Math.min(getMinPathSum(grid, m, n-1) , 
				getMinPathSum(grid, m-1, n));
	}
	
	//	DP Way
	public int getMinPathSum2(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                if(m == 0 && n == 0) 
                	grid[m][n] = grid[m][n];
                else if(m == 0 && n != 0) 
                	grid[m][n] += grid[m][n - 1];
                else if(n == 0 && m != 0) 
                	grid[m][n] += grid[m - 1][n];
                else grid[m][n] = grid[m][n] + 
                		Math.min(grid[m - 1][n], grid[m][n - 1]);
            }
        }
        return grid[height - 1][width - 1];
	}
	
	//	DP : 2D 
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        
        for(int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        
        for(int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[m-1][n-1];
    }
	
	
}

/*

 	Input: grid = [[1,3,1],
 	               [1,5,1],
 	               [4,2,1]]
	Output: 7
	Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
  
 */
