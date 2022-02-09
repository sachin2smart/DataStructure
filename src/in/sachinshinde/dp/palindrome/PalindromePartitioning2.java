package in.sachinshinde.dp.palindrome;

//	https://leetcode.com/problems/palindrome-partitioning-ii/

/*
 * 	Given a string s, partition s such that every substring of the partition is a palindrome.
 * 	Return the minimum cuts needed for a palindrome partitioning of s.
 */

public class PalindromePartitioning2 {
	
	public int minCut(String s) {
		int n = s.length();
		
		int[] dp = new int[n+1];
		
		for(int i=0; i<=n; i++)
			dp[i] = i-1;
		
		for(int i=0; i<n; i++) {
			for(int j=0; i-j>=0 && i+j<n && s.charAt(i-j)==s.charAt(i+j); j++)	// odd length
				dp[i+j+1] = Math.min(dp[i+j+1], 1+dp[i-j]);
			
			for(int j=1; i-j+1>=0 && i+j<n && s.charAt(i-j+1)==s.charAt(i+j); j++)	// even length
				dp[i+j+1] = Math.min(dp[i+j+1], 1+dp[i-j+1]);
		}
		
		return dp[n];
	}
	
	public static void main(String[] args) {
		PalindromePartitioning2 partitioning = new PalindromePartitioning2();
		System.out.println(partitioning.minCut("aab"));  	//	1
		System.out.println(partitioning.minCut("a"));  		//	0
	}
}
