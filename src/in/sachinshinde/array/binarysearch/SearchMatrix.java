package in.sachinshinde.array.binarysearch;

//	https://leetcode.com/problems/search-a-2d-matrix/

/*
 	You are given an m x n integer matrix matrix with the following two properties:

        Each row is sorted in non-decreasing order.
        The first integer of each row is greater than the last integer of the previous row.
        Given an integer target, return true if target is in matrix or false otherwise.
                
        You must write a solution in O(log(m * n)) time complexity.
        
        Example 1:
        ---------
        Input: matrix = [
        	[1 ,3 ,5 ,7 ],
        	[10,11,16,20],
        	[23,30,34,60]] 
        	
        	target = 3
        
        Output: true
        
        Example 2:
        ---------
        Input: matrix = [
        	[1 ,3 ,5 ,7 ],
        	[10,11,16,20],
        	[23,30,34,60]]
        	
        	target = 13
        
        Output: false
         
        
        Constraints:
            m == matrix.length
            n == matrix[i].length
            1 <= m, n <= 100
            -104 <= matrix[i][j], target <= 104
 */

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
	if(matrix == null || matrix.length == 0) 
	    return false;
		
	int m = matrix.length;
	int n = matrix[0].length;
		
	int low = 0;
	int high = (m * n) - 1;
		
	while(low <= high) {
	    int mid = low + (high - low) / 2;
	    int midValue = matrix[mid/n][mid%n];	// understand the math for this calculation

	    if(midValue == target) 
		return true;
	    else if(midValue > target)
		high = mid - 1;
	    else if(midValue < target)
		low = mid + 1;
	}
	
        return false;
    }
    
    public static void main(String[] args) {
	SearchMatrix matrix = new SearchMatrix();
	
	int m[][] = new int[][]{
	    {1,3,5,7},
	    {10,11,16,20},
	    {23,30,34,60}};
	
	System.out.println(matrix.searchMatrix(m, 3));	 // true
	
	m = new int[][]{
	    {1,3,5,7},
	    {10,11,16,20},
	    {23,30,34,60}};
	
	System.out.println(matrix.searchMatrix(m, 13));	 // false
    }
}