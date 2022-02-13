package in.sachinshinde.graph.dfs;

//	https://leetcode.com/problems/max-area-of-island/

/*
 * 	You are given an m x n binary matrix grid. 
 * 	An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
 * 	You may assume all four edges of the grid are surrounded by water.
 * 	The area of an island is the number of cells with a value 1 in the island.
 * 
 * 	Return the maximum area of an island in grid. If there is no island, return 0.
 */

public class MaxAreaOfIsland {

	public int getMaxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length < 1 || grid[0].length < 1)
		      return 0;
		
		int max = 0;
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 1) {
					max = Math.max(max, dfs(grid, i, j));
				}
			}
		}
		
		return max;
	}

	private int dfs(int[][] grid, int i, int j) {
		if(i<0 || i >= grid.length || j<0 || j >= grid[0].length || grid[i][j] == 0)
			return 0;
		
		grid[i][j] = 0;
		
		return 1 +
				dfs(grid,i+1,j)+
				dfs(grid,i-1,j)+
				dfs(grid,i,j+1)+
				dfs(grid,i,j-1);
	}
	
	public static void main(String[] args) {
		MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
		int[][] grid = new int[][] {
			{0,0,1,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,0,0,0},
			{0,1,1,0,1,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,1,0,0,1,0,1,0,0},
			{0,1,0,0,1,1,0,0,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,1,1,0,0,0,0}
		};
			
		System.out.println(maxAreaOfIsland.getMaxAreaOfIsland(grid));
	}
	
}

/*

*/