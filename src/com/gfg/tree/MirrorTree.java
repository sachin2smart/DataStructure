package com.gfg.tree;

public class MirrorTree {
	
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
        Node b = new Node(1); 
        a.left = new Node(2); 
        a.right = new Node(3); 
        a.left.left = new Node(4); 
        a.left.right = new Node(5); 
  
        b.left = new Node(3); 
        b.right = new Node(2); 
        b.right.left = new Node(5); 
        b.right.right = new Node(4); 
        
        if(areMirrorTree(a, b))
        	System.out.println("Both trees are mirror of each other");
        else
        	System.out.println("Not mirror tree.");
		
	}
}
