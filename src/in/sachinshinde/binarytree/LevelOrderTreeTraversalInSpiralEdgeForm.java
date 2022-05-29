package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LevelOrderTreeTraversalInSpiralEdgeForm {

	
	private static void printLevelwiseSpriralTree(Node root) {
		if(root == null)
			return;
		
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		
		Node currNode;
		s1.push(root);
		
		boolean evenLevel = true;
		while(!s1.isEmpty() || !s2.isEmpty()) {
			
			List<Integer> l1 = new ArrayList<Integer>();
			List<Integer> l2 = new ArrayList<Integer>();
			
			while(!s1.isEmpty()) {
				currNode = s1.pop();
				l1.add(currNode.key);
				
				if(currNode.right != null)
					s2.push(currNode.right);
				
				if(currNode.left != null)
					s2.push(currNode.left);

			}
			
			while(!s2.isEmpty()) {
				currNode = s2.pop();
				l2.add(currNode.key);
				
				if(currNode.left != null)
					s1.push(currNode.left);
				
				if(currNode.right != null)
					s1.push(currNode.right);
					
			}
			
			if(evenLevel) {
				if(l1.size()>=1)
					System.out.print(" "+l1.get(0));
				if(l1.size()>=1  && l1.get(0) != l1.get(l1.size()-1))
					System.out.print(" "+l1.get(l1.size()-1));
				if(l2.size()>=1)
					System.out.print(" "+l2.get(0));
				if(l2.size()>=1  && l2.get(0) != l2.get(l2.size()-1))
					System.out.print(" "+l2.get(l2.size()-1));
			}
			else {
				if(l1.size()>=1)
					System.out.print(" "+l1.get(l1.size()-1));
				if(l1.size()>=1 && l1.get(0) != l1.get(l1.size()-1))
					System.out.print(" "+l1.get(0));
				if(l2.size()>=1 && l2.get(0) != l2.get(l2.size()-1))
					System.out.print(" "+l2.get(0));
				if(l2.size()>1)
					System.out.print(" "+l2.get(l2.size()-1));
			}
			
			evenLevel = !evenLevel;
		}

	}
	
	public static void main(String args[]) {
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
//		root.left.right.left.left = new Node(12);
//		root.left.right.right.right = new Node(13);
		
		printLevelwiseSpriralTree(root);
	}
}
