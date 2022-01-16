package in.sachinshinde.graph;

import java.util.ArrayList;

// Bellman Ford Algorithm : 	Single-source shortest path problem (for weighted directed graphs)

/*
 * 	https://www.youtube.com/watch?v=75yC1vbS8S8
 * 	https://github.com/striver79/StriversGraphSeries/blob/main/BellmanFordJava
 */

public class BellmanFord {
	
	public static void bellmanFord(ArrayList<Node> edges, int N, int src){
		int[] dist = new int[N];
		
		// initial infinity distance
		for(int i=0;i<N;i++)
			dist[i] = 10000000;
		
		// Distance for the source is always be zero
		dist[src] = 0;
		
		// Relax N-1 edges
		//	if dist[u] + w < dist[v] then
        //		dist[v] := dist[u] + w
		for(int i=1; i< N-1; i++) {
			for(Node node: edges) {
				if(dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
					dist[node.getV()] = dist[node.getU()] + node.getWeight();  // Always store distance for v'th node
				}
			}
		}
		
		// Check for one more relaxation 
		boolean flag = false;
		for(Node node: edges) {
			if(dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
				flag = true;
				System.out.println("Negative Cycle"); // Bellman ford Algo detects any negative cycle
				break;
			}
		}
		
		// If no negative cycle then dist[] array will represent the distance of each node from given src node
		if(flag == false) {
			for(int i=0; i<N ; i++) {
				System.out.println(i+" "+ dist[i]);
			}
		}
		
	}
	
	public static void main(String args[])
    {
        int n = 6;
        ArrayList<Node> adj = new ArrayList<Node>();
		
			
		adj.add(new Node(3, 2, 6));
		adj.add(new Node(5, 3, 1));
		adj.add(new Node(0, 1, 5));
		adj.add(new Node(1, 5, -3));
		adj.add(new Node(1, 2, -2));
		adj.add(new Node(3, 4, -2));
		adj.add(new Node(2, 4, 3));
		
		bellmanFord(adj, n, 0);
		/* 
		 * Output :
			0 0
			1 5
			2 3
			3 3
			4 1
			5 2
		 */
    }
	
}

/*
	The Bellman–Ford algorithm is an algorithm that computes shortest paths from a single source vertex 
	to all of the other vertices in a weighted digraph
	
	It is slower than Dijkstra's algorithm for the same problem, but more versatile, 
	as it is capable of handling graphs in which some of the edge weights are negative numbers.
	
	 If a graph contains a "negative cycle" (i.e. a cycle whose edges sum to a negative value) 
	 that is reachable from the source, then there is no cheapest path: 
	 	any path that has a point on the negative cycle can be made cheaper 
	 	by one more walk around the negative cycle. 
	 In such a case, the Bellman–Ford algorithm can detect and report the negative cycle
	 
*/