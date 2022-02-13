package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.ca/all/323.html

/*
 * 	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * 	write a function to find the number of connected components in an undirected graph.
 */

public class NumberOfConnectedComponents {
	
    public int countComponents(int[][] edges, int n) {
    	int count = 0;
    
    	List<List<Integer>> adj = new ArrayList<>();
    	for (int i = 0; i < n; i++)
        	adj.add(new ArrayList<>());
    	
    	boolean[] visited = new boolean[n];

	    for(int[] e: edges) {
	    	adj.get(e[0]).add(e[1]);
	    	adj.get(e[1]).add(e[0]);
	    } 
	
	    for(int i=0; i<n; i++) {
	    	if(!visited[i]) {
	    		count++;
	    		dfs(visited, adj, i);
	    	}
	    }
	    
	    return count;
    }

    private void dfs(boolean[] visited, List<List<Integer>> adj, int i) {
    	if(visited[i])
    		return;
    	
	    visited[i] = true;
	
	    for(int j=0; j<adj.get(i).size(); j++)
	    dfs(visited, adj, adj.get(i).get(j)); 
    }

    public static void main(String[] args) {
		int[][] edges = new int[][] {{0, 1}, {1, 2}, {3, 4} };
		NumberOfConnectedComponents components = new NumberOfConnectedComponents();
		System.out.println(components.countComponents(edges, 5));		// 2
		
		edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4} };
		System.out.println(components.countComponents(edges, 5));		// 1
	}
}

/*

  	Example 1:
	----------
	Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
	
	     0          3
	     |          |
	     1 --- 2    4 
	
	Output: 2
	
	
	Example 2:
	----------
	Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
	
	     0           4
	     |           |
	     1 --- 2 --- 3
	
	Output: 1

*/