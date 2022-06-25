package in.sachinshinde.z_basicsjava;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class DS_Basics {
	private void levelOrderTraversalByQueue(Node root) {
		if(root == null)
			return;

		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);

		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for(int i=0; i<qSize; i++) {
				Node curr = q.poll();
				
				if(curr.left != null)
					q.offer(curr.left);
				
				if(curr.right != null)
					q.offer(curr.right);	
			}
		}
	}
	
	private void iterateTreeByStack(Node root) {
		if(root == null)
			return;
        
		Stack<Node> st = new Stack<Node>();
        st.push(root);
        
        while(!st.isEmpty()) {
            
        	Node curr = st.pop();
            
        	if(curr.left != null)
                st.push(curr.left);
        	
            if(curr.right != null)
                 st.push(curr.right);
            
        }
	}
}
