package in.sachinshinde.dp.coinchange;

//	https://leetcode.com/problems/target-sum/

/*
 * 	Target Sum:
 * 		You are given an integer array nums and an integer target.
 * 		You want to build an expression out of nums 
 * 			by adding one of the symbols '+' and '-' before each integer in nums 
 * 			and then concatenate all the integers.
 * 
 * 		For example, if nums = [2, 1], 
 * 			you can add a '+' before 2 and a '-' before 1 
 * 			and concatenate them to build the expression "+2-1".
 * 
 * 		Return the number of different expressions that you can build, which evaluates to target.	
 */
	
public class TargetSum {
	
	public static int findTargetSumWays(int[] nums, int targetSum) {
		int start = 0;
		int currSum = 0;
        return aux(nums, start, currSum, targetSum);
	}
	
	private static int aux(int[] nums, int i, int currSum, int targetSum){
        if(i == nums.length){
            if(currSum == targetSum)
            	return 1;
            return 0;
        }
        int pos=aux(nums, i+1, currSum+nums[i], targetSum);
        int neg=aux(nums, i+1, currSum-nums[i], targetSum);
        
        return pos + neg;
    }
	
	public static void main(String[] args) {
		/*
		 * Input: nums = [1,1,1,1,1], target = 3
			Output: 5
			Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
			-1 + 1 + 1 + 1 + 1 = 3
			+1 - 1 + 1 + 1 + 1 = 3
			+1 + 1 - 1 + 1 + 1 = 3
			+1 + 1 + 1 - 1 + 1 = 3
			+1 + 1 + 1 + 1 - 1 = 3
		 */
		
		System.out.println(findTargetSumWays(new int[] {1,1,1,1,1}, 3));	//	5
		System.out.println(findTargetSumWays_nsTime(new int[] {1,1,1,1,1}, 3));	//	5
		
		System.out.println(findTargetSumWays_nsTime(new int[] {1}, 1));	//	1
	}

	//	ts = target sum 
	//	mts = modifiedTargetSum 
    
	public static int findTargetSumWays_nsTime(int[] nums, int ts) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < ts || (ts + sum) % 2 == 1 ? 
        		0 : subsetSum(nums, (ts + sum) / 2); 
    }   

    public static int subsetSum(int[] nums, int mts) {
        int[] dp = new int[mts + 1]; 
        dp[0] = 1;
        
        for (int num : nums)
            for (int i = mts; i >= num; i--)
                dp[i] += dp[i - num]; // similar like coin-change problem dp[i] = dp[i] + dp[i - coin]
        
        return dp[mts];
    } 
 
}

/*
 	P = Positive Numbers, N = Negative Numbers 
 	
 	sum(P) - sum(N) = target
	sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    2 * sum(P) = target + sum(nums) 
    sum(P) = (target + sum(nums))/2

	Case : 
		(i)  sum(nums) must always be greater than target
		(ii) target + sum(nums) must always be an even number 
	
 */