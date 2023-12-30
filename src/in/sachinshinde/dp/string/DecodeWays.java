package in.sachinshinde.dp.string;

import java.util.Arrays;

//	https://leetcode.com/problems/decode-ways/

/*
 	A message containing letters from A-Z can be encoded into numbers using the following mapping:

            'A' -> "1"
            'B' -> "2"
            ...
            'Z' -> "26"
        To decode an encoded message, all the digits must be grouped then mapped back into letters using 
        the reverse of the mapping above (there may be multiple ways). 
        
        For example, "11106" can be mapped into:
            "AAJF" with the grouping (1 1 10 6)
            "KJF" with the grouping (11 10 6)
            
        Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
        
        Given a string s containing only digits, return the number of ways to decode it.
        The test cases are generated so that the answer fits in a 32-bit integer.
        
         
        
        Example 1:
        ---------
        Input: s = "12"
        Output: 2
        Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
        
        Example 2:
        ---------
        Input: s = "226"
        Output: 3
        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
        
        Example 3:
        ---------
        Input: s = "06"
        Output: 0
        Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
        
        Constraints:
            1 <= s.length <= 100
            s contains only digits and may contain leading zero(s).
 */

public class DecodeWays {
//	--------------------------------------------------------------------	//
    //	Method 1 : Recursion
    //  	This code gives TLE
    public int numDecodings(String s) {
        return s.length() == 0 ? 0 : numDecodings(0, s);
    }
    
    private int numDecodings(int p, String s) {
	int n = s.length();
	
	if(p == n)
	    return 1;
	
	if(s.charAt(p) == '0')
	    return 0;
	
	int res = numDecodings(p+1, s);
	
	if(p < n-1 && (Integer.parseInt(s.substring(p, p+2)) < 27))
	    res += numDecodings(p+2, s);
	
	return res;
    }
    
//	--------------------------------------------------------------------	//
//	Method 2 : Memoization [Top-Down]
    
    public int numDecodings2(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return topDownDecode(0, s, memo);
    }
        
    private int topDownDecode(int p, String s, int[] memo) {
    	if(p == s.length())
    	    return 1;
    	
    	if(memo[p] != -1)
    	    return memo[p];
    	
    	if(s.charAt(p) == '0')
    	    return 0;
    	
    	int res = topDownDecode(p+1, s, memo);
    	
    	if(p < s.length()-1 && (s.charAt(p) == '1' || (s.charAt(p) == '2' && s.charAt(p+1) < '7')))
    	    res += topDownDecode(p+2, s, memo);
    	
    	memo[p] = res;
    	
    	return res;
    }
//	--------------------------------------------------------------------	//
//	Method 3 : Memoization [Bottom-Up]
    public int numDecodings3(String s) {
	int n = s.length();
	
	if(n == 0)
	    return 0;
	
	int[] dp = new int[n+1];
	dp[n] = 1;
	
	for(int i = n-1; i >= 0; i--) {
	    if(s.charAt(i) == '0')
		dp[i] = 0;
	    else {
		dp[i] += dp[i+1];
		if(i < n-1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i+1) < '7')))
		    dp[i] += dp[i+2];
	    }
	}
	
	return dp[0];
    }
//	--------------------------------------------------------------------	//
    
    public static void main(String[] args) {
	DecodeWays decodeWays = new DecodeWays();
	
	System.out.println(decodeWays.numDecodings("11106"));	// 2
	System.out.println(decodeWays.numDecodings("12"));	// 2
	System.out.println(decodeWays.numDecodings("226"));	// 3
	System.out.println(decodeWays.numDecodings("06"));	// 0
	System.out.println(decodeWays.numDecodings("301"));	// 0
	
	System.out.println(decodeWays.numDecodings2("11106"));	// 2
	System.out.println(decodeWays.numDecodings2("12"));	// 2
	System.out.println(decodeWays.numDecodings2("226"));	// 3
	System.out.println(decodeWays.numDecodings2("06"));	// 0
	System.out.println(decodeWays.numDecodings2("301"));	// 0
	
	System.out.println(decodeWays.numDecodings3("11106"));	// 2
	System.out.println(decodeWays.numDecodings3("12"));	// 2
	System.out.println(decodeWays.numDecodings3("226"));	// 3
	System.out.println(decodeWays.numDecodings3("06"));	// 0
	System.out.println(decodeWays.numDecodings3("301"));	// 0
    }
}