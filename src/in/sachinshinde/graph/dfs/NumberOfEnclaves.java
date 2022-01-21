package in.sachinshinde.graph.dfs;

//	https://leetcode.com/problems/number-of-enclaves/

//	Number of Enclaves
/*
 *	You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 *	A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 *	Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 */


//	Approach : DFS

public class NumberOfEnclaves {
	
    public int numEnclaves(int[][] grid) {
        int count=0;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[i].length; j++) {
                if(i==0 || j==0 || i==grid.length-1 || j==grid[i].length-1)
                    dfs(grid, i, j);
            }
        }
        
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[i].length; j++) {
                if(grid[i][j]==1)
                    count++;
            }
        }
        
        return count;
    }
    
    private void dfs(int grid[][], int i, int j) {
        if(i >= 0 && i <= grid.length-1 && j >= 0 && j <= grid[i].length-1 && grid[i][j]==1) {
            grid[i][j]=0;
            dfs(grid, i + 1, j);
            dfs(grid, i-1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j-1);
        }
    }
    
}
