package in.sachinshinde.graph.bipartition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartiteAdjListBFS {

	private static boolean isBipartite(ArrayList<ArrayList<Integer>> adjList, int n) {
		int color[] = new int[n];
		for(int i = 0;i<n;i++)
			color[i] = -1; 
		
		for(int node=0; node<n; node++)
			if(color[node] == -1)
				if(!isAdjColoringValid(adjList, node, color))
					return false; 
				
		return true; 
	}
	
	private static 	boolean isAdjColoringValid(ArrayList<ArrayList<Integer>> adjList, int node, int color[]) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node); 
		color[node] = 1; 
		
		while(!q.isEmpty()) {
			Integer currNode = q.poll(); 

			for(Integer adjNode: adjList.get(currNode)) {
				if(color[adjNode] == -1) {
					color[adjNode] = 1 - color[currNode]; 
					q.add(adjNode); 
				}
				else if(color[adjNode] == color[currNode]) {
					return false; 
				}
			}
		}
		return true; 
	}
	
	public static void main(String[] args) {
		int n = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < n; i++) 
			adj.add(new ArrayList<Integer>());
			
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
		n = 4;
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) 
			adj.add(new ArrayList<Integer>());
		
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