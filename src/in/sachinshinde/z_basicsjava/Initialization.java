package in.sachinshinde.z_basicsjava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import in.sachinshinde.tree.Node;

public class Initialization {
	
	//	1. List of List
	List<List<String>> accounts = Arrays.asList(
			Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
			Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
			Arrays.asList("Mary","mary@mail.com"),
			Arrays.asList("John","johnnybravo@mail.com"));

	//	2. Array of ArrayList
	ArrayList<Integer>[] graph = new ArrayList[10];
	
	//	3. ArrayList Initialization
	List<Integer> nums = new ArrayList<Integer>(List.of(1,2,5));
	
	//	4. Queue initialization
	Queue<String> q = new LinkedList<>();
	
	//	5. Deque initialization
	Deque<Node> queue = new LinkedList<>();
	Deque<Integer> result = new ArrayDeque<Integer>();
	
	//	6. To generate stream of characters/words/strings, use StringBuilder
	StringBuilder sb = new StringBuilder();
	
	//	7. List of Set of Integers
	List<Set<Integer>> adjacencyList = new ArrayList<>();

}
