package in.sachinshinde.graph.clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
	
	public static Map<Integer, Node> hm = new HashMap<>();
	
	public static Node getClone(Node node) {
		return clone(node);
	}

	private static Node clone(Node node) {
		if(node == null)
			return null;
		
		if(hm.containsKey(node.val))
			return hm.get(node.val);
		
		Node newNode = new Node(node.val, new ArrayList<>());
		hm.put(node.val, newNode);
		
		for(Node n: node.neighbors)
			newNode.neighbors.add(clone(n));
		
		return newNode;
	}
	
}
