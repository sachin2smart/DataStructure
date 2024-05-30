package in.sachinshinde.misc.grids;

import java.util.PriorityQueue;

//	https://leetcode.com/problems/minimum-falling-path-sum-ii/

/*
	Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.	
	A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that
	 	no two elements chosen in adjacent rows are in the same column.
 */

public class MinFallingPathSum2 {
	   public int minFallingPathSum(int[][] matrix) {
		   int m = matrix.length;
		   int n = matrix[0].length;
		   PriorityQueue<Integer> pq;
		   
		   for(int i=1; i<m; i++) {
			    pq = new PriorityQueue<Integer>();
			    for(int j=0; j<n; j++)
			        pq.offer(matrix[i-1][j]);
			    
			    int n1 = pq.poll();
			    int n2 = pq.poll();
			    
			    for (int j=0; j<n; j++)
			        if (matrix[i-1][j] == n1) 
			            matrix[i][j] += n2;
			        else
			            matrix[i][j] += n1;
		   }
		
			int res = Integer.MAX_VALUE;
			for(int j=0; j<n; j++)
			    res = Math.min(res, matrix[m-1][j]);
			
			return res;
	   	}
	   
	   public static void main(String[] args) {
			int[][] matrix = new int[][] {
				{1,2,3},
				{4,5,6},
				{7,8,9}
			};
			MinFallingPathSum2 minFallingPathSum2 = new MinFallingPathSum2();
			System.out.println(minFallingPathSum2.minFallingPathSum2(matrix));
	   }
	   
	   public int minFallingPathSum2(int[][] arr) {
	        int m = arr.length, n = arr[0].length, dp[][] = new int[m][n], res = Integer.MAX_VALUE;
	        dp[0] = arr[0];
	        for (int i = 1; i < m; i++) {
	            
	            for (int j = 0; j < n; j++) {
	                dp[i][j] = Integer.MAX_VALUE;
	                for (int k = 0; k < n; k++) {
	                    if (j != k) dp[i][j] = Math.min(dp[i][j], arr[i][j] + dp[i - 1][k]);
	                }
	                if (i == m - 1) res = Math.min(res, dp[i][j]);
	            }
	        }
	        return res;
	    }
	   
}

/*
	Input: matrix = [[1,2,3],
				  [4,5,6],
				  [7,8,9]]
	Output: 13
	Explanation: 
	The possible falling paths are:
	[1,5,9], [1,5,7], [1,6,7], [1,6,8],
	[2,4,8], [2,4,9], [2,6,7], [2,6,8],
	[3,4,8], [3,4,9], [3,5,7], [3,5,9]
	The falling path with the smallest sum is [1,5,7], so the answer is 13.
*/