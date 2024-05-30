package in.sachinshinde.graph.dfs;

public class MColoringProblem {
    private boolean isSafe(int node,int currColor,boolean graph[][], int color[]) {
        for(int i = 0; i < graph.length; i++) {
            if(graph[node][i] && color[i] == currColor) {
                return false;
            }
        }
        return true;
    }

    private boolean canColor(boolean graph[][], int node, int color[], int m, int n) {
        if(node == n) {
            return true;
        }
        for(int i = 1; i <= m; i++) {
            if(isSafe(node, i, graph, color)) {
                color[node] = i;
                if(canColor(graph, node + 1, color, m, n)) {
                    return true;
                }
                color[node] = 0;
            }
        }
        return false;
    }

    public boolean graphColoring(boolean graph[][], int m, int n) {
        return canColor(graph, 0, new int[n], m, n);
    }

    public static void main(String[] args) {
        MColoringProblem mColoringProblem = new MColoringProblem();
        boolean[][] graph = new boolean[][] {
                {false,true,true,false},
                {false,false,true,false},
                {false,false,false,true},
                {true,false,false,false}
        };
        System.out.println(mColoringProblem.graphColoring(graph, 3, 4)); // true

        graph = new boolean[][]{
                {false,true,true},
                {false,false,true},
                {false,false,false}
        };
        System.out.println(mColoringProblem.graphColoring(graph, 2, 3)); // false
    }
}