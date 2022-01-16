package in.sachinshinde.graph.bipartition;

import java.util.LinkedList;
import java.util.Queue;

//	0 : no color
//	1 : blue color
//	-1: green color

public class IsGraphBipartiteAdjMatrixBFS {

	public static boolean isBipartite(int[][] graph) {
	    int n = graph.length;
	    int[] color = new int[n];
	    
	    for(int node = 0; node < n; node++) {
	        if(color[node] != 0) 
	        	continue;
	        
	        Queue<Integer> queue = new LinkedList<>();
	        queue.offer(node);
	        color[node] = 1;   
	        
	        while(!queue.isEmpty()) {
	            int currNode = queue.poll();
	            for(int adjNode : graph[currNode]) {
	                if(color[adjNode] == 0) {
	                    color[adjNode] = -color[currNode];  // Color adj-node with an opposite color of curr-node
	                    queue.offer(adjNode);
	                } 
	                else if(color[adjNode] == color[currNode]) {
	                    return false;
	                }
	            }
	        }
	    }
	    return true;
	}
        
	public static void main(String[] args) {
		int[][] graph =new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}};
		if(isBipartite(graph))
			System.out.println("Bipartite");
		else
			System.out.println("Not Bipartite"); // result
		
		
		graph =new int[][]{{1,3},{0,2},{1,3},{0,2}};
		if(isBipartite(graph))
			System.out.println("Bipartite");	// result
		else
			System.out.println("Not Bipartite");
	}
}
