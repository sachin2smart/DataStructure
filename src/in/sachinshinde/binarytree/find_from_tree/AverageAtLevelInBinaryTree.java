package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	https://leetcode.com/problems/average-of-levels-in-binary-tree/

/*
 * 	Given the root of a binary tree, 
 * 	return the average value of the nodes on each level in the form of an array.
 */

public class AverageAtLevelInBinaryTree {
	public List<Double> averageOfLevels(Node root) {
		List<Double> result = new ArrayList<>();
	    Queue<Node> q = new LinkedList<>();
	    
	    if(root == null) 
	    	return result;
	    
	    q.add(root);
	    
	    while(!q.isEmpty()) {
	        int n = q.size();	// this is divisor
	        double sum = 0.0;	// this is dividend
	        
	        for(int i = 0; i < n; i++) {
	            Node node = q.poll();
	            sum += node.key;
	            // prepare a queue for next level
	            if(node.left != null) 
	            	q.offer(node.left);
	            if(node.right != null) 
	            	q.offer(node.right);
	        }
	        
	        result.add(sum / n);	// store the division result at each level
	    }
	    return result;
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		
		AverageAtLevelInBinaryTree avg = new AverageAtLevelInBinaryTree();
		System.out.println(avg.averageOfLevels(root));
	}
}
