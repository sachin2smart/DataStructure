package com.gfg.bst;

import java.util.ArrayList;

public class UniqueBSTsPrint {
	
	static ArrayList<Node> totalTreesFrom_1_to_N(int start, int end) {
		
		ArrayList<Node> list = new ArrayList<Node>();
		
		if(start > end) {
			list.add(null);
			return list;
		}
			
		for(int i=start ; i<=end; i++) {
			
			ArrayList<Node> leftSubTree = totalTreesFrom_1_to_N(start, i-1);
			ArrayList<Node> rightSubTree = totalTreesFrom_1_to_N(i+1, end);
			
			for(int j=0; j<leftSubTree.size(); j++) {
				Node left = leftSubTree.get(j);
				
				for(int k=0; k<rightSubTree.size(); k++) {
					Node right = rightSubTree.get(k);
					
					Node node = new Node(i);
					node.left = left;
					node.right = right;
					
					list.add(node);
				}
			}
		}
		return list;
	}
	
	static void preorder(Node root) {
		if(root!=null) {
			System.out.print(" "+root.key);
			preorder(root.left);
			preorder(root.right);
		}

	}
	public static void main(String[] args) {
		ArrayList<Node> nodes = totalTreesFrom_1_to_N(1,3);
		
		for(int i=0 ; i< nodes.size(); i++) {
			preorder(nodes.get(i));
			System.out.println();
		}
			
			
	}
}
