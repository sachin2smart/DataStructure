package in.sachinshinde.bst.find_from_bst;

//	https://leetcode.ca/all/285.html

/*
 	Given a binary search tree and a node in it, 
 	find the in-order successor of that node in the BST.

	The successor of a node p is the node with the smallest key greater than p.val.
 */

import in.sachinshinde.bst.Node;

/*
 	Example 1:	
 	---------
        Input: root = [2,1,3], p = 1
        Output: 2
        Explanation: 
        	1's in-order successor node is 2. 
        	Note that both p and the return value is of TreeNode type.
        
        Example 2:
        ---------
        Input: root = [5,3,6,2,4,null,null,1], p = 6
        Output: null
        Explanation: 
        	There is no in-order successor of the current node, so the answer is null.
         
        
         Given tree = [2,1] and node = 1:
             2
            /
           1
         
         return node 2.
        
         Given tree = [2,1,3] and node = 2:
           2
          / \
         1   3
        
         return node 3.

        Note:
        ---------
        If the given node has no in-order successor in the tree, return null.
        It's guaranteed that the values of the tree are unique.
 */
public class InorderSuccessorInBST1 {
	public Node inorderSuccessor1(Node root, Node p) {

		Node successor = null;

		while (root != null) {

			if (p.key >= root.key) {
				root = root.right;
			}
			else {
				successor = root;
				root = root.left;
			}
		}

		return successor;
	}

    public Node inorderSuccessor(Node root, Node p) {
	if(root == null)
	    return null;
	
	if(root.key <= p.key) 
	    return inorderSuccessor(root.right, p);
	else {
	    Node left = inorderSuccessor(root.left, p);
	    return left == null ? root : left;
	}
    }
    
    public static void main(String[] args) {
	InorderSuccessorInBST1 bst1 = new InorderSuccessorInBST1();
	Node n = new Node(2);
	n.left = new Node(1);
	n.right = new Node(3);
	
	Node resultNode;
	
	resultNode = bst1.inorderSuccessor(n, n.left);
	System.out.println(resultNode != null ? resultNode.key : "null");	//	2
	
	resultNode = bst1.inorderSuccessor(n, n);
	System.out.println(resultNode != null ? resultNode.key : "null");	//	2
	
	resultNode = bst1.inorderSuccessor(n, n.right);
	System.out.println(resultNode != null ? resultNode.key : "null");	//	2
    }

}
