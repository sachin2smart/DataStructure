package in.sachinshinde.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;

// Check if cycle exists in an undirected graph using BFS

public class UndirectedGraphDetectCycleByBFS {
	private static int V;
	private static LinkedList<Integer> adj[];
	
	@SuppressWarnings("unchecked")
	UndirectedGraphDetectCycleByBFS(int v){
		V = v;
		adj = new LinkedList[v];
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public static void main(String args[]) {
		UndirectedGraphDetectCycleByBFS g = new UndirectedGraphDetectCycleByBFS(4);
		
		g.addEdge(0, 1);
		g.addEdge(2, 0);
		g.addEdge(2, 1);
	
		if(isCyclicDisconntected()) {
			System.out.println("Cycle exists");
		}
		else {
			System.out.println("Cycle does not exists");
		}
	}
	
	static boolean isCyclicDisconntected(){
		boolean visited[] = new boolean[V];
		for(int i=0; i<V; i++) {
			visited[i] = false;
		}
		
		for (int i = 0; i < V; i++) {
			if (!visited[i] && isCyclicConnected(i, visited)) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isCyclicConnected(int s, boolean visited[]) {
		int[] parent = new int[V];
		Arrays.fill(parent, -1);
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		visited[s] = true;
		q.add(s);
		
		while(q.size() != 0) {
			int u = q.poll();
			for(int i=0; i < adj[u].size(); i++) {
				int v = adj[u].get(i);
				if(!visited[v]) {
					visited[v] = true;
					q.add(v);
					parent[v] = u;
				}
				else if( v != parent[u]) {
					return true;
				}
			}
		}
		return false;
	}
}