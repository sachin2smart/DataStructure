package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/word-pattern/

/*
 	Given a pattern and a string s, find if s follows the same pattern.

        Here follow means a full match, such that there is a bijection between a letter in pattern and 
        	a non-empty word in s.
         
        
        Example 1:
        ---------
        Input: pattern = "abba", s = "dog cat cat dog"
        Output: true
        
        Example 2:
        ---------
        Input: pattern = "abba", s = "dog cat cat fish"
        Output: false
        
        Example 3:
        ---------
        Input: pattern = "aaaa", s = "dog cat cat dog"
        Output: false
         
        
        Constraints:
        -----------
            1 <= pattern.length <= 300
            pattern contains only lower-case English letters.
            1 <= s.length <= 3000
            s contains only lowercase English letters and spaces ' '.
            s does not contain any leading or trailing spaces.
            All the words in s are separated by a single space.
 */

public class WordPattern {
    // Using HashMap Property: 
    //	   The put method of map returns the previous value associated with the key if present, 
    //		else return -1 
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        
        if(words.length != pattern.length()) {
            return false;
        }
        
        Map<Object, Integer> hm = new HashMap<>();
        
        for(int i = 0; i<words.length; i++) {
            if (hm.put(words[i], i) != hm.put(pattern.charAt(i), i)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        WordPattern pattern = new WordPattern();
        System.out.println(pattern.wordPattern("abba", "dog cat cat dog"));	// true
        System.out.println(pattern.wordPattern("abba", "dog cat cat fish"));	// false
        System.out.println(pattern.wordPattern("aaaa", "dog cat cat dog"));	// false
    }
}