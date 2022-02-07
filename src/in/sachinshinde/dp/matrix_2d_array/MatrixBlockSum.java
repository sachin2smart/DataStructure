package in.sachinshinde.dp.matrix_2d_array;

import java.util.Arrays;

//	https://leetcode.com/problems/matrix-block-sum/

/*
 *	 -----------------------
 *	|	Matrix Block Sum	|
 *	 -----------------------
 */

public class MatrixBlockSum {

	public int[][] matrixBlockSum(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][] sum = new int[row+1][col+1];
		
		for(int i=1; i<=row; i++)
			for(int j=1; j<=col; j++)
				sum[i][j] = matrix[i-1][j-1] +
							sum[i-1][j] +
							sum[i][j-1] -
							sum[i-1][j-1];
		
		int ans[][] = new int[row][col];
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				int row1 = Math.max(0,   i-k);
				int col1 = Math.max(0,   j-k);
				int row2 = Math.min(row, i+k+1);
				int col2 = Math.min(col, j+k+1);
				
				ans[i][j] = sum[row2][col2] - 
							sum[row2][col1] - 
							sum[row1][col2] + 
							sum[row1][col1];
			}
		}
		
		return ans;
    }

	public static void main(String[] args) {
		int[][] matrix = new int[][]
				{
			     {1,2,3},
			     {4,5,6},
			     {7,8,9}
				};
		
		MatrixBlockSum sum = new MatrixBlockSum();
		
		System.out.println(Arrays.deepToString(sum.matrixBlockSum(matrix, 1)));
//		{{12, 21, 16}, {27, 45, 33}, {24, 39, 28}}
		System.out.println(Arrays.deepToString(sum.matrixBlockSum(matrix, 2)));
//		{{45, 45, 45}, {45, 45, 45}, {45, 45, 45}}			
	}
}