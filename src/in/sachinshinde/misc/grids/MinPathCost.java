package in.sachinshinde.misc.grids;

//	https://leetcode.com/problems/minimum-path-cost-in-a-grid/

/*
 	You are given a 0-indexed m x n integer matrix grid 
 		consisting of distinct integers from 0 to m * n - 1. 
 	You can move in this matrix from a cell to any other cell in the next row. 
 	That is, if you are in cell (x, y) such that x < m - 1, 
 		you can move to any of the cells (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1). 
 	Note that it is not possible to move from cells in the last row.

        Each possible move has a cost given by a 0-indexed 2D array moveCost of size (m * n) x n, 
        	where moveCost[i][j] :
        		is the cost of moving from a cell with value i to a cell in column j of the next row. 
        The cost of moving from cells in the last row of grid can be ignored.
        
        The cost of a path in grid is the sum of all values of cells visited plus 
        	the sum of costs of all the moves made. 
        Return the minimum cost of a path that starts from any cell in the first row and 
        	ends at any cell in the last row.
        
         
        
        Example 1:
        ---------
        Input: grid = [
        
        	[5,3],
        	[4,0],
        	[2,1]], 
        	
              moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
              
        Output: 17
        
        Explanation: The path with the minimum possible cost is the path 5 -> 0 -> 1.
        - The sum of the values of cells visited is 5 + 0 + 1 = 6.
        - The cost of moving from 5 to 0 is 3.
        - The cost of moving from 0 to 1 is 8.
        So the total cost of the path is 6 + 3 + 8 = 17.
        
        Example 2:
        ---------
        Input: grid = [
        	[5,1,2],
        	[4,0,3]], 
        	
               moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
        
        Output: 6
        
        Explanation: The path with the minimum possible cost is the path 2 -> 3.
        - The sum of the values of cells visited is 2 + 3 = 5.
        - The cost of moving from 2 to 3 is 1.
        So the total cost of this path is 5 + 1 = 6.
         
        
        Constraints:
        -----------
            m == grid.length
            n == grid[i].length
            2 <= m, n <= 50
            grid consists of distinct integers from 0 to m * n - 1.
            moveCost.length == m * n
            moveCost[i].length == n
            1 <= moveCost[i][j] <= 100
 */

public class MinPathCost {
    //	Method 1 ---------------------------------------------------------
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int min = Integer.MAX_VALUE;
        for (int i = grid.length - 2; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] += findMin(grid, moveCost, i, j);
            }
        }
        for (int i = 0 ; i < grid[0].length; i++) {
            if (grid[0][i] < min)
                min = grid[0][i];
        }
        return min;
    }
    
    private int findMin(int[][] grid, int[][] moveCost, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < grid[0].length; k++) {
            int currVal = grid[i+1][k] + moveCost[grid[i][j]][k];
            if (currVal < min) {
                min = currVal;
            }
        }
        return min;
    }
    
    //	Method 2 ---------------------------------------------------------
    public int minPathCost_2(int[][] grid, int[][] moveCost) {
	int m = grid.length;
	int n = grid[0].length;

	int[][] currCost = new int[m][n];
	
	for (int c = 0; c < n; c++) {
	    currCost[0][c] = grid[0][c];
	}

	for (int r = 1; r < m; r++) {
	    for (int c = 0; c < n; c++) {
		int minCostFromPeviousRow = Integer.MAX_VALUE;
		for (int j = 0; j < n; j++) {
		    minCostFromPeviousRow = Math.min(minCostFromPeviousRow, 
			    currCost[r - 1][j] + moveCost[grid[r - 1][j]][c]);
		}
		currCost[r][c] = minCostFromPeviousRow + grid[r][c];
	    }
	}

	int leastCostInLastRow = Integer.MAX_VALUE;

	for(int c: currCost[m-1]) {
	    leastCostInLastRow = Math.min(leastCostInLastRow, c);
	}
	
	return leastCostInLastRow;
    }
    
    // ------------------------------------------------------------------------
    
    public static void main(String[] args) {
	MinPathCost cost = new MinPathCost();
	
	System.out.println(cost.minPathCost(
		new int[][] {
        	    {5,3},
        	    {4,0},
        	    {2,1}
        	}, new int[][] {
        	    {9,8},{1,5},{10,12},{18,6},{2,4},{14,3}
        	}));	// 17
	
	System.out.println(cost.minPathCost(
		new int[][] {
        	    {5,1,2},
        	    {4,0,3}
        	}, 
		new int[][] {
        	    {12,10,15},{20,23,8},{21,7,1},{8,1,13},{9,10,25},{5,3,2}
        	}));	// 6
	
	System.out.println(cost.minPathCost_2(
		new int[][] {
        	    {5,3},
        	    {4,0},
        	    {2,1}
        	}, new int[][] {
        	    {9,8},{1,5},{10,12},{18,6},{2,4},{14,3}
        	}));	// 17
	
	System.out.println(cost.minPathCost_2(
		new int[][] {
        	    {5,1,2},
        	    {4,0,3}
        	}, 
		new int[][] {
        	    {12,10,15},{20,23,8},{21,7,1},{8,1,13},{9,10,25},{5,3,2}
        	}));	// 6
    }
}