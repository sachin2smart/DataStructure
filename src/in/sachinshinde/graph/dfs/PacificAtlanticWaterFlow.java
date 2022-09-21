package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/pacific-atlantic-water-flow/

/*
 	There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
 	The Pacific Ocean touches the island's left and top edges, and 
 		the Atlantic Ocean touches the island's right and bottom edges.

	The island is partitioned into a grid of square cells. 
	You are given an m x n integer matrix heights where 
		heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

	The island receives a lot of rain, and 
		the rain water can flow to neighboring cells directly north, south, east, and west 
		if the neighboring cell's height is less than or equal to the current cell's height. 
		Water can flow from any cell adjacent to an ocean into the ocean.

	Return a 2D list of grid coordinates result where 
		result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) 
			to both the Pacific and Atlantic oceans.

	Example 1:
	---------
	Input: 
		heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
	
	Output: 
		[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
	
	Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
		[0,4]: [0,4] -> Pacific Ocean 
		       [0,4] -> Atlantic Ocean
		[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
		       [1,3] -> [1,4] -> Atlantic Ocean
		[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
		       [1,4] -> Atlantic Ocean
		[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
		       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
		[3,0]: [3,0] -> Pacific Ocean 
		       [3,0] -> [4,0] -> Atlantic Ocean
		[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
		       [3,1] -> [4,1] -> Atlantic Ocean
		[4,0]: [4,0] -> Pacific Ocean 
		       [4,0] -> Atlantic Ocean
	
	Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
	
	Example 2:
	---------
	Input: 
		heights = [[1]]
	
	Output: 
		[[0,0]]
	
	Explanation: 
		The water can flow from the only cell to the Pacific and Atlantic oceans.
 */

public class PacificAtlanticWaterFlow {
	// Method 1: DFS
	public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(heights == null || heights.length == 0 || heights[0].length == 0)
        	return result;
        
        int m = heights.length;
        int n = heights[0].length;
        
        boolean[][] pacific  = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        //	Horizontal border (x remains same, y will vary 0 & n-1)
        for(int i = 0; i < m; i++) {
        	dfs(heights, pacific , Integer.MIN_VALUE, i , 0    );
        	dfs(heights, atlantic, Integer.MIN_VALUE, i , n - 1);
        }
        
        //	Vertical border	(y remains same, x will vary 0 & m-1)
        for(int i = 0; i < n; i++) {
        	dfs(heights, pacific , Integer.MIN_VALUE, 0    , i);
        	dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, i);
        }
        
        for(int i = 0; i < m; i++)
        	for(int j = 0; j < n; j++)
        		if(pacific[i][j] && atlantic[i][j])
        			result.add(Arrays.asList(i, j));
        
        return result;
    }
	
	private void dfs(int[][] heights, boolean[][] visited, int height, int x, int y) {
		if(x < 0 || x >= heights.length || y < 0 || y >= heights[0].length ||
				visited[x][y] || heights[x][y] < height)
			return;
		
		visited[x][y] = true;
		
		dfs(heights, visited, heights[x][y], x + 1, y    );
		dfs(heights, visited, heights[x][y], x - 1, y    );
		dfs(heights, visited, heights[x][y], x    , y + 1);
		dfs(heights, visited, heights[x][y], x    , y - 1);
	}
	
	public static void main(String[] args) {
		PacificAtlanticWaterFlow flow = new PacificAtlanticWaterFlow();
		System.out.println(flow.pacificAtlantic(new int[][] {
			{1,2,2,3,5},
			{3,2,3,4,4},
			{2,4,5,3,1},
			{6,7,1,4,5},
			{5,1,1,2,4}
		}));	//	Answer: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
		
		System.out.println(flow.pacificAtlantic(new int[][] {
			{1}
		}));	//	Answer: [[0,0]]
	}
}