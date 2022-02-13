package in.sachinshinde.graph.union_find;

//https://leetcode.ca/all/323.html

/*
* 	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
* 	write a function to find the number of connected components in an undirected graph.
*/

public class NumberOfConnectedComponents {

	int[] map;

	public int countComponents(int[][] edges, int n){
		buildMap(edges, n);
		
		int count = n;

		for(int[] e: edges) {
			int parent1 = findParent(e[0]);
			int parent2 = findParent(e[1]);
			if(parent1 != parent2) {
				count --;
				union(parent1, parent2);
			}
		}
		return count;
	}

	private int findParent(int child) {
		while(map[child] != child) {
			child = map[child];
		}
		return child;
	}

	private void union(int child, int parent) {
		map[child] = parent;
	}

	private void buildMap(int[][] edges, int n) {
		map = new int[n];
		for(int[] e: edges) {
			map[e[0]] = e[0];
			map[e[1]] = e[1]; 
		}
	}
	
	public static void main(String[] args) {
		int[][] edges = new int[][] {{0, 1}, {1, 2}, {3, 4} };
		NumberOfConnectedComponents components = new NumberOfConnectedComponents();
		System.out.println(components.countComponents(edges, 5));		// 2
		System.out.println(components.countComponents(5, edges));		// 2
		
		edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4} };
		System.out.println(components.countComponents(edges, 5));		// 1
		System.out.println(components.countComponents(5, edges));		// 1
	}
	
	// without using a map[]
	
	 public int countComponents(int n, int[][] edges) {
        int[] root = new int[n];
        
        for (int i = 0; i < n; i ++)
            root[i] = i;
        
        int count = n;
        
        for (int i = 0; i < edges.length; i ++) {
            int r1 = getRoot(root, edges[i][0]);
            int r2 = getRoot(root, edges[i][1]);
            if (r1 != r2) {
                root[r1] = r2;
                count --;
            }
        }
        return count;
    }
	 
    private int getRoot(int[] root, int i) {
        while (root[i] != i) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
}

/*

	Example 1:
----------
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2


Example 2:
----------
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output: 1

*/