package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/set-matrix-zeroes/

/*
	Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
        You must do it in place.
        
        Example 1:
        Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
        Output: [[1,0,1],[0,0,0],[1,0,1]]
        
        Example 2:
        Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
        Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        boolean isFirstColumnZero = false;
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) 
                isFirstColumnZero = true;
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (isFirstColumnZero) 
                matrix[i][0] = 0;
        }
    }
    
    public void setZeroes2(int[][] nums) {
	int m = nums.length;
	int n = nums[0].length;
	    
	int[] arr1 = new int[m];
	int[] arr2 = new int[n];
	    
	Arrays.fill(arr1, -1);
	Arrays.fill(arr2, -1);
	    
	for (int i=0; i<m; i++)
	    for (int j=0; j<n; j++) {
		if (nums[i][j] == 0) {
		    arr1[i] = 0;
		    arr2[j] = 0;
	        }
	    }
	
	    
	for (int i=0; i<m; i++)
	    for (int j=0; j<n; j++)
		if (arr1[i] == 0 || arr2[j] == 0)
		    nums[i][j] = 0;
    }
    
    public static void main(String[] args) {
	SetMatrixZeroes matrixZeroes = new SetMatrixZeroes();
	int[][] matrix = new int[][] {
	    {1,1,1},
	    {1,0,1},
	    {1,1,1}
	};
	
	System.out.println("\n Before : \n");
	for (int i=0; i<matrix.length; i++)  {
	    for (int j=0; j<matrix[0].length; j++)
		System.out.print(" "+matrix[i][j]);
	    System.out.println();
	}
	
	matrixZeroes.setZeroes(matrix);
	
	System.out.println("\n After : \n");
	for (int i=0; i<matrix.length; i++)  {
	    for (int j=0; j<matrix[0].length; j++)
		System.out.print(" "+matrix[i][j]);
	    System.out.println();
	}
	/*
                {1,0,1},
                {0,0,0},
                {1,0,1}
	 */
	
	matrix = new int[][] {
	    {0,1,2,0},
	    {3,4,5,2},
	    {1,3,1,5}
	};
	
	System.out.println("\n Before : \n");
	for (int i=0; i<matrix.length; i++)  {
	    for (int j=0; j<matrix[0].length; j++)
		System.out.print(" "+matrix[i][j]);
	    System.out.println();
	}
	
	matrixZeroes.setZeroes(matrix);
	
	System.out.println("\n After : \n");
	for (int i=0; i<matrix.length; i++)  {
	    for (int j=0; j<matrix[0].length; j++)
		System.out.print(" "+matrix[i][j]);
	    System.out.println();
	}
	/*
                {0,0,0,0},
                {0,4,5,0},
                {0,3,1,0}
	 */
    }
}
