package in.sachinshinde.graph.bfs;

import java.util.*;

//  https://leetcode.com/problems/cut-off-trees-for-golf-event/

/*
        You are asked to cut off all the trees in a forest for a golf event.

        The forest is represented as an m x n matrix. In this matrix:
            -   0 means the cell cannot be walked through.
            -   1 represents an empty cell that can be walked through.
            -   A number greater than 1 represents a tree in a cell that can be walked through, and
                    this number is the tree's height.

        In one step, you can walk in any of the four directions: north, east, south, and west.
        If you are standing in a cell with a tree, you can choose whether to cut it off.

        You must cut off the trees in order from shortest to tallest.
        When you cut off a tree, the value at its cell becomes 1 (an empty cell).

        Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees.
        If you cannot cut off all the trees, return -1.

        Note: The input is generated such that no two trees have the same height, and
        there is at least one tree needs to be cut off.

        Example 1:
        ----------
        Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
        Output: 6

        Example 2:
        ----------
        Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
        Output: -1
        Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.

        Example 3:
        ----------
        Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
        Output: 6
        Explanation: You can follow the same path as Example 1 to cut off all the trees.
        Note that you can cut off the first tree at (0, 0) before making any steps.


        Constraints:
        ----------
            m == forest.length
            n == forest[i].length
            1 <= m, n <= 50
            0 <= forest[i][j] <= 109
            Heights of all trees are distinct.
 */

public class CutOffTreesForGolfEvent {

    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        int rows = forest.size();
        int cols = forest.get(0).size();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int height = forest.get(i).get(j);
                if(height > 1) {
                    pq.offer(new int[]{i, j, height});
                }
            }
        }

        int steps = 0;
        int sourceX = 0;
        int sourceY = 0;

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int distance = shortestPath(forest, sourceX, sourceY, current[0], current[1]);
            if(distance < 0) {
                return -1;
            }
            else {
                steps += distance;
                sourceX = current[0];
                sourceY = current[1];
            }
        }

        return steps;
    }

    private int shortestPath(List<List<Integer>> forest, int sx, int sy, int dx, int dy) {
        int rows = forest.size();
        int cols = forest.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{1, 0},{0, 1},{-1, 0},{0, -1}};

        Queue<int[]> q = new ArrayDeque();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        int moves = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                int[] current = q.poll();
                int cx = current[0];
                int cy = current[1];

                if(cx == dx && cy == dy) {
                    return moves;
                }

                for(int k = 0; k < 4; k++) {
                    int nx = cx + directions[k][0];
                    int ny = cy + directions[k][1];

                    if(nx >=0 && nx < rows && ny >=0 && ny < cols &&
                            !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
                size--;
            }
            moves++;
        }

        return -1;
    }

    public static void main(String[] args) {
        CutOffTreesForGolfEvent trees = new CutOffTreesForGolfEvent();
        System.out.println(trees.cutOffTree(Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(0,0,4),
                Arrays.asList(7,6,5))));
        System.out.println(trees.cutOffTree(Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(0,0,0),
                Arrays.asList(7,6,5))));
        System.out.println(trees.cutOffTree(Arrays.asList(
                Arrays.asList(2,3,4),
                Arrays.asList(0,0,5),
                Arrays.asList(8,7,6))));
    }

/*
    int[] DIR = {-1,0,1,0,-1};

    public int cutOffTree2(List<List<Integer>> forest) {
        List<int[]> pos = new ArrayList<>();
        int rows = forest.size();
        int cols = forest.get(0).size();
        int[][] matrix = new int[rows][cols];

        for (int r = 0; r != rows; r++) {
            List<Integer> row = forest.get(r);
            for (int c = 0; c != cols; c++) {
                int h = row.get(c);
                matrix[r][c]= h;
                if (h> 1) {
                    pos.add(new int[] {r, c, h});
                }
            }
        }

        if(matrix[0][0] == 0) {
            return -1;
        }

        if (dfs(matrix, 0, 0, rows, cols, new boolean[rows][cols]) != pos.size()) {
            return -1;
        }

        pos.sort((a, b) -> a[2] - b[2]);
        int r = 0, c = 0;
        int res = 0;

        for (int[] tree : pos) {
            res += walk(matrix, r, c, tree[0], tree[1], rows, cols);
            r = tree[0];
            c = tree[1];
        }
        return res;
    }

    private int walk(int[][] matrix, int sr, int sc, int er, int ec, final int rows, final int cols) { // BFS
        if (sr == er && sc == ec) {
            return 0;
        }
        boolean[][] vis = new boolean[rows][cols];
        Queue<int[]> q = new ArrayDeque<>();
        vis[sr][sc] = true;
        q.offer(new int[]{sr, sc});
        int step = 1;
        int[] now = q.poll();
        for (int i = 0; i != 4 && !q.isEmpty(); i++) {
            int nr = now[0] + DIR[i];
            int nc = now[1] + DIR[i + 1];
            if (nr == -1 || nr == rows || nc == -1 || nc == cols || matrix[nr][nc] == 0 || vis[nr][nc])
                continue;
            vis[nr][nc] = true;
            if (nr == er && nc == ec)
                return step;
            q.offer(new int[]{nr, nc});
            step++;
        }
        return -1;
    }

    private int dfs(int[][] matrix, int r, int c, final int R, final int C, boolean[][] vis) {
        vis[r][c] = true;
        int res = matrix[r][c] == 1 ? 0 : 1;
        for (int i = 0; i != 4; i++) {
            final int nr = r + DIR[i];
            final int nc = c + DIR[i + 1];
            if (nr == -1 || nr == R || nc == -1 || nc == C || matrix[nr][nc] == 0 || vis[nr][nc])
                continue;
            res += dfs(matrix, nr, nc, R, C, vis);
        }
        return res;
    }
    */
}
