package in.sachinshinde.graph.bipartition;

//	https://leetcode.com/problems/is-graph-bipartite/

/*
 * 	Is Graph Bipartite?
 * 
 * 	There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. 
 * 		You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. 
 * 		More formally, for each v in graph[u], there is an undirected edge between node u and node v. 
 * 
 * The graph has the following properties:
 * 		There are no self-edges (graph[u] does not contain u).
 * 		There are no parallel edges (graph[u] does not contain duplicate values).
 * 		If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * 		The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * 		A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that - 
 * 			every edge in the graph connects a node in set A and a node in set B.

	
		Return true if and only if it is bipartite.
 * 
 */


//	Approach : DFS
/*
 * 	0 	: not colored
 * 	1	: blue color
 * 	-1	: green color
 */


public class IsGraphBipartiteAdjMatrixDFS {
	
	public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];			
				
        for (int node = 0; node < n; node++)
	        if (colors[node] == 0 && !validColor(graph, colors, 1, node))
	            return false;
            
        return true;
    }
    
    private static boolean validColor(int[][] graph, int[] colors, int bgColor, int node) {
        if (colors[node] != 0) 
            return colors[node] == bgColor;
        
        colors[node] = bgColor;    
        
        for (int adjNode : graph[node]) 
            if (!validColor(graph, colors, -bgColor, adjNode))
                return false;
            
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


/*
 	Our goal is trying to use two colors to color the graph and see 
  		if there are any adjacent nodes having the same color.
		
	Initialize a colors[] array for each node. 
		Here are three states for colors[] array:
			0: Haven't been colored yet.
			1: Blue.
			-1: Red.

	For each node,
		If it hasn't been colored, use a color to color it. 
			Then use the other color to color all its adjacent nodes (DFS).
		If it has been colored, check if the current color is the same as the adjacent nodes color
 */
