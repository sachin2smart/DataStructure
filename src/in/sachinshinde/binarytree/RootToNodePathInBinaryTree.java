package in.sachinshinde.binarytree;

import java.util.ArrayList;

public class RootToNodePathInBinaryTree {
	
	private static boolean getPath(Node root, ArrayList<Integer> arr, int x) {
		if(root == null)
			return false;
		
		arr.add(root.key);
		
		if(root.key == x)
			return true;
		
		if(getPath(root.left, arr, x) || getPath(root.right, arr, x))
			return true;
		
		arr.remove(arr.size() -1);
		
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

        ArrayList < Integer > arr = new ArrayList < > ();

        boolean res;
        res = getPath(root, arr, 7);

        System.out.print("The path is ");
        for (int it: arr) {
            System.out.print(it + " ");
        }

    }
}
