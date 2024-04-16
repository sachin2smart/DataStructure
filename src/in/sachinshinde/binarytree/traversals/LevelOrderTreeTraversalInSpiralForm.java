package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.Stack;

public class LevelOrderTreeTraversalInSpiralForm {
	
	private static void printLevelwiseSpriralTree(Node root) {
		if(root == null)
			return;
		
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		
		Node currNode;
		s1.push(root);
		
		while(!s1.isEmpty() || !s2.isEmpty()) {
			
			while(!s1.isEmpty()) {
				currNode = s1.pop();
				
				System.out.print(currNode.key + " ");
				
				if(currNode.right != null)
					s2.push(currNode.right);
				
				if(currNode.left != null)
					s2.push(currNode.left);

			}
			
			while(!s2.isEmpty()) {
				currNode = s2.pop();
				
				System.out.print(currNode.key + " ");
				
				if(currNode.left != null)
					s1.push(currNode.left);
				
				if(currNode.right != null)
					s1.push(currNode.right);
					
			}
		}

	}
	
	public static void main(String args[]) {
		/*
		        	1
				 /      \
			    2        3
			  /   \     / \
			 4     5   6   7
			/ \   / \
			8   9 10  11
		      /     \
		     12     13
		     
		Answer: 1 2 3 7 6 5 4 8 9 10 11 13 12 
	    
		 */
		
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(11);
		root.left.right.left.left = new Node(12);
		root.left.right.right.right = new Node(13);
		
		printLevelwiseSpriralTree(root);
	}
	
}
