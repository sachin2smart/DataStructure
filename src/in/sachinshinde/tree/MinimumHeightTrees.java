package in.sachinshinde.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
		
		if(n<=0)
			return new ArrayList<>();
		
	    if(n==1) 
	    	return Collections.singletonList(0);

	    List<Set<Integer>> adj = new ArrayList<>(n);
	    
	    for(int i = 0; i < n; ++i) 
	    	adj.add(new HashSet<>());
	    
	    for(int[] edge : edges) {
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    List<Integer> leaves = new ArrayList<>();
	    
	    for(int i = 0; i < n; ++i)
	        if (adj.get(i).size() == 1) 
	        	leaves.add(i);

	    while (n > 2) {
	        n -= leaves.size();
	        List<Integer> newLeaves = new ArrayList<>();
	        for (int i : leaves) {
	            int j = adj.get(i).iterator().next();	//	IMP Step: get the index node to remove leaf node 
	            adj.get(j).remove(i);
	            if (adj.get(j).size() == 1) 
	            	newLeaves.add(j);
	        }
	        leaves = newLeaves;
	    }
	    
	    return leaves;
	}
}
