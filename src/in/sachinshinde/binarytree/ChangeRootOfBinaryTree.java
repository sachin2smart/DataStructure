package in.sachinshinde.binarytree;

//	https://leetcode.ca/all/1666.html
//	https://leetcode.ca/2020-06-22-1666-Change-the-Root-of-a-Binary-Tree/

/*----------------------------------
 *	Change the Root of a Binary Tree
 *----------------------------------
 *		Given the root of a binary tree and a leaf node, reroot the tree so that the leaf is the new root.

	 You can reroot the tree with the following steps -
	 
	 For each node cur on the path starting from the leaf up to the root​​​ excluding the root:
	 1. If cur has a left child, then that child becomes cur's right child. 
	 	Note that it is guaranteed that cur will have at most one child.
	 2. cur's original parent becomes cur's left child.
	 
	 Return the new root of the rerooted tree.
	
	 Note: Ensure that your solution sets the Node.parent pointers correctly after rerooting 
	 or you will receive "Wrong Answer".
	 
 */

public class ChangeRootOfBinaryTree {

	public static RerootNode flipBinaryTree(RerootNode root,RerootNode leaf) {
		RerootNode curr = leaf;
		RerootNode newParent = null;
		
		while(curr != root) {
			// First condition
			if(curr.left != null)
				curr.right = curr.left;

			// Second condition
			if(curr.parent != null) {
				curr.left = curr.parent;
				
				// TODO : why below condition ? 
				if(curr.left.parent == curr)
					curr.left.parent = null;
				else
					curr.right.parent = null;
			}
			
			// update curr.parent and new-parent
			curr.parent = newParent;
			newParent = curr;
			
			// next iteration of curr
			curr = curr.left;
		}
		return leaf;
	}
}
