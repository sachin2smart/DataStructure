package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/all-paths-from-source-to-target/

/*
 	Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
 	find all possible paths from node 0 to node n - 1 and return them in any order.
 	
 	Input: graph = [[1,2],[3],[3],[]]  
 					There is a directed edge from 0 to [1,2]
 					There is a directed edge from 1 to [3]
 					There is a directed edge from 2 to [3]
 					There is a directed edge from 3 to []
	Output: [[0,1,3],[0,2,3]]
	Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */

public class AllPathsFromSourceToTarget {

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> finalResult = new ArrayList<>();
		List<Integer> currResultPath = new ArrayList<>();
		int currNodeIndex = 0;
		currResultPath.add(currNodeIndex);
		dfs(graph, finalResult, currResultPath, currNodeIndex);
		return finalResult;
	}

	private void dfs(int[][] graph,  List<List<Integer>> finalResult, List<Integer> currResultPath, int currNodeIndex) {
		if(currNodeIndex == graph.length-1) {
			finalResult.add(new ArrayList<>(currResultPath));
			return;
		}
		for(int nextNodeIndex: graph[currNodeIndex]) {
			currResultPath.add(nextNodeIndex);
			dfs(graph, finalResult, currResultPath, nextNodeIndex);
			currResultPath.remove(currResultPath.size()-1);
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
