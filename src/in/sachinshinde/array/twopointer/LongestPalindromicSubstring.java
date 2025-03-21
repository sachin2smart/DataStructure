package in.sachinshinde.array.twopointer;

//	https://leetcode.com/problems/longest-palindromic-substring/

/*
		Given a string s, return the longest palindromic substring in s.

		Example 1:
		---------
			Input: s = "babad"
			Output: "bab"
			Explanation: "aba" is also a valid answer.

		Example 2:
		---------
			Input: s = "cbbd"
			Output: "bb"


		Constraints:
		-----------
			1 <= s.length <= 1000
			s consist of only digits and English letters.
 */

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
	      if(s == null || s.length() <= 1) {	//	empty string or a string with a single character
			  return s;
		  }
		
		String longest = s.substring(0, 1);	//	initial value of the answer is the first character
											//	String.substring(beginIndex, endIndex);

		for(int i = 0; i < s.length(); i++) {
			String oddLengthSubstring = getPalindromeSubString(s, i, i);
			String evenLengthSubstring = getPalindromeSubString(s, i, i + 1);
			
			if(oddLengthSubstring.length() > longest.length()) {
				longest = oddLengthSubstring;
			}

			if(evenLengthSubstring.length() > longest.length()) {
				longest = evenLengthSubstring;
			}

		}
		return longest;
	}

    private String getPalindromeSubString(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
        }
        return s.substring(left + 1, right);
    }
    
    public static void main(String[] args) {
		LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(palindromicSubstring.longestPalindrome("abdbc"));		//	"bdb"	
		System.out.println(palindromicSubstring.longestPalindrome("babad"));		//	"bab"	
		System.out.println(palindromicSubstring.longestPalindrome("cbbd"));			//	"bb"	
	}
}
