package in.sachinshinde.graph.dfs;

//	https://leetcode.com/problems/number-of-provinces/

/*
 *	Number of Provinces
 *		There are n cities. Some of them are connected, while some are not. 
 *		If city a is connected directly with city b, and city b is connected directly with city c, 
 *			then city a is connected indirectly with city c.

		A province is a group of directly or indirectly connected cities and no other cities outside of the group.

		You are given an n x n matrix M where 
			M[i][j] = 1 if the ith city and the jth city are directly connected, and 
			M[i][j] = 0 otherwise.

		Return the total number of provinces. 
 * 
 */

public class NumberOfProviences {
	
	public int findNumOfProviences(int[][] M) {
		int numProvices = 0;
		int[] visited = new int[M.length];
		for(int i=0; i<M.length; i++) {
			if(visited[i] == 0) {
				dfs(M, visited, i);
				numProvices++;
			}
		}	
		return numProvices;
	}

	private void dfs(int[][] M, int[] visited, int i) {
		for(int j=0; j<M.length; j++) {
			if(M[i][j] == 1 & visited[j] == 0) {
				visited[j] = 1;
				dfs(M, visited, j);
			}
		}
	}
}
