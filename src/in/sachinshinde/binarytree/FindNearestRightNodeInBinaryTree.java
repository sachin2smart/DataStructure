package in.sachinshinde.binarytree;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.ca/all/1602.html

/*
 * ---------------------------------------
 * Find Nearest Right Node in Binary Tree
 * ---------------------------------------
 * 	Given the root of a binary tree and a node u in the tree. 
 * 	Return the nearest node on the same level that is to the right of u, 
 * 		or return null if u is the rightmost node in its level.
 */

public class FindNearestRightNodeInBinaryTree {

	public static Node getNearestRightNodeOnSameLevelInBinaryTree(Node root, Node u) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				Node curr = q.poll();
				
				//	If the node found, check if it a last node node on that level
				if(curr.key == u.key) {
					if(i < size-1)
						return q.poll();	// here, at this current level- q is already formed, 
											//	so just get the next value of the queue 
					return null;
				}
				
				if(curr.left != null)
					q.offer(curr.left);
				
				if(curr.right != null)
					q.offer(curr.right);
			}
		}
		return null;
	}
}
