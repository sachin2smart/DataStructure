package in.sachinshinde.graph.dfs.visited_array;

import in.sachinshinde.graph.union_find.MaxStonesRemoved;

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
        int stonesRemoved = 0;

        for (int[] currStone: stones){
            if (!visited.contains(currStone)){
               dfs(currStone, visited, stones);
                stonesRemoved++;
            }
        }
        return stones.length - stonesRemoved;
    }
    
    private static void dfs(int[] currStone, Set<int[]> visited, int[][] stones) {
        visited.add(currStone);
        for (int[] stone: stones) {
            if (!visited.contains(stone)) {
                if (currStone[0] == stone[0] || currStone[1] == stone[1]) {
                    dfs(stone, visited, stones);
                }
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

        //
        MostStonesRemoved maxStones = new MostStonesRemoved();
        stones = new int[][] {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        maxStonesRemoved = maxStones.removeStones2(stones);
        System.out.println(maxStonesRemoved);	// 5

        stones = new int[][] {{0,0},{0,2},{1,1},{2,0},{2,2}};
        maxStonesRemoved = maxStones.removeStones2(stones);
        System.out.println(maxStonesRemoved);	// 3

        stones = new int[][] {{0,0}};
        maxStonesRemoved = maxStones.removeStones2(stones);
        System.out.println(maxStonesRemoved);	// 0
	}

    public int removeStones2(int[][] stones) {
        boolean[] visited = new boolean[stones.length];
        int remainingStones = stones.length;
        for(int i=0; i<stones.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i, visited, stones);
                remainingStones--;
            }
        }
        return remainingStones;
    }
    public void dfs(int curri, boolean[] visited, int[][] stones){
        for(int i=0; i<stones.length; i++) {
            if(!visited[i] && (stones[i][0] == stones[curri][0] || stones[i][1] == stones[curri][1])) {
                visited[i] = true;
                dfs(i, visited, stones);
            }
        }
    }
}