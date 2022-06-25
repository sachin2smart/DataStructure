package in.sachinshinde.graph.dfs;

//	https://leetcode.com/problems/count-sub-islands/

/*
 * 	You are given two m x n binary matrices grid1 and grid2 
 * 		containing only 0's (representing water) and 1's (representing land). 
 * 	An island is a group of 1's connected 4-directionally (horizontal or vertical). 
 * 	Any cells outside of the grid are considered water cells.
 * 	
 * 	An island in grid2 is considered a sub-island 
 * 		if there is an island in grid1 that contains all the cells that make up this island in grid2.
 * 
 * 	Return the number of islands in grid2 that are considered sub-islands.
 */

public class CountSubIslands {

	public int countSubIslands(int[][] grid1, int[][] grid2) {
        int res = 0;
        for (int i = 0; i < grid1.length; i++)
            for (int j = 0; j < grid1[0].length; j++)
                if (grid2[i][j] == 1)
                    res += dfs(grid1, grid2, i, j);
        return res;
    }


    private int dfs(int[][] grid1, int[][] grid2, int i, int j) {

        int res = 1;

        if (i < 0 || i >= grid1.length || j < 0 || j >= grid1[0].length || grid2[i][j] == 0)	// last check for grid2
                return 1;	// Return 1 not 0 -- IMP --

        grid2[i][j] = 0;	// updates only for grid2

        res &= dfs(grid1, grid2, i - 1, j);	// AND Operation (1&0=0 , 0&0=0, 1&1=1, 0&1=0)
        res &= dfs(grid1, grid2, i + 1, j);	// AND Operation
        res &= dfs(grid1, grid2, i, j - 1);	// AND Operation
        res &= dfs(grid1, grid2, i, j + 1);	// AND Operation

        return res & grid1[i][j];	//	result ANDed with grid1
    }
    
    public static void main(String[] args) {
    	CountSubIslands subIslands = new CountSubIslands();
		int[][] grid1 = new int[][] {
						 {1,1,1,0,0},
		    			 {0,1,1,1,1},
		    			 {0,0,0,0,0},
		    			 {1,0,0,0,0},
		    			 {1,1,0,1,1}
    					};
		    			  
    	int[][] grid2 = new int[][] {
		    			 {1,1,1,0,0},
		    			 {0,0,1,1,1},
		    			 {0,1,0,0,0},
		    			 {1,0,1,1,0},
		    			 {0,1,0,1,0}
		    			};
		    			
		System.out.println(subIslands.countSubIslands(grid1, grid2));	//	3
	}
    
}

/*
	grid1 = [[1,1,1,0,0],
			 [0,1,1,1,1],
			 [0,0,0,0,0],
			 [1,0,0,0,0],
			 [1,1,0,1,1]],
			  
	grid2 = [[1,1,1,0,0],
			 [0,0,1,1,1],
			 [0,1,0,0,0],
			 [1,0,1,1,0],
			 [0,1,0,1,0]]

	Output: 3
		First sub-islands can be formed as (0,0),(0,1),(0,2),(1,2),(1,3),(1,4)
		Second sub-islands can be formed as (3,0)
		Third sub-islands can be formed as (4,1)
*/