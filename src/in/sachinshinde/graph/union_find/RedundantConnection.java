package in.sachinshinde.graph.union_find;

import java.util.Arrays;

//	https://leetcode.com/problems/redundant-connection/

/*
 * 	Redundant Connection
 * 		In this problem, a tree is an undirected graph that is connected and has no cycles.
 * 		You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. 
 * 		The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. 
 * 		
 * 		The graph is represented as an array edges[][] of length "n" where 
 * 				edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

		Return an edge that can be removed so that the resulting graph is a tree of n nodes. 
		If there are multiple answers, return the answer that occurs last in the input.
 * 
 */

//	Approach : Union-Find Algorithm

public class RedundantConnection {

	public static int[] findRedundantConnection(int[][] edges) {
	    int[] sets = new int[edges.length + 1];
	    
	    for(int[] edge : edges) {
	        int u = find(sets, edge[0]);
	        int v = find(sets, edge[1]); 
	        
	        if(u == v) 
	            return edge;
	        
	        sets[u] = v;	// ** Imp Step : u'th position in set[] assigned the value as v
	    }
	    
	    return new int[]{};
	}
	
	private static int find(int[] sets, int v) {
        if (sets[v] == 0) 
        	return v;
        
        sets[v] = find(sets, sets[v]);	//	the value for v in each iteration get value from set[v] 
        								// (i.e. v=set[v] for recursive call)
        
        return sets[v];
    }
	
	public static void main(String[] args) {
		/*
		 * 		(1)
		 * 	   /   \
		 *	(2)	---	(3)
		 */
		int[][] edges = new int[][] {{1,2},{1,3},{2,3}};
		int[] redundantEdge = findRedundantConnection(edges);
		System.out.println(Arrays.toString(redundantEdge));	// [2, 3]
		// By removing edge [2,3] the graph will be a tree
	}
}
