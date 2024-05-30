package in.sachinshinde.graph.dfs.visited_array;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.com/problems/shortest-bridge/

/*
 	You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

        An island is a 4-directionally connected group of 1's not connected to any other 1's. 
        There are exactly two islands in grid.
        
        You may change 0's to 1's to connect the two islands to form one island.
        Return the smallest number of 0's you must flip to connect the two islands.
        
        Example 1:
        Input: grid = [[0,1],[1,0]]
        Output: 1
        
        Example 2:
        Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
        Output: 2
        
        Example 3:
        Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
        Output: 1
 */

public class ShortestBridge {

    public int shortesBridge(int[][] grid) {
	int m = grid.length;
	int n = grid[0].length;
	
	boolean[][] visited = new boolean[m][n];
	int[][] dirs = new int[][] {
                	    {1,0},
                	    {-1,0},
                	    {0,1},
                	    {0,-1}
	    		};
	    
	Queue<int[]> q = new LinkedList<>();
	
	boolean found = false;
	int step = 0;
	
	// apply dfs() to find the island and mark it as visited
	for(int i=0; i<m ; i++) {
	    if(found) {
		break;
	    }
	    for(int j=0; j<n; j++) {
		if(grid[i][j] == 1) {
		    dfs(grid, visited, q, i, j, dirs);
		    found = true;
		    break;
		}
	    }
	}
	
	// expand this island using bfs
	while(!q.isEmpty()) {
	    int size = q.size();
	    while(size-- > 0) {
		int[] curr = q.poll();
		for(int[] dir: dirs) {
		   int i = curr[0] + dir[0];
		   int j = curr[1] + dir[1];
		   if(i>=0 && j>=0 && i<m && j<n && !visited[i][j]) {
		       if(grid[i][j] == 1) {
			   return step;
		       }
		       q.offer(new int[] {i,j});
		       visited[i][j] = true;
		   }
		}
	    }
	    step++;
	}
	
	return -1;
    }
    
    private void dfs(int[][] grid, boolean[][] visited, Queue<int[]> q, int i, int j, int[][] dirs) {
	// 1. boundary conditions i within [0...grid.length]  
	//			  j within [0...grid[0].length]
	// 2. not visited
	// 3. value should not be zero
	if(i<0 || j<0 || grid.length <= i || grid[0].length <= j || visited[i][j] || grid[i][j] == 0) {
	    return;
	}
	
	visited[i][j] = true;
	q.offer(new int[] {i,j});
	for(int[] dir: dirs) {
	    dfs(grid, visited, q, i+dir[0], j+dir[1], dirs);
	}
    }
    
    public static void main(String[] args) {
	ShortestBridge bridge = new ShortestBridge();
	System.out.println(bridge.shortesBridge(new int[][] {{0,1},{1,0}}));	//	1
	System.out.println(bridge.shortesBridge(new int[][] {{0,1,0},{0,0,0},{0,0,1}}));	//	2
	System.out.println(bridge.shortesBridge(new int[][] {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}}));	// 1
    }
}
