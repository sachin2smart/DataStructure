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
		int n = s.length();
		if (n < 2) {
			return s;
		}
		char[] t = s.toCharArray();

	    for (int i = 0; i < n-1; i++) {
	     	extendPalindrome(t, i, i);  //assume odd length
			if(t[i] == t[i+1]) {
				extendPalindrome(t, i, i + 1); //assume even length
			}
	    }
	    return s.substring(start, start + maxLen);
	}

	private void extendPalindrome(char[] t, int leftPtr, int rightPtr) {
		while (leftPtr >= 0 && rightPtr < t.length && t[leftPtr] == t[rightPtr]) {
			leftPtr--;
			rightPtr++;
		}
		if (maxLen < rightPtr - leftPtr - 1) {
			start = leftPtr + 1;
			maxLen = rightPtr - leftPtr - 1;
		}
	}
	
	public static void main(String[] args) {
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();

		lps.start = 0; lps.maxLen = 0;
		System.out.println(lps.longestPalindrome("babad"));	//	bab

		lps.start = 0; lps.maxLen = 0;
		System.out.println(lps.longestPalindrome("cbbd"));	//	bb

		lps.start = 0; lps.maxLen = 0;
		System.out.println(lps.longestPalindrome("babcbaabcbaccba"));   //	abcbaabcba

		lps.start = 0; lps.maxLen = 0;
		System.out.println(lps.longestPalindrome("cdbabcbabdab"));	//	dbabcbabd
	}
}