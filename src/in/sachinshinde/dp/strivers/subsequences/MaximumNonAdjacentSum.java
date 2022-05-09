package in.sachinshinde.dp.strivers.subsequences;

import java.util.ArrayList;
import java.util.List;

//	https://www.codingninjas.com/codestudio/problems/maximum-sum-of-non-adjacent-elements_843261?leftPanelTab=0
//	https://www.youtube.com/watch?v=GrMBfJNk_NY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=6

public class MaximumNonAdjacentSum {

	public static void main(String[] args) {
		MaximumNonAdjacentSum sum = new MaximumNonAdjacentSum();
		List<Integer> nums = new ArrayList<Integer>(List.of(1,7,5));
		System.out.println(sum.getSum_naive(nums));
		System.out.println(sum.getSum_nonTLE(nums));
		System.out.println(sum.getSum_tabulation(nums));
		System.out.println(sum.getSum_spaceOptimization(nums));
	}
	
	// Method -1 
	private int getSum_naive(List<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		f_naive(n-1, nums, dp);
		return dp[n-1];
	}

	private int f_naive(int ind, List<Integer> nums, int[] dp) {
		if(ind==0) return nums.get(0);
		if(ind<0) return 0;
		
		if(dp[ind] != 0)
			return dp[ind];
		
		int pick = nums.get(ind) + f_naive(ind-2, nums, dp);
		int notPick = 0 + f_naive(ind-1, nums, dp);
		
		return dp[ind] = Math.max(pick, notPick);
	}

	// Method - 2
	public int getSum_nonTLE(List<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		return f_nonTLE(n-1, nums, dp);
	}

	private int f_nonTLE(int ind, List<Integer> nums, int[] dp) {
		if(ind==0) return nums.get(0);
		if(ind<0) return 0;
		
		if(dp[ind] != 0)
			return dp[ind];
		
		int pick = nums.get(ind) + f_nonTLE(ind-2, nums, dp);
		int notPick = 0 + f_nonTLE(ind-1, nums, dp);
		
		return dp[ind] = Math.max(pick, notPick);
	}
	
	//	Method -3 
	private int getSum_tabulation(List<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		
		dp[0] = nums.get(0);
		
		for(int i=1; i<n; i++) {
			int take = nums.get(i);
			if(i>1)
				take += dp[i-2];
			int nonTake = 0 + dp[i-1];
			dp[i] = Math.max(take, nonTake);
		}
		
		return dp[n-1];
	}
	
//	Method -4 
	private int getSum_spaceOptimization(List<Integer> nums) {
		int n = nums.size();
		int prev = nums.get(0);
		int prev2 = 0;
		
		for(int i=1; i<n; i++) {
			int take = nums.get(i);
			if(i>1)
				take += prev2;
			
			int nonTake = 0 + prev;
			
			int curi = Math.max(take, nonTake);
			prev2 = prev;
			prev = curi;
		}
		
		return prev;
	}
}
