package in.sachinshinde.graph.union_find;

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


//	Approach : Union Find

public class MakeNetworkConnected {

	public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) 
        	return -1; // To connect all nodes need at least n-1 edges
        
        int[] parent = new int[n];
        
        for (int i = 0; i < n; i++) 
        	parent[i] = i;
        
        int components = n;
        
        for (int[] edge : connections) {
            int p1 = findParent2(parent, edge[0]);
            int p2 = findParent2(parent, edge[1]);
            if (p1 != p2) {
                parent[p1] = p2; // union
                components--;
            }
        }
        
        return components - 1; // Need (components-1) cables to connect components together
    }
	
	// find
    private static int findParent(int[] parent, int edge) {
        while (edge != parent[edge]) 
        	edge = parent[edge];
        
        return edge; // Without Path Compression
    }
    
    // find
    private static int findParent2(int[] parent, int edge) {
        if (edge == parent[edge]) 
        	return edge;
        
        return findParent2(parent, parent[edge]); // Path compression
    }
	
	public static void main(String[] args) {
		int[][] connections = new int[][] {{0,1},{0,2},{1,2}};
		int minArrangeNeeded = makeConnected2(4, connections);
		System.out.println(minArrangeNeeded);	// 1
		
		int[][] connections1 = new int[][] {{0,1},{0,2},{0,3},{1,2},{1,3}};
		int minArrangeNeeded1 = makeConnected2(6, connections1);
		System.out.println(minArrangeNeeded1);	// 2
		
		int[][] connections2 = new int[][] {{0,1},{0,2},{0,3},{1,2}};
		int minArrangeNeeded2 = makeConnected2(6, connections2);
		System.out.println(minArrangeNeeded2);	// -1
		
	}
	
	// Union by Size
	public static int makeConnected2(int n, int[][] connections) {
        if (connections.length < n - 1) 
        	return -1; // To connect all nodes need at least n-1 edges
        
        int[] parent = new int[n];
        int[] size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        int components = n;
        
        for (int[] edge : connections) {
            int p1 = findParent2(parent, edge[0]);
            int p2 = findParent2(parent, edge[1]);
            
            if (p1 != p2) {
                if (size[p1] < size[p2]) { // Merge small size to large size
                    size[p2] += size[p1];
                    parent[p1] = p2;
                } else {
                    size[p1] += size[p2];
                    parent[p2] = p1;
                }
                components--;
            }
            
        }
        
        return components - 1; // Need (components-1) cables to connect components together
    }
	
}
