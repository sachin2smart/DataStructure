package in.sachinshinde.graph.floydwarshall;

//  https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

/*
       You are in a city that consists of n intersections numbered from 0 to n - 1
           with bi-directional roads between some intersections.
       The inputs are generated such that you can reach any intersection from any other intersection and
           that there is at most one road between any two intersections.

       You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that
           there is a road between intersections ui and vi that takes timei minutes to travel.
       You want to know in how many ways you can travel from intersection 0 to intersection n - 1
           in the shortest amount of time.

       Return the number of ways you can arrive at your destination in the shortest amount of time.
       Since the answer may be large, return it modulo 10^9 + 7.

       Example 1:
       -------------
           Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
           Output: 4
           Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
           The four ways to get there in 7 minutes are:
           - 0 ➝ 6
           - 0 ➝ 4 ➝ 6
           - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
           - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

       Example 2:
       -------------
           Input: n = 2, roads = [[1,0,10]]
           Output: 1
           Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.


       Constraints:
       -------------
           1 <= n <= 200
           n - 1 <= roads.length <= n * (n - 1) / 2
           roads[i].length == 3
           0 <= ui, vi <= n - 1
           1 <= timei <= 109
           ui != vi
           There is at most one road connecting any two intersections.
           You can reach any intersection from any other intersection.
*/

import java.util.*;

public class NumberOfWaysToArriveAtDestination {

  private static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    NumberOfWaysToArriveAtDestination n = new NumberOfWaysToArriveAtDestination();

    System.out.println(
        n.countPaths(
            7,
            new int[][] {
              {0, 6, 7}, {0, 1, 2}, {1, 2, 3},
              {1, 3, 3}, {6, 3, 3}, {3, 5, 1},
              {6, 5, 1}, {2, 5, 1}, {0, 4, 5},
              {4, 6, 2}
            })); //  4

    System.out.println(n.countPaths(2, new int[][] {{1, 0, 10}})); // 1

    System.out.println(
        n.countPaths2(
            7,
            new int[][] {
              {0, 6, 7}, {0, 1, 2}, {1, 2, 3},
              {1, 3, 3}, {6, 3, 3}, {3, 5, 1},
              {6, 5, 1}, {2, 5, 1}, {0, 4, 5},
              {4, 6, 2}
            })); //  4

    System.out.println(n.countPaths2(2, new int[][] {{1, 0, 10}})); // 1

    System.out.println(
        n.countPaths3(
            7,
            new int[][] {
              {0, 6, 7}, {0, 1, 2}, {1, 2, 3},
              {1, 3, 3}, {6, 3, 3}, {3, 5, 1},
              {6, 5, 1}, {2, 5, 1}, {0, 4, 5},
              {4, 6, 2}
            })); //  4

    System.out.println(n.countPaths3(2, new int[][] {{1, 0, 10}})); // 1
  }

  public int countPaths(int n, int[][] roads) {
    // Step 1: Create distance matrix
    long[][][] dp = new long[n][n][2];

    // Step 2: Initialize distances
    for (int src = 0; src < n; src++) {
      for (int dest = 0; dest < n; dest++) {
        if (src != dest) {
          dp[src][dest][0] = (long) 1e12;
          dp[src][dest][1] = 0;
        } else {
          dp[src][dest][0] = 0;
          dp[src][dest][1] = 1;
        }
      }
    }

    // Step 3: Fill direct start-end bidirectional distances
    for (int[] road : roads) {
      int startNode = road[0], endNode = road[1], travelTime = road[2];
      dp[startNode][endNode][0] = travelTime;
      dp[endNode][startNode][0] = travelTime;
      dp[startNode][endNode][1] = 1;
      dp[endNode][startNode][1] = 1;
    }

    // Step 4: Floyd–Warshall algorithm
    for (int mid = 0; mid < n; mid++) {
      for (int src = 0; src < n; src++) {
        for (int dest = 0; dest < n; dest++) {
          if (src != mid && dest != mid) {
            long newTime = dp[src][mid][0] + dp[mid][dest][0];
            if (newTime < dp[src][dest][0]) {
              dp[src][dest][0] = newTime;
              dp[src][dest][1] = (dp[src][mid][1] * dp[mid][dest][1]) % MOD;
            } else if (newTime == dp[src][dest][0]) {
              dp[src][dest][1] = (dp[src][dest][1] + dp[src][mid][1] * dp[mid][dest][1]) % MOD;
            }
          }
        }
      }
    }

    //  Step 5: Return the result
    return (int) dp[n - 1][0][1];
  }

  public int countPaths2(int n, int[][] roads) {
    // Step 1: Create distance matrix
    long[][][] dp = new long[n][n][2];

    // Step 2: Initialize distances
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i != j) {
          dp[i][j][0] = (long) 1e12;
          dp[i][j][1] = 0;
        } else {
          dp[i][j][0] = 0;
          dp[i][j][1] = 1;
        }
      }
    }

    // Step 3: Fill direct start-end bidirectional distances
    for (int[] road : roads) {
      int i = road[0], j = road[1], t = road[2];
      dp[i][j][0] = t;
      dp[j][i][0] = t;
      dp[i][j][1] = 1;
      dp[j][i][1] = 1;
    }

    // Step 4: Floyd–Warshall algorithm
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (i != k && j != k) {
            long nt = dp[i][k][0] + dp[k][j][0];
            if (nt < dp[i][j][0]) {
              dp[i][j][0] = nt;
              dp[i][j][1] = (dp[i][k][1] * dp[k][j][1]) % MOD;
            } else if (nt == dp[i][j][0]) {
              dp[i][j][1] = (dp[i][j][1] + dp[i][k][1] * dp[k][j][1]) % MOD;
            }
          }
        }
      }
    }

    //  Step 5: Return the result
    return (int) dp[n - 1][0][1];
  }

  public int countPaths3(int n, int[][] roads) {
    List<int[]>[] adj = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int[] road : roads) {
      int u = road[0];
      int v = road[1];
      int time = road[2];
      adj[u].add(new int[] {v, time});
      adj[v].add(new int[] {u, time});
    }

    long[] dist = new long[n];
    int[] ways = new int[n];
    Arrays.fill(dist, Long.MAX_VALUE);

    dist[0] = 0;
    ways[0] = 1;

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.offer(new long[] {0L, 0L}); // {distance, node}

    while (!pq.isEmpty()) {
      long[] top = pq.poll();
      long currDist = top[0];
      int currNode = (int) top[1];

      if (currDist <= dist[currNode]) {
        for (int[] neighbor : adj[currNode]) {
          int nextNode = neighbor[0];
          int edgeWeight = neighbor[1];
          long newDist = currDist + edgeWeight;

          if (newDist < dist[nextNode]) {
            dist[nextNode] = newDist;
            ways[nextNode] = ways[currNode];
            pq.offer(new long[] {newDist, nextNode});
          } else if (newDist == dist[nextNode]) {
            ways[nextNode] = (ways[nextNode] + ways[currNode]) % MOD;
          }
        }
      }
    }

    return ways[n - 1];
  }
}
