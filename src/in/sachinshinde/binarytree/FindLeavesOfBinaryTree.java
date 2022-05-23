package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//	https://leetcode.ca/all/366.html

//	Given a binary tree, collect a tree's nodes as if you were doing this: 
//		Collect and remove all leaves, repeat until the tree is empty.

/*
 * 	
 	Input: [1,2,3,4,5]
 
          1
         / \
        2   3
       / \
      4   5

	Output: [[4,5,3],[2],[1]]
 */

public class FindLeavesOfBinaryTree {
	
	public List<List<Integer>> findLeaves(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(root, res);
		return res;
	}

	private int dfs(Node root, List<List<Integer>> res) {
		if(root == null) return 0;
		
		int left = dfs(root.left, res);
		int right = dfs(root.right, res);
		
		int curr = Math.max(left, right) + 1;
		
		if(res.size() < curr)
			res.add(new ArrayList<>());
		
		res.get(curr-1).add(root.key);	//	store root value at the curr-1 index
		return curr;
	}
	
	public static void main(String[] args) {
		FindLeavesOfBinaryTree listOfLeavesOfBinaryTree = new FindLeavesOfBinaryTree();
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		List<List<Integer>> result = listOfLeavesOfBinaryTree.findLeaves(root);
		System.out.println(Arrays.asList(result));
	}
	
}