package in.sachinshinde.graph.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//	https://leetcode.com/problems/course-schedule-ii/

/*	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 	You are given an array prerequisites where prerequisites[i] = [ai, bi] 
 		indicates that you must take course bi first if you want to take course ai.

	For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	
	Return the ordering of courses you should take to finish all courses. 
	If there are many valid answers, return any of them. 
	If it is impossible to finish all courses, return an empty array.
 */



public class CourseSchedule2 {
	/* Pseudo-Code:
		 	➔ let S be a stack of courses
			➔ function dfs(node)
			➔     for each neighbor in adjacency list of node
			➔          dfs(neighbor)
			➔     add node to S
	 */	
	
	/*
	 	Algorithm:
				1. Initialize a stack S that will contain the topologically sorted order of the courses in our graph.
				2. Construct the adjacency list using the edge pairs given in the input. 
					An important thing to note about the input for the problem is that a pair such as 
						[a, b] represents that the course b needs to be taken in order to do the course a. 
						This implies an edge of the form b ➔ a.
				3. For each of the nodes in our graph, we will run a depth first search in case that 
					node was not already visited in some other node's DFS traversal.
				4. Suppose we are executing the depth first search for a node N. 
					We will recursively traverse all of the neighbors of node N which have not been processed before.
				5. Once the processing of all the neighbors is done, we will add the node N to the stack. 
					We are making use of a stack to simulate the ordering we need. When we add the node N to the stack, 
					all the nodes that require the node N as a prerequisites (among others) will already be in the stack.
				6. Once all the nodes have been processed, we will simply return the nodes as 
					they are present in the stack from top to bottom.
	 */
	
	  static int WHITE = 1;
	  static int GRAY = 2;
	  static int BLACK = 3;
	
	  boolean isPossible;
	  Map<Integer, Integer> color;
	  Map<Integer, List<Integer>> adjList;
	  List<Integer> topologicalOrder;
	
	  private void init(int numCourses) {
	    isPossible = true;
	    color = new HashMap<Integer, Integer>();
	    adjList = new HashMap<Integer, List<Integer>>();
	    topologicalOrder = new ArrayList<Integer>();
	
	    // By default all vertices are WHITE
	    for (int i = 0; i < numCourses; i++) {
	    	color.put(i, WHITE);
	    }
	  }
	
