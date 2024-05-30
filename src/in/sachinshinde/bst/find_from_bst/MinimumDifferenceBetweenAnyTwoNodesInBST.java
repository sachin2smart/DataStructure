package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/minimum-absolute-difference-in-bst/
// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

public class MinimumDifferenceBetweenAnyTwoNodesInBST {

	Integer min = Integer.MAX_VALUE;
	Integer prev = 0;
	
	private int getMinimumDifferenceBetweenAnyTwoNodesInBST(Node root) {
		
		if(root == null)
			return min;
		
		getMinimumDifferenceBetweenAnyTwoNodesInBST(root.left);
		
		if(prev != null)
			min = Math.min(min, root.key - prev);
		
		prev = root.key;
		
		getMinimumDifferenceBetweenAnyTwoNodesInBST(root.right);
		
		return min;
	}
	
	
	/* The inorder of BST will always gives Sorted order
	   and hence the minimum difference will always be difference between
	   two consecutive elements in inorder*/
	
	public int minDiffInBST(Node root) {
	    List<Integer> list = new ArrayList<>();
	    inorder(root, list);
	    
	    int ans = Integer.MAX_VALUE;
	    for (int i = 0; i < list.size()-1; i++) {
	        ans = Math.min(ans,list.get(i+1)-list.get(i));
	    }
	    
	    return ans;
	}
	
	public void inorder(Node root , List<Integer> list) {
	    if (root == null) 
	    	return;
	    
	    inorder(root.left, list);
	    list.add(root.key);
	    inorder(root.right, list);
	}
}
