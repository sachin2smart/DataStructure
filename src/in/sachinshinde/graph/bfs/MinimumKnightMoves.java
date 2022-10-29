package in.sachinshinde.graph.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//	https://leetcode.ca/all/1197.html
//	https://leetcode.ca/2019-03-11-1197-Minimum-Knight-Moves/	

/*
 	In an infinite chess board with coordinates from -infinity to +infinity, 
 	you have a knight at square [0, 0].

        A knight has 8 possible moves it can make. 
        Each move is two squares in a cardinal direction, 
        	then one square in an orthogonal direction.
        
        Return the minimum number of steps needed 
        	to move the knight to the square [x, y].  
        It is guaranteed the answer exists.
        --------------------------------------------------
        Example 1:
        ----------
        Input: x = 2, y = 1
        Output: 1
        Explanation: [0, 0] → [2, 1]
        Example 2:
        --------------------------------------------------
        Input: x = 5, y = 5
        Output: 4
        Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
        --------------------------------------------------

 */
public class MinimumKnightMoves {

    public int minKnightMoves(int x, int y) {
	int[][] DIR = { {2, 1},   {1, 2}, 
			{-1, 2},  {-2, 1}, 
			{-2, -1}, {-1, -2}, 
			{1, -2},  {2, -1}};
	x = Math.abs(x);
	y = Math.abs(y);
	Queue<int[]> q = new LinkedList<>();
	q.offer(new int[] {0, 0});
	int result = 0;
	Set<String> visited = new HashSet<>();
	visited.add("0,0");
	
	while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[] curr = q.poll();
                int currX = curr[0];
                int currY = curr[1];
                
                if(currX == x && currY == y)
                    return result;
                
                for(int[] d: DIR) {
                    int newX = currX + d[0];
                    int newY = currY + d[1];

                    if(!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                	q.add(new int[] {newX, newY});
                	visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
	}
	return -1;
    }
    
    public static void main(String[] args) {
	MinimumKnightMoves moves = new MinimumKnightMoves();
	System.out.println(moves.minKnightMoves(2, 1));		//	1
	System.out.println(moves.minKnightMoves(5, 5));		//	4
    }
}
