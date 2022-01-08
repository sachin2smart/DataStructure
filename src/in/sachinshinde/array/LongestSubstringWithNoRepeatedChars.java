package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/longest-substring-without-repeating-characters/

/*
 * 	Given a string s, find the length of the longest substring without repeating characters.

	Example:
	
	Input: s = "abcabcbb"
	Output: 3
	Explanation: The answer is "abc", with the length of 3.
 */

public class LongestSubstringWithNoRepeatedChars {

	public static int getLengthOfLongestSubstring(String s) {
		int i = 0, j = 0, longLength = 0;
	    Set<Character> set = new HashSet<>();
	    
	    while (j < s.length()) {
	        if (!set.contains(s.charAt(j))) {
	            set.add(s.charAt(j++));
	            longLength = Math.max(longLength, set.size());
	        }
	        else
	        	set.remove(s.charAt(i++));
	        
	    }
	    return longLength;
	}
	
	public static void main(String[] args) {
		System.out.println(getLengthOfLongestSubstring("abcabcbb"));
	}
	
}

/*
 * 	Used a hash set to track the longest substring without repeating characters so far, 
 * 		used a fast pointer j to see if character j is in the hash set or not, 
 * 			- if not, great, add it to the hash set, move j forward and update the max length, 
 * 			- otherwise, delete from the head by using a slow pointer i until we can put character j to the hash set.
 */