package in.sachinshinde.binarytree;

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
		Node root = new Node(10);
		root.left = new Node(8);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right = new Node(2);
		root.right.left = new Node(2);
		
		printLevelwiseSpriralTree(root);
	}
	
}
