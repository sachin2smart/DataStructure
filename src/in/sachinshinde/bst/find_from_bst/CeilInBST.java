package in.sachinshinde.bst.find_from_bst;

//	https://www.geeksforgeeks.org/floor-and-ceil-from-a-bst/

/*
 * 	Ceil Value Node: Node with the smallest data larger than or equal to the key value. 
 */

import in.sachinshinde.bst.Node;

public class CeilInBST {
	
	private int getCeilInBST(Node root, int x) {
		int ceil = -1;
		
		while(root != null) {
			
			if(x == root.key) {
				ceil = root.key;
				return ceil;
			}
			else if(x > root.key) {
				root = root.right;
			}
			else {
				ceil = root.key;	//	since the value exists in left subtree, current node can be a ceil
				root = root.left;
			}
		}
		
		return ceil;
	}
	
	public static void main(String[] args) {
		CeilInBST bst = new CeilInBST();
		Node root = new Node(10);
		root = new Node(10);
		root.left = new Node(5);
		root.right = new Node(15);
		root.right.left = new Node(12);
		root.right.right = new Node(30);
		System.out.println("Ceil in tree for 14 is Node with key " + bst.getCeilInBST(root, 14));	//	15
		System.out.println("Ceil in tree for 15 is Node with key " + bst.getCeilInBST(root, 15));	//	15
		System.out.println("Ceil in tree for 35 is Node with key " + bst.getCeilInBST(root, 35));	//	-1
	}
}


// Test Case 1
/*
 *           10
 *          /  \
 *         5   15
 *            /  \
 *           12  30
 *
 *		X = 14
 *	
 *-->	Ceil = 15
 */


//Test Case 1
/*
*           10
*          /  \
*         5   15
*            /  \
*           12  30
*
*		X = 15
*	
*-->	Floor = 15
*/


// Ask Questions : (1) Is there a possibility that x < leftmost-value-in-tree ?
//				   (2) Is there a possibility that x > rightmost-value-in-tree ?
//				   (3) Can we consider float/double value for x ?