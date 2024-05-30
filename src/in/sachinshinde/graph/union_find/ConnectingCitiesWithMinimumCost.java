package in.sachinshinde.graph.union_find;

//  https://leetcode.ca/all/1135.html

import java.util.*;

/*
        There are N cities numbered from 1 to N.

        You are given connections,
            where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
            (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

        Return the minimum cost so that for every pair of cities,
            there exists a path of connections (possibly of length 1) that connects those two cities together.

        The cost is the sum of the connection costs used.
        If the task is impossible, return -1.



        Example 1:
        ---------
        Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
        Output: 6
        Explanation:
        Choosing any 2 edges will connect all cities so we choose the minimum 2.

        Example 2:
        ---------
        Input: N = 4, connections = [[1,2,3],[3,4,4]]
        Output: -1
        Explanation:
        There is no way to connect all cities even if all edges are used.

        Note:
        ----
            1 <= N <= 10000
            1 <= connections.length <= 10000
            1 <= connections[i][0], connections[i][1] <= N
            0 <= connections[i][2] <= 10^5
            connections[i][0] != connections[i][1]
*/

public class ConnectingCitiesWithMinimumCost {

    // Using Kruskal's Algorithm
    public int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a,b) -> a[2] - b[2]);

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int cost = 0;
        int numPaths = 0;

        for(int[] connection: connections) {
            if(union(parent, rank, connection[0], connection[1])) {
                cost += connection[2];
                numPaths++;
            }
        }

        return numPaths == n - 1 ? cost : -1;
    }

    private boolean union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if(rootX == rootY) {
            return false;
        }

        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        }
        else if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        }
        else {
            parent[rootY] = rootX;
            ++rank[rootX];
        }

        return true;
    }

    private int find(int[] parent, int x) {
        if(parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) {
        ConnectingCitiesWithMinimumCost cities = new ConnectingCitiesWithMinimumCost();
        System.out.println(cities.minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}}));    // 6
        System.out.println(cities.minimumCost(4, new int[][]{{1,2,3},{3,4,5}}));    // -1

        System.out.println(cities.minimumCost2(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}}));    // 6
        System.out.println(cities.minimumCost2(4, new int[][]{{1,2,3},{3,4,5}}));    // 8
        // above gives wrong answer
    }

    //  Solution 2
    int[] parent;
    public int minimumCost2(int n, int[][] connections) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        for(int[] connection: connections) {
            pq.offer(connection);
        }

        parent = new int[n + 1];
        for(int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        Set<Integer> visited = new HashSet<>();
        int minCost = 0;

        while(!pq.isEmpty()) {
            int[] connection = pq.poll();

            int x = connection[0];
            int y = connection[1];
            int cost = connection[2];

            if(find(x) != find(y)) {
                union(x, y);
                visited.add(x);
                visited.add(y);
                minCost += cost;
            }
        }

        return visited.size() < n ? -1 : minCost;
    }

    private int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        parent[find(x)] = parent[find(y)];
    }
}