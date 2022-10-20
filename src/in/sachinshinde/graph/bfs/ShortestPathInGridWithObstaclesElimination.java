package in.sachinshinde.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

/*
 	You are given an m x n integer matrix grid 
 		where each cell is either 0 (empty) or 1 (obstacle). 
 	
 	You can move up, down, left, or right from and to an empty cell in one step.

	Return the minimum number of steps to walk from the upper left corner (0, 0) 
		to the lower right corner (m - 1, n - 1) given that 
		you can eliminate at most k obstacles. 
	If it is not possible to find such walk return -1.
 */

/*
 	Example 1: 
 	Input: 
 		grid = [[0,0,0],
 			[1,1,0],
 			[0,0,0],
 			[0,1,1],
 			[0,0,0]], 
 		k = 1
        
        Output: 6
        
        Explanation: 
        	The shortest path without eliminating any obstacle is 10.
        	The shortest path with one obstacle elimination at position (3,2) is 6. 
        		Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> 
        			(2,2) -> (3,2) -> (4,2).
        	[The position (3,2) can be eliminated]
        ----------------------------------------------
        Example 2:
        Input: 
        	grid = [[0,1,1],
        		[1,1,1],
        		[1,0,0]], 
        	k = 1
	
	Output: -1
	
	Explanation: 
		We need to eliminate at least two obstacles to find such a walk.
 */

//	Video Link: https://youtu.be/ID9YJXy3OJk

public class ShortestPathInGridWithObstaclesElimination {
    
    public int shortestPath(int[][] grid, int k) {
	int m = grid.length, n = grid[0].length;
        int[][] DIR = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        boolean[][][] v = new boolean[m][n][k+1];
        Queue<int[]> q = new LinkedList<>();
        int steps = 0;
        q.offer(new int[]{0,0,k});
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[] curr = q.poll();
                //If curr is the destination; return steps
                if(curr[0] == m-1 && curr[1] == n-1) return steps;
                //Else go in all valid directions
                for(int[] d : DIR) {
                    int i = curr[0]+d[0];
                    int j = curr[1]+d[1];
                    int obs = curr[2];
                    
                   //Traverse through the valid cells
                    if(i >= 0 && i < m && j >= 0 && j < n) {
                        //If cell is empty visit the cell and add in queue
                        if(grid[i][j] == 0 && !v[i][j][obs]) {
                            q.offer(new int[]{i,j,obs});
                            v[i][j][obs] = true;
                        }
                        else if(grid[i][j] == 1 && obs > 0 && !v[i][j][obs-1]) {
                            q.offer(new int[]{i,j,obs-1});
                            v[i][j][obs-1] = true;
                        }
                    }
                }
            }
            ++steps;
        }
	return -1;
    }
    
    public static void main(String[] args) {
	ShortestPathInGridWithObstaclesElimination path = 
		new ShortestPathInGridWithObstaclesElimination();
	int[][] grid = new int[][] {
	    	{0,0,0},
		{1,1,0},
		{0,0,0},
		{0,1,1},
		{0,0,0}
	};
	
	System.out.println(path.shortestPath(grid, 1));		//	6
	
	grid = new int[][] {
	    	{0,1,1},
		{1,1,1},
		{1,0,0}
	};
	System.out.println(path.shortestPath(grid, 1));		//	-1
    }
}
