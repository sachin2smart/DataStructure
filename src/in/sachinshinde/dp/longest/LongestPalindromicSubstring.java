package in.sachinshinde.dp.longest;

//	https://leetcode.com/problems/longest-palindromic-substring/

/*
	Given a string s, return the longest palindromic substring in s.

	Example 1:
	Input: s = "babad"
	Output: "bab"
	Explanation: "aba" is also a valid answer.

	Example 2:
	Input: s = "cbbd"
	Output: "bb"
 */

public class LongestPalindromicSubstring {
    private int start, maxLen;
    
    public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2)
			return s;
		
	    for (int i = 0; i < len-1; i++) {
	     	extendPalindrome(s, i, i);  //assume odd length
	     	extendPalindrome(s, i, i+1); //assume even length
	    }
	    return s.substring(start, start + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			start = j + 1;
			maxLen = k - j - 1;
		}
	}
	
	public static void main(String[] args) {
		LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(longestPalindromicSubstring.longestPalindrome("babad"));	//	bab
		longestPalindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(longestPalindromicSubstring.longestPalindrome("cbbd"));	//	bb
		longestPalindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(longestPalindromicSubstring.longestPalindrome("babcbaabcbaccba"));		//	abcbaabcba
		longestPalindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(longestPalindromicSubstring.longestPalindrome("cdbabcbabdab"));	//	dbabcbabd
	}
}