package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/longest-palindrome

public class LongestPalindrome {
	public int longestPalindrome(String s) {
		if(s==null || s.isEmpty())
			return 0;
		
		Set<Character> hs = new HashSet<>();
		int count = 0;
		
		for(int i=0; i<s.length(); i++) {
			if(hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(i));
				count++;
			}
			else
				hs.add(s.charAt(i));
		}
		
		if(hs.isEmpty())
			return count *2;
		return count*2 + 1;
	}
	
	public static void main(String[] args) {
		LongestPalindrome longestPalindrome = new LongestPalindrome();
		System.out.println(longestPalindrome.longestPalindrome("a"));	//	1
		System.out.println(longestPalindrome.longestPalindrome("abccccdd"));	// 7	
	}
	
	public int longestPalindrome2(String s) {
        Set<Character> hs = new HashSet<>();
        for (char c : s.toCharArray())
            if (hs.contains(c)) 
            	hs.remove(c);
            else 
            	hs.add(c);

        return s.length() - (hs.size() == 0 ? 0 : hs.size() - 1);
    }
}
