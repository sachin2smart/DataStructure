package in.sachinshinde.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class MirrorTreeConversionOfBinaryTree {
	
	Node mirror(Node node) {
        if (node == null)
            return null;
            
        Node left = mirror(node.left);
        Node right = mirror(node.right);
 
        node.left = right;
        node.right = left;
 
        return node;
    }
	
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
}