	  private void dfs(int node) {
		  // Don't recurse further if we found a cycle already
		  if (!this.isPossible) {
			return;
		  }
	
		  // Start the recursion
		  color.put(node, GRAY);
	
		  // Traverse on neighboring vertices
		  for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<Integer>())) {
			  if (this.color.get(neighbor) == WHITE) {
				  dfs(neighbor);
			  } else if (color.get(neighbor) == GRAY) {
				 // An edge to a GRAY vertex represents a cycle
				 isPossible = false;
			  }
		  }
	
		  // Recursion ends. We mark it as black
		  color.put(node, BLACK);
		  topologicalOrder.add(node);
	  }
	
	  public int[] findOrder(int numCourses, int[][] prerequisites) {
	
	    init(numCourses);
	
	    // Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
			lst.add(dest);
			adjList.put(src, lst);
		}
	
		// If the node is unprocessed, then call dfs on it.
	    for (int i = 0; i < numCourses; i++) {
	    	if (color.get(i) == WHITE) {
	    		dfs(i);
	    	}
	    }
	
	    int[] order;
	    if (this.isPossible) {
	    	order = new int[numCourses];
	    	for (int i = 0; i < numCourses; i++) {
	    		order[i] = this.topologicalOrder.get(numCourses - i - 1);
	    	}
	    } else {
	    	order = new int[0];
	    }
	
	    return order;
	}
		

	public static void main(String[] args) {
		CourseSchedule2 courseSchedule2 = new CourseSchedule2();
		System.out.println(Arrays.toString(courseSchedule2.findOrder(2, new int[][] {{1,0}})));	//	[0,1]
		System.out.println(Arrays.toString(courseSchedule2.findOrder(2, new int[][] {{1,0},{0,1}})));	//	[]
		
		//	Method 2 (Using in-degree)
		System.out.println(Arrays.toString(courseSchedule2.findOrder(2, new int[][] {{1,0}})));	//	[0,1]
		System.out.println(Arrays.toString(courseSchedule2.findOrder(2, new int[][] {{1,0},{0,1}})));	//	[]
	}
	
	public int[] findOrder2(int numCourses, int[][] prerequisites) {
		/*
		 	Intuition:
			---------
				This approach is much easier to think about intuitively as will be clear from the following point/fact about topological ordering.

				The first node in the topological ordering will be the node that doesn't have any incoming edges. 
				Essentially, any node that has an in-degree of 0 can start the topologically sorted order. 
				If there are multiple such nodes, their relative order doesn't matter and they can appear in any order.

				The algorithm is based on this idea. 
				We first process all the nodes/course with 0 in-degree implying no prerequisite courses required. 
				If we remove all these courses from the graph, along with their outgoing edges, 
					we can find out the courses/nodes that should be processed next. 
					These would again be the nodes with 0 in-degree. 
				We can continuously do this until all the courses have been accounted for.
		*/
		
		/*
		 		Algorithm:
				---------
					1. Initialize a queue, Q to keep a track of all the nodes in the graph with 0 in-degree.
					2. Iterate over all the edges in the input and create an adjacency list and also a map of node v/s in-degree.
					3. Add all the nodes with 0 in-degree to Q.
					4. The following steps are to be done until the Q becomes empty.
							1. Pop a node from the Q. Let's call this node, N.
							2. For all the neighbors of this node, N, reduce their in-degree by 1. 
								If any of the nodes' in-degree reaches 0, add it to the Q.
							3. Add the node N to the list maintaining topologically sorted order.
							4. Continue from step 4.1.
		 */
		
		boolean isPossible = true;
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
		int[] indegree = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

	    // Create the adjacency list representation of the graph
	    for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			List<Integer> currAdjList = adjList.getOrDefault(src, 
													new ArrayList<Integer>());
			currAdjList.add(dest);
			adjList.put(src, currAdjList);
	
			// Record in-degree of each vertex
			indegree[dest] += 1;
	    }
	
	    // Add all vertices with 0 in-degree to the queue
	    Queue<Integer> q = new LinkedList<Integer>();
	    for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
	    }
	
	    int i = 0;
	    // Process until the Q becomes empty
	    while (!q.isEmpty()) {
			int node = q.remove();
			topologicalOrder[i++] = node;
	
			// Reduce the in-degree of each neighbor by 1
			if (adjList.containsKey(node)) {
				for (Integer neighbor : adjList.get(node)) {
					indegree[neighbor]--;
	
				// If in-degree of a neighbor becomes 0, add it to the Q
				if (indegree[neighbor] == 0) {
					q.add(neighbor);
				}
	        }
	    }
	  }
	
	    // Check to see if topological sort is possible or not.
	    if (i == numCourses) {
			return topologicalOrder;
	    }
	
	    return new int[0];
	}
	
	//  	Method -3 
	public int[] findOrder3(int numCourses, int[][] prerequisites) { 
	    if(numCourses == 0) 
			return null;
	    
		int indegree[] = new int[numCourses];
		int topologicalOrder[] = new int[numCourses];
		int	index = 0;
		
	    for(int i = 0; i < prerequisites.length; i++)
	        indegree[prerequisites[i][0]]++;

	    Queue<Integer> queue = new LinkedList<Integer>();
	    for(int i = 0; i < numCourses; i++) {
	        if(indegree[i] == 0) {
				topologicalOrder[index++] = i;
	            queue.offer(i);
	        }
		}

	    while(!queue.isEmpty()) {
	        int prerequisite = queue.poll();
	        for(int i = 0; i < prerequisites.length; i++)  {
	            if(prerequisites[i][1] == prerequisite) {
	                indegree[prerequisites[i][0]]--; 
	                if(indegree[prerequisites[i][0]] == 0) {
	                    topologicalOrder[index++] = prerequisites[i][0];
	                    queue.offer(prerequisites[i][0]);
	                }
	            }
	        }
	    }

	    return (index == numCourses) ? topologicalOrder : new int[0];
	}
}
