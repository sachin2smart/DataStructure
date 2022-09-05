package in.sachinshinde.design;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/map-sum-pairs/

/*
 * 	Design a map that allows you to do the following:
		Maps a string key to a given value.
		Returns the sum of the values that have a key with a prefix equal to a given string.

	Implement the MapSum class:
		MapSum() Initializes the MapSum object.
		void insert(String key, int val) Inserts the key-val pair into the map. 
			If the key already existed, the original key-value pair will be overridden to the new one.
		int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.		
 */

public class MapSumPairs {

	//	Using HashMap
	class MapSum {
		
		Map<String, Integer> hm;
	    
		public MapSum() {
	    	hm = new HashMap<>();
	    }
	    
	    public void insert(String key, int val) {
	        hm.put(key, val);
	    }
	    
	    public int sum(String prefix) {
	    	int ans = 0;
	    	for(String key: hm.keySet()) {
	    		if(key.startsWith(prefix))
	    			ans += hm.get(key);
	    	}
	    	return ans;
	    }
	}
	
	//	Using Prefix HashMap
	Map<String, Integer> hm;
	Map<String, Integer> score;
	
	class MapSum2 {
		
		public MapSum2() {
			hm = new HashMap<String, Integer>();
			score = new HashMap<String, Integer>();
		}
		
		public void insert(String key, int val) {
			hm.put(key, val);
			
			int delta = val - hm.getOrDefault(key, 0);
			String prefix = "";
			for(char c: key.toCharArray()) {
				prefix += c;
				score.put(prefix, hm.getOrDefault(prefix, 0) + delta);
			}
		}
		
		public int sum(int prefix) {
			return score.getOrDefault(prefix, 0);
		}
	}
	
	
	//	Using Prefix Tree (Trie) and HashMap 
	class TrieNode {
		int score;
		Map<Character, TrieNode> children = new HashMap<>();
	}
	
	class MapSum3 {
		HashMap<String, Integer> hm;
		TrieNode root;
		
		public MapSum3(){
			hm = new HashMap<>();
			root = new TrieNode();
		}
		
		public void insert(String key, int val) {
			hm.put(key, val);
			
			int delta = val - hm.getOrDefault(key, 0);
			TrieNode curr = root;
			curr.score += delta;
			
			for(char c: key.toCharArray()) {
				curr.children.putIfAbsent(c, new TrieNode());
				curr = curr.children.get(c);
				curr.score += delta;
			}
		}
		
		public int sum(String prefix) {
			TrieNode curr = root;
			for(char c: prefix.toCharArray()) {
				curr = curr.children.get(c);
				if(curr == null)
					return 0;
			}
			return curr.score;
		}
	}
	
	//	Using Trie and HashMap
	class TrieNode4 {
	    TrieNode4[] children;
	    int val;
	    
	    public TrieNode4() {
	    	val = 0;
	        children = new TrieNode4[26];
	    }
	}
	
	class MapSum4 {
	    TrieNode4 root;
	    HashMap<String,Integer> words;
	    
	    public MapSum4() {
	        root = new TrieNode4();
	        words = new HashMap<>();
	    }
	    
	    public void insert(String key, int val) {
	    	words.put(key,val);
	    	
	    	TrieNode4 node = root;
	        int add = val - hm.getOrDefault(key, 0);

	        for(char c : key.toCharArray()) {
	            if(node.children[c-'a'] == null)
	                node.children[c-'a'] = new TrieNode4();
	            
	            node = node.children[c-'a'];
	            node.val += add;
	        }
	    }
	    
	    public int sum(String prefix) {
	        TrieNode4 node = root;
	        for(char c : prefix.toCharArray()) {
	            if(node.children[c-'a'] == null) 
	            	return 0;
	            node = node.children[c-'a'];
	        }
	        return node.val;
	    }
	}
	
}
