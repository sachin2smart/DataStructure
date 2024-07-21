package in.sachinshinde.z_basics_ds;

import java.util.*;

public class CommonStructures {

	public class Node {
		int key;
		Node left, right;
		
		public Node(int data) {
			this.key = data;
			this.left = null;
			this.right = null;
		}
	}
	
	//	1. BFS traversal on tree using queue
	public void bfs_Integer(Integer key) {
		Queue<Integer> q = new LinkedList<>();
		q.add(key);
		while(q.isEmpty()) {
			int n = q.size();
			for(int i=0; i<n; i++) {
				Integer currKey = q.poll();
			}
		}
	}

	public void bfs_IntegerArray(int[] arr) {
		Queue<int[]> q = new LinkedList<>();
		q.add(arr);
		while(q.isEmpty()) {
			int n = q.size();
			for(int i=0; i<n; i++) {
				int[] currArr = q.poll();
			}
		}
	}

	public void bfs_Node(Node root) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(q.isEmpty()) {
			int n = q.size();
			for(int i=0; i<n; i++) {
				Node currNode = q.poll();
			}
		}
	}

	
	//	2. For most substring problem, we are given a string and 
	//	need to find a substring of it which satisfy some restrictions. 
	//	A general way is to use a hashmap assisted with two pointers.

	/*
	int findSubstring(String s){
	        List<Integer> l = new ArrayList<>();
	        int counter; // check whether the substring is valid
	        int begin=0, end=0; //two pointers, one point to tail and one  head
	        int d; //the length of substring

	        for() { 
	            // initialize the hash map here 

	        while(end<s.size()){

	            if(map[s[end++]]-- ?){  // modify counter here // }

	            while(// counter condition //){ 
	                 
	                 // update d here if finding minimum//

	                //increase begin to make it invalid/valid again
	                
	                if(map[s[begin++]]++ ?){ // modify counter here // }
	            }  

	            // update d here if finding maximum
	        }
	        return d;
	  }
	  
	*/
	
	//	One thing - when asked to find maximum substring, 
	//		we should update maximum after the inner while loop 
	//		to guarantee that the substring is valid. 
	//	On the other hand, when asked to find minimum substring, 
	//		we should update minimum inside the inner while loop.



	//	3. Backtracking problems
	/*
			A general algorithm for finding all (or some) solutions to some computational problems,
			 that incrementally builds candidates to the solutions.

			 As soon as it determines that a candidate cannot possibly lead to a valid complete solution,
			 it abandons this partial candidate and “backtracks’’ (return to the upper level) and
			  reset to the upper level’s state so that the search process can continue to explore the next branch.

			  Backtracking is all about choices and consequences
	 */
	/*
			Characteristics:
				[1] No Repetition
				[2] Search Pruning
	 */

	public List<Integer> getSomething() {
		List<Integer> res = new ArrayList<>();
		backtrack(res, 0, 0);
		return res;
	}

	public void backtrack(List<Integer> res, int i, int j) {
		if(true) {	// this is the termination condition
			return;
		}

		if(true) {	// take or not take condition
			backtrack(res, i+1, j);
		}

		if(true) {	// take or not take condition
			backtrack(res, i, j+1);
		}
	}

	//	4.	Prefix Sum

	public void prefixSum() {
		int[] nums = new int[]{1, 2, 3, 4};
		int n = nums.length;
		int[] pSum;

		//	representations 1 : [0, 1, 3, 6, 10]
		pSum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			pSum[i + 1] = pSum[i] + nums[i];
		}
		System.out.println(Arrays.toString(pSum));

		//	representations 2 : [0, 1, 3, 6, 10]
		pSum = new int[n + 1];
		for (int i = 1; i < pSum.length; i++) {
			pSum[i] = pSum[i - 1] + nums[i - 1];
		}
		System.out.println(Arrays.toString(pSum));

		//	representations 3 : [1, 3, 6, 10]
		pSum = new int[n];
		pSum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			pSum[i] = pSum[i - 1] + nums[i];
		}
		System.out.println(Arrays.toString(pSum));
	}


	public static void main(String[] args) {
		CommonStructures cs = new CommonStructures();
		cs.prefixSum();
	}
}
