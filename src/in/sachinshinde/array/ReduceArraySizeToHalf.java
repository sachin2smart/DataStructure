package in.sachinshinde.array;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/problems/reduce-array-size-to-the-half/

public class ReduceArraySizeToHalf {
	
	public int minSetSize(int[] arr) {
		
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int key: arr)
			map.put(key, map.getOrDefault(key, 0)+1);
		
		
		PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
		for(int key: map.keySet())
			pq.add(map.get(key));
		
		int size=arr.length;
		int count=0;
		
		while(size>arr.length/2){
			size-=pq.remove();
			count++;
		}
		
		return count;
	}
}
