package in.sachinshinde.array;

//	https://leetcode.com/problems/palindrome-number/

/*
 * 	Given an integer x, return true if x is palindrome integer.
 *  An integer is a palindrome when it reads the same backward as forward.
 *  For example, 121 is a palindrome while 123 is not.
 *  Input: x = -121 Output: false
 */

public class PalindromeNumber {
	
	public static boolean isPalindrome(int x) {
        // Negative values and values having 0 at end are not palidrome
        if (x<0 || (x!=0 && x%10==0)) 
            return false;
        
        int rev = 0;

        while (x > rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        
        return (x==rev || x==rev/10);
    }
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(51215));
		System.out.println(isPalindrome(512215));
		System.out.println(isPalindrome(512217));
		System.out.println(isPalindrome(51217));
	}
}

/*
 * 	5 1  2  1    5 
 *  x x    rev  rev
 *  
 *  
    rev = from right to left
    x = decreased from right, left part remains for x
    n/2 half elements needs to traverse
    
 */