package in.sachinshinde.z_basicsjava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
	
	//	8.	Queue of List of Integer
	Queue<int[]> queue2 = new LinkedList<int[]>();
	
	//	9. Map of String with Integer
	Map<String, Integer> hm = new HashMap<String, Integer>();
	
	//	10. Priority Queues with Map Entries
	PriorityQueue<Map.Entry<String, Integer>> pq = 
    		new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ?
    					b.getKey().compareTo(a.getKey()) : 
    						a.getValue() - b.getValue() );
	
	//	11. Array of HashSet (using generics wildcard)
	Set<?>[] rows = new HashSet<?>[9];
	
	//	12. Array of HashSet
	//	You can declare the generic on the type declaration, 
	//		but not when you actually allocate the object.
	Set<Character> chars = new HashSet<Character>();
	Set<Character>[] charsArray = new HashSet[4];
	
	//	12. List of hashsets:
	List<HashSet<Integer>> cols = new ArrayList<HashSet<Integer>>();
	
	//	13. Initialize 2D array
	public int[][] getMatrix() {
	    int[][] mat = new int[1][1];
	    mat[0][0] = 1;
	    return mat;
	}
	public int[][] getMatrix_byList() {
	    List<int[]> mat = new ArrayList<>();
	    mat.add(new int[] {1});
	    return mat.toArray(new int[1][1]);
	}

	// Initialize array with some default value for all places
	private void arrTest() {
		int arr[] = new int[10];
		Arrays.fill(arr, -1);
	}

	// instead of declaring like this -
	ArrayList<ArrayList<Integer>> arr1 = new ArrayList<ArrayList<Integer>>();
	// declare like this -
	ArrayList<ArrayList<Integer>> arr2 = new ArrayList<>(); // No need to add the explicite data type

	public void func() {
		// List of integer array
		List<int[]> l1 = new LinkedList<>();
		l1.add(new int[2]);
		System.out.println(Arrays.toString(l1.get(0)));	// [0, 0]
		int[] arr1 = new int[]{1,2};
		l1.add(arr1);
		System.out.println(Arrays.toString(l1.get(1)));	//	[1, 2]

		// Array of list
		List<Integer>[] l2 = new ArrayList[4];
		l2[0] = new ArrayList<>();
		System.out.println(l2[0]);	//	[]
		List<Integer> arr2 = new ArrayList<>();
		arr2.add(1);
		arr2.add(2);
		l2[1] = arr2;
		System.out.println(l2[1]);	//	[1, 2]
	}

	public static void main(String[] args) {
		Initialization initialization = new Initialization();
		initialization.func();
	}

}
