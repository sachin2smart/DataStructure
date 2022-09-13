package in.sachinshinde.binarytree;

public class RerootNode {
	public int val;
    public RerootNode left;
    public RerootNode right;
    public RerootNode parent;
    
    public RerootNode(int data) {
    	this.val = data;
    	this.left = null;
    	this.right = null;
    	this.parent = null;
    }
    
    public RerootNode(int data, RerootNode n) {
    	this.val = data;
    	this.left = null;
    	this.right = null;
    	this.parent = n;
    }
}
