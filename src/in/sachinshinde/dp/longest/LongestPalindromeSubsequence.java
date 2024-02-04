package in.sachinshinde.dp.longest;

public class LongestPalindromeSubsequence {

	//	2D DP Array
	public int longestPalindromeSubseq(String s) {
		int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
	
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
	
	//	Memoization
	public int longestPalindromeSubseq_mem(String s) {
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
    
    // 1D Array
    public int longestPalindromeSubseq_1d(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int pre=0;
        for(int i=len-1; i>=0; i--) {   
          for(int j=i; j<len; j++) {  
        	  int temp=dp[j];
              if(i==j) {
            	  dp[j]=1;
            	  pre=temp;
            	  continue;
               }
               if(s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre+ 2;
                }
                else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                pre=temp;
             }
         }
         return dp[len-1];
    }
	
	public static void main(String[] args) {
		LongestPalindromeSubsequence subsequence = new LongestPalindromeSubsequence();
		System.out.println(subsequence.longestPalindromeSubseq("bbbab"));		//bbbb:4
		System.out.println(subsequence.calculateRecursive("bbbab", 0, 5));		//bbbb:4
		System.out.println(subsequence.longestPalindromeSubseq_mem("bbbab"));	//bbbb:4
		System.out.println(subsequence.longestPalindromeSubseq_1d("bbbab"));	//bbbb:4
	}
	
}
