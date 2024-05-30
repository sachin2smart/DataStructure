package in.sachinshinde.graph.dfs.grids;

import java.util.Arrays;

//	https://leetcode.ca/all/286.html

/*
 * 	You are given a m x n 2D grid initialized with these three possible values.

	-1 	- A wall or an obstacle.
	0 	- A gate.
	INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
	
	Fill each empty room with the distance to its nearest gate. 
	If it is impossible to reach a gate, it should be filled with INF.
 */

public class WallsAndGates {
	
	static int INF = 2147483647;

	public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) 
        	return;
        
        for(int i=0; i< rooms.length; i++)
            for(int j=0; j< rooms[0].length; j++)
                if(rooms[i][j] == 0)
                    dfs(rooms, i, j, 0);    
	}

    private void dfs(int[][] rooms, int i, int j, int dis){
        if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] == -1)
        	return;
        
        if(dis > rooms[i][j]) 
        	return;
        
        rooms[i][j] = Math.min(rooms[i][j], dis);
        
        dfs(rooms, i+1, j, dis+1);
        dfs(rooms, i-1, j, dis+1);
        dfs(rooms, i, j+1, dis+1);
        dfs(rooms, i, j-1, dis+1);
   
    }
	
    public static void main(String[] args) {
		int[][] rooms = new int[][] {
			{INF,  -1,  0,  INF},
			{INF, INF, INF,  -1},
			{INF,  -1, INF,  -1},
			{0,    -1, INF, INF}
		};
		
		System.out.println(Arrays.deepToString(rooms));
		
		WallsAndGates wallsAndGates = new WallsAndGates();
		wallsAndGates.wallsAndGates(rooms);
		
		System.out.println(Arrays.deepToString(rooms));
		
		rooms = new int[][] {
			{INF,  -1,  0,  INF},
			{-1, INF, INF,  -1},
			{INF,  -1, INF,  -1},
			{0,    -1, -1, INF}
		};
		
		wallsAndGates.wallsAndGates(rooms);
		
		System.out.println(Arrays.deepToString(rooms));
		
	}
}

/*
	Example: 

	Given the 2D grid:
	
	INF  -1  0  INF
	INF INF INF  -1
	INF  -1 INF  -1
	  0  -1 INF INF
	  
	After running your function, the 2D grid should be:
	
	  3  -1   0   1
	  2   2   1  -1
	  1  -1   2  -1
	  0  -1   3   4
*/