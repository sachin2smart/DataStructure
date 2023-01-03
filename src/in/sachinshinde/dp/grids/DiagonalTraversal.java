package in.sachinshinde.dp.grids;


//	https://leetcode.com/problems/diagonal-traverse/

/*
 * 	Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 
 	Example 1:
		Input: 
			mat = [ [1,2,3],
				[4,5,6],
				[7,8,9]]
				
		Output: [1,2,4,7,5,3,6,8,9]

	Example 2:
		Input: mat = [[1,2],
			      [3,4]]
		Output: [1,2,3,4]
 */

public class DiagonalTraversal {

    private int[]  findDiagonalOrder(int[][] mat) {
	if (mat == null || mat.length == 0) {
	    return new int[0];
	}
	
	int m = mat.length, n = mat[0].length, i = 0, row = 0, col = 0;
	int[] arr = new int[m*n];
	boolean up = true;
	
	while(row < m && col < n) {
	    // if diagonal is goin up
	    if(up) {
		while(row > 0 && col < n-1) {
		    arr[i++] = mat[row][col];
		    row--;
		    col++;
		}
		arr[i++] = mat[row][col];
		if(col == n-1) {
		    row++;
		}
		else {
		    col++;
		}
	    }
	    else {
		while(col > 0 && row < m-1) {
		    arr[i++] = mat[row][col];
		    row++;
		    col--;
		}
		arr[i++] = mat[row][col];
		if(row == m-1) {
		    col++;
		}
		else {
		    row++;
		}
	    }
	    up = !up;
	}
	
	return arr;
    }

    public static void main(String[] args) {
	DiagonalTraversal diagonalTraversal = new DiagonalTraversal();
	int[][] mat = new int[][] {
	    {1,2,3},
	    {4,5,6},
	    {7,8,9}
	};
	System.out.println(diagonalTraversal.findDiagonalOrder(mat)); // [1,2,4,7,5,3,6,8,9]
	
	mat = new int[][] {
	    {1,2},
	    {3,4}
	};
	System.out.println(diagonalTraversal.findDiagonalOrder(mat)); // [1,2,3,4]
    }
}
