package in.sachinshinde.graph.bfs;

//  https://leetcode.ca/all/1102.html
//  Find a path in grid with maximum score value and minimum path value


/*
        Given a matrix of integers A with R rows and C columns,
        find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

        The score of a path is the minimum value in that path.
        -   For example, the value of the path 8 →  4 →  5 →  9 is 4.

        A path moves some number of times from one visited cell to
            any neighbouring unvisited cell in one of the 4 cardinal directions :
                north,
                east,
                west,
                south



        Example 1:
        ---------
        Input: [[5,4,5],[1,2,6],[7,4,6]]
        Output: 4
        Explanation:
        The path with the maximum score is highlighted in yellow.

        Example 2:
        ---------
        Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
        Output: 2

        Example 3:
        ---------
        Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
        Output: 3

    Note:
    -----
        1 <= R, C <= 100
        0 <= A[i][j] <= 10^9
 */

import java.util.PriorityQueue;

public class PathWithMaximumMinimumValue {

    // If interviewer asked - Do not modify the given arrays
    public int maximumMinimumPath(int[][] A) {
        int r = A.length;
        int c = A[0].length;
        int min = Math.min(A[r-1][c-1], A[0][0]);

        //max heap
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x1, x2) -> x2.val - x1.val);
        int[][] visited = new int[r][c];
        int i = 0,j = 0;

        while(i != r-1 || j != c-1){
            visited[i][j] = 1;

            //up
            int rowUp = i-1;
            if(rowUp >= 0 && visited[rowUp][j] == 0){
                visited[rowUp][j] = 1;
                pq.offer(new Tuple(rowUp, j, A[rowUp][j]));
            }

            //down
            int rowDown = i+1;
            if(rowDown < r && visited[rowDown][j] == 0){
                if(rowDown == r-1 && j == c-1) {
                    return min;
                }
                visited[rowDown][j] = 1;
                pq.offer(new Tuple(rowDown, j, A[rowDown][j]));
            }

            //left
            int colLeft = j-1;
            if(colLeft >= 0 && visited[i][colLeft] == 0){
                visited[i][colLeft] = 1;
                pq.offer(new Tuple(i, colLeft, A[i][colLeft]));
            }

            //right
            int colRight = j+1;
            if(colRight < c && visited[i][colRight] == 0) {
                if(i == r-1 && colRight == c-1) {
                    return min;
                }
                visited[i][colRight] = 1;
                pq.offer(new Tuple(i, colRight, A[i][colRight]));
            }

            // pick next step
            Tuple next = pq.poll();
            if(next.val < min) {
                min = next.val;
            }
            i = next.r;
            j = next.c;
        }

        return min;

    }

    private class Tuple{
        private int r;
        private int c;
        private int val;

        public Tuple(int r , int c, int val){
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        PathWithMaximumMinimumValue minPath = new PathWithMaximumMinimumValue();
        System.out.println(minPath.maximumMinimumPath(new int[][]{{5,4,5},{1,2,6},{7,4,6}}));   // 4
        System.out.println(minPath.maximumMinimumPath2(new int[][]{{5,4,5},{1,2,6},{7,4,6}}));   // 4

        System.out.println(minPath.maximumMinimumPath(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}}));   // 2
        System.out.println(minPath.maximumMinimumPath2(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}}));   // 2

        System.out.println(minPath.maximumMinimumPath(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}}));   // 3
        System.out.println(minPath.maximumMinimumPath2(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}}));   // 3
    }


    // If interviewer is fine with modifying the given arrays
    public int maximumMinimumPath2(int[][] A) {
        int[][] moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x1, x2) -> x2.val - x1.val);
        int rows = A.length, cols = A[0].length;
        int min = A[0][0];
        pq.add(new Tuple(0, 0, A[0][0]));
        A[0][0] = -1;

        while (!pq.isEmpty()) {
            int size = pq.size();
            for(int i = 0; i < size ; i++) {
                Tuple currTuple = pq.poll();
                min = Math.min(min, currTuple.val);

                if(currTuple.r == rows-1 && currTuple.c == cols-1) {
                    return min;
                }

                for(int[] move: moves) {
                    int r = currTuple.r + move[0];
                    int c = currTuple.c + move[1];
                    if(r >= 0 && c >= 0 && r < rows && c < cols && A[r][c] >=0) {
                        pq.add(new Tuple(r, c , A[r][c]));
                        A[r][c] = -1;
                    }
                }
            }
        }
        return min;
    }

}

