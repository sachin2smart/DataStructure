package in.sachinshinde.array.matrix;

//	https://leetcode.com/problems/unique-paths-iii/

/*
 * 	You are given an m x n integer array grid where grid[i][j] could be:

	1 	representing the starting square. There is exactly one starting square.
	2 	representing the ending square. There is exactly one ending square.
	0 	representing empty squares we can walk over.
   -1 	representing obstacles that we cannot walk over.

	Return the number of 4-directional walks from the starting square to the ending square, 
	that walk over every non-obstacle square exactly once.
 */



public class UniquePaths3 {

	private static int result = 0; 
	private static int totalEmptyCells = 1;
	
	public int uniquePaths3(int[][] grid) {
		int startX = 0, startY = 0;
		
		for(int i=0;i<grid.length; i++) {
			for(int j=0;j<grid[0].length;j++) {
				if(grid[i][j] == 1) {
					startX = i;
					startY = j;
				}
				else if(grid[i][j] == 0)
					totalEmptyCells++;
			}
		}
		
		dfs(grid, startX, startY, 0);
		
		return result;
	}

	private void dfs(int[][] grid, int x, int y, int currTraversedCells) {
		if(x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y]==-1)
			return;
		
		if(grid[x][y]==2) {
			if(currTraversedCells == totalEmptyCells)
				result++;
			return;
		}
		
		grid[x][y] = -1;
		
		dfs(grid, x+1, y, currTraversedCells+1);
		dfs(grid, x-1, y, currTraversedCells+1);
		dfs(grid, x, y+1, currTraversedCells+1);
		dfs(grid, x, y-1, currTraversedCells+1);
		
		grid[x][y] = 0;
	}
	
	public static void main(String[] args) {
		UniquePaths3 uniquePaths3 = new UniquePaths3();
		int[][] grid;
		
		grid = new int[][] {
			{1,0,0,0},
			{0,0,0,0},
			{0,0,2,-1}
		};
		System.out.println(uniquePaths3.uniquePaths3(grid));	//	2
		
		result = 0; 
		totalEmptyCells = 1;
		grid = new int[][] {
			{1,0,0,0},
			{0,0,0,0},
			{0,0,0,2}
		};
		System.out.println(uniquePaths3.uniquePaths3(grid));	//	4
		
		result = 0; 
		totalEmptyCells = 1;
		grid = new int[][] {
			{0,1},
			{2,0}
		};
		System.out.println(uniquePaths3.uniquePaths3(grid));	//	0
		
	}
}

/*
 	Steps:
 		1. Determine the starting point (i.e x,y coordinates)
 		2. Determine the number of empty cell
 		3. Apply DFS
 			i. 		Check boundary condition
 			ii.		if we reach to destination, check if we have covered all empty cell
 						if yes - increment the result 
 			iii.	For current traversal mark the current cell as traversed (-1)
 			iv.		DFS to up,down,left,right increment the current traversal count by 1 
 			v.		Mark the current cell as empty (i.e. 0), to be eligible to traverse for next turn  
 
 */

/*
 	Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
	Output: 2
	Explanation: We have the following two paths: 
	1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
	2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 
 	Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
	Output: 4
	Explanation: We have the following four paths: 
	1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
	2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
	3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
	4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 
 	Input: grid = [[0,1],[2,0]]
	Output: 0
	Explanation: There is no path that walks over every empty square exactly once.
	Note that the starting and ending square can be anywhere in the grid.
 */
