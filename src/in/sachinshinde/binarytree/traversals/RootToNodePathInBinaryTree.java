package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.stream.Collectors;

// https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/

public class RootToNodePathInBinaryTree {
	
	private static boolean getPath(Node root, ArrayList<Integer> arr, int x) {
		if(root == null)
			return false;
		
		arr.add(root.key);	// add to list while iterating
		
		if(root.key == x)
			return true;
		
		if(getPath(root.left, arr, x) || getPath(root.right, arr, x))
			return true;
		
		arr.remove(arr.size() -1);	// remove from the list when target node not found 
		
		return false;
	}
	
	public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(7);
        root.right = new Node(3);

        ArrayList<Integer> arr = new ArrayList < > ();

        boolean res;
        res = getPath(root, arr, 7);

        if(res) {
	        System.out.print("The path is " + arr.stream()
	        									.map(Object::toString)
	        									.collect(Collectors.joining(" -> ")));
	        //	The path is 1 -> 2 -> 5 -> 7
        }
        else {
        	System.out.println("Path not exists");
        }

    }
}
