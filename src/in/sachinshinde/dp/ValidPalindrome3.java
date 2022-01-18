package in.sachinshinde.dp;

//	https://leetcode.com/problems/valid-palindrome-iii/

/*
 *	Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 *	A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it. 	
 */

public class ValidPalindrome3 {
	
	public static boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i=n-1; i>=0; i--) {
            dp[i][i] = 1;	//	why 1 ?
            for (int j=i+1; j<n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;	//	why +2 here ?
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);	// what does it signifies 
            }
        }
        int maxLength = dp[0][n-1];
        return n-maxLength <= k;
    }
	
	public static void main(String[] args) {
		System.out.println(isValidPalindrome("abcdeca", 2));
	}
}

/*
 * 	Example 1:
Input: s = “abcdeca”, k = 2
Output: true  
 * 
 */
