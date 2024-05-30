package in.sachinshinde.graph.dfs.visited_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/course-schedule/
/*
 	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 	You are given an array prerequisites where prerequisites[i] = [ai, bi]
 		indicates that you must take course bi first if you want to take course ai.

	For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	Return true if you can finish all courses. Otherwise, return false.
 */

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] pre : prerequisites) {
            adj[pre[0]].add(pre[1]);
        }

        // Define an array of visited (0 -> unvisited, 1 -> visited, 2 -> completed), initially filled with 0's
        int[] visited = new int[numCourses];

        for(int i=0; i<numCourses; i++) {
            if(!dfs(i, visited, adj))
                return false;
        }

        return true;
    }

    public boolean dfs(int node, int[] visited, ArrayList<Integer>[] adj) {
        // Return false if the node is visited and viewed again before completion
        if(visited[node] == 1) {
            return false;
        }

        // Return true if the node is completed processing
        if(visited[node] == 2) {
            return true;
        }

        // Mark the node as visited
        visited[node] = 1;

        // DFS of all the other nodes current "node" is connected to
        for(int n : adj[node]) {
            if(!dfs(n, visited, adj)) {
                return false;
            }
        }

        // If no more other nodes for the current "node" mark as completed and return true
        visited[node] = 2;
        return true;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        // building graph
        List<List<Integer>> list = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        // for 3 courses list = [[][][]]
        // for 2 courses list = [[][]]

        for(int[] p: prerequisites) {
            int prerequisite = p[1];
            int course = p[0];
            list.get(course).add(prerequisite);
        }
        // [[][0]]  - example 1
        // [[1][0]  - example 2

        int[] visited = new int[numCourses];
        // by default the values for visited will be 0 : [0,0]

        for(int i = 0; i < numCourses; i++) {
            // if there is a cycle, return false
            if(dfs(list, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> list, int[] visited, int cur) {
        if(visited[cur] == 1) {
            return true;
        }

        if(visited[cur] == 2) {
            return false;
        }

        visited[cur] = 1;

        for(int next: list.get(cur)) {
            if(dfs(list, visited, next)) {
                return true;
            }
        }

        visited[cur] = 2;
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        System.out.println(courseSchedule.canFinish(2, new int[][]{{1,0}})); // true
        System.out.println(courseSchedule.canFinish(2, new int[][]{{1,0},{0,1}})); // false

        System.out.println(courseSchedule.canFinish2(2, new int[][]{{1,0}})); // true
        System.out.println(courseSchedule.canFinish2(2, new int[][]{{1,0},{0,1}})); // false
    }
}
