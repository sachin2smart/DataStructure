package in.sachinshinde.heap_priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

//  https://leetcode.com/problems/swim-in-rising-water

/*
        You are given an n x n integer matrix grid where
            each value grid[i][j] represents the elevation at that point (i, j).

        The rain starts to fall. At time t, the depth of the water everywhere is t.
        You can swim from a square to another 4-directionally adjacent square if and only if
            the elevation of both squares individually are at most t.
        You can swim infinite distances in zero time.
        Of course, you must stay within the boundaries of the grid during your swim.

        Return the least time until you can reach the bottom right square (n - 1, n - 1)
            if you start at the top left square (0, 0).

        Example 1:
        ---------
        Input: grid = [[0,2],[1,3]]
        Output: 3
        Explanation:
        At time 0, you are in grid location (0, 0).
        You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
        You cannot reach point (1, 1) until time 3.
        When the depth of water is 3, we can swim anywhere inside the grid.

        Example 2:
        ---------
        Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
        Output: 16
        Explanation: The final route is shown.
        We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

        Constraints:
        -----------
            n == grid.length
            n == grid[i].length
            1 <= n <= 50
            0 <= grid[i][j] < n2
            Each value grid[i][j] is unique.
 */

public class SwimInRisingWater {

    //  Approach 1  :   Priority Queue (Similar to Dijkstra's algorithm)

    private static final int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        pq.offer(new int[] {0, 0, grid[0][0]});
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            int[] a = pq.poll();
            for(int[] d: dir) {
                int x = a[0] + d[0];
                int y = a[1] + d[1];
                if(x >= 0 && x < n && y >= 0 && y < n) {
                    if (!visited[x][y]) {
                        visited[x][y] = true;
                        int t = Math.max(a[2], grid[x][y]);
                        if (x == n - 1 && y == n - 1) {
                            return t;
                        }
                        pq.offer(new int[]{x, y, t});
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SwimInRisingWater swimTime = new SwimInRisingWater();
        System.out.println(swimTime.swimInWater(new int[][]{
                {0,2},
                {1,3}}));   //  3
        System.out.println(swimTime.swimInWater(new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}}));    //  16

        System.out.println(swimTime.swimInWater2(new int[][]{
                {0,2},
                {1,3}}));   //  3
        System.out.println(swimTime.swimInWater2(new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}}));    //  16

        System.out.println(swimTime.swimInWater3(new int[][]{
                {0,2},
                {1,3}}));   //  3
        System.out.println(swimTime.swimInWater3(new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}}));    //  16
    }

    //  Approach 2  :   Binary Search

    public int swimInWater2(int[][] grid) {
        int n = grid.length;
        int l = grid[0][0];
        int r = n * n - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(dfs(grid, 0, 0, m, new boolean[n][n], n)) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean dfs(int[][] grid, int i, int j, int t, boolean[][] visited, int n) {
        if(i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || grid[i][j] > t) {
            return false;
        }
        visited[i][j] = true;
        if(i == n-1 && j == n-1) {
            return true;
        }
        return dfs(grid, i-1, j, t, visited, n) || dfs(grid, i+1, j, t, visited, n)
                || dfs(grid, i, j-1, t, visited, n) || dfs(grid, i, j+1, t, visited, n);
    }

    //  Approach 3  :   TreeSet (Similar to Dijkstra's algorithm)

    public int swimInWater3(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        TreeSet<int[]> ts = new TreeSet<>(
                (a, b) -> a[2] == b[2] ? (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]) : a[2] - b[2]);

        dist[0][0] = grid[0][0];
        ts.add(new int[] {0, 0, grid[0][0]});
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], n * n);
        }

        while(!ts.isEmpty()) {
            int[] p = ts.pollFirst();
            int i = p[0];
            int j = p[1];
            int time = p[2];
            if(i != n-1 || j != n-1) {
                for (int[] d : dir) {
                    int x = i + d[0], y = j + d[1];
                    if(x >= 0 && x < n && y >= 0 && y < n) {
                        int alt = time + Math.max(0, grid[x][y] - time);
                        if (alt < dist[x][y]) {
                            int[] key = {x, y, dist[x][y]};
                            ts.remove(key);
                            key[2] = dist[x][y] = alt;
                            ts.add(key);
                        }
                    }
                }
            }
        }
        return dist[n-1][n-1];
    }
}
