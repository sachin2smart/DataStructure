package in.sachinshinde.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

//Check if cycle exists in an directed graph using DFS

public class DirectedGraphDetectCycleByDFS {
	private static int V;
	private static LinkedList<Integer> adj[];
	
	@SuppressWarnings("unchecked")
	DirectedGraphDetectCycleByDFS(int v){
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
		DirectedGraphDetectCycleByDFS g = new DirectedGraphDetectCycleByDFS(4);
		
		g.addEdge(0, 1);
		g.addEdge(2, 0);
		g.addEdge(2, 1);
		g.addEdge(1, 2);
		
		if(g.isCyclic()) {
			System.out.println("Cycle exists");
		}
		else {
			System.out.println("Cycle does not exists");
		}
	}
	
	boolean isCyclic() {
		boolean visited[] = new boolean[V];
		Arrays.fill(visited, false);
		
		boolean visitedAgain[] = new boolean[V];
		Arrays.fill(visitedAgain, false);
		
		for(int i=0; i<V; i++) {
			if(!visited[i] && isCyclicUtil(i, visited, visitedAgain)) {
				return true;
			}
		}
		
		return false;
	}
	
	boolean isCyclicUtil(int u, boolean[] visited, boolean[] visitedAgain) {
		
		if(visitedAgain[u]) {
			return true;
		}
		
		if(visited[u]) {
			return false;
		}
		
		visited[u] = true;
		visitedAgain[u] = true;
		
		Iterator<Integer> it = adj[u].listIterator();
		while(it.hasNext()) {
			int v = it.next();
			if(isCyclicUtil(v, visited, visitedAgain)) {
				return true;
			}
		}
		visitedAgain[u] = false;
		
		return false;
	}
}
