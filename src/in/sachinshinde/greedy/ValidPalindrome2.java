package in.sachinshinde.greedy;

//	https://leetcode.com/problems/valid-palindrome-ii/

/*
 *	Valid Palindrome
 *		Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * 	E.g.1 	Input: s = "aba"	Output: true
 *  E.g.2	Input: s = "abca"	Output: true 	
 *  E.g.3 	Input: s = "abc"	Output: false
 */

public class ValidPalindrome2 {
	
	public static boolean validPalindrome(String s) {
		int l = -1;
		int r = s.length();
		
		while(++l < --r)
			if(s.charAt(l) != s.charAt(r))
				return isPalindrome(s, l, r+1) || isPalindrome(s, l-1, r);
		
		return true;
    }

	private static boolean isPalindrome(String s, int l, int r) {
		while(++l <--r)
			if(s.charAt(l) != s.charAt(r))
				return false;
		
		return true;
	}

	public static void main(String[] args) {
		String s = "abca";
		if(validPalindrome(s))
			System.out.println("Valid Parindrome");
		else
			System.out.println("Invalid Parindrome");
	}
}
