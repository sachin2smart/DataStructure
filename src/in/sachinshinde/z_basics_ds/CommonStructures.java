package in.sachinshinde.z_basics_ds;

import java.util.LinkedList;
import java.util.Queue;

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
	public void bfs(Node root) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(q.isEmpty()) {
			int n = q.size();
			for(int i=0; i<n; i++) {
				Node currNode = q.poll();
				if(currNode.left != null)
					q.offer(currNode.left);
				if(currNode.right != null)
					q.offer(currNode.right);
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


}
