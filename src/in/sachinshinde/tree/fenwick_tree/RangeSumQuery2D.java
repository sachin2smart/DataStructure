package in.sachinshinde.tree.fenwick_tree;

import java.util.Arrays;

//	https://leetcode.ca/all/308.html
//	https://leetcode.ca/2016-10-03-308-Range-Sum-Query-2D-Mutable/

/*
 *  --------------------------
 * | 	Range Sum Query - 2D  |
 *  --------------------------
 *  
 *  Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

	Given matrix = [
	  [3, 0, 1, 4, 2],
	  [5, 6, 3, 2, 1],
	  [1, 2, 0, 1, 5],
	  [4, 1, 0, 1, 7],
	  [1, 0, 3, 0, 5]
	]
	
	sumRegion(2, 1, 4, 3) -> 8
	update(3, 2, 2)
	sumRegion(2, 1, 4, 3) -> 10
 */

public class RangeSumQuery2D {

	int[][] tree;	// 	This represent the BinaryIndexedTree in 2D format 
	int[][] nums;	//	This is a copy of matrix input - useful while performing an update post BIT creation
	int m;
	int n;
	
	public RangeSumQuery2D(int[][] matrix) {
		if(matrix.length == 0 || matrix[0].length == 0)
			return;
		m = matrix.length;
		n = matrix.length;
		tree = new int[m+1][n+1];
		nums = new int[m][n];
		
		for(int i=0; i<m; i++)
			for(int j=0; j<n; j++)
				update(i, j, matrix[i][j]);
	}

	private void update(int row, int col, int val) {
		if(m==0 || n==0)
			return;
		
		int delta = val - nums[row][col];
		nums[row][col] = delta;
		
		for(int i=row+1; i<=m; i += i&(-i))
			for(int j=col+1; j<=n; j += j&(-j))
				tree[i][j] += delta;
	}
	
	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(m==0 || n==0)
			return 0;
		return sum(row2+1, col2+1)
				+ sum(row1, col1)
				- sum(row1, col2+1)
				- sum(row2+1, col1);
	}

	private int sum(int row, int col) {
		int sum = 0;
		for(int i=row; i>0; i -= i&(-i))
			for(int j=col; j>0; j -= j&(-j))
				sum += tree[i][j];
		return sum;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				 {3, 0, 1, 4, 2},
				 {5, 6, 3, 2, 1},
				 {1, 2, 0, 1, 5},
				 {4, 1, 0, 1, 7},
				 {1, 0, 3, 0, 5}
			 };
			 
		RangeSumQuery2D query = new RangeSumQuery2D(matrix);
		
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(); System.out.println();
		System.out.println(Arrays.deepToString(query.nums));
		System.out.println(); System.out.println();
		System.out.println(Arrays.deepToString(query.tree));
		
		System.out.println(); System.out.println();
		System.out.println(query.sumRegion(2, 1, 4, 3));	// 8
		
		query.update(3, 2, 2);
		
		System.out.println(); System.out.println();
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(); System.out.println();
		System.out.println(Arrays.deepToString(query.nums));
		System.out.println(); System.out.println();
		System.out.println(Arrays.deepToString(query.tree));
		
		System.out.println(); System.out.println();
		System.out.println(query.sumRegion(2, 1, 4, 3));	//	10
	}
	
}
