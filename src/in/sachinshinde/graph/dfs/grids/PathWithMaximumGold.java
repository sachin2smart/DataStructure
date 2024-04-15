package in.sachinshinde.graph.dfs.grids;

//	https://leetcode.com/problems/path-with-maximum-gold/

/*
 * 	In a gold mine grid of size m x n, each cell in this mine has an integer representing 
 * 		the amount of gold in that cell, 0 if it is empty.

	Return the maximum amount of gold you can collect under the conditions:

        1. Every time you are located in a cell you will collect all the gold in that cell.
        2. From your position, you can walk one step to the left, right, up, or down.
        3. You can't visit the same cell more than once.
        4. Never visit a cell with 0 gold.
        5. You can start and stop collecting gold from any position in the grid that 
           has some gold.
 */

/*
 * 	Example 1:

        Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
        Output: 24
        Explanation:
        [[0,6,0],
         [5,8,7],
         [0,9,0]]
        Path to get the maximum gold, 9 -> 8 -> 7.
        Example 2:
        
        Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
        Output: 28
        Explanation:
        [[1,0,7],
         [2,0,6],
         [3,4,5],
         [0,3,0],
         [9,0,20]]
        Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
         
        
        Constraints:
        
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 15
        0 <= grid[i][j] <= 100
        There are at most 25 cells containing gold.

 */

import java.util.Arrays;
import java.util.Collections;

public class PathWithMaximumGold {

	// Approach 1 (Approach 2 is the best)
    public int getMaximumGold(int[][] grid) {
		int maxGold = 0;

		int m = grid.length;
		int n = grid[0].length;

		for(int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				maxGold = Math.max(maxGold, dfs(grid, m, n, r, c));
			}
		}

		return maxGold;
	}

	private int dfs(int[][] grid, int m, int n, int r, int c) {
		if(r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0) {
			return 0;
		}

		int original = grid[r][c];

		grid[r][c] = 0; 	// marking it as visited

		int maxGold = Collections.max(Arrays.asList(
				dfs(grid, m, n, r + 1, c),
				dfs(grid, m, n, r - 1, c),
				dfs(grid, m, n, r, c + 1),
				dfs(grid, m, n, r, c - 1)));

		grid[r][c] = original;	//	backtrack

		return maxGold + original;
    }

    public static void main(String[] args) {
		PathWithMaximumGold maximumGold = new PathWithMaximumGold();
		int[][] grid = new int[][] {
			{0,6,0},
			{5,8,7},
			{0,9,0}
		};
		System.out.println(maximumGold.getMaximumGold(grid)); 	//	24
		System.out.println(maximumGold.getMaximumGold2(grid)); 	//	24

		grid = new int[][] {
			{1,0,7},
			{2,0,6},
			{3,4,5},
			{0,3,0},
			{9,0,20}
		};
		System.out.println(maximumGold.getMaximumGold(grid)); 	//	28
		System.out.println(maximumGold.getMaximumGold2(grid)); 	//	28
    }
    

    // Approach : 2
    public int getMaximumGold2(int[][] grid) {
		int count = gridWithNoZeros(grid);
		if(count != -1) {
			return count;
		}

		int maxGold = 0;
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] != 0) {
					maxGold = Math.max(maxGold, dfs2(grid, i, j));
				}
			}
		}
		return maxGold;
	}

	private int dfs2(int[][] grid, int i, int j) {
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
			return 0;
		}

		int original = grid[i][j];

		grid[i][j] = 0;	// mark visited

		int maxGold = original + Collections.max(Arrays.asList(
				dfs2(grid,  i + 1, j),
				dfs2(grid,  i - 1, j),
				dfs2(grid, i, j + 1),
				dfs2(grid, i, j - 1)));

		grid[i][j] = original;	// backtrack

		return maxGold;
	}

	public int gridWithNoZeros(int[][] grid){
		int count = 0;
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 0) {
					return -1;
				}
				else {
					count += grid[i][j];
				}
			}
		}
		return count;
	}
}