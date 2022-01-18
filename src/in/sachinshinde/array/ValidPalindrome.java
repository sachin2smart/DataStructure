package in.sachinshinde.array;

//	https://leetcode.com/problems/valid-palindrome/

/*
 *	Valid Palindrome
 *		A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
 *		and removing all non-alphanumeric characters, it reads the same forward and backward. 
 *		Alphanumeric characters include letters and numbers.

		Given a string s, return true if it is a palindrome, or false otherwise.
 */


public class ValidPalindrome {
    
	public static boolean isPalindrome(String s) {
    	
		int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l)))
                l++;
            
            while (l < r && !Character.isLetterOrDigit(s.charAt(r)))
                r--;

            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                return false;

            l++;
            r--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
		System.out.println(isPalindrome("race a car"));	//false
		System.out.println(isPalindrome(" "));	//true
	}
}

/*
 * 	Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 */