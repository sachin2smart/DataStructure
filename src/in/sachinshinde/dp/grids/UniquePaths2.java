package in.sachinshinde.dp.grids;

import java.util.Arrays;

public class UniquePaths2 {
	
	// Using 2D DP
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		
        int[][] dp = new int[m+1][n+1];
        Arrays.fill(dp[0], 0);	// First Row = 0
        
        dp[0][1] = 1;
        
        for(int i=1; i<=m; i++)
            for(int j=1; j<=n;j++)
            	if(obstacleGrid[i-1][j-1] != 1)
            		dp[i][j] = dp[i-1][j] + dp[i][j-1];
            
        return dp[m][n];
    }
	
	public static void main(String[] args) {
		UniquePaths2 uniquePaths = new UniquePaths2();
		int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
		System.out.println(uniquePaths.uniquePathsWithObstacles(obstacleGrid));	//2
		System.out.println(uniquePaths.uniquePathsWithObstacles2(obstacleGrid));	//2
	}
	
	// Using 1D DP
	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
	    
		int[] dp = new int[n];
		
	    dp[0] = 1;
	    
	    for (int i=0; i<m; i++) {
	        for (int j=0; j<n; j++) {
	            if (obstacleGrid[i][j] == 1)
	                dp[j] = 0;
	            else if (j > 0)
	                dp[j] += dp[j - 1];
	        }
	    }
	    return dp[n - 1];
	}
}
