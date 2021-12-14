package in.sachinshinde.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph_DFS_BFS {
	private static int V;
	private static LinkedList<Integer> adj[];
	
	@SuppressWarnings("unchecked")
	Graph_DFS_BFS(int v){
		V = v;
		adj = new LinkedList[v];
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public static void main(String args[]) {
		Graph_DFS_BFS g = new Graph_DFS_BFS(4);
		
		g.addEdge(0, 1);
		g.addEdge(2, 0);
		
		BFS(2);
		DFS(2);
	}
	
	static void BFS(int s) {
		boolean visited [] = new boolean[V];
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		visited[s] = true;
		q.add(s);
		
		System.out.print("\n BFS: ");
		
		while(q.size() != 0) {
			s = q.poll();
			System.out.print(s + " ");
			
			Iterator<Integer> i = adj[s].listIterator();
			while(i.hasNext()) {
				int n = i.next();
				if(!visited[n]) {
					visited[n] = true;
					q.add(n);
				}
			}
		}
	}
	
	static void DFS(int v) {
		System.out.print("\n DFS: ");
		boolean visited[] = new boolean[V];
		DFSUtil(v, visited);
	}
	
	static void DFSUtil(int v, boolean visited[]) {
		visited[v] = true;
		System.out.print(v + " ");
		Iterator<Integer> i = adj[v].listIterator();
		
		while(i.hasNext()) {
			int n = i.next();
			if(!visited[n]) {
				DFSUtil(n, visited);
			}
		}
	}
}
