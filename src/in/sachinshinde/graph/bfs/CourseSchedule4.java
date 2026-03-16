package in.sachinshinde.graph.bfs;

import java.util.*;

public class CourseSchedule4 {
  public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
    Map<Integer, List<Integer>> adj = new HashMap<>();
    for (int i = 0; i < n; i++) {
      adj.put(i, new ArrayList<>());
    }

    for (int[] p : prerequisites) {
      adj.get(p[0]).add(p[1]);
    }

    boolean[][] reach = new boolean[n][n];
    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      q.offer(i);

      while (!q.isEmpty()) {
        int nd = q.poll();
        for (int nr : adj.get(nd)) {
          if (!reach[i][nr]) {
            reach[i][nr] = true;
            q.offer(nr);
          }
        }
      }
    }

    List<Boolean> res = new ArrayList<>();
    for (int[] qr : queries) {
      res.add(reach[qr[0]][qr[1]]);
    }
    return res;
  }
}
