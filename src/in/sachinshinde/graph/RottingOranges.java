package in.sachinshinde.graph;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.com/problems/rotting-oranges/

/*
	You are given an m x n grid where each cell can have one of three values:

		0 representing an empty cell,
		1 representing a fresh orange, or
		2 representing a rotten orange.
		
	Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
	
	Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
	If this is impossible, return -1.

 */

public class RottingOranges {
	//	Method 1: 
	/*
	 	First, count fresh oranges. 
	 	Then, until fresh is non-zero, perform BFS to rot oranges, decreasing fresh. 
	 	Count minutes and return it in the end. 
	 	If, after another minute, fresh does not change, return -1.

		For BFS, we can use the minute counter (minutes + 2) to 
		only process oranges that rotted on previous minutes.
	 */
	
	//	Time Complexity	: O(h * w * (h + w))
	//	Space Complexity: O(1) 
    public int orangesRotting(int[][] grid) {
    	int fresh=0, minutes=0;
    	
    	for(int i=0; i<grid.length; i++)
    		for(int j=0; j<grid[i].length; j++)
    			if(grid[i][j]==1)
    				fresh++;
    	
    	for(int prev_fresh=fresh; fresh>0; minutes++) {
    		for(int i=0; i<grid.length; i++)
        		for(int j=0; j<grid[i].length; j++)
        			if(grid[i][j] == minutes+2) 
        				fresh -= rot(grid, i+1, j, minutes)
        						+rot(grid, i-1, j, minutes)
        						+rot(grid, i, j+1, minutes)
        						+rot(grid, i, j-1, minutes);
    		
    		if(prev_fresh == fresh)
    				return -1;
    		
    		prev_fresh = fresh;
    	}
    	return minutes;
    }

	private int rot(int[][] grid, int i, int j, int minutes) {
		if(i<0 || j<0 || i>=grid.length || j>=grid[i].length || grid[i][j] != 1)
			return 0;
		
		grid[i][j] = minutes + 3;
		return 1;
	}
    
	public static void main(String[] args) {
		RottingOranges rottingOranges = new RottingOranges();
		int[][] grid = new int[][] {
							{2,1,1},
							{1,1,0},
							{0,1,1}
						};
		System.out.println(rottingOranges.orangesRotting(grid));	//	4
		int[][] grid2 = new int[][] {
			{2,1,1},
			{1,1,0},
			{0,1,1}
		};
		System.out.println(rottingOranges.orangesRotting2(grid2));	//	4
	}
	
	//	Method : 2
	public int orangesRotting2(int[][] grid) {	
		if(grid == null || grid.length == 0)
			return 0;
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		Queue<int[]> queue = new LinkedList<>();
		int freshCount = 0;
		
		for(int i=0; i<rows; i++)
			for(int j=0; j<cols; j++)
				if(grid[i][j] == 2)
					queue.offer(new int[] {i,j});	//	Put the position of all rotten oranges in queue
				else if(grid[i][j] == 1)
					freshCount++;					//	count the number of fresh oranges
		
		if(freshCount == 0)
			return 0;
		
		int minutes = 0;
		int[][] dirs = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
		
		while(!queue.isEmpty()) {
			minutes++;
			int qSize = queue.size();
			// For each rotten oranges
			for(int i=0; i<qSize; i++) {
				int[] cell = queue.poll();
				//	For each direction
				for(int[] dir: dirs) {
					//	Get the up, down, left, right position
					int x = cell[0] + dir[0];
					int y = cell[1] + dir[1];
					
					//	do nothing, when - 
					//		- 	x or y is out of bounds
					//		-	orange at (x,y) is already being rotten
					//		-	cell at (x,y) is empty
					
					if(x<0 || y<0 || x>=rows || y>=cols || grid[x][y]==0 || grid[x][y] == 2)
						continue;
					
					//	If above check fails, it means current orange is fresh; so rot it
					grid[x][y] = 2;
					
					//	Now, put this new rotten orange at cell position (x,y) into the queue
					queue.offer(new int[] {x,y});
					
					//	decrease the freshCount 
					freshCount--;
				}
			}
		}
		
		return freshCount==0 ? minutes-1 : -1;
	}
}