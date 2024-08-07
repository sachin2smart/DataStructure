package in.sachinshinde.misc.grids;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/spiral-matrix/

/*
 * 	Given an m x n matrix, return all elements of the matrix in spiral order.
 * 	Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]	
 * 	Output: [1,2,3,6,9,8,7,4,5]
 */

public class SpiralMatrix {

	/*
	 * 	Rules:
	 * 		1.	Traverse right and increment rowBegin
	 * 		2.	Traverse down and decrement colEnd
	 * 		3.	Traverse left and decrement rowEnd
	 * 		4.	Traverse up and increment colBegin
	 * 
	 * 	Check:
	 * 		While traversing left or up check : whether the row or col still exists (to prevent duplicates)
	 * 
	 */
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
        
        if (matrix.length == 0) {
            return res;
        }
        
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) {
                // Traverse Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        
        return res;
    }

}
