package in.sachinshinde.graph.floydwarshall;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule4 {
  public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
    boolean[][] connected = new boolean[numCourses][numCourses];

    for (int[] p : prerequisites) {
      connected[p[0]][p[1]] = true; // p[0] -> p[1]
    }

    for (int k = 0; k < numCourses; k++) {
      for (int i = 0; i < numCourses; i++) {
        for (int j = 0; j < numCourses; j++) {
          connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
        }
      }
    }

    List<Boolean> ans = new ArrayList<>();
    for (int[] q : queries) {
      ans.add(connected[q[0]][q[1]]);
    }

    return ans;
  }

  public List<Boolean> checkIfPrerequisite2(int n, int[][] edges, int[][] queries) {
    boolean[][] m = new boolean[n][n];
    for (int[] edge : edges) {
      m[edge[0]][edge[1]] = true;
    }

    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          m[i][j] = m[i][j] || (m[i][k] && m[k][j]);
        }
      }
    }

    List<Boolean> ans = new ArrayList<>();
    for (int[] q : queries) {
      ans.add(m[q[0]][q[1]]);
    }

    return ans;
  }
}
