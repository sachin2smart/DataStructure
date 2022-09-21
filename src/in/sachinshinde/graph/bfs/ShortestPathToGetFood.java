package in.sachinshinde.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.ca/2021-03-13-1730-Shortest-Path-to-Get-Food/

/*
 	You are starving and you want to eat food as quickly as possible. 
 	You want to find the shortest path to arrive at any food cell.

	You are given an m x n character matrix, grid, of these different types of cells:
	
		'*' 
			is your location. There is exactly one '*' cell.
			
		'#' 
			is a food cell. There may be multiple food cells.
			
		'O' 
			is free space, and you can travel through these cells.
			
		'X' 
			is an obstacle, and you cannot travel through these cells.
			
	You can travel to any adjacent cell north, east, south, or west of your current location 
		if there is not an obstacle.

	Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

    ---------
	Example 1:
	---------
	Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”],
	               [“X”,”*”,”O”,”O”,”O”,”X”],
	               [“X”,”O”,”O”,”#”,”O”,”X”],
	               [“X”,”X”,”X”,”X”,”X”,”X”]]
	Output: 3
	Explanation: It takes 3 steps to reach the food.
	---------
	
	
	Example 2:
	---------
	Input: grid = [[“X”,”X”,”X”,”X”,”X”],
	               [“X”,”*”,”X”,”O”,”X”],
	               [“X”,”O”,”X”,”#”,”X”],
	               [“X”,”X”,”X”,”X”,”X”]]
	
	Output: -1
	Explanation: It is not possible to reach the food.
	---------
	
	
	Example 3:
	---------
	Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”],
				   [“X”,”*”,”O”,”X”,”O”,”#”,”O”,”X”],
				   [“X”,”O”,”O”,”X”,”O”,”O”,”X”,”X”],
				   [“X”,”O”,”O”,”O”,”O”,”#”,”O”,”X”],
				   [“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”]]
	Output: 6
	Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
	---------
	
	
	Example 4:
	---------
	Input: grid = [[“O”,”*”],
	               [”#”,”O”]]
	Output: 2
	---------
	
	
	Example 5:
	---------
	Input: grid = [[“X”,”*”],
	               [”#”,”X”]]
	Output: -1
 */


/*
 		Constraints:
		-----------
			m == grid.length
			n == grid[i].length
			1 <= m, n <= 200
			grid[row][col] is '*', 'X', 'O', or '#'.
			The grid contains exactly one '*'.
 */
	
public class ShortestPathToGetFood {
   public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];

        int x = -1;
        int y = -1;

        int total = m * n;

        //	Get the (x,y) position where * exists
        for(int i = 0; i < total; i++) {
            int r = i / n;
            int c = i % n;
            
            if (grid[r][c] == '*') {
                x = r;
                y = c;
                break;
            }
        }

        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<int[]>();	//	Queue of List of Integers

        queue.offer(new int[]{x, y});

        int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int numSteps = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int[] currPosition = queue.poll();
                int r = currPosition[0];	//	x
                int c = currPosition[1];	//	y
                
                //	if food found
                if(grid[r][c] == '#')
                    return numSteps;
                
                for(int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && 
                    		grid[nr][nc] != 'X' && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            numSteps++;
        }
        return -1;
   }
   
   public static void main(String[] args) {
	   ShortestPathToGetFood path = new ShortestPathToGetFood();
	   System.out.println(path.getFood(new char[][] {
		   {'X','X','X','X','X','X'},
		   {'X','*','O','O','O','X'},
		   {'X','O','O','#','O','X'},
		   {'X','X','X','X','X','X'}
	   }));	//	3
	   
	   System.out.println(path.getFood(new char[][] {
	   	   {'X','X','X','X','X'},
	   	   {'X','*','X','O','X'},
	   	   {'X','O','X','#','X'},
	   	   {'X','X','X','X','X'}
	   }));	//	-1
	   
	   System.out.println(path.getFood(new char[][] {
		   {'X','X','X','X','X','X','X','X'},
		   {'X','*','O','X','O','#','O','X'},
		   {'X','O','O','X','O','O','X','X'},
		   {'X','O','O','O','O','#','O','X'},
		   {'X','X','X','X','X','X','X','X'}
	   }));	//	6
	   
	   System.out.println(path.getFood(new char[][] {
		   {'O','*'},
		   {'#','O'}
	   }));	//2
		
	   System.out.println(path.getFood(new char[][] {
		   {'X','*'},
		   {'#','X'}   
	   }));	//	-1
   }
}
