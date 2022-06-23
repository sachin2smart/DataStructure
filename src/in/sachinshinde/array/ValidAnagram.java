package in.sachinshinde.array;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/valid-anagram/

/*
 		Given two strings s and t, 
 			return true if t is an anagram of s, 
 				and false otherwise.

		An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
		typically using all the original letters exactly once.
 */

/*
		Example 1:

		Input: s = "anagram", t = "nagaram"
		Output: true
		Example 2:
		
		Input: s = "rat", t = "car"
		Output: false
 */
public class ValidAnagram {
	
	//	using array
	public boolean isAnagram(String s, String t) {
        int[] alphabates = new int[26];
        for(char c: s.toCharArray()) 
        	alphabates[c-'a']++;
        for(char c: t.toCharArray()) 
        	alphabates[c-'a']--;
        for(int i: alphabates)
        	if(i!=0)
        		return false;
        return true;
    }
	
	//	using 2 HashMaps
	public boolean isAnagram2(String s, String t) {
		if (s == null && t == null)
	        return true;
	    if (s.length() != t.length())
	        return false;
	    Map<Character, Integer> mapS = new HashMap<>();
	    for (char c : s.toCharArray())
	        mapS.put(c, mapS.getOrDefault(c, 0) + 1);
	    Map<Character, Integer> mapT = new HashMap<>();
	    for (char c : t.toCharArray())
	        mapT.put(c, mapT.getOrDefault(c, 0) + 1);
	    if (mapS.equals(mapT))
	        return true;
	    return false;
	}
	
	//	using 1 HashMap
	public boolean isAnagram3(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c: s.toCharArray())
            map.put(c, map.getOrDefault(c,0)+1);
        for(char c: t.toCharArray())
            map.put(c, map.getOrDefault(c,0)-1);
        for(Map.Entry<Character,Integer> entry : map.entrySet())
            if(entry.getValue() != 0)
                return false;
        return true;
    }

	public static void main(String[] args) {
		ValidAnagram validAnagram = new ValidAnagram();
		System.out.println(validAnagram.isAnagram("anagram", "nagaram"));	//	true
		System.out.println(validAnagram.isAnagram("rat", "car"));	//	false
		
		System.out.println(validAnagram.isAnagram2("anagram", "nagaram"));	//	true
		System.out.println(validAnagram.isAnagram2("rat", "car"));	//	false
		
		System.out.println(validAnagram.isAnagram3("anagram", "nagaram"));	//	true
		System.out.println(validAnagram.isAnagram3("rat", "car"));	//	false
	}
}
