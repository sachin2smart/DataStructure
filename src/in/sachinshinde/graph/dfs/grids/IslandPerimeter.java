package in.sachinshinde.graph.dfs.grids;

//	https://leetcode.com/problems/island-perimeter/

/*
 	You are given row x col grid representing a map where 
 		grid[i][j] = 1 represents land & 
 		grid[i][j] = 0 represents water.

	- Grid cells are connected horizontally/vertically (not diagonally). 
	- The grid is completely surrounded by water, and there is exactly one island 
		(i.e., one or more connected land cells).
	- The island doesn't have "lakes", meaning 
		 the water inside isn't connected to the water around the island. 
	- One cell is a square with side length 1. 
	- The grid is rectangular, width and height don't exceed 100. 
	
	Determine the perimeter of the island.
		The perimeter is the edge around the node where it touches to land.
	
 */
public class IslandPerimeter {

	//	Method : 1, second method is easy to understand
	private int getIslandPerimeter(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length ==0)
			return 0;
		
		int size = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j=0; j < grid[0].length; j++) {
				if(grid[i][j] == 1) {
					size += 4;
					if(i > 0 && grid[i-1][j] == 1)
						size -= 2;
					if(j > 0 && grid[i][j-1] == 1)
						size -= 2;
				}
			}
		}
		
		return size;
	}
	
	public static void main(String[] args) {
		IslandPerimeter islandPerimeter = new IslandPerimeter();
		
		int[][] grid = new int[][] {
			{1,0,0},
			{1,1,1},
			{0,0,1}
		};
		
		System.out.println(islandPerimeter.getIslandPerimeter(grid));	// 12
		System.out.println(islandPerimeter.islandPerimeter(grid));	//	12
		
		grid = new int[][] {
			{0,0,0},
			{0,1,1},
			{0,0,1}
		};
		
		System.out.println(islandPerimeter.getIslandPerimeter(grid));	// 8
		System.out.println(islandPerimeter.islandPerimeter(grid));	//	8
	}
	
	//	Method 2 : DFS
	public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
        
        for(int i = 0 ; i < grid.length ; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return getPerimeter(grid, i, j);
				}
			}
		}
        
        return 0;
    }
    
    private int getPerimeter(int[][] grid, int i, int j){
		// if it crosses the border - means we have to stop here and return 1 indicating the edge at the border
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return 1;
		}

		/*
			Remember the sequence:
				- check for water
				- check for visited
				- mark as visited
		 */

		// if there is water means we need to consider that there exists an edge
        if(grid[i][j] == 0) {
			return 1;
		}

		// if the island is already visited, means associated edge is already being counted for parameter, so exclude it
        if(grid[i][j] == -1) {
			return 0;
		}

		// Mark that the island is visited, don't mark it with 0 otherwise it will difficult to determine which is border and which represent visited
		grid[i][j] = -1;

		// visit neighbour islands
        return getPerimeter(grid, i+1, j)
				+ getPerimeter(grid, i-1, j)
				+ getPerimeter(grid, i, j+1)
				+ getPerimeter(grid, i, j-1);
    }
}
