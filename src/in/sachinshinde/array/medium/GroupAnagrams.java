package in.sachinshinde.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/group-anagrams/

/*
 	Given an array of strings strs, group the anagrams together. 
 	You can return the answer in any order.

	An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
	typically using all the original letters exactly once.
	
	Example 1:
	---------
		Input: strs = ["eat","tea","tan","ate","nat","bat"]
		Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
	
	Example 2:
	---------
		Input: strs = [""]
		Output: [[""]]
	
	Example 3:
	---------
		Input: strs = ["a"]
		Output: [["a"]]
	
	Constraints:
	-----------
		1 <= strs.length <= 104
		0 <= strs[i].length <= 100
		strs[i] consists of lowercase English letters.
 */

public class GroupAnagrams {

	public List<List<String>> groupAnagrams0(String[] strs) {
		// word --> List(Anagram Words)
		HashMap<String, List<String>> hm = new HashMap<>();

		for(String str: strs) {
			//	freq represents the key for the hm, but it is not required for the result
			//	same key can be present for multiple words, that gives the anagram words
			char[] freq = new char[26];
			for(char ch: str.toCharArray()) {
				freq[ch - 'a']++;
			}

			String key = String.valueOf(freq);

			hm.putIfAbsent(key, new ArrayList<>());
			hm.get(key).add(str);
		}

		return new ArrayList<>(hm.values());	// take only value from the hm
	}

    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0 || strs==null)
        	return new ArrayList<>();
        
        Map<String, List<String>> mappedAnagrams = new HashMap<>();
        
        for(String str: strs) {
        	char[] letters = str.toCharArray();
        	Arrays.sort(letters);
        	String anagramWord = String.valueOf(letters);
        	
        	if(!mappedAnagrams.containsKey(anagramWord))
        		mappedAnagrams.put(anagramWord, new ArrayList<>());
        	
        	mappedAnagrams.get(anagramWord).add(str);
        }
        
        return new ArrayList<>(mappedAnagrams.values());
    }
    
	public static void main(String[] args) {
		GroupAnagrams anagrams = new GroupAnagrams();
		System.out.println(anagrams.groupAnagrams0(new String[]
				{"eat","tea","tan","ate","nat","bat"}));
		System.out.println(anagrams.groupAnagrams3(new String[]
				{"eat","tea","tan","ate","nat","bat"}));	
				//	[["bat"],["nat","tan"],["ate","eat","tea"]]
		System.out.println(anagrams.groupAnagrams(new String[] {""}));	//	[[]]
		System.out.println(anagrams.groupAnagrams(new String[] {"a"}));	//	[["a"]]
	}

	public List<List<String>> groupAnagrams2(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		if (strs == null || strs.length == 0) {
			return result;
		}
		if (strs.length == 1) {
			result.add(Arrays.asList(strs));
			return result;
		}

		HashMap<String, List<String>> groups = new HashMap<>();

		for (String stringValue : strs) {
			String key = getAnagramKey(stringValue);
			groups.putIfAbsent(key, new ArrayList<>());
			groups.get(key).add(stringValue);
		}

		return new ArrayList<>(groups.values());
	}

	private String getAnagramKey(String s) {
		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] != 0) {
				sb.append((char) ('a' + i)).append(count[i]); // a1e1t1  : here we are appending the count for chars
			}
		}
		return sb.toString();
	}

	//
	public List<List<String>> groupAnagrams3(String[] strs) {
		HashMap<String,List<String>> map = new HashMap<>();
		for(String str:strs) {
			char[] freq=new char[26];
			for(char c:str.toCharArray()) {
				freq[c - 'a']++;
			}
			String key = String.valueOf(freq); // key will be of 26 char length like 102001020....
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(str);
		}
		return new ArrayList<>(map.values());
	}
}
