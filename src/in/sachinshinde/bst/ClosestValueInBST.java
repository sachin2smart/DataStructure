package in.sachinshinde.bst;

//	https://leetcode.com/problems/closest-binary-search-tree-value/

/*	Note:
		Given target value is a floating point.
		You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

// When we recursively traversed then the closest value with be either to the root node of recursive call
//	or it will be at the childClosed node which is nothing but either left node or right node of recursive travered node

public class ClosestValueInBST {
	
	public int closestValue(Node root, double target) {

        Node child = target < root.key ? root.left : root.right;

        if (child == null) {
            return root.key;
        }

        int childClosest = closestValue(child, target);

        return Math.abs(root.key - target) < Math.abs(childClosest - target) ? root.key : childClosest;
    }
}


/*
 * Test the above code with below
 * 
 *           5
 *          / \ 
 *         3   7
 *        / \
 *       2   4
 * 
 * 		target = 3.7
 * 
 * 		-> Result : 4
 */
