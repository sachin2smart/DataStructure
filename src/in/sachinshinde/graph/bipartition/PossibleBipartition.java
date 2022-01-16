package in.sachinshinde.graph.bipartition;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/possible-bipartition/

/*
 *	Possible Bipartition
 *	
 *	We want to split a group of n people (labeled from 1 to n) into two groups of any size. 
 *	Each person may dislike some other people, and they should not go into the same group.
 *	
 *	Given the integer n and the array dislikes 
 *		where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi
 *	return true if it is possible to split everyone into two groups in this way. 
 */


// Approach : DFS

public class PossibleBipartition {

	private static boolean isBipartite(int n, int[][] dislikes) {
	    List<Integer>[] graph = new ArrayList[n + 1];  
	    
	    for (int i = 1; i <= n; ++i) 
	    	graph[i] = new ArrayList<>();        
	    
	    for (int[] dislike : dislikes) {
	        graph[dislike[0]].add(dislike[1]);
	        graph[dislike[1]].add(dislike[0]);
	    }
	    
	    Integer[] color = new Integer[n + 1];
	    
	    for (int currNode = 1; currNode <= n; ++currNode)
	        if (color[currNode] == null && !isColoringValid(graph, color, currNode, 1)) 
	        	return false;
	    
	    return true;   
	}

	private static boolean isColoringValid(List<Integer>[] graph, Integer[] color, int currNode, int currColor) {
	    color[currNode] = currColor;
	    
	    for (Integer adjNode : graph[currNode]) {
	        if (color[adjNode] == null && !isColoringValid(graph, color, adjNode, currColor * -1)) { 
	            return false;     
	        }
	        else if (color[adjNode] == currColor) {
	            return false;                                     
	        }
	    }
	    
	    return true;        
	}
	
	public static void main(String[] args) {
		int[][] dislikes =new int[][]{{1,2},{1,3},{2,3}};
		if(isBipartite(4, dislikes))
			System.out.println("Bipartite");
		else
			System.out.println("Not Bipartite"); // result
		
		dislikes =new int[][]{{1,2},{1,3},{2,4}};
		if(isBipartite(4, dislikes))
			System.out.println("Bipartite"); // result
		else
			System.out.println("Not Bipartite"); 
	}
}
