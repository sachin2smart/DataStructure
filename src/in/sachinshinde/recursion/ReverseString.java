package in.sachinshinde.recursion;

//	https://leetcode.com/problems/reverse-string/

//	Reverse String
/*
 * 	Write a function that reverses a string. The input string is given as an array of characters s.
	You must do this by modifying the input array in-place with O(1) extra memory.
Example:
	Input: s = ["h","e","l","l","o"]
	Output: ["o","l","l","e","h"]
 */

public class ReverseString {

	public String reverseString(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
    }
	
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }    
    }
    
}
