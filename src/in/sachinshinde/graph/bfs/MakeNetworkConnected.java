package in.sachinshinde.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/number-of-operations-to-make-network-connected/

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


//Approach : BFS

public class MakeNetworkConnected {

    //  Video Solution: https://youtu.be/FYrl7iz9_ZU   - (Striver)
	public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1; // To connect all nodes need at least n-1 edges
        }
        
        List<Integer>[] graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) 
        	graph[i] = new ArrayList<>();
        
        for (int[] edge : connections) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        int components = 0;
        boolean[] visited = new boolean[n];
        
        for (int v = 0; v < n; v++) 
        	components += bfs(v, graph, visited);
        
        return components - 1; // Need (components-1) cables to connect components together
    }
	
    private static int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src]) 
        	return 0;
        
        visited[src] = true;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
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
		
	}
}
