package in.sachinshinde.bst;

//	https://www.geeksforgeeks.org/floor-in-binary-search-tree-bst/

/*
 * 	Floor Value Node: Node with the greatest data lesser than or equal to the key value. 
 */

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