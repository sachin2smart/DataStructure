package in.sachinshinde.graph.bipartition;

import java.util.ArrayList;
import java.util.Arrays;

//	-1	: not colored
//	0	: blue color
//	1	: green color

public class IsGraphBipartiteAdjListDFS {

	private static boolean isBipartite(ArrayList<ArrayList<Integer>> adjList, int n) {
		int[] color = new int[n];
		Arrays.fill(color, -1);
		
		for(int node=0; node<n; node++) {
			if (color[node] == -1) {
				if (!validColoring(adjList, node, color)) {
					return false;
				}
			}
		}
				
		return true;
	}
	
	private static 	boolean validColoring(ArrayList<ArrayList<Integer>> adjList, int node, int[] color) {
		for(Integer adjNode: adjList.get(node)) {
			if(color[adjNode] == -1) {
				color[adjNode] = 1 - color[node]; 

				if(!validColoring(adjList, adjNode, color)) {
					return false;
				}
			}
			else if(color[adjNode] == color[node]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int n = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		
		for(int i = 0; i < n; i++) 
			adj.add(new ArrayList<>());
			
		adj.get(0).add(1);
		adj.get(1).add(0);

		adj.get(1).add(2);
		adj.get(2).add(1);

		adj.get(2).add(3);
		adj.get(3).add(2);

		adj.get(3).add(0);
		adj.get(0).add(3);
		
		adj.get(0).add(2);
		adj.get(2).add(0);
		
		if(isBipartite(adj, n))
			System.out.println("Bipartite");	
		else
			System.out.println("Not Bipartite"); // result
		
		// Test-2
		adj = new ArrayList<>();
		for (int i = 0; i < n; i++) 
			adj.add(new ArrayList<>());
		
		adj.get(0).add(1);
		adj.get(1).add(0);

		adj.get(1).add(2);
		adj.get(2).add(1);

		adj.get(2).add(3);
		adj.get(3).add(2);

		adj.get(3).add(0);
		adj.get(0).add(3);
		
		if(isBipartite(adj, n))
			System.out.println("Bipartite");	// result
		else
			System.out.println("Not Bipartite"); 

	}
}
