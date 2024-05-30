package in.sachinshinde.graph.dfs.visited_array;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/number-of-operations-to-make-network-connected/

/*
 * 	Number of Operations to Make Network Connected
 * 	
 * 	There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network 
 * 		where connections[i] = [ai, bi] represents a connection between computers ai and bi. 
 * 	Any computer can reach any other computer directly or indirectly through the network.

	You are given an initial computer network connections. 
	You can extract certain cables between two directly connected computers, 
		and place them between any pair of disconnected computers to make them directly connected.

	Return the minimum number of times you need to do this in order to make all the computers connected. 
	If it is not possible, return -1.
 */


//	Approach : DFS

public class MakeNetworkConnected {

	public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1; // To connect all nodes need at least n-1 edges
        }
        
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Converting connections[][] matrix into graph adjacency list 
        for (int[] edge : connections) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int components = 0;
        boolean[] visited = new boolean[n];
        
        for(int v = 0; v < n; v++) {
            components += dfs(v, graph, visited);
        }
        
        return components - 1; // Need (components-1) cables to connect components together
    }
	
    private static int dfs(int u, List<List<Integer>> graph, boolean[] visited) {
        if (visited[u]) {
            return 0;
        }
        
        visited[u] = true;
        
        for(int v : graph.get(u)) {
            dfs(v, graph, visited);
        }
        
        return 1;
    }
	
	public static void main(String[] args) {
		int[][] connections = new int[][] {{0,1},{0,2},{1,2}};
		int minArrangeNeeded = makeConnected(4, connections);
		System.out.println(minArrangeNeeded);	// 1
		
		int[][] connections1 = new int[][] {{0,1},{0,2},{0,3},{1,2},{1,3}};
		int minArrangeNeeded1 = makeConnected(6, connections1);
		System.out.println(minArrangeNeeded1);	// 2
		
		int[][] connections2 = new int[][] {{0,1},{0,2},{0,3},{1,2}};
		int minArrangeNeeded2 = makeConnected(6, connections2);
		System.out.println(minArrangeNeeded2);	// -1

        // the dfs function will be called for (2*connections.length)+1 times
        MakeNetworkConnected g = new MakeNetworkConnected();
        int[][] connectionsNew = new int[][] {{0,1},{0,2},{1,2}};
        System.out.println(g.makeConnected2(4, connectionsNew));

        connectionsNew = new int[][] {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println(g.makeConnected2(6, connectionsNew));

        connectionsNew = new int[][] {{0,1},{0,2},{0,3},{1,2}};
        System.out.println(g.makeConnected2(6, connectionsNew));
	}

    public int makeConnected2(int n, int[][] connections) {
        if(connections.length < n - 1) {
            return -1;
        }

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] node: connections) {
            graph.get(node[0]).add(node[1]);
            graph.get(node[1]).add(node[0]);
        }

        int numEdges = 0;
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++) {
            if(dfs(graph, i, visited) == 1) {
                numEdges++;
            }
        }

        return numEdges - 1;
    }

    private int dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        // if there exists an edge, no need to connect it again
        if(visited[node]) {
            return 0;
        }

        visited[node] = true;
        for(int nextNode: graph.get(node)) {
            dfs(graph, nextNode, visited);
        }

        // current node is not connected, we need an edge to connect with existing graph
        return 1;
    }

}
