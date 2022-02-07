package in.sachinshinde.dp.range_sum;

//	https://leetcode.com/problems/range-sum-query-2d-immutable/

/*
 * 	 -----------------------------------
 * 	|	Range Sum Query 2D - Immutable	|
 * 	 -----------------------------------
 */

public class RangeSumQuery2D {
	
	int[][] sum;
	int row, col;
	
	public RangeSumQuery2D(int[][] matrix) {
		// Base case
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		
		row = matrix.length;
		col = matrix[0].length;
		
		sum = new int[row+1][col+1];
		
		for(int i=1; i<=row; i++)
			for(int j=1; j<=col; j++)
				sum[i][j] = matrix[i-1][j-1] +
							sum[i-1][j] +
							sum[i][j-1] -
							sum[i-1][j-1];
	}
	
	public int rangeSum(int row1, int col1, int row2, int col2) {
		return sum[row2+1][col2+1] -
				sum[row2+1][col1] -
				sum[row1][col2+1] +
				sum[row1][col1];
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] 
				{{3, 0, 1, 4, 2}, 
			 	 {5, 6, 3, 2, 1}, 
			     {1, 2, 0, 1, 5}, 
			     {4, 1, 0, 1, 7},
			     {1, 0, 3, 0, 5}};
			     
	     RangeSumQuery2D query = new RangeSumQuery2D(matrix);  
	     System.out.println(query.rangeSum(2, 1, 4, 3));	// 8
	     System.out.println(query.rangeSum(1, 1, 2, 2));	// 11
	     System.out.println(query.rangeSum(1, 2, 2, 4));	// 12
	}
}
