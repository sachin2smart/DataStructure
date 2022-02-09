package in.sachinshinde.dp.palindrome;

//	https://leetcode.com/problems/palindromic-substrings/

/*
 * 	 ---------------------------
 * 	|	Palindromic Substrings	|
 * 	 ---------------------------
 * 
 * 	Given a string s, return the number of palindromic substrings in it.
 * 	
 */

public class PalindromicSubstrings {
	int count;
	    
    public int countSubstrings(String s) {
    	count = 0;
        if (s == null || s.length() == 0) 
            return 0;
        
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; 
            right++;
            count++;
        }
    }
    
    public static void main(String[] args) {
    	PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
    	System.out.println(palindromicSubstrings.countSubstrings("abc"));	 	//	3
    	System.out.println(palindromicSubstrings.countSubstrings("aaa"));	 	//	6
	}
}
