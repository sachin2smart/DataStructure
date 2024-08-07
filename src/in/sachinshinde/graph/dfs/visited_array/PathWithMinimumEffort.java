package in.sachinshinde.graph.dfs.visited_array;

//  https://leetcode.com/problems/path-with-minimum-effort/

/*
        You are a hiker preparing for an upcoming hike.
        You are given heights, a 2D array of size rows x columns,
            where heights[row][col] represents the height of cell (row, col).
        You are situated in the top-left cell, (0, 0), and
            you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
        You can move up, down, left, or right, and
            you wish to find a route that requires the minimum effort.

        A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

        Return the minimum effort required to travel from the top-left cell to the bottom-right cell.



        Example 1:



        Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
        Output: 2
        Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
        This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
        Example 2:



        Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
        Output: 1
        Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
        Example 3:


        Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
        Output: 0
        Explanation: This route does not require any effort.


        Constraints:

        rows == heights.length
        columns == heights[i].length
        1 <= rows, columns <= 100
        1 <= heights[i][j] <= 106
 */

public class PathWithMinimumEffort {

    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = (int) 10e6;

        int m = heights.length;
        int n = heights[0].length;

        int lastHeight = heights[0][0];

        while(left < right) {
            int mid = left + (right - left) / 2;
            boolean isCurrPathValid = dfs(heights, new boolean[m][n], 0, 0, lastHeight, mid);

            if(isCurrPathValid) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean dfs(int[][] heights, boolean[][] visited, int row, int col, int lastHeight, int threshold) {
        if (row < 0 || row >= heights.length || col < 0 || col >= heights[0].length ||
                visited[row][col] || Math.abs(heights[row][col] - lastHeight) > threshold) {
            return false;
        }

        visited[row][col] = true;

        return ((row == heights.length - 1 && col == heights[0].length - 1)
                || dfs(heights, visited, row + 1, col, heights[row][col], threshold)
                || dfs(heights, visited, row - 1, col, heights[row][col], threshold)
                || dfs(heights, visited, row, col + 1, heights[row][col], threshold)
                || dfs(heights, visited, row, col - 1, heights[row][col], threshold));
    }

}
