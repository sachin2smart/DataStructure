package in.sachinshinde.dp.grids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/spiral-matrix-iii/

/*
 	You start at the cell (rStart, cStart) of an rows x cols grid facing east. 
 	
 	The northwest corner is at the first row and column in the grid, and 
 		the southeast corner is at the last row and column.

	
     		North
     		  |
         	 ____
         	|    |
     	West <- |    | -> East
         	|____|
         	  |
         	South
         	
    
    
        You will walk in a "clockwise spiral" shape to visit every position in this grid.
           
        Whenever you move outside the grid's boundary, 
        	we continue our walk outside the grid (but may return to the grid boundary later.). 
        Eventually, we reach all rows * cols spaces of the grid.
        
        Return an array of coordinates representing the positions of the grid in the order you visited them.
        
        Example 1:
        ---------
            Input: rows = 1, cols = 4, rStart = 0, cStart = 0
            Output:    [[0,0],
            		[0,1],
            		[0,2],
            		[0,3]]
            
       	Example 2:
        ---------
        	Input: rows = 5, cols = 6, rStart = 1, cStart = 4
        	Output:[[1,4],
        		[1,5],
        		[2,5],
        		[2,4],
        		[2,3],
        		[1,3],
        		[0,3],
        		[0,4],
        		[0,5],
        		[3,5],
        		[3,4],
        		[3,3],
        		[3,2],
        		[2,2],
        		[1,2],
        		[0,2],
        		[4,5],
        		[4,4],
        		[4,3],
        		[4,2],
        		[4,1],
        		[3,1],
        		[2,1],
        		[1,1],
        		[0,1],
        		[4,0],
        		[3,0],
        		[2,0],
        		[1,0],
        		[0,0]]
         
        Constraints:
        -----------
        1 <= rows, cols <= 100
        0 <= rStart < rows
        0 <= cStart < cols
 */

public class SpiralMatrix3 {
    /*
     	Take steps one by one.
        If the location is inside of grid, add it to mat.
        
        But how to simulate the path?
        
        	if we observe the path:
        
                move right 1 step, turn right
                move down 1 step, turn right
                move left 2 steps, turn right
                move top 2 steps, turn right,
                move right 3 steps, turn right
                move down 3 steps, turn right
                move left 4 steps, turn right
                move top 4 steps, turn right,
        
        	we can find the sequence of steps: 1,1,2,2,3,3,4,4,5,5....
        
        So there are two thing to figure out:
        --------------------
        how to generate sequence 1,1,2,2,3,3,4,4,5,5
        how to turn right?
        --------------------
        Generate sequence 1,1,2,2,3,3,4,4,5,5
        	Let n be index of this sequence.
        	Then A0 = 1, A1 = 1, A2 = 2 ......
        	We can find that An = n / 2 + 1
        
        
        How to turn right?
        	By cross product:
        	Assume current direction is (x, y) in plane, which is (x, y, 0) in space.
        	Then the direction after turn right (x, y, 0) × (0, 0, 1) = (y, -x, 0)
        	Translate to code: tmp = x; x = y; y = -tmp;
        
        	By arrays of arrays:
        	The directions order is (0,1),(1,0),(0,-1),(-1,0), then repeat.
        	Just define a variable.
        
        
        Time Complexity:
        	Time O(max(R,C)^2)
        	Space O(R*C) for output
     */

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
	int[][] mat = new int[rows * cols][2];
	
	int dx = 0, dy = 1, n =0, tmp;
	
	for (int j = 0; j < rows * cols; n++) {
	    for (int i = 0; i < n/2 + 1; i++) {
		if (rStart >= 0 && rStart < rows &&
			cStart >= 0 && cStart < cols) {
		    mat[j++] = new int[] {rStart, cStart};
		}
		rStart += dx;
		cStart += dy;
	    }
	    tmp = dx;
	    dx = dy;
	    dy = -tmp;
	}
	
