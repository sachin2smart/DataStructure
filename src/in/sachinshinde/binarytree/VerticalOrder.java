package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
 * 
 * Good one: https://leetcode.ca/2016-10-09-314-Binary-Tree-Vertical-Order-Traversal/
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
    }
}
