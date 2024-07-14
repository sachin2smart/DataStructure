package in.sachinshinde.graph.dfs.visited_array;

//  https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/

/*
        You are given an m x n grid. Each cell of grid represents a street.

        The street of grid[i][j] can be:
            -   1 which means a street connecting the left cell and the right cell.
            -   2 which means a street connecting the upper cell and the lower cell.
            -   3 which means a street connecting the left cell and the lower cell.
            -   4 which means a street connecting the right cell and the lower cell.
            -   5 which means a street connecting the left cell and the upper cell.
            -   6 which means a street connecting the right cell and the upper cell.

        You will initially start at the street of the upper-left cell (0, 0).
        A valid path in the grid is a path that starts from the upper left cell (0, 0) and
            ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

        Notice that you are not allowed to change any street.
        Return true if there is a valid path in the grid or false otherwise.

        Example 1:
        ---------
        Input: grid = [[2,4,3],[6,5,2]]
        Output: true
        Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).

        Example 2:
        ---------
        Input: grid = [[1,2,1],[1,2,1]]
        Output: false
        Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)

        Example 3:
        ---------
        Input: grid = [[1,1,2]]
        Output: false
        Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).


        Constraints:
        ------------
            m == grid.length
            n == grid[i].length
            1 <= m, n <= 300
            1 <= grid[i][j] <= 6
 */

public class CheckIfThereIsAValidPathInAGrid {

    public boolean hasValidPath(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        int[][] visited=new int[m][n];
        return dfs(grid, 0, 0, m, n, visited);
    }

    private boolean dfs(int[][] grid, int i, int j, int m, int n, int[][] visited){
        if(i==m-1 && j==n-1) {
            return true;
        }

        if(i<0 || i>=m || j<0 || j>=n || visited[i][j]==1) {
            return false;
        }

        visited[i][j]=1;

        if(grid[i][j] == 1) {
            return (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6) &&
                            dfs(grid, i, j - 1, m, n, visited)) ||
                    (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5) &&
                            dfs(grid, i, j + 1, m, n, visited));
        }
        else if(grid[i][j] == 2) {
            return (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6) &&
                            dfs(grid, i + 1, j, m, n, visited)) ||
                    (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4) &&
                            dfs(grid, i - 1, j, m, n, visited));
        }
        else if(grid[i][j] == 3) {
            return (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6) &&
                            dfs(grid, i, j - 1, m, n, visited)) ||
                    (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6) &&
                            dfs(grid, i + 1, j, m, n, visited));
        }
        else if(grid[i][j] == 4) {
            return ((i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) &&
                            dfs(grid, i + 1, j, m, n, visited)) ||
                    (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5) &&
                            dfs(grid, i, j + 1, m, n, visited));
        }
        else if(grid[i][j] == 5) {
            return (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6) &&
                            dfs(grid, i, j - 1, m, n, visited)) ||
                    (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4) &&
                            dfs(grid, i - 1, j, m, n, visited));
        }
        else {
            return (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4) &&
                            dfs(grid, i - 1, j, m, n, visited)) ||
                    (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5) &&
                            dfs(grid, i, j + 1, m, n, visited));
        }
    }

    public static void main(String[] args) {
        CheckIfThereIsAValidPathInAGrid checkPath = new CheckIfThereIsAValidPathInAGrid();
        System.out.println(checkPath.hasValidPath(new int[][]{{2,4,3},{6,5,2}}));
        System.out.println(checkPath.hasValidPath(new int[][]{{1,2,1},{1,2,1}}));
        System.out.println(checkPath.hasValidPath(new int[][]{{1,1,2}}));
    }

}
