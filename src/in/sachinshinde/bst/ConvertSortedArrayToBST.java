package in.sachinshinde.bst;

import java.util.Arrays;

/*
	https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
	
	Given an integer array nums where the elements are sorted in ascending order, 
	convert it to a height-balanced binary search tree.
	
	A height-balanced binary tree is a binary tree in which 
	the depth of the two subtrees of every node never differs by more than one.

*/

public class ConvertSortedArrayToBST {
	
	 public Node sortedArrayToBST(int[] nums) {
	        
        if(nums.length == 0)
            return null;
        
        int mid = nums.length / 2;
        
        Node root = new Node(nums[mid]);
        
        root.left =  sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        root.right =  sortedArrayToBST(Arrays.copyOfRange(nums, mid+1, nums.length));
        
        return root;
	  
	 }
	 
}
