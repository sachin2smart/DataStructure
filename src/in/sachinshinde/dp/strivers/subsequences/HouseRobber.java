package in.sachinshinde.dp.strivers.subsequences;

import java.util.ArrayList;
import java.util.List;

public class HouseRobber {

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
	
	private int getSum(List<Integer> nums) {
		List<Integer> nums1 = new ArrayList<>();
		List<Integer> nums2 = new ArrayList<>();
		int n = nums.size();
		
		for(int i=0; i<n; i++) {
			if(i!=0)
				nums1.add(nums.get(i));
			if(i != n-1)
				nums2.add(nums.get(i));
		}
		
		return Math.max(getSum_spaceOptimization(nums1), getSum_spaceOptimization(nums2));
	}
	
	public static void main(String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		List<Integer> nums = new ArrayList<Integer>(List.of(1,8,7));
		System.out.println(houseRobber.getSum(nums));
	}
}
