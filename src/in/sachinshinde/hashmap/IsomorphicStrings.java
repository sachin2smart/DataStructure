package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/isomorphic-strings/

/*
 	Given two strings s and t, determine if they are isomorphic.

        Two strings s and t are isomorphic if the characters in s can be replaced to get t.
        
        All occurrences of a character must be replaced with another character 
        while preserving the order of characters. 
        No two characters may map to the same character, but a character may map to itself.
        
        
        Example 1:
        
        Input: s = "egg", t = "add"
        Output: true
        Example 2:
        
        Input: s = "foo", t = "bar"
        Output: false
        Example 3:
        
        Input: s = "paper", t = "title"
        Output: true
         
        Input: s = "badc", t = "baba"
        Output: false
        
        Constraints:
        -----------
            1 <= s.length <= 5 * 104
            t.length == s.length
            s and t consist of any valid ascii character.
 */

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        
        Map<Character, Character> hm = new HashMap<>();
        
        for(int i = 0; i < sChars.length; i++) {
            if(!hm.containsKey(sChars[i])) {
                if(hm.containsValue(tChars[i])) {
                    return false;
                }
                hm.put(sChars[i], tChars[i]);
            }
            else {
                if(!hm.get(sChars[i]).equals(tChars[i])) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean isIsomorphic2(String s, String t) {

        int map1[]=new int[200];
        int map2[]=new int[200];

        if(s.length()!=t.length())
            return false;


        for(int i=0;i<s.length();i++)
        {
            if(map1[s.charAt(i)]!=map2[t.charAt(i)])
                return false;

            map1[s.charAt(i)]=i+1;
            map2[t.charAt(i)]=i+1;
        }
        return true;
    }
    
    public static void main(String[] args) {
        IsomorphicStrings strings = new IsomorphicStrings();
        System.out.println(strings.isIsomorphic("egg", "add"));	// true
        System.out.println(strings.isIsomorphic("foo", "bar"));	// false
        System.out.println(strings.isIsomorphic("paper", "title"));	// true
        System.out.println(strings.isIsomorphic("badc", "baba"));	// false
    }
}
