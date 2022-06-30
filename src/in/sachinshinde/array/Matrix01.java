package in.sachinshinde.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.com/problems/01-matrix/

/*
 	Given an m x n binary matrix mat, 
 	return the distance of the nearest 0 for each cell.

	The distance between two adjacent cells is 1.
 */

public class Matrix01 {
	public int[][] updateMatrix(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=0; i<m; i++)
			for(int j=0; j<n; j++)
				if(mat[i][j] == 0)
					q.offer(new int[] {i,j});
				else
					mat[i][j] = Integer.MAX_VALUE;
		
		int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int[] d: dirs) {
				int r = p[0] + d[0];
				int c = p[1] + d[1];
				if(r<0 || r>=m || c<0 || c>=n ||
						mat[r][c] <= mat[p[0]][p[1]]+1)
					continue;
				q.offer(new int[] {r,c});
				mat[r][c] = mat[p[0]][p[1]]+1;
			}
		}
		
		return mat;
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
                    	continue;
                    q.offer(new int[]{x,y});
                    mat[x][y] = dist;
                }
            }
        }
        
        return mat;
    }
	
	public static void main(String[] args) {
		Matrix01 matrix01 = new Matrix01();
		System.out.println(Arrays.deepToString(matrix01.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));	//	[[0,0,0],[0,1,0],[0,0,0]]
		System.out.println(Arrays.deepToString(matrix01.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}})));	//	[[0,0,0],[0,1,0],[1,2,1]]
		
		System.out.println(Arrays.deepToString(matrix01.updateMatrix2(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));	//	[[0,0,0],[0,1,0],[0,0,0]]
		System.out.println(Arrays.deepToString(matrix01.updateMatrix2(new int[][]{{0,0,0},{0,1,0},{1,1,1}})));	//	[[0,0,0],[0,1,0],[1,2,1]]
	}
}
