package in.sachinshinde.array;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/minimum-window-substring/

/*
 	Given two strings s and t of lengths m and n respectively, 
 	return the minimum window substring of s such that 
 		every character in t (including duplicates) is included in the window. 
 	If there is no such substring, return the empty string "".

	A substring is a contiguous sequence of characters within the string.

        ---------
        Example 1:
        ---------
            Input: 
            		s = "ADOBECODEBANC", 
            		t = "ABC"
            
            Output: "BANC"
            
            Explanation: The minimum window substring "BANC" includes 
            			'A', 'B', and 'C' from string t.
        ---------
        Example 2:
        ---------
            Input: 
            		s = "a", 
            		t = "a"
            
            Output: "a"
            
            Explanation: The entire string s is the minimum window.
 */

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
	if(s.length() == 0 || t.length() == 0) 
	    return "";
	
	Map<Character, Integer> hm = new HashMap<Character,Integer>();
	for(int i=0; i<t.length(); i++)
	    hm.put(t.charAt(i), hm.getOrDefault(t.charAt(i), 0) + 1);
	
	int required = hm.size();
	int l = 0, r = 0;
	
	int formed = 0;
	
	Map<Character, Integer> currWindowCounts = 
		new HashMap<Character, Integer>();
	
	// ans list of the form (window length, left, right)
        int[] ans = { -1, 0, 0 };
        
        while(r < s.length()) {
            char c = s.charAt(r);
            currWindowCounts.put(c, currWindowCounts.getOrDefault(c, 0) + 1);
            
            if(hm.containsKey(c) && 
        	    hm.get(c).intValue() == currWindowCounts.get(c).intValue())
        	formed++;
            
            while(l <= r && formed == required) {
        	c = s.charAt(l);
        	
        	// Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                
                currWindowCounts.put(c, currWindowCounts.get(c) - 1);
                
                if(hm.containsKey(c) && 
                	hm.get(c).intValue() > currWindowCounts.get(c).intValue())
                    formed--;
                
                l++;
            }
            r++;
        }
        
        return ans[0] == -1 ? "" : 
            s.substring(ans[1], ans[2] + 1);
    }
    
    public static void main(String[] args) {
	MinimumWindowSubstring w = new MinimumWindowSubstring();
	System.out.println(w.minWindow("ABAACBAB", "ABC"));	//	ACB
	System.out.println(w.minWindow("ADOBECODEBANC", "ABC"));//	BANC
	System.out.println(w.minWindow("a", "a"));//	"a"
	System.out.println(w.minWindow("a", "aa"));//	""
    }
}