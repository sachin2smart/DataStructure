package in.sachinshinde.binarytree.checks_on_tree;

import in.sachinshinde.binarytree.Node;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.ca/2019-10-30-1430-Check-If-a-String-Is-a-Valid-Sequence-from-Root-to-Leaves-Path-in-a-Binary-Tree/

public class CheckIfAStringIsAValidSequenceFromRootToLeavesInBinaryTree {

	//	1. Using DFS
	private boolean isValidSequence(Node root, int[] arr) {
		if(root == null)
			return arr.length == 0;
		
		return dfs(root, arr, 0);
	}

	private boolean dfs(Node root, int[] arr, int currLen) {
		if(currLen >= arr.length) 
			return false;
		
		if(root == null || root.key != arr[currLen])
			return false;
		
		if(currLen == arr.length-1)
			return root.left == null && root.right == null;
		
		return dfs(root.left, arr, currLen+1) || 
				dfs(root.right, arr, currLen+1);
	}
	
	// 2. Using BFS
	private boolean isValidSequence2(Node root, int[] arr) {
		if(root == null || root.key != arr[0])
			return false;
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		
		int currIndex = 0;
		
		while(!queue.isEmpty() & currIndex < arr.length) {
			int qSize = queue.size();

			for(int i=0; i < qSize; i++) {
				Node n = queue.poll();
			
				if(n.key == arr[currIndex]) {
					if(n.left != null)
						queue.offer(n.left);
					
					if(n.right != null)
						queue.offer(n.right);
					
					if(n.left == null && n.right == null && 
							currIndex == arr.length-1)
						return true;
				}
			}
			currIndex++;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		CheckIfAStringIsAValidSequenceFromRootToLeavesInBinaryTree check = new
				CheckIfAStringIsAValidSequenceFromRootToLeavesInBinaryTree();
		
		Node n = new Node(0);
		n.left = new Node(1);
		n.left.left = new Node(0);
		n.left.left.right = new Node(1);
		n.left.right = new Node(1);
		n.left.right.left = new Node(0);
		n.left.right.right = new Node(0);
		n.right = new Node(0);
		n.right.left = new Node(0);
		
		System.out.println(check.isValidSequence(n, new int[] {0,1,0,1})); 		//	true
		System.out.println(check.isValidSequence(n, new int[] {0,0,1})); 		//	false
		System.out.println(check.isValidSequence(n, new int[] {0,1,1})); 		//	false
		
		System.out.println("\n");
		
		System.out.println(check.isValidSequence2(n, new int[] {0,1,0,1})); 	//	true
		System.out.println(check.isValidSequence2(n, new int[] {0,0,1})); 		//	false
		System.out.println(check.isValidSequence2(n, new int[] {0,1,1})); 		//	false
	}
	
}