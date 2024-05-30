package in.sachinshinde.dp.longest;

//	https://leetcode.com/problems/longest-common-subsequence/
//	https://www.youtube.com/watch?v=NnD96abizww

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String text1 = "xsdfabc";
		String text2 = "abc";
		LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
		System.out.println(subsequence.getSubsequenceLength(text1, text2));
		System.out.println(subsequence.getSubsequenceLength_spaceOptimized(text1, text2));
		System.out.println(subsequence.getSubsequenceLength(text1, text2));

		System.out.println(subsequence.getSubsequenceLengthStriverDP2D(text1, text2));
		System.out.println(subsequence.getSubsequenceLengthStriverDP1D(text1, text2));
		System.out.println(subsequence.longestCommonSubsequence(text1, text2));
	}

	// See Striver's video: https://www.youtube.com/watch?v=NPZn9jBrX8U
	private int getSubsequenceLengthStriverDP2D(String text1, String text2) {
		char[] s = text1.toCharArray();
		char[] t = text2.toCharArray();

		int m = s.length;
		int n = t.length;

		int[][] dp = new int[m+1][n+1];

		// i'th position will determine the traversal on "s"
		// j'th position will determine the traversal on "t"
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(s[i-1] == t[j-1]) { // if character matches means we found the same character in a sequence while interacting over both of char arrays
					dp[i][j] = 1 + dp[i-1][j-1]; // one character matches hence +1 with whatever result we hold in dp[i-1][j-1]
				}
				else {
					// - goto one character on left side from "s"
					// - goto one character on left side from "t"
					// - find maximum of them
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		return dp[m][n];
	}

	// See Striver's video: https://www.youtube.com/watch?v=NPZn9jBrX8U
	private int getSubsequenceLengthStriverDP1D(String text1, String text2) {
		char[] s = text1.toCharArray();
		char[] t = text2.toCharArray();

		int m = s.length;
		int n = t.length;

		int[] prev = new int[m+1];
		int[] curr = new int[m+1];

		for(int j = 0;  j<=m ; j++) {
			prev[j] = 0;
		}

		// i'th position will determine the traversal on "s"
		// j'th position will determine the traversal on "t"
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(s[i-1] == t[j-1]) { // if character matches means we found the same character in a sequence while interacting over both of char arrays
					curr[j] = 1 + prev[j-1]; // one character matches hence +1 with whatever result we hold in prev[j-1]
				}
				else {
					// - goto one character on left side from "s"
					// - goto one character on left side from "t"
					// - find maximum of them
					curr[j] = Math.max(prev[j], curr[j-1]);
				}
			}
			prev = curr;
		}

		return prev[n];
	}
	
	private int getSubsequenceLength(String text1, String text2) {
		int[][] m = new int[1001][1001];
		for(int i=0; i<text1.length(); i++)
			for(int j=0; j<text2.length(); j++)
				m[i+1][j+1] = text1.charAt(i) == text2.charAt(j) ? m[i][j]+1 : Math.max(m[i+1][j], m[i][j+1]);
		
		return m[text1.length()][text2.length()];
	}
	
	private int getSubsequenceLength_spaceOptimized(String text1, String text2) {
		int[][] m = new int[2][1001];
		for(int i=0, k = 1; i<text1.length(); i++, k^= 1)
			for(int j=0; j<text2.length(); j++)
				m[k][j+1] = text1.charAt(i) == text2.charAt(j) ? m[k^1][j]+1 : Math.max(m[k][j], m[k^1][j+1]);
		
		return m[text1.length()%2][text2.length()];
	}
	
	public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

		int m = text1.length();
        int n = text2.length();

		int[] prev = new int[m+1];
        int[] curi = new int[m+1];
        
        for(int j = n-1; j >= 0; j--){
            for(int i = m-1; i >=0; i--){
                if(t1[i] == t2[j])
                    curi[i] = 1 + prev[i+1];
                else
                    curi[i] = Math.max(prev[i], curi[i+1]);
            }
            int[] temp = prev;
            prev = curi;
            curi = temp;
        }
        
        return prev[0];
    }
}
