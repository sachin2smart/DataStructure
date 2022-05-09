package in.sachinshinde.dp.strivers;

// https://leetcode.com/problems/climbing-stairs/

public class ClimbingStairs {

	public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int step = 3; step<=n; step++) {
            dp[step] = dp[step-1] + dp[step-2];
        }
        return dp[n];
    }
	
	public int climbStairs2(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
	
	public int climbStairs3(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int n2 = 2;
        int n1 = 1;
        int nf = 0;

        for(int i=2; i<n; i++){
            nf = n1 + n2;
            n1 = n2;
            n2 = nf;
        }
        return nf;
    }
	
	public static void main(String[] args) {
		ClimbingStairs climbingStairs = new ClimbingStairs();
		System.out.println(climbingStairs.climbStairs(10));		// 89
		System.out.println(climbingStairs.climbStairs2(10));	// 89
		System.out.println(climbingStairs.climbStairs3(10));    // 89
	}
}
