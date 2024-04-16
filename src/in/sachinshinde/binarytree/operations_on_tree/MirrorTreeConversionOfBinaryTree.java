package in.sachinshinde.binarytree.operations_on_tree;

import in.sachinshinde.binarytree.Node;

import java.util.LinkedList;
import java.util.Queue;

public class MirrorTreeConversionOfBinaryTree {
	
	//	Method : 1	(Recursive)
	Node getMirrorTree(Node node) {
        if (node == null)
            return null;
            
        Node left = getMirrorTree(node.left);
        Node right = getMirrorTree(node.right);
 
        node.left = right;
        node.right = left;
 
        return node;
    }
	
	//	Method : 2	(Non-recursive)
	void mirrorTree(Node node) {
        if (node == null)
        	return;
 
        Queue<Node> q = new LinkedList<>();
        q.add(node);
     
        while (q.size() > 0)
        {
            Node curr = q.peek();
            q.remove();
     
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;;
     
            if (curr.left != null)
                q.add(curr.left);
                
            if (curr.right != null)
                q.add(curr.right);
        }
    }
	
	public static void main(String[] args) {
		/* 
		        1
		      /   \
		     /     \
		    2       3
		   / \     / \
		  4   5   6   7
		
		*/
	
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
	
		preorder(root);	//	1 2 4 5 3 6 7 
		convertToMirror(root);
	
		System.out.println("\nAfter conversion : ");
		preorder(root);	//	1 3 7 6 2 5 4
		
		/* 
		        1
		      /   \
		     /     \
		    3       2
		   / \     / \
		  7   6   5   4
		
		*/
	}
	
	//	method : 3
	
	 // Function to convert a given binary tree into its mirror
    public static void convertToMirror(Node root) {
        // base case: if the tree is empty
        if (root == null)
            return;
 
        // convert left subtree
        convertToMirror(root.left);
 
        // convert right subtree
        convertToMirror(root.right);
 
        // swap left subtree with right subtree
        swap(root);
    }
    
    // Utility function to swap left subtree with right subtree
    public static void swap(Node root) {
        if(root == null)
            return;
        
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
    public static void preorder(Node root) {
        if (root == null)
            return;
 
        System.out.print(root.key + " ");
        preorder(root.left);
        preorder(root.right);
    }
}
