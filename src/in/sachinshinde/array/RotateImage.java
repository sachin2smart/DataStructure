package in.sachinshinde.array;

//	https://leetcode.com/problems/rotate-image/

/*
 	You are given an n x n 2D matrix representing an image, 
 	rotate the image by 90 degrees (clockwise).

        You have to rotate the image in-place,
        	which means you have to modify the input 2D matrix directly.
        DO NOT allocate another 2D matrix and do the rotation.
        
        Example 1:
        ---------
        Input: matrix = [
        [1,2,3],
        [4,5,6],
        [7,8,9]]
        
        Output: [
        [7,4,1],
        [8,5,2],
        [9,6,3]]
        
        Example 2:
	---------
        Input: matrix = [
        [5,1,9,11],
        [2,4,8,10],
        [13,3,6,7],
        [15,14,12,16]]
        
        Output: [
        [15,13,2,5],
        [14,3,4,1],
        [12,6,8,9],
        [16,7,10,11]]
        
        Constraints:
        -----------
            n == matrix.length == matrix[i].length
            1 <= n <= 20
            -1000 <= matrix[i][j] <= 1000
 */

public class RotateImage {
    /*

    [j][n-i]  *   * [n-i][n-j]
    [i,j]     *   * [n-j][i]
    
    
    [1, 2]     -->   [3,  1]
    [3, 4]           [4,  2]

    */
    public void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        int i = 0;
        
        while(i<matrix.length/2) {  //  transform half
            for(int j=i; j<n-i; j++) {  //  transform remaining i
                int origVal = matrix[i][j];
                matrix[i][j] = matrix[n-j][i];
                matrix[n-j][i] = matrix[n-i][n-j];
                matrix[n-i][n-j] = matrix[j][n-i];
                matrix[j][n-i] = origVal;
            }
            i++;
        }
    }
    
    //	Method 2
    public void rotate2(int[][] matrix) {
	//	transpose the matrix
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int orgVal = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = orgVal;
            }
        }
        //	flip it symmetrically
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int orgVal = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = orgVal;
            }
        }
    }
    
    private void print(int m[][]){
        for(int i=0; i < m.length; i++){
            for(int j=0; j < m.length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    public static void main(String[] args) {
	RotateImage image = new RotateImage();
	int[][] m1 = new int [][]{
	    {1,2,3},
	    {4,5,6},
	    {7,8,9}};
	image.rotate(m1);
	image.print(m1);
	/*
	 	7 4 1 
                8 5 2 
                9 6 3 
	 */
	
	int[][] m2 = new int [][]{
	    {5,1,9,11},
	    {2,4,8,10},
	    {13,3,6,7},
	    {15,14,12,16}
	};
	image.rotate(m2);
	image.print(m2);
	/*
	 	15 13 2 5 
                14 3 4 1 
                12 6 8 9 
                16 7 10 11
	 */
	
	int[][] m3 = new int [][]{
	    {1,2,3},
	    {4,5,6},
	    {7,8,9}};
	image.rotate2(m3);
	image.print(m3);
	/*
	 	7 4 1 
                8 5 2 
                9 6 3 
	 */
	
	int[][] m4 = new int [][]{
	    {5,1,9,11},
	    {2,4,8,10},
	    {13,3,6,7},
	    {15,14,12,16}
	};
	image.rotate2(m4);
	image.print(m4);
	/*
	 	15 13 2 5 
                14 3 4 1 
                12 6 8 9 
                16 7 10 11
	 */
    }
}