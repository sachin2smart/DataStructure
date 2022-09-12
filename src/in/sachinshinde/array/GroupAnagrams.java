package in.sachinshinde.array;

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
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0 || strs==null)
        	return new ArrayList<>();
        
        Map<String, List<String>> mappedAnagrams = 
        		new HashMap<String, List<String>>();
        
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
		System.out.println(anagrams.groupAnagrams(new String[] 
				{"eat","tea","tan","ate","nat","bat"}));	
				//	[["bat"],["nat","tan"],["ate","eat","tea"]]
		System.out.println(anagrams.groupAnagrams(new String[] {""}));	//	[[]]
		System.out.println(anagrams.groupAnagrams(new String[] {"a"}));	//	[["a"]]
	}
}
