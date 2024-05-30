package in.sachinshinde.misc.grids;

//	https://leetcode.com/problems/maximal-square/

/*
 	Given an m x n binary matrix filled with 0's and 1's, 
 		find the largest square containing only 1's and return its area.

        Example 1:
      	---------
        Input: matrix = [
            ["1","0","1","0","0"],
            ["1","0","1","1","1"],
            ["1","1","1","1","1"],
            ["1","0","0","1","0"]]
        Output: 4
        
        Example 2:
        ---------
        Input: matrix = [
            ["0","1"],
            ["1","0"]]
        Output: 1
        
        Example 3:
        ---------
        Input: matrix = [["0"]]
        Output: 0
         
        
        Constraints:
        -----------    
            m == matrix.length
            n == matrix[i].length
            1 <= m, n <= 300
            matrix[i][j] is '0' or '1'.
 */

public class MaximalSquare {

    //	Method 1: Using Recursion[Use cache matrix to keep track of down, right and diagonal squares]
    public int maximalSquare(char[][] matrix) {
	int rows = matrix.length;
	int cols = matrix[0].length;
	
	int[][] maxSquareCacheMatrix = new int[rows][cols];
	for(int i=0; i<rows; i++)
	    for(int j=0; j<cols; j++)
		maxSquareCacheMatrix[i][j] = -1;
	
	constructMaxSquareCacheMatrix(0, 0, maxSquareCacheMatrix, rows, cols, matrix);
	
	int maxSquare = 0;
	
	for(int i=0; i<rows; i++)
	    for(int j=0; j<cols; j++)
		if(maxSquareCacheMatrix[i][j] > maxSquare)
		    maxSquare = maxSquareCacheMatrix[i][j];
	
	return maxSquare*maxSquare;
    }
    
    private int constructMaxSquareCacheMatrix(int r, int c, int[][] maxSquareCacheMatrix, int rows, int cols, char[][] matrix) {
	if(r >= rows || c >= cols)
	    return 0;
	
	if(maxSquareCacheMatrix[r][c] == -1) {
	    int down = constructMaxSquareCacheMatrix(r+1, c, maxSquareCacheMatrix, rows, cols, matrix);
	    int right = constructMaxSquareCacheMatrix(r, c+1, maxSquareCacheMatrix, rows, cols, matrix);
	    int diagonal = constructMaxSquareCacheMatrix(r+1, c+1, maxSquareCacheMatrix, rows, cols, matrix);
	    
	    maxSquareCacheMatrix[r][c] = 0;
	    if(matrix[r][c] == '1') {
		maxSquareCacheMatrix[r][c] = 1 + Math.min(Math.min(down, right), diagonal);
	    }
	}
	
	return maxSquareCacheMatrix[r][c];
    }

    //	Method 2: Using Dynamic programming
    
    public int maximalSquare_dp(char[][] matrix) {
	if(matrix.length == 0)
	    return 0;
	
	int rows = matrix.length, cols = matrix[0].length, res = 0;
	int[][] dp = new int[rows+1][cols+1]; 
	
	for(int i=1; i<=rows; i++) {
	    for(int j=1; j<=cols; j++) {
		if(matrix[i-1][j-1] == '1') {
		    dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
		    res = Math.max(dp[i][j], res);
		}
	    }
	}
	
	return res * res;
    }
    
    public static void main(String[] args) {
	MaximalSquare maximalSquare = new MaximalSquare();
	char[][] m1 = new char[][] {
	    {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
	};
	
	System.out.println(maximalSquare.maximalSquare(m1));	// 4
	System.out.println(maximalSquare.maximalSquare_dp(m1));	// 4
	
	char[][] m2 = new char[][] {
	    {'0','1'},
	    {'1','0'}
	};
	
	System.out.println(maximalSquare.maximalSquare(m2));	// 1
	System.out.println(maximalSquare.maximalSquare_dp(m2));	// 1
	
	char[][] m3 = new char[][] {{'0'}};
	
	System.out.println(maximalSquare.maximalSquare(m3));	// 0
	System.out.println(maximalSquare.maximalSquare_dp(m3));	// 0
    }
}
