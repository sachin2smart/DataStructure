package in.sachinshinde.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.com/problems/01-matrix/

/*
 	Given an m x n binary matrix mat, 
 	return the distance of the nearest 0 for each cell.

	The distance between two adjacent cells is 1.

	--------------------------------------------
	Example 1:
	Input: mat = [	[0,0,0],
					[0,1,0],
					[0,0,0]]

		Output: [	[0,0,0],
					[0,1,0],
					[0,0,0]]
	--------------------------------------------
	Example 2:
	Input: mat = [	[0,0,0],
					[0,1,0],
					[1,1,1]]

		Output: [	[0,0,0],
					[0,1,0],
					[1,2,1]]
	 ---------------------------------------------

	Constraints:
	-----------
		m == mat.length
		n == mat[i].length
		1 <= m, n <= 10^4
		1 <= m * n <= 10^4
		mat[i][j] is either 0 or 1.
		There is at least one 0 in mat.

 */

public class Matrix01 {
	public int[][] updateMatrix(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		Queue<int[]> q = new LinkedList<>();	//	queue of int array **

		//	0 ==> add to queue
		//	1 ==> update to max value
		for(int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] == 0) {
					q.offer(new int[]{i, j});
				}
				else {
					mat[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		int[][] dirs = {{-1,0}, //	up
						{0,-1},	//	left
						{1,0},	//	down
						{0,1}};	//	right
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];

			for(int[] d: dirs) {
				int nr = p[0] + d[0];
				int nc = p[1] + d[1];
				//	assign the min distance from r,c to the nr,nc position
				if(nr>=0 && nr<m && nc>=0 && nc<n && mat[nr][nc] > mat[r][c] + 1) {
					mat[nr][nc] = mat[r][c] + 1;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		return mat;
    }

	/*
			[0,0,0,0,0]
			[0,1,1,1,1]
			[1,1,1,1,1]
			[1,0,0,1,1]
	 */

	/*
			[0,0,0,0,0]
			[0,1,1,1,1]
			[1,1,1,2,2]
			[1,0,0,1,2]
	 */
	

	
	public static void main(String[] args) {
		Matrix01 matrix01 = new Matrix01();
		System.out.println(Arrays.deepToString(matrix01.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));	//	[[0,0,0],[0,1,0],[0,0,0]]
		System.out.println("---------------------------------------");
		System.out.println(Arrays.deepToString(matrix01.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}})));	//	[[0,0,0],[0,1,0],[1,2,1]]
		System.out.println("---------------------------------------");
		System.out.println(Arrays.deepToString(matrix01.updateMatrix2(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));	//	[[0,0,0],[0,1,0],[0,0,0]]
		System.out.println("---------------------------------------");
		System.out.println(Arrays.deepToString(matrix01.updateMatrix2(new int[][]{{0,0,0},{0,1,0},{1,1,1}})));	//	[[0,0,0],[0,1,0],[1,2,1]]
		System.out.println("---------------------------------------");

		System.out.println(Arrays.deepToString(matrix01.updateMatrix(new int[][]
				   {{0,0,0,0,0},
					{0,1,1,1,1},
					{1,1,1,1,1},
					{1,0,0,1,1}})));

				/* Ans:
						[[0,0,0,0,0],
						 [0,1,1,1,1],
						 [1,1,1,2,2],
						 [1,0,0,1,2]]
	 				*/
	}

	public int[][] updateMatrix2(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		Queue<int[]> q = new LinkedList<>();

		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				if(mat[i][j] == 0)
					q.offer(new int[]{i,j});
				else
					mat[i][j] = -1;

		int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
		int dist = 0;

		while(!q.isEmpty()) {
			int size = q.size();
			dist++;
			for(int i=0; i<size; i++) {
				int[] cur = q.poll();
				for(int[] dir : dirs) {
					int x = cur[0] + dir[0];
					int y = cur[1] + dir[1];
					if(x<0 || x>=m || y<0 || y>=n || mat[x][y] != -1)
						continue;			//	avoid programs having continue statements
					q.offer(new int[]{x,y});
					mat[x][y] = dist;
				}
			}
		}

		return mat;
	}
}
