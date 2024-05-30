package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
 */

public class VerticalOrder {
	
	public static List<List<Integer>> findVertical(Node root) {
		TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();
		
		Queue<Tuple> q = new LinkedList<Tuple>();
        q.offer(new Tuple(root, 0, 0));
        
        while (!q.isEmpty()) {
            
        	Tuple tuple = q.poll();
            Node node = tuple.node;
            
            int x = tuple.row;
            int y = tuple.col;

            if (!map.containsKey(x)) {
                map.put(x, new TreeMap < > ());
            }

            if (!map.get(x).containsKey(y)) {
                map.get(x).put(y, new PriorityQueue < > ());
            }
            
            map.get(x).get(y).offer(node.key);

            if (node.left != null) {
                q.offer(new Tuple(node.left, x - 1, y + 1));
            }
            
            if (node.right != null) {
                q.offer(new Tuple(node.right, x + 1, y + 1));
            }
        }
        
		List<List<Integer>> list = new ArrayList<>();
        
		for (TreeMap<Integer,PriorityQueue<Integer>> ys: map.values()) {
            list.add(new ArrayList<Integer>());
            for (PriorityQueue<Integer> nodes: ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size()- 1).add(nodes.poll());
                }
            }
        }
     
        return list;
        
	}
	
	/* This function has issues (https://leetcode.ca/2016-10-09-314-Binary-Tree-Vertical-Order-Traversal/)
	private static List<List<Integer>> getVerticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null) {
            return res;
        }
        
        Queue<TreeColumnNode> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        queue.offer(new TreeColumnNode(root, 0));
        
        int curLevelCount = 1;
        int nextLevelCount = 0;

        while(!queue.isEmpty()) {
        
        	TreeColumnNode node = queue.poll();
            
        	if(map.containsKey(node.col)) {
                map.get(node.col).add(node.treeNode.key);
            } 
        	else {
                map.put(node.col, new ArrayList<Integer>(Arrays.asList(node.treeNode.key)));
            }
            
        	curLevelCount--;

            if(node.treeNode.left != null) {
                queue.offer(new TreeColumnNode(node.treeNode.left, node.col - 1));
                nextLevelCount++;
            }
            
            if(node.treeNode.right != null) {
                queue.offer(new TreeColumnNode(node.treeNode.right, node.col + 1));
                nextLevelCount++;
            }
            
            if(curLevelCount == 0) {
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }

        return new ArrayList<List<Integer>>(map.values());
    }
  */
	
	public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(10);
        root.left.left.right = new Node(5);
        root.left.left.right.right = new Node(6);
        root.right = new Node(3);
        root.right.left = new Node(9);
        root.right.right = new Node(10);

        List<List<Integer>> list = new ArrayList<>();
        
        list = findVertical(root);

        System.out.println("The Vertical Traversal is : ");
        for (List < Integer > it: list) {
            for (int nodeVal: it) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
        
//        list = getVerticalOrder(root);
//
//        System.out.println("\nThe Vertical Traversal is : ");
//        for (List < Integer > it: list) {
//            for (int nodeVal: it) {
//                System.out.print(nodeVal + " ");
//            }
//            System.out.println();
//        }
    }
}
