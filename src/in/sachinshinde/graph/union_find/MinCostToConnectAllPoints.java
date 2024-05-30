package in.sachinshinde.graph.union_find;

import java.util.PriorityQueue;


//  https://leetcode.com/problems/min-cost-to-connect-all-points/

/*
        You are given an array points representing integer coordinates of some points on a 2D-plane,
            where points[i] = [xi, yi].

        The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them:
            |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

        Return the minimum cost to make all points connected.
        All points are connected if there is exactly one simple path between any two points.



        Example 1:
        ---------
        Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
        Output: 20
        Explanation:

        We can connect the points as shown above to get the minimum cost of 20.
        Notice that there is a unique path between every pair of points.

        Example 2:
        ---------
        Input: points = [[3,12],[-2,5],[-4,1]]
        Output: 18


        Constraints:
        ---------
            1 <= points.length <= 1000
            -106 <= xi, yi <= 106
            All pairs (xi, yi) are distinct.

 */

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int [] {0, 0, 0});

        int n = points.length;
        boolean [] visited = new boolean [n];
        int cost = 0;
        int edges = 0;

        while (!pq.isEmpty() || edges < n - 1) {
            int [] current = pq.remove();
            if (!visited[current[1]]) {
                visited[current[1]] = true;
                cost += current[2];
                edges++;
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        pq.add(new int[]{current[1], j,
                                Math.abs(points[current[1]][0] - points[j][0]) + Math.abs(points[current[1]][1] - points[j][1])});
                    }
                }
            }
        }
        return cost;
    }

}
