package in.sachinshinde.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/find-all-anagrams-in-a-string/
/*
 	Given two strings s and p, return an array of all the start indices of p's anagrams in s. 
 	You may return the answer in any order.

	An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
		typically using all the original letters exactly once.


	Example 1:
		Input: s = "cbaebabacd", p = "abc"
		Output: [0,6]
		Explanation:
		The substring with start index = 0 is "cba", which is an anagram of "abc".
		The substring with start index = 6 is "bac", which is an anagram of "abc".

	Example 2:
		Input: s = "abab", p = "ab"
		Output: [0,1,2]
		Explanation:
		The substring with start index = 0 is "ab", which is an anagram of "ab".
		The substring with start index = 1 is "ba", which is an anagram of "ab".
		The substring with start index = 2 is "ab", which is an anagram of "ab".

 */

public class FindAllAnagramsInAString {
	static final int NUM_CHARS = 26;
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		
		if(s.length() < p.length()) 
			return res;
		
		int [] countS = new int[NUM_CHARS];
		int [] countP = new int[NUM_CHARS];
	
	    for(int i=0;i<p.length();i++) {
	        countS[s.charAt(i)-'a']++;
	        countP[p.charAt(i)-'a']++;
	    }
	    
	    if(areSame(countS,countP)){
	        res.add(0);
	    }
		    
	    for(int i=p.length();i<s.length();i++) {
			countS[s.charAt(i)-'a']++;
			countS[s.charAt(i-p.length())-'a']--;
			
			if(areSame(countS,countP)) {
			    res.add(i-p.length()+1);
			}
	    }
	    
	    return res;
	}

	public boolean areSame(int[] countS, int[] countP) {
	    for(int i=0;i<NUM_CHARS;i++) {
	        if(countS[i]!=countP[i]) {
	            return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args) {
		FindAllAnagramsInAString anagrams = new FindAllAnagramsInAString();
		System.out.println(anagrams.findAnagrams("cbaebabacd", "abc"));	//	[0,6]
		System.out.println(anagrams.findAnagrams("abab", "ab"));	//	[0,1,2]
		
		System.out.println(anagrams.findAnagrams2("cbaebabacd", "abc"));	//	[0,6]
		System.out.println(anagrams.findAnagrams2("abab", "ab"));	//	[0,1,2]
	}
	
	//	Sliding-Window
	public List<Integer> findAnagrams2(String s, String p) {
		int countS[] = new int[26];
		int countP[] = new int[26];
	    List<Integer> list = new ArrayList<>();
	    
	    if(s.length() < p.length())
	        return list;
	    
	    for(int i=0; i < p.length(); i++) {
	        countS[s.charAt(i) - 'a']++;
	        countP[p.charAt(i) - 'a']++;
	    }
	 
	    int start = 0;
	    int end = p.length();
	    
	    if(Arrays.equals(countS,countP))
	        list.add(start);
	    
	    while(end < s.length()) {
	        countS[s.charAt(start) - 'a']--;
	        countS[s.charAt(end) - 'a']++;
	        
	        if(Arrays.equals(countS,countP))
	        	list.add(start + 1);
	        
	        start++;
	        end++;
	    }
	    
	    return list;  
	}
}