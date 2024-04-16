package in.sachinshinde.binarytree.checks_on_tree;

//	https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/

//	Given two Binary Trees, write a function that returns 
//	true if two trees are mirror of each other, 
//		else false. 

import in.sachinshinde.binarytree.Node;

public class MirrorTreeCheck {
	
	static boolean areMirrorTree(Node a, Node b) {
		if(a == null && b == null)
			return true;
		
		if(a == null || b == null)
			return false;
		
		if(a.key == b.key &&
			areMirrorTree(a.left, b.right) &&
			areMirrorTree(a.right, b.left))
				return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		Node a = new Node(1);  
        a.left = new Node(2); 
        a.right = new Node(3); 
        a.left.left = new Node(4); 
        a.left.right = new Node(5); 
        
        Node b = new Node(1);
        b.left = new Node(3); 
        b.right = new Node(2); 
        b.right.right = new Node(4);
        b.right.left = new Node(5); 
        
        if(areMirrorTree(a, b))
        	System.out.println("Both trees are mirror of each other");
        else
        	System.out.println("Not mirror tree.");
		
	}
}
