package in.sachinshinde.dp.palindrome;

//	https://leetcode.com/problems/longest-palindromic-substring/

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
	      if(s == null || s.length()<=1 || s.equals(""))
			return s;
		
		String longest = s.substring(0,1);

		for(int i=0; i<s.length(); i++) {
			String oddLengthSubstring = getPalindromeSubString(s, i, i);
			String evenLengthSubstring = getPalindromeSubString(s, i, i+1);
			
			if(oddLengthSubstring.length() > longest.length())
				longest = oddLengthSubstring;
			if(evenLengthSubstring.length() > longest.length())
				longest = evenLengthSubstring;

		}
		return longest;
	}

    private String getPalindromeSubString(String s, int begin, int end) {
        while(begin >=0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin+1, end);
    }
    
    public static void main(String[] args) {
		LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
		System.out.println(palindromicSubstring.longestPalindrome("abdbc"));		//	"bdb"	
		System.out.println(palindromicSubstring.longestPalindrome("babad"));		//	"bab"	
		System.out.println(palindromicSubstring.longestPalindrome("cbbd"));			//	"bb"	
	}
}
