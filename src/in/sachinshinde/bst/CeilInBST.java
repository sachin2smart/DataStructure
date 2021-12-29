package in.sachinshinde.bst;

//	https://www.geeksforgeeks.org/floor-and-ceil-from-a-bst/

/*
 * 	Ceil Value Node: Node with the smallest data larger than or equal to the key value. 
 */

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
				ceil = root.key;
				root = root.left;
			}
		}
		
		return ceil;
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