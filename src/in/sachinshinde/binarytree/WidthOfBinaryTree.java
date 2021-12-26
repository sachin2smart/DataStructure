package in.sachinshinde.binarytree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/* 
 * Problem : https://leetcode.com/problems/maximum-width-of-binary-tree/
 * 
 * Optimal solution : https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
 * Right ans : https://leetcode.com/submissions/detail/607373222/
 * Wrong ans : https://leetcode.com/submissions/detail/607371181/
 */

public class WidthOfBinaryTree {
	
	private static int getWidthOfBinaryTree(Node root) {	
		if(root == null)
			return 0;
		
		int result = Integer.MIN_VALUE;	
		
		ArrayDeque<Pair> q = new ArrayDeque<>();
		q.add(new Pair(root, 0));
		
		while(!q.isEmpty()) {
			int n = q.size();
			result = Math.max(n, (q.getLast().num - q.getFirst().num + 1));
			
			for(int i=0; i<n; i++) {
				Pair p = q.poll();
				
				Node node = p.node;
				int index = p.num;
				
				if(node.left != null)
					q.add(new Pair(node.left, 2*index + 1 ));
				
				if(node.right != null)
					q.add(new Pair(node.right, 2*index + 2 ));
			}
		}
		
		return result;
	}
	
	private static int widthOfBinaryTree(Node root) {
		if(root == null)
            return 0;
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        int min = 0;
        int max = 0;
        int res = 1;
        
        while(!q.isEmpty())
        {
            int size = q.size();
    
            for(int i = 0; i<size; i++)
            {
                Pair p = q.poll();
                
                if(i == 0)
                    min = p.num;
                
                if(i == size-1)
                    max = p.num;
                
                if(p!=null && p.node.left!=null)
                    q.add(new Pair(p.node.left, 2*p.num + 1));
                
                if(p!=null && p.node.right!=null)
                    q.add(new Pair(p.node.right, 2*p.num + 2));
                
            }
			
            res = Math.max(res, max - min + 1);
        }
        
        return res;
        
	}
	
	private static int widthOfBianryTreeWithoutOverflow(Node root) {
		if(root == null) 
			return 0;
        
		int ans = 0;
        
		Queue<Pair> q = new LinkedList<>(); 
        q.add(new Pair(root, 0)); 
        
        while(!q.isEmpty()){
            int size = q.size();
            int mmin = q.peek().num;
            int first = 0; 
            int last = 0;
            
            for(int i=0; i<size; i++){
                int cur_id = q.peek().num - mmin;
                Node node = q.peek().node;
                
                q.poll();
                
                if(i==0) 
                	first = cur_id;
                
                if(i==size-1) 
                	last = cur_id;
                
                if(node.left != null)
                    q.add(new Pair(node.left, cur_id*2+1));
                
                if(node.right != null) 
                    q.add(new Pair(node.right, cur_id*2+2));
            }
            ans = Math.max(ans, last-first+1);
        }
        return ans;
	}
	
	public static void main(String[] args) {
		  Node  root = new Node(1);
		  root . left = new Node(3);
		  root . left . left = new Node(5);
		  root . left . left . left = new Node(7);
		  root . right = new Node(2);
		  root . right . right = new Node(4);
		  root . right . right . right = new Node(6);

		  int maxWidth = widthOfBianryTreeWithoutOverflow(root);
		  System.out.println("The maximum width of the Binary Tree is "+maxWidth);
	}
}
