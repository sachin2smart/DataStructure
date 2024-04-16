package in.sachinshinde.binarytree.operations_on_tree;

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
/*
	private RerootNode flipBinaryTree(RerootNode root,RerootNode leaf) {
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
*/
	
	public RerootNode flipBinaryTree2(RerootNode root, RerootNode leaf) {
		return reroot(root, leaf, null);
	}
	
	private RerootNode reroot(RerootNode root, RerootNode node, RerootNode newParent) {
		RerootNode oldParent = node.parent;
		node.parent = newParent;
		
		// clean up the child if it's the new parent
		if (node.left == newParent)
		  node.left = null;
		
		if (node.right == newParent)
		  node.right = null;
		
		// we meet the original root, so we're done
		if (node == root)
		  return node;
		
		if (node.left != null)
		  node.right = node.left;
		
		node.left = reroot(root, oldParent, node);
		
		return node;
	}
	  
	public static void main(String[] args) {
		RerootNode n = new RerootNode(1);
		n.left = new RerootNode(2, n);
		n.right = new RerootNode(3, n);
		
		ChangeRootOfBinaryTree reroot = new ChangeRootOfBinaryTree();
		reroot.inOrderTraversal(n);
		
		RerootNode r = reroot.flipBinaryTree2(n, n.left);
		reroot.inOrderTraversal(r);
	}
	  
	private void inOrderTraversal(RerootNode root) {
		if(root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(" "+root.val);
		inOrderTraversal(root.right);
	}
}
