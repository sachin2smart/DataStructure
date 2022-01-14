package in.sachinshinde.graph.union_find;

public class UnionFind3 {

    int count; // # of connected components (or islands) 
    int[] parent;
    int[] rank;
	
    public UnionFind3(int[][] stones) { 
		count = 0;
		int m = stones.length;
		parent = new int[m];
		rank = new int[m];
		
		for (int i = 0; i < m; ++i) {
			parent[i] = i;	// set each node to be it's own parent 
			count++;
			rank[i] = 1;	// initial rank to be 1
		}
    }

	// find with path compression 
	public int find(int i) { 
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
        
		return parent[i];
    }

    public void union(int x, int y) { // union with rank
      int rootx = find(x);
      int rooty = find(y);
      
      if (rootx != rooty) {
          if (rank[rootx] > rank[rooty]) {	//** why rank of rootx only being used ?
              parent[rooty] = rootx;
          } else if (rank[rootx] < rank[rooty])  {
              parent[rootx] = rooty;
          } else {
              parent[rooty] = rootx; 
              rank[rootx] += 1;
          }
          count--;
      }
    }

    public int getCount() {
      return count;
    }
    
}
