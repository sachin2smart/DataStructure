package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/all-paths-from-source-to-target/

public class AllPathsFromSourceToTarget {

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<Integer>();
		path.add(0);
		dfs(graph, path, 0, res);
		return res;
	}

	private void dfs(int[][] graph, List<Integer> path, int node, List<List<Integer>> res) {
		if(node == graph.length-1) {
			res.add(new ArrayList(path));
			return;
		}
		for(int nextNode: graph[node]) {
			path.add(nextNode);
			dfs(graph, path, nextNode, res);
			path.remove(path.size()-1);
		}
	}
	
	public static void main(String[] args) {
		int[][] graph = new int[][] {{1,2},{3},{3},{}};
		AllPathsFromSourceToTarget paths = new AllPathsFromSourceToTarget();
		System.out.println(paths.allPathsSourceTarget(graph));	//	[[0, 1, 3], [0, 2, 3]]
		
		graph = new int[][] {{4,3,1},{3,2,4},{3},{4},{}};
		System.out.println(paths.allPathsSourceTarget(graph));	//	[[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]]
	}
}
