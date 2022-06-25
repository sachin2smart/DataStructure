package in.sachinshinde.bst;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

/*
 * 	Given the root of a Binary Search Tree and a target number k, 
 * 		return true if there exist two elements in the BST 
 * 		such that their sum is equal to the given target
 */

public class CheckTheSumOfAnyTwoNodesToKInBST {
	
	Set<Integer> res = new HashSet<>();
	
    public boolean findTarget(Node root, int k) {
        if(root==null)	//	if we reach to the leaf node and does not found a sum of two nodes equal to k
            return false;
        
        if(res.contains(k-root.key))	// if the current key and any of the added key in the hashset sum to k
            return true; // k = root.val + anotherValueAlreadyBeingAddedToSet
        
        res.add(root.key);	// add every key in the hashset 
        
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
}
