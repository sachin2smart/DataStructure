package in.sachinshinde.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	https://leetcode.com/problems/course-schedule/
/*
 	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 	You are given an array prerequisites where prerequisites[i] = [ai, bi] 
 		indicates that you must take course bi first if you want to take course ai.

	For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	Return true if you can finish all courses. Otherwise, return false.
 */

public class CourseSchedule {
	//	An array of list to create the adjacency list
	//	An integer array to keep a track of in-degree of each node
	//	A list to store records with in-degree as 0
	//		for n records there should be n/2 records with in-degree = 0
	//									& n/2 records with in-degree = 1
	//	==> (records with in-degree as 0) + (records with in-degree as 1) = n
	
	 public boolean canFinish(int numCourses, int[][] prerequisites) {
		 List<Integer>[] adj = new ArrayList[numCourses];
		 int[] inDegree = new int[numCourses];
		 List<Integer> possibleCourses = new ArrayList<Integer>();
		 
		 //	Create an adjacency list for in-degree nodes
		 for(int i=0; i<numCourses; i++) 
			 adj[i] = new ArrayList<Integer>();
		 
		 for(int[] edge : prerequisites) {
			 adj[edge[1]].add(edge[0]);
			 inDegree[edge[0]]++;
		 }
		 
		 // insert the nodes into list with in-degree = 0
		 for(int i=0; i<numCourses; i++)
			 	if(inDegree[i] == 0)
			 		possibleCourses.add(i);
		 
		// insert the nodes into list with in-degree = 1
		 for(int i=0; i<possibleCourses.size(); i++)
			 for(int j: adj[possibleCourses.get(i)])
				 if(--inDegree[j] == 0)
					 possibleCourses.add(j);
		 
		 return possibleCourses.size() == numCourses;
	 }
	 
	 public static void main(String[] args) {
		 CourseSchedule courseSchedule = new CourseSchedule();
		 int[][] prerequesites = new int[][] {{1,0}};
		 System.out.println(courseSchedule.canFinish(2, prerequesites));	// true
		 System.out.println(courseSchedule.canFinish2(2, prerequesites));	// true
		 
		 prerequesites = new int[][] {{1,0},{0,1}};
		 System.out.println(courseSchedule.canFinish(2, prerequesites));	// false
		 System.out.println(courseSchedule.canFinish2(2, prerequesites));	// false
	 }
	 
	 public boolean canFinish2(int numCourses, int[][] prerequisites) {
	    int[] indegree = new int[numCourses];
	    Queue<Integer> queue = new LinkedList<Integer>();
	    
	    for(int[] pair:prerequisites)
	        indegree[pair[1]]++;
	    
	    for(int i=0;i<indegree.length;i++)
	        if(indegree[i]==0)
	            queue.add(i);
	        
	    while(!queue.isEmpty()) {
	        numCourses--;
	        int course = queue.poll();
	        for(int[] pair:prerequisites)
	            if(pair[0]==course)
	                if(--indegree[pair[1]]==0)
	                    queue.add(pair[1]);
	    }
	    
	    return numCourses==0;
	}
}

/*
	Example 1:
	Input: numCourses = 2, prerequisites = [[1,0]]
	Output: true
	Explanation: There are a total of 2 courses to take. 
	To take course 1 you should have finished course 0. So it is possible.
	
	Example 2:
	Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
	Output: false
	Explanation: There are a total of 2 courses to take. 
	To take course 1 you should have finished course 0, and 
	to take course 0 you should also have finished course 1. 
	So it is impossible.
*/