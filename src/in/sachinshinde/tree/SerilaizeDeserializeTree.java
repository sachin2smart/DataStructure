package in.sachinshinde.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


//	https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

/*
 *	Serialization is the process of converting a data structure or object into a sequence of bits 
 *	so that it can be stored in a file or memory buffer, or transmitted across a network connection link 
 *	to be reconstructed later in the same or another computer environment.

	Design an algorithm to serialize and deserialize a binary tree. 
	There is no restriction on how your serialization/deserialization algorithm should work. 
	You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized 
	to the original tree structure. 
 */

public class SerilaizeDeserializeTree {
	
	private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.key).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private Node buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            Node node = new Node(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
    
    public static void main(String[] args) {
    	Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(5);
		root.left.left.right.right = new Node(6);
		root.left.left.right.right.right = new Node(7);
		root.left.right = new Node(8);
		root.left.right.right = new Node(9);
		root.left.right.right.right = new Node(10);
		root.left.right.right.right.right = new Node(11);
		root.right = new Node(12);
		
		String serializedTree = serialize(root);
		System.out.println(serializedTree);
		
		String serializedTree2 = serialize2(root);
		System.out.println(serializedTree2);
	}
    
    
    // Second Way
    
    public static String NULL = "#";
    public static String DELIMITER = ",";

    // Encodes a tree to a single string.
    public static String serialize2(Node root) {
        if (root == null) {	//	base case
            return null;
        }
        
        StringBuilder sb = new StringBuilder();	// will store answer 
        Deque<Node> queue = new LinkedList<>();
        
        queue.offer(root);	// start from the root
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();	// poll = remove the head (first element) of the deque and return the same
            							//			return null, if the deque is empty
            if (node == null) {
                sb.append(NULL).append(DELIMITER);
                continue;
            }
            
            sb.append(node.key).append(DELIMITER);
            
            queue.offer(node.left);
            queue.offer(node.right);
        }
        
        String res = sb.toString();
        
        return res.substring(0, res.length() - 1);
    }

    // Decodes your encoded data to tree.
    public static Node deserialize2(String data) {
        if (data == null) {	// base condition
            return null;
        }
        
        String[] values = data.split(DELIMITER);	// splitted by comma
        Deque<Node> queue = new ArrayDeque<>();
        
        Node root = new Node(Integer.parseInt(values[0]));
        queue.offer(root);
        
        for (int i = 1; i < values.length; ++i) {	// i starts with 1
        	//	Get the parent
            Node parent = queue.poll();
            
            //	Assign the left child
            if (!values[i].equals(NULL)) {
                Node left = new Node(Integer.parseInt(values[i]));
                parent.left = left;
                queue.offer(left);
            }
            
            //	Assign the right child
            if (!values[++i].equals(NULL)) {
                Node right = new Node(Integer.parseInt(values[i]));
                parent.right = right;
                queue.offer(right);
            }
        }
        
        return root;
    }
}
