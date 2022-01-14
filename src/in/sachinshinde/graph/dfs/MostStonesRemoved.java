package in.sachinshinde.graph.dfs;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

/*
 * 	Most Stones Removed with Same Row or Column
 * 
 * 	On a 2D plane, we place n stones at some integer coordinate points. 
 * 	Each coordinate point may have at most one stone.
 * 	A stone can be removed if it shares either the same row or the same column as another stone 
 * 	that has not been removed.
 * 
 * 	Given an array stones of length n 
 * 		where stones[i] = [xi, yi] represents the location of the ith stone, 
 * 		return the largest possible number of stones that can be removed.
 */


//	Approach : DFS

public class MostStonesRemoved {
	
    public static int removeStones(int[][] stones) {
        Set<int[]> visited = new HashSet<int[]>();
        int numOfStonesToRemove = 0;
        for (int[] s1:stones){
            if (!visited.contains(s1)){
               dfs(s1, visited, stones); 
               numOfStonesToRemove++;
            }
        }
        return stones.length - numOfStonesToRemove;
    }
    
    private static void dfs(int[] s1, Set<int[]> visited, int[][] stones){
        visited.add(s1);
        for (int[] s2: stones){
            if (!visited.contains(s2)){
                if (s1[0] == s2[0] || s1[1] == s2[1])
                    dfs(s2, visited, stones);
            }
        }
    }
	
	public static void main(String[] args) {
		int[][] stones = new int[][] {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
		int maxStonesRemoved = MostStonesRemoved.removeStones(stones);
		System.out.println(maxStonesRemoved);	// 5
		
		stones = new int[][] {{0,0},{0,2},{1,1},{2,0},{2,2}};
		maxStonesRemoved = removeStones(stones);
		System.out.println(maxStonesRemoved);	// 3
		
		stones = new int[][] {{0,0}};
		maxStonesRemoved = removeStones(stones);
		System.out.println(maxStonesRemoved);	// 0
	}
}
