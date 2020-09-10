package in.sachinshinde.bst;

import java.util.Arrays;

public class UniqueBSTs {

	static int numberOfUniqueBSTs(int numOfnodes) {
		int dp[] = new int[numOfnodes+1];
		Arrays.fill(dp, 0);
		
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=2; i<=numOfnodes; i++) 
			for(int j=1; j<=i; j++) 
				dp[i] = dp[i] + (dp[i-j]*dp[j-1]);

		return dp[numOfnodes];
	}
	
	public static void main(String args[]) {
		int k= 4;
		System.out.println(" The number of unique BSTs for " + k + " are " + numberOfUniqueBSTs(k));
	}
}
