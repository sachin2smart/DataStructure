package in.sachinshinde.graph.union_find;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/number-of-provinces/

public class NumOfProviences {

	public int findCircleNum(int[][] M) {
		
        if (M.length == 0 || M[0].length == 0) 
        	return 0;
        
        int row = M.length, column = M[0].length;
        
        UnionFind uf = new UnionFind(row);
        Set<Integer> set = new HashSet<Integer>();
        
        for (int i = 0; i < row; i++){
            for(int j = i + 1; j < column; j ++){
                if (M[i][j] == 1){
                    uf.unite(i,j);
                }
            }
        }
        
        for(int i=0; i<row; i++){
            uf.f[i] = uf.find(i);
            set.add(uf.f[i]);
        }
        
        return set.size();
    }
	
}
