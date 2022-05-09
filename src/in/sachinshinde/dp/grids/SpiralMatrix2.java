package in.sachinshinde.dp.grids;

//	https://leetcode.com/problems/spiral-matrix-ii/

/*
 *	Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order. 
 * 
 */

public class SpiralMatrix2 {

	public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        if (n == 0)
            return matrix;
        
        int rowBegin = 0;
        int rowEnd = n-1;
        int colBegin = 0;
        int colEnd = n-1;
        
        int num = 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                matrix[rowBegin][i] = num++;
            }
            rowBegin++;
            
            for (int i = rowBegin; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++;
            }
            colEnd--;
            
            for (int i = colEnd; i >= colBegin; i--) {
                if (rowBegin <= rowEnd)
                    matrix[rowEnd][i] = num++;
            }
            rowEnd--;
            
            for (int i = rowEnd; i >= rowBegin; i--) {
                if (colBegin <= colEnd)
                    matrix[i][colBegin] = num++;
            }
            colBegin++;
        }
        
        return matrix;
    }
	
}
