package in.sachinshinde.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//	https://leetcode.com/problems/minimum-height-trees/

/*
 	A tree is an undirected graph in which any two vertices are connected by exactly one path. 
 	In other words, any connected graph without simple cycles is a tree.

	Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges 
		where edges[i] = [ai, bi] indicates that 
			there is an undirected edge between the two nodes ai and bi in the tree, 
			you can choose any node of the tree as the root. 
		When you select a node x as the root, the result tree has height h. 
		Among all possible rooted trees, those with minimum height (i.e. min(h)) are called 
			"minimum height trees (MHTs)"
	
	Return a list of all MHTs' root labels. You can return the answer in any order.
	
	The height of a rooted tree is the number of edges on the longest downward path 
	between the root and a leaf.
 */

public class MinimumHeightTrees {

	public static List<Integer> getMinHeightTrees(int[][] edges, int n) {
		List<Integer> res = new ArrayList<>();
		
		if(n<=0)
			return res;
		
		if(n==1) {
			res.add(0);
			return res;
		}
		
		int[] degree = new int[n];
		List<List<Integer>> adj = new ArrayList<>();
		
		for(int i=0; i<n; i++)
			adj.add(new ArrayList<>());
		
		for(int[] e : edges) {
			degree[e[0]]++;
			degree[e[1]]++;
			adj.get(e[0]).add(e[1]);
			adj.get(e[1]).add(e[0]);
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0; i<n; i++)
			if(degree[i] == 1)
				q.add(i);
		
		while(n > 2) {
			int size = q.size();
			n = n-size;
			
			while(size-- > 0) {
				int v = q.poll();
				for(int i: adj.get(v)) {
					degree[i]--;
					if(degree[i] ==1)
						q.add(i);
				}
			}
		}
		
		res.addAll(q);
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(getMinHeightTrees(new int[][] {{1,0},{1,2},{1,3}}, 4));
		System.out.println(getMinHeightTrees(new int[][] {{3,0},{3,1},{3,2},{3,4},{5,4}}, 6));
		
		System.out.println();
		
		System.out.println(getMinHeightTrees2(new int[][] {{1,0},{1,2},{1,3}}, 4));
		System.out.println(getMinHeightTrees2(new int[][] {{3,0},{3,1},{3,2},{3,4},{5,4}}, 6));
	}
	
	public static List<Integer> getMinHeightTrees2(int[][] edges, int n) {
		
		if(n <= 0)	// base condition -1
			return new ArrayList<>();
		
	    if(n == 1) //	base condition - 2
	    	return Collections.singletonList(0);

	    //	Consider tree as a non-cyclic graph & create the adjacency matrix
	    List<Set<Integer>> adj = new ArrayList<>(n);
	    
	    for(int i = 0; i < n; ++i) 
	    	adj.add(new HashSet<>());
	    
	    for(int[] edge : edges) {
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    //	Determine the leaves of the tree
	    List<Integer> currLeaves = new ArrayList<>();
	    
	    for(int i = 0; i < n; ++i)
	        if (adj.get(i).size() == 1) 
	        	currLeaves.add(i);

	    while(n > 2) {
	    	//	Remove all current leaves
	        n -= currLeaves.size();
	        
	        //	After removing the current leaves, 
	        //		we will be going to have a new set of leaves
	        List<Integer> newLeaves = new ArrayList<>();
	        
	        //	for each leaves
	        for(int child : currLeaves) {
	        	//	since we have used the set, we can use the iterator to get the next element
	            int parent = adj.get(child).iterator().next();	
	            //	IMP Step: get adjacency list of parent node & remove leaf node i.e. child node 
	            adj.get(parent).remove(child);
	            if (adj.get(parent).size() == 1) 
	            	newLeaves.add(parent);
	        }
	        currLeaves = newLeaves;
	    }
	    
	    return currLeaves;
	}
}