	return mat;
    }
  
    public int[][] spiralMatrixIII_2(int rows, int cols, int rStart, int cStart) {
	List<int[]> res = new ArrayList<>();
	
	int[][] dirs = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
	int numSteps = 0, redirect = 0;
	/*
	 *	redirect 
	 *		0 	= 	right (towards East)
	 *		1	=	down (towards South)
	 *		2	=	left (towards West)
	 *		3	=	up (towards North)
	 */
	
	res.add(new int[]{rStart, cStart});
	
	while(res.size() < rows * cols) {
	    // when move east or west, the length of path need plus 1 
	    if(redirect == 0 || redirect == 2)
		numSteps++;
	    
	    for (int i = 0; i < numSteps; i++) {
		rStart += dirs[redirect][0];	//	move to next row
		cStart += dirs[redirect][1];	//	move to next col
		
		// condition before add into list
		if (rStart >= 0 && rStart < rows &&
			cStart >= 0 && cStart < cols) {
		    res.add(new int[] {rStart, cStart});
		}
	    }
	    
	    redirect = (redirect + 1) % 4; 
	}
	
	return res.toArray(new int[rows * cols][2]);
    }
    
    public int[][] spiralMatrixIII_3(int R, int C, int r0, int c0) {
        List<int[]> ans = new ArrayList<>();
        final int[] dx = {1, 0, -1, 0};
        final int[] dy = {0, 1, 0, -1};
    
        ans.add(new int[] {r0, c0});
    
        for (int i = 0; ans.size() < R * C; ++i)
          for (int step = 0; step < i / 2 + 1; ++step) {
            r0 += dy[i % 4];
            c0 += dx[i % 4];
            if (0 <= r0 && r0 < R && 0 <= c0 && c0 < C)
              ans.add(new int[] {r0, c0});
          }
    
        return ans.toArray(new int[ans.size()][]);
    }
    
    public int[][] spiralMatrixIII_4(int rows, int cols, int rStart, int cStart) {
        int[][] ans = new int[rows*cols][2];
        int dc = 0;// direction count
        int point = 0;// use to fill answer in 'ans' matrix
        
        // we have starting point so fill it in 'ans' matrix first
        ans[point][0] = rStart;
        ans[point][1] = cStart;
        point++;

        // travel matrix in given pattern
        while(point < rows*cols){
            // 1 - 1 - 2 - 2 - 3 - 3 - 4 - 4 - ...
            // E - S - W - N - E - S - W - N - ...
            // East --> column increase --> cStart++
            dc++;
            for(int i = 1; i <= dc; i++){
                cStart++;
                // if not valid place then simply ignore
                if(rStart < 0 || cStart < 0 || rStart > rows-1 || cStart > cols-1)
                    continue;
                // else put location in to 'ans' matrix
                ans[point][0] = rStart;
                ans[point][1] = cStart;
                point++;
            }
            
            // South --> row increase --> rStart++
            for(int i = 1; i <= dc; i++){
                rStart++;
                // if not valid place then simply ignore
                if(rStart < 0 || cStart < 0 || rStart > rows-1 || cStart > cols-1)
                    continue;
                // else put location in to 'ans' matrix
                ans[point][0] = rStart;
                ans[point][1] = cStart;
                point++;
            }

            // West --> column decrease --> cStart--
            dc++;
            for(int i = 1; i <= dc; i++){
                cStart--;
                // if not valid place then simply ignore
                if(rStart < 0 || cStart < 0 || rStart > rows-1 || cStart > cols-1)
                    continue;
                // else put location in to 'ans' matrix
                ans[point][0] = rStart;
                ans[point][1] = cStart;
                point++;
            }
            
            // North --> row decrease --> rStart--
            for(int i = 1; i <= dc; i++){
                rStart--;
                // if not valid place then simply ignore
                if(rStart < 0 || cStart < 0 || rStart > rows-1 || cStart > cols-1)
                    continue;
                // else put location in to 'ans' matrix
                ans[point][0] = rStart;
                ans[point][1] = cStart;
                point++;
            }

        }
        //return 'ans' matrix
        return ans;
    }
    
    public static void main(String[] args) {
	SpiralMatrix3 matrix3 = new SpiralMatrix3();
	System.out.println(Arrays.deepToString(matrix3.spiralMatrixIII(1, 4, 0, 0)));
	System.out.println(Arrays.deepToString(matrix3.spiralMatrixIII_2(1, 4, 0, 0)));
	System.out.println(Arrays.deepToString(matrix3.spiralMatrixIII_3(1, 4, 0, 0)));
	System.out.println(Arrays.deepToString(matrix3.spiralMatrixIII_4(1, 4, 0, 0)));
    }
}