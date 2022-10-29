package in.sachinshinde.graph.dfs;

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

public class PathWithMaximumGold {
    public int getMaximumGold(int[][] grid) {
	int maxGold = 0;
	int m = grid.length;
	int n = grid[0].length;
	
	for(int r = 0; r < m; r++)
	    for(int c = 0; c < n; c++) 
		maxGold = Math.max(maxGold, dfs(grid, m, n, r, c));
	
	return maxGold;
    }
    
    private int dfs(int[][] grid, int m, int n, int r, int c) {
	if(r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0)
	    return 0;
	
	// pairs for changing [r,c]: [0,1], [1,0], [0,-1], [-1,0] i.e. "current, next"
	int[] DIR = new int[] {0, 1, 0, -1, 0};	
	int original = grid[r][c];
	
	grid[r][c] = 0; 	// marking it as visited
	int maxGold = 0;
	for(int i = 0; i < 4; i++) 
	    maxGold = Math.max(maxGold, dfs(grid, m, n, r + DIR[i], c + DIR[i+1]));
	
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
	System.out.println(maximumGold.getMaximumGold3(grid)); 	//	24
	
	grid = new int[][] {
	    {1,0,7},
	    {2,0,6},
	    {3,4,5},
	    {0,3,0},
	    {9,0,20}
	};
	System.out.println(maximumGold.getMaximumGold(grid)); 	//	28
	System.out.println(maximumGold.getMaximumGold2(grid)); 	//	28
	System.out.println(maximumGold.getMaximumGold3(grid)); 	//	28
    }
    

    // Approach : 2
    public int getMaximumGold2(int[][] grid) {
	int maxGold = 0;
	for(int i = 0; i < grid.length; i++)
	    for(int j = 0; j < grid[0].length; j++)
		maxGold = Math.max(maxGold, dfs2(grid, i, j));
	return maxGold;
    }
    
    private int dfs2(int[][] grid, int i, int j) {
	if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
	    return 0;
	
	int original = grid[i][j];
	grid[i][j] = 0;
	
	int pickfromUp = dfs2(grid, i-1, j);
	int pickfromDown = dfs2(grid, i+1, j);
	int pickfromLeft = dfs2(grid, i, j-1);
	int pickfromRight = dfs2(grid, i, j+1);
	
	grid[i][j] = original; 
	
	return original + Math.max(Math.max(pickfromUp, pickfromDown), 
		Math.max(pickfromLeft, pickfromRight));
    }
    
    // Approach 3
    public int getMaximumGold3(int[][] grid) {
        int maxCost = 0;
        for(int i = 0; i < grid.length; i++)
          for(int j = 0; j < grid[0].length; j++)
              if(grid[i][j] != 0)
                maxCost = Math.max(maxCost, dfs3(grid, i, j, 0, false));
              
      return maxCost;
    }
    
    public int dfs3(int[][] grid, int row,int col,int used, boolean full) {
      if(used == 25) {
        full = true;
        return 0;
      }
      
      if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0)
        return 0;
      
      int orgGold = grid[row][col];
      grid[row][col] = 0;
      
      int pickfromLeft = dfs3(grid, row, col - 1, used + 1, full);
      if(full) return pickfromLeft;
      
      int pickfromRight = dfs3(grid, row, col + 1, used + 1, full);
      if(full) return pickfromRight;
      
      int pickfromUp = dfs3(grid, row - 1, col, used + 1, full);
      if(full) return pickfromUp;
      
      int pickFromDown = dfs3(grid, row + 1, col, used + 1, full);
      if(full) return pickFromDown;
      
      grid[row][col] = orgGold;
      
      return orgGold + Math.max(Math.max(pickfromLeft, pickfromRight),
	      Math.max(pickfromUp, pickFromDown));
      
    }
}