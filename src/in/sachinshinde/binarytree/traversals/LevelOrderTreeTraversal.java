package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	https://leetcode.com/problems/binary-tree-level-order-traversal/

//	Given the root of a binary tree, return the level order traversal of its nodes' values. 
//	(i.e., from left to right, level by level).

public class LevelOrderTreeTraversal {
	
	public static void printNodeLevelwise(Node root) {
		
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(root);
		while(!queue.isEmpty()) {
	
			Node currNode = queue.poll();
			System.out.print(" "+currNode.key);
			
			if(currNode.left!=null ) 
				queue.add(currNode.left);
					
			if(currNode.right!=null)
				queue.add(currNode.right);
		}
	}
	
	private static List<List<Integer>> getLevelOrderOfBinaryTree(Node root){
		List<List<Integer>> wrapList = new LinkedList<>();
		
		if(root == null) 
			return wrapList;
		
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			List<Integer> subList = new LinkedList<Integer>();
			int n = q.size();
			
			for(int i=0; i<n; i++) {
				if(q.peek().left != null)
					q.offer(q.peek().left);
				
				if(q.peek().right != null)
					q.offer(q.peek().right);
				
				subList.add(q.poll().key);
			}
			
			wrapList.add(subList);
		}
		
		return wrapList;
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
		
		System.out.print("\n\n Level Order traversal : ");
		List<List<Integer>> result = getLevelOrderOfBinaryTree(root);	// main function
	
		result.forEach((list) -> {
			System.out.println();
            list.forEach((num) -> System.out.print(" "+num));
        });
	}

}
