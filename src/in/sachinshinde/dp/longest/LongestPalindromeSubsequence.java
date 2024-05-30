package in.sachinshinde.dp.longest;

import java.util.Arrays;

//  https://leetcode.com/problems/longest-palindromic-subsequence/

/*
    Given a string s, find the longest palindromic subsequence's length in s.

    A subsequence is a sequence that can be derived from another sequence
        by deleting some or no elements without changing the order of the remaining elements.

    Example 1:
    ---------
    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".

    Example 2:
    ---------
    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".

    Constraints:
    ------------
        1 <= s.length <= 1000
        s consists only of lowercase English letters.
 */

public class LongestPalindromeSubsequence {


    //	Recursive
    public int calculateRecursive(String str,int start,int len){
        if(len == 0)
            return 0;

        if(len == 1)
            return 1;

        if(str.charAt(start) == str.charAt(start+len-1))
            return 2 + calculateRecursive(str,start+1,len-2);
        else
            return Math.max(calculateRecursive(str, start+1, len-1), calculateRecursive(str, start, len-1));

    }


    //	2D DP Array - Good solution to understand
	public int getLongestPalindromeSubSeqDP2D(String s) {
		int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    // 1D Array
    public int longestPalindromeSubseq_1d(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int prev = 0;

        for(int i = n-1; i>=0; i--) {
            for(int j = i; j<n; j++) {
                int curr = dp[j];
                if( i!= j && s.charAt(i) == s.charAt(j)) {
                    dp[j] = prev + 2;
                }
                else if(i ==j) {
                    dp[j] = 1;
                }
                else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                prev = curr;
            }
        }
        return dp[n-1];
    }

    // 1D DP Array
    private int getLongestPalindromeSubSeqDP1D(String s) {
        int n = s.length();

        int[] dp = new int[n];
        dp[0] = 1;

        for(int i = 1; i < n; i++) {
            dp[i] = 1;
            int currMax = 0;
            for(int j = i-1; j>=0; j--) {
                int next = dp[j];
                if(s.charAt(i) == s.charAt(j)) {
                    dp[j] = 2 + currMax;
                }
                currMax = Math.max(currMax, next);
            }
        }

        return Arrays.stream(dp).max().getAsInt();

    }

	//	Memoization
	public int longestPalindromeSubSeq_mem(String s) {
        return getSubLength(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }
    
    private int getSubLength(String s, int i, int j, Integer[][] dp) {
        if (dp[i][j] != null)	// default value for "Integer" is NULL, for "int" it is 0
            return dp[i][j];
        
        if (i > j)      
        	return 0;
        
        if (i == j)
        	return 1;
        
        if (s.charAt(i) == s.charAt(j))
            dp[i][j] = getSubLength(s, i + 1, j - 1, dp) + 2;
        else
            dp[i][j] = Math.max(getSubLength(s, i + 1, j, dp), getSubLength(s, i, j - 1, dp));
        
        return dp[i][j];
    }
	
	public static void main(String[] args) {
		LongestPalindromeSubsequence subsequence = new LongestPalindromeSubsequence();

        System.out.println(subsequence.getLongestPalindromeSubSeqDP1D("bbbab"));		//bbbb:4
        System.out.println(subsequence.getLongestPalindromeSubSeqDP2D("bbbab"));

        System.out.println(subsequence.calculateRecursive("bbbab", 0, 5));		//bbbb:4
		System.out.println(subsequence.longestPalindromeSubSeq_mem("bbbab"));	//bbbb:4

        System.out.println(subsequence.longestPalindromeSubseq_1d("bbbab"));	//bbbb:4
	}
	
}