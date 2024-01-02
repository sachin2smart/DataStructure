package in.sachinshinde.graph.topologicalsort;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

// Topological sort for directed acyclic graph using DFS

public class TopoSortByDFS {
	private static int V;
	private static LinkedList<Integer> adj[];
	
	@SuppressWarnings("unchecked")
	TopoSortByDFS(int v){
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
		TopoSortByDFS g = new TopoSortByDFS(4);
		
		g.addEdge(0, 1);
		g.addEdge(2, 0);
		
		g.topologcalSort();
	}
	
	void topologcalSort() {
		boolean[] visited = new boolean[V];
		Stack<Integer> st = new Stack<>();
		
		for(int i=0; i<V; i++) {
			if(!visited[i]) {
				topoSortUtil(i, visited, st);
			}
		}
		
		while(st.empty() == false) {
			System.out.print(st.pop() + " ");
		}
	}
	
	void topoSortUtil(int u, boolean[] visited, Stack<Integer> st) {
		visited[u] = true;
		int i;
		
		Iterator<Integer> it = adj[u].listIterator();
		while(it.hasNext()) {
			i = it.next();
			if(!visited[i]) {
				topoSortUtil(i, visited, st);
			}
		}
		st.push(u);
	}
}

// output : 3 2 0 1 
