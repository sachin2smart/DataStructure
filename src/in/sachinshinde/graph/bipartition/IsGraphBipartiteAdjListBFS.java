package in.sachinshinde.graph.bipartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
	Properties of Bipartite Graph:
		- Coloring with two colors: A bipartite graph can be colored with two colors, such that no adjacent vertices have the same color.
		- No odd-length cycles: A bipartite graph cannot contain any odd-length cycles, as this would result in having same color for 2 adjacent vertices.
 */

public class IsGraphBipartiteAdjListBFS {

	private static boolean isBipartite(ArrayList<ArrayList<Integer>> adjList, int n) {
		int[] color = new int[n];
		Arrays.fill(color, -1);
		
		for(int node=0; node<n; node++) {
			if (color[node] == -1) {
				if (!isAdjColoringValid(adjList, node, color)) {
					return false;
				}
			}
		}
				
		return true; 
	}
	
	private static 	boolean isAdjColoringValid(ArrayList<ArrayList<Integer>> adjList, int node, int[] color) {
		Queue<Integer> coloredNodesQueue = new LinkedList<>();
		coloredNodesQueue.add(node);

		// Color it either 0 or 1
		color[node] = 1; 
		
		while(!coloredNodesQueue.isEmpty()) {
			Integer currNode = coloredNodesQueue.poll();

			for(Integer adjNode: adjList.get(currNode)) {
				// if adjacent node is not yet colored
				if(color[adjNode] == -1) {
					color[adjNode] = 1 - color[currNode]; // color with opposite color of currNode, if currNode is colored with 0 then color adjNode with 1, else if currNode is colored with 1 then color adjNode wth 0
					coloredNodesQueue.add(adjNode);
				}
				else if(color[adjNode] == color[currNode]) { // if adjacent node have the same color
					return false; 
				}
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
