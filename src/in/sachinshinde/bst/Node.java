package in.sachinshinde.bst;

public class Node {
	public int key;
	public Node left, right;
	
	public Node(int data){
		this.key = data;
		this.left = this.right = null;
	}
};
