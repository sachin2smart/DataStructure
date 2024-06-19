package in.sachinshinde.z_basicsjava;

import java.util.*;

public class PriorityQueueInitialization {

    public static void main(String[] args) {
//	------------------------------------------------------------------------------------------------------------------
	
	Queue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	
//	------------------------------------------------------------------------------------------------------------------	
	
	Queue<Integer> pq1 = new PriorityQueue<>((a, b) -> (b - a));
	
//	------------------------------------------------------------------------------------------------------------------
	
	Queue<int[]> pq2 = new PriorityQueue<>((a,b) -> { return Integer.compare(a[0], b[0]); });
	
//	------------------------------------------------------------------------------------------------------------------
	
	Queue<int[]> pq3 = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
	
//	------------------------------------------------------------------------------------------------------------------
	
	Queue<int[]> pq4 = new PriorityQueue<>((a,b) -> a[2] - b[2]);
	
//	------------------------------------------------------------------------------------------------------------------
	
	TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> treemap = new TreeMap<>();
	
//	------------------------------------------------------------------------------------------------------------------		
	
	Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
	
//	------------------------------------------------------------------------------------------------------------------	
	
	Node[] lists = new Node[] {};
	Queue<Node> qNode = new PriorityQueue<>(lists.length, (a,b) -> a.key - b.key);
	
//	------------------------------------------------------------------------------------------------------------------
	
	Queue<Map.Entry<String, Integer>> pqOfMapEntry = 
    		new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ?
    					b.getKey().compareTo(a.getKey()) : 
    						a.getValue() - b.getValue());
	
//	------------------------------------------------------------------------------------------------------------------
	
	List<List<Integer>> nums = new ArrayList<>();
	int[] next = new int[10];
	Queue <Integer> min_queue = new PriorityQueue <Integer> (
        	(i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
	
//	------------------------------------------------------------------------------------------------------------------
	
	Map<Integer, Integer> map = new HashMap<>();
	Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
	
//	------------------------------------------------------------------------------------------------------------------

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> b - a);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> a - b);

		PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap2 = new PriorityQueue<>(Comparator.naturalOrder());

//	------------------------------------------------------------------------------------------------------------------

		PriorityQueue<Integer> pqInt = new PriorityQueue<>((a, b) -> Math.abs(a - 10) > Math.abs(b - 10) ? -1: 1);

//	------------------------------------------------------------------------------------------------------------------

		// Priority queue to store fractions with comparator to sort by fraction value
		//	Comparison by : a[0] / a[1]  < b[0] / b[1]
		int[] arr = new int[]{};
		PriorityQueue<int[]> pqIntArr = new PriorityQueue<>((a, b) -> Integer.compare(
					arr[a[0]] * arr[b[1]], arr[a[1]] * arr[b[0]]));

//	------------------------------------------------------------------------------------------------------------------

	}
    
}
