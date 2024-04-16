package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//	https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

/*
 *	Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
 *		(i.e., from left to right, then right to left for the next level and alternate between). 
 */

public class ZigZagLevelOrderTraversal {
	
	public List<List<Integer>> zigzagLevelOrder(Node root) {
		List<List<Integer>> ans = new ArrayList<>();
		travel(root, ans, 0);
		return ans;
    }

	private void travel(Node curr, List<List<Integer>> ans, int level) {
		if(curr == null)
			return;
		
		if(ans.size() <= level)
        {
            List<Integer> nextLevel = new LinkedList<>();
            ans.add(nextLevel);
        }
        
        List<Integer> currentLevelList  = ans.get(level);
        
        if(level % 2 == 0) 
        	currentLevelList.add(curr.key);	//	this method will append the value to next node
        else 
        	currentLevelList.add(0, curr.key);	// The add(index, val) method inserts an element at a specified index in the list. 
        										//	It shifts the element currently at that position (if any) and 
        										//	any subsequent elements to the right (will add one to their indices).
        
		travel(curr.left, ans, level+1);
		travel(curr.right, ans, level+1);
	}
}
