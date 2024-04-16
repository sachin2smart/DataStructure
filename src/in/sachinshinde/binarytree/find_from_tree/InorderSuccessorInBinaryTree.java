package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.Stack;

public class InorderSuccessorInBinaryTree {

    public Node inorderSuccessor(Node root, Node p) {
	if(root == null)
	    return null;
	
	boolean found = false;
	Stack<Node> st = new Stack<>();
	Node curr = root;
	
	while(curr != null || !st.isEmpty()) {
	    while(curr != null) {
		st.push(curr);
		curr = curr.left;
	    }
	    curr = st.pop();
	    
	    if(found) 
		return curr;
	    
	    if(curr == p)
		found = true;
	    
	    curr = curr.right;
	}
	
	return null;
    }
    
    public static void main(String[] args) {
	InorderSuccessorInBinaryTree tree = new InorderSuccessorInBinaryTree();
	
	Node n = new Node(5);
	n.left = new Node(3);
	n.right = new Node(4);
	n.left.left = new Node(6);
	n.left.right = new Node(8);
	n.right.left = new Node(9);
	n.right.right = new Node(11);
	n.left.right.right = new Node(15);
	
	Node resultNode = tree.inorderSuccessor(n, n.left);
	System.out.println(resultNode != null ? resultNode.key : "null");	// 8
	
	resultNode = tree.inorderSuccessor(n, n.left.right.right);
	System.out.println(resultNode != null ? resultNode.key : "null");	// 5
	
	resultNode = tree.inorderSuccessor(n, n.right.right);
	System.out.println(resultNode != null ? resultNode.key : "null");	// null
	
    }

}