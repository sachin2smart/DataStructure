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
}
