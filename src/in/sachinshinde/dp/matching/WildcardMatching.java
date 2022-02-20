package in.sachinshinde.dp.matching;

//	https://leetcode.com/problems/wildcard-matching/

//	Wildcard Matching
/* ------------------
	Given an input string (s) and a pattern (p), 
	implement wildcard pattern matching with support for '?' and '*' where:

		'?' Matches any single character.
		'*' Matches any sequence of characters (including the empty sequence).
		
	The matching should cover the entire input string (not partial).

 */

public class WildcardMatching {

	public static boolean isMatch(String s, String p) {
	    int sL = s.length(), pL = p.length();
	    
	    boolean[][] dp = new boolean[pL+1][sL+1];
	    dp[0][0] = true;
	
	    for(int i=1; i<=pL; i++) {
	        boolean flag = false;
	
	        for(int j=0; j<=sL; j++) {
	            flag = flag || dp[i-1][j];
	            char c = p.charAt(i-1);
	
	            if(c != '*')
	                dp[i][j] = j>0 && 
	                            dp[i-1][j-1] && 
	                            (c =='?' || c == s.charAt(j-1));
	
	            else 
	                dp[i][j] = (i==1 || flag);
	
	        }
	    }
	
	    return dp[pL][sL];
	}

	public static void main(String[] args) {
		System.out.println(isMatch("abc", "a*c"));
		System.out.println(isMatch2("abc", "a*c"));
	}
	
	// Bottom Up DP
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null)
            return false;

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int j = 1; j < n + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j][0] = dp[j - 1][0];
            }
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char charS = s.charAt(i - 1);
                char charP = p.charAt(j - 1);
                if (charS == charP || charP == '?')
                    dp[j][i] = dp[j - 1][i - 1];
                else if (charP == '*')
                    dp[j][i] = dp[j - 1][i] || dp[j][i - 1];

            }
        }

        return dp[n][m];
    }
    
    /*
     	1. dp[0][0] = true, since empty string matches empty pattern
     	2. dp[0][i] = false
         	since empty pattern cannot match non-empty string
        3.  dp[j][0]
         	for any continuative '*' will match empty string
         	e.g s='aasffdasda' p='*'/'**'/'***'....
        
        
         1. if p.charAt(j) == s.charAt(i), match single character
         	=>>> dp[i][j] = dp[i - 1][j - 1]
         2. if p.charAt(j) == '?', '?' match single character
         	=>>> dp[i][j] = dp[i - 1][j - 1]

         3. if p.charAt(j) == '*', dp[i][j]=dp[i-1][j]||dp[i][j-1]
         	=>>>   '*' match empty: dp[i][j]=dp[i-1][j]
         	=>>>   '*' match multiple characters: dp[i][j]=dp[i][j-1]
     */
}
