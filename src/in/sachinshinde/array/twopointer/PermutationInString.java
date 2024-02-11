package in.sachinshinde.array.twopointer;

import java.util.Arrays;

//	https://leetcode.com/problems/permutation-in-string/

/*
 	Given two strings s1 and s2, 
 		return true if s2 contains a permutation of s1, or false otherwise.

        In other words, return true if one of s1's permutations is the substring of s2.
        
        Example 1:
        ---------
        Input: s1 = "ab", s2 = "eidbaooo"
        Output: true
        Explanation: s2 contains one permutation of s1 ("ba").
        
        Example 2:
        ---------
        Input: s1 = "ab", s2 = "eidboaoo"
        Output: false
         
        
        Constraints:
        -----------
            1 <= s1.length, s2.length <= 104
            s1 and s2 consist of lowercase English letters.
 */

public class PermutationInString {

    //	Video URL: https://youtu.be/XFh_AoEdOTw 
    public boolean checkInclusion(String s1, String s2) {
	if(s1.length() > s2.length())
	    return false;
	
	int[] a1 = new int[26];
	int[] a2 = new int[26];
	
	for(int i=0; i<s1.length(); i++) {
	    a1[s1.charAt(i) - 'a']++;
	    a2[s2.charAt(i) - 'a']++;
	}
	
	if(Arrays.equals(a1, a2))
	    return true;
	
	int i=0;
	int j=s1.length();
	
	while(j < s2.length()) {
	    a2[s2.charAt(i) - 'a']--;
	    a2[s2.charAt(j) - 'a']++;
	    
	    if(Arrays.equals(a1, a2))
		return true;
	    
	    i++;
	    j++;
	}
	
        return false;
    }
    
    public boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        int[] dict = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            dict[s1.charAt(i) - 'a']++;
        }

        int i = 0, j = 0, counter = s1.length();

        while (j < s1.length()) {
            if (dict[s2.charAt(j++) - 'a']-- > 0) {
                counter--;
            }
        }

        while (j < s2.length() && counter != 0) {
            if (dict[s2.charAt(i++) - 'a']++ >= 0) {
                counter++;
            }
            if (dict[s2.charAt(j++) - 'a']-- > 0) {
                counter--;
            }
        }

        return counter == 0;
    }
    
    public boolean checkInclusion3(String s1, String s2) {

        if(s1.length() > s2.length()) 
            return false;
        
        int[] a1 = new int[26];
        int[] a2 = new int[26];

        for(int i = 0; i < s1.length(); i++) {
            a1[s1.charAt(i) - 'a']++;
            a2[s2.charAt(i) - 'a']++;
        }

        int left = 0;

        for(int right = s1.length(); right < s2.length(); right++) {
            if(Arrays.equals(a1, a2)) 
        	return true;
            
            a2[s2.charAt(right) - 'a']++;
            a2[s2.charAt(left++) - 'a']--;
        }
        
        return Arrays.equals(a1, a2);
    }
    
    public static void main(String[] args) {
	PermutationInString str = new PermutationInString();
	System.out.println(str.checkInclusion("ab", "eidbaooo"));
	System.out.println(str.checkInclusion("ab", "eidboaoo"));
	
	System.out.println(str.checkInclusion2("ab", "eidbaooo"));
	System.out.println(str.checkInclusion2("ab", "eidboaoo"));
	
	System.out.println(str.checkInclusion3("ab", "eidbaooo"));
	System.out.println(str.checkInclusion3("ab", "eidboaoo"));
    }
}
