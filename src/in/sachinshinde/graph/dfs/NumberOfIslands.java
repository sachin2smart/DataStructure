package in.sachinshinde.graph.dfs;

//	https://leetcode.com/problems/number-of-islands/

/*
 * 	Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), 
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
	 * 
	 * ** Java tip : clone the given graph to make it useful for sinking, 
	 * 				 keep original graph unaffected.
	 */
	
	char[][] g;
	
	public int getNumOfIslands(char[][] grid) {
		g = grid;
		int numIslands = 0;
		
		for(int i=0; i<g.length; i++)
			for(int j=0; j<g[i].length; j++)
				numIslands += sink(i,j);
		
		return numIslands;
	}
	
	private int sink(int i, int j) {
		if(i<0 || i==g.length || j<0 || j==g[0].length || g[i][j] =='0')
			return 0;

		g[i][j] = '0';
		
		sink(i-1, j);
		sink(i+1, j);
		sink(i, j+1);
		sink(i, j-1);
		
		return 1;
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