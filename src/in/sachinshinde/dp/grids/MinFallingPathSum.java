package in.sachinshinde.dp.grids;

import java.util.Arrays;

//	https://leetcode.com/problems/minimum-falling-path-sum/

/*
 * 	Given an n x n array of integers matrix, 
 * 		return the minimum sum of any falling path through matrix.
 * 
 * 	A falling path starts at any element in the first row and
 * 	 chooses the element in the next row that is either directly below or diagonally left/right. 
 * 	Specifically, the next element from position (row, col) will be 
 * 	(row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */

public class MinFallingPathSum {

	public int minFallingPathSum(int[][] matrix) {
		for (int i = 1; i < matrix.length; ++i)
		    for (int j = 0; j < matrix.length; ++j)
		    	matrix[i][j] += Math.min(matrix[i - 1][j], 
		    					Math.min(matrix[i - 1][Math.max(0, j - 1)], 
		    							matrix[i - 1][Math.min(matrix.length - 1, j + 1)]));
		  return Arrays.stream(matrix[matrix.length - 1]).min().getAsInt();
    }
	
	public static void main(String[] args) {
		int matrix[][] = new int[][] {
			{2,1,3},
			{6,5,4},
			{7,8,9}
		};
		MinFallingPathSum minFallingPathSum = new MinFallingPathSum();
		System.out.println(minFallingPathSum.minFallingPathSum(matrix));
		
		matrix = new int[][] {
			{2,1,3},
			{6,5,4},
			{7,8,9}
		};
		System.out.println(minFallingPathSum.miniFallingPathSum(matrix));
	}
	
	//	DP, DFS, Memoization
	public int miniFallingPathSum(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] dp = new int[rows][cols];
		int res = Integer.MAX_VALUE;
		
		for(int col=0; col<cols; col++)
			res = Math.min(res, dfs(0, col, matrix, dp));
		
		return res;
	}

	private int dfs(int row, int col, int[][] matrix, int[][] dp) {
		if(row >= matrix.length)
			return 0;
		
		if(col == -1 || col >= matrix[0].length)
			return Integer.MAX_VALUE;
		
		if(dp[row][col] != 0)
			return dp[row][col];
		
		return dp[row][col] = matrix[row][col] +
				min(dfs(row+1, col-1, matrix, dp),
						dfs(row+1, col, matrix, dp),
						dfs(row+1, col+1, matrix, dp));
	}
	
	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
}

/*
 	------------------------------------------------------------------
	Input: matrix = [[2,1,3],
					 [6,5,4],
					 [7,8,9]]
	Output: 13
	Explanation: There are two falling paths with a minimum sum as -
		1	->	4	->	8
		1	->	5	->	7
	------------------------------------------------------------------
	Input: matrix = [[-19,57],
					 [-40,-5]]
	Output: -59
	Explanation: The falling path with a minimum sum is -
		-11	->	-40
	-------------------------------------------------------------------
*/