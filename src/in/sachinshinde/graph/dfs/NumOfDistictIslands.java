package in.sachinshinde.graph.dfs;

import java.util.HashSet;
import java.util.Set;

/*
 * 	Given a non-empty 2D array grid of 0's and 1's, 
 * 		an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
 * 	You may assume all four edges of the grid are surrounded by water.
 * 
 * 	Count the number of distinct islands. 
 * 	An island is considered to be the same as another 
 * 		if and only if one island can be translated (and not rotated or reflected) to equal the other.
 */

public class NumOfDistictIslands {

	public int numDistinctIslands(int[][] grid) {
	    if (grid == null || grid.length < 1 || grid[0].length < 1)
	      return 0;
	    
	    Set<String> masterSet = new HashSet<>();	// First Set - to store unique island
	    
	    for (int i = 0; i < grid.length; i++) {
	      for (int j = 0; j < grid[0].length; j++) {
	    	  
	        Set<String> currSet = new HashSet<>();	//	Second Set - to get islands based on current position 
	        
	        if (grid[i][j] == 1) {
	          dfs(grid, i, j, i, j, currSet);
	          masterSet.add(currSet.toString());	// Since set contains unique elements, repeated will be mapped into existing
	        }
	        
	      }
	    }
	    
	    return masterSet.size();
	  }

	  public void dfs(int[][] grid, int i, int j, int baseX, int baseY, Set<String> currSet) {
	    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
	      return;
	    
	    currSet.add((i - baseX) + "_" + (j - baseY));	//	Adding a delimiter will avoid any math operation for indices 
	    
	    grid[i][j] = 0;	// once traversed mark it as closed
	    
	    dfs(grid, i + 1, j, baseX, baseY, currSet);
	    dfs(grid, i - 1, j, baseX, baseY, currSet);
	    dfs(grid, i, j - 1, baseX, baseY, currSet);
	    dfs(grid, i, j + 1, baseX, baseY, currSet);
	  }
	  
	  public static void main(String[] args) {
		NumOfDistictIslands distictIslands = new NumOfDistictIslands();
		int[][] grid = new int[][] {
			{1,1,0,0,0},
			{1,1,0,0,0},
			{0,0,0,1,1},
			{0,0,0,1,1}
		};
		
		System.out.println(distictIslands.numDistinctIslands(grid));	//	1
		
		grid = new int[][] {
			{1,1,0,1,1},
			{1,0,0,0,0},
			{0,0,0,0,1},
			{1,1,0,1,1}
		};
		
		System.out.println(distictIslands.numDistinctIslands(grid));	//	3
	}
	
}

/*

	Example 1:
	
	11000
	11000
	00011
	00011
	
	Given the above grid map, return 1.

	Example 2:
	
	11011
	10000
	00001
	11011
	
	Given the above grid map, return 3.
*/