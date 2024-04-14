package in.sachinshinde.graph.dfs.grids;

//	https://leetcode.com/problems/number-of-islands/

/*
 * 	Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * 	return the number of islands.

	An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	You may assume all four edges of the grid are all surrounded by water.
 */

public class NumberOfIslands {

	// Simple DFS approach:
	/*
	 * 	Steps: iterate over the graph to sink 
	 * 
	 * 	sink ==>
	 * 		- return 0 if value is 0 or crossing the border
	 * 		- mark current value to 0
	 * 		- move up, down, left, right to recursively check for sinking
	 * 		- return 1
	 */

	
	public int getNumOfIslands(char[][] grid) {
		int numIslands = 0;
		
		for(int i=0; i<grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == '1') {
					numIslands += dfs(grid, i, j);
				}
			}
		}
		
		return numIslands;
	}
	
	private int dfs(char[][] grid, int i, int j) {
		//	boundary condition
		if(i<0 || i == grid.length || j<0 || j  == grid[i].length || grid[i][j] == '0') {
			return 0;
		}

		// mark visited (making land into water)
		grid[i][j] = '0';

		dfs(grid, i-1, j);	//	sink island into the water on the "up" side if land exists
		dfs(grid, i+1, j);	//	sink island into the water on the "down" side if land exists
		dfs(grid, i, j+1);   //	sink island into the water on the "right" side if land exists
		dfs(grid, i, j-1);	//	sink island into the water on the "left" side if land exists

		return 1;
	}
	
	public static void main(String[] args) {
		char[][] grid = new char[][]  {
			  {'1','1','1','1','0'},
			  {'1','1','0','1','0'},
			  {'1','1','0','0','0'},
			  {'0','0','0','0','0'}
			};
		NumberOfIslands islands = new NumberOfIslands();
		System.out.println(islands.getNumOfIslands(grid));	//	1
		
		 grid = new char[][]  {
			  {'1','1','0','0','0'},
			  {'1','1','0','0','0'},
			  {'0','0','1','0','0'},
			  {'0','0','0','1','1'}
			};
		System.out.println(islands.getNumOfIslands(grid));	//	3
	}
}

/*
	Input: grid = [
	  ["1","1","1","1","0"],
	  ["1","1","0","1","0"],
	  ["1","1","0","0","0"],
	  ["0","0","0","0","0"]
	]
	Output: 1
	
	Input: grid = [
	  ["1","1","0","0","0"],
	  ["1","1","0","0","0"],
	  ["0","0","1","0","0"],
	  ["0","0","0","1","1"]
	]
	Output: 3
*/