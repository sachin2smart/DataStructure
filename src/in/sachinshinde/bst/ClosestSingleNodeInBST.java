package in.sachinshinde.bst;

//	https://leetcode.ca/2016-08-26-270-Closest-Binary-Search-Tree-Value/

/*
 * 	Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:
     Given target value is a floating point.
     You are guaranteed to have only one unique value in the BST that is closest to the target.

 */

public class ClosestSingleNodeInBST {

	public int closestValue(Node root, double target) {

		// First determine the child node (either of right child or a left child)
        Node child = target < root.key ? root.left : root.right;

        //	Then comes the base condition 
        if (child == null) {
            return root.key;
        }

        // Recursively call the same function till we get the closest value 
        int childClosest = closestValue(child, target);

        // Final calculation with root and closest child
        return Math.abs(root.key - target) < Math.abs(childClosest - target) ? root.key : childClosest;
    }
	
}
