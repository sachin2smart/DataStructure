package in.sachinshinde.graph.union_find;

public class UnionFind{
    
	int[] f;
    
    public UnionFind(int size){
        f = new int[size];
        for(int i = 0; i < size; i++){
            f[i] = i;
        }
    }
    
    public int find(int x){
        if (f[x] != x){
            f[x] = find(f[x]);
        }
        return f[x];
    }
    
    public void unite(int x, int y){
        int fx = find(x);
        int fy = find(y);
        f[f[y]] = fx;
    }
    
}
