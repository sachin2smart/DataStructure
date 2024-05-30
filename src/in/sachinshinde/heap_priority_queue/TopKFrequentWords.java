package in.sachinshinde.heap_priority_queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//	https://leetcode.com/problems/top-k-frequent-words/

/*
 	Given an array of strings words and an integer k, 
 		return the k most frequent strings.

	Return the answer sorted by the frequency from highest to lowest. 
	Sort the words with the same frequency by their lexicographical order.

	Example 1:
	---------
	Input: 
		words = ["i","love","leetcode","i","love","coding"], 
		k = 2
	Output: 
		["i","love"]
	Explanation: 
		"i" and "love" are the two most frequent words.
		Note that "i" comes before "love" due to a lower alphabetical order.
	
	---------
	Example 2:
	---------
	Input: 
		words = ["the","day","is","sunny","the","the","the","sunny","is","is"], 
		k = 4
	Output: 
		["the","is","sunny","day"]
	Explanation: 
		"the", "is", "sunny" and "day" are the four most frequent words, 
		with the number of occurrence being 4, 3, 2 and 1 respectively.
 */

public class TopKFrequentWords {
	
    public List<String> topKFrequent(String[] words, int k) {
        List<String> frequentWords = new ArrayList<>();
        Map<String, Integer> hm = new HashMap<String, Integer>();
        
        //	Map of word with it's frequency 
        for(int i = 0; i < words.length; i++)
        	hm.put(words[i], hm.getOrDefault(words[i], 0) + 1);
        
        //	Above Map is not properly ordered with elements, so design PQ for ordering 
        PriorityQueue<Map.Entry<String, Integer>> pq = 
        		new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ?
        					b.getKey().compareTo(a.getKey()) : 
        						a.getValue() - b.getValue() );
        
        for(Map.Entry<String, Integer> entry: hm.entrySet()) {
        	pq.offer(entry);
        	
        	//	Limiting only k values in Priority Queue
        	if(pq.size() > k)
        		pq.poll();
        }
        
        // We need keys only
        while(!pq.isEmpty())
        	frequentWords.add(0, pq.poll().getKey());
        
        //	At each iteration, adding element at zero position in List, 
        //	will shift remaining elements to it's right
        
        return frequentWords;
    }
    
	public static void main(String[] args) {
		TopKFrequentWords words = new TopKFrequentWords();
		System.out.println(words.topKFrequent(new String[] 
				{"i","love","leetcode","i","love","coding"}, 2));
		//	["i","love"]
		
		System.out.println(words.topKFrequent(new String[] 
				{"the","day","is","sunny","the","the","the","sunny","is","is"}, 4));
		//	["the","is","sunny","day"]
	}

}
