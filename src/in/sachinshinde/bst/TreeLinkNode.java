package in.sachinshinde.bst;

class TreeLinkNode {
    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;

    public TreeLinkNode() {}
    
    public TreeLinkNode(int _val) {
        val = _val;
    }

    public TreeLinkNode(int _val, TreeLinkNode _left, TreeLinkNode _right, TreeLinkNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};