package in.sachinshinde.graph.union_find;

//https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

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


//Approach : Union Find

public class MaxStonesRemoved {

	 public static int removeStones(int[][] stones) {
        UnionFind3 uf = new UnionFind3(stones);
        
		// check all the elements with each other and try to union them if they have same column or row 
        for(int i = 0; i < stones.length; i++){
            for(int j = 0; j < stones.length; j++){
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) { // in the same column or row
					uf.union(i, j);	// union based on index 
                }
            }
        }
         
        return stones.length - uf.getCount();
    }
	
	 public static void main(String[] args) {
		int[][] stones = new int[][] {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
		int maxStonesRemoved = removeStones(stones);
		System.out.println(maxStonesRemoved);	// 5
		
		stones = new int[][] {{0,0},{0,2},{1,1},{2,0},{2,2}};
		maxStonesRemoved = removeStones(stones);
		System.out.println(maxStonesRemoved);	// 3
		
		stones = new int[][] {{0,0}};
		maxStonesRemoved = removeStones(stones);
		System.out.println(maxStonesRemoved);	// 0
	}
}
