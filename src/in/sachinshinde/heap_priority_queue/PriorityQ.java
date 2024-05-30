package in.sachinshinde.heap_priority_queue;

import java.util.PriorityQueue;

public class PriorityQ {

	public static void main(String[] args) {
		int a[] = {9, 8, 8, 5}; 
        int k = 3; 
        //int n = a.length; 
        
		PriorityQueue<Integer> pq = new PriorityQueue<>(); 
        for (int x : a) 
            pq.add(x); 
  
        // Do k negations by removing a minimum element k times 
        while (k-- > 0) 
        { 
            // Retrieve and remove min element 
            int temp = pq.poll(); 
            
            // Modify the minimum element and add back 
            // to priority queue 
            temp *= -1;
            System.out.println(temp);
            pq.add(temp); 
        } 
  
        // Compute sum of all elements in priority queue. 
        int sum = 0; 
        for (int x : pq) 
            sum += x; 

        System.out.println(sum);
	}

}
