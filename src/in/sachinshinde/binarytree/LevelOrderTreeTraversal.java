package in.sachinshinde.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTreeTraversal {
	
	public static void printNodeLevelwise(Node root) {
		
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(root);
		while(!queue.isEmpty()) {
	
			Node currNode = queue.poll();
			System.out.print(currNode.key);
			
			if(currNode.left!=null ) 
				queue.add(currNode.left);
					
			if(currNode.right!=null)
				queue.add(currNode.right);
				
		}
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		printNodeLevelwise(root);
		
	}

}
