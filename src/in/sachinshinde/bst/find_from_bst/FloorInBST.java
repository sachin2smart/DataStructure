package in.sachinshinde.bst.find_from_bst;

//	https://www.geeksforgeeks.org/floor-in-binary-search-tree-bst/

/*
 * 	Floor Value Node: Node with the greatest data lesser than or equal to the key value. 
 */

import in.sachinshinde.bst.Node;

public class FloorInBST {
	
	private int getFloorInBST(Node root, int x) {
		int floor = -1;
		
		while(root != null) {
			
			if(x == root.key) {
				floor = root.key;
				return floor;
			}
			else if(x > root.key) {
				floor = root.key;
				root = root.right;
			}
			else {
				root = root.left;
			}
		}
		
		return floor;
	}
	
	public static void main(String[] args) {
		FloorInBST bst = new FloorInBST();
		Node root = new Node(10);
		root = new Node(10);
		root.left = new Node(5);
		root.right = new Node(15);
		root.right.left = new Node(12);
		root.right.right = new Node(30);
		System.out.println("Ceil in tree for 14 is Node with key " + bst.getFloorInBST(root, 14));	//	12
		System.out.println("Ceil in tree for 15 is Node with key " + bst.getFloorInBST(root, 15));	//	15
		System.out.println("Ceil in tree for 35 is Node with key " + bst.getFloorInBST(root, 30));	//	30
		System.out.println("Ceil in tree for 3 is Node with key " + bst.getFloorInBST(root, 3));	//	-1
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
 *-->	Floor = 12
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