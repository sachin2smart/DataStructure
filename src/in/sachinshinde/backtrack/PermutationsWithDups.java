package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/permutations-ii/

/*
 *	Given a collection of numbers, nums, that might contain duplicates.
 *	Return all possible unique permutations in any order. 
 */

/*
		Example 1:
		---------
		Input: nums = [1,1,2]
		Output:
		[[1,1,2],
		 [1,2,1],
		 [2,1,1]]

		Example 2:
		---------
		Input: nums = [1,2,3]
		Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

public class PermutationsWithDups {
	
	private static List<List<Integer>> subsetsWithDupsAllPermutations(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void backtrack(int[] nums, boolean[] used, List<Integer> currList, List<List<Integer>> subsets) {
		// search pruning
    	if(currList.size() == nums.length) {
    		subsets.add(new ArrayList<>(currList));
    	}
    	else {
	        for(int i=0; i<nums.length; i++) {        // starts with zero
				// early termination
				if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
					continue; // element already exists, skip
				}

				used[i] = true;                // set
				currList.add(nums[i]);        // set

				backtrack(nums, used, currList, subsets);

				used[i] = false;                                // unset (backtrack)
				currList.remove(currList.size() - 1);        // unset (backtrack)
			}
    	}
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {3,3,0,3};
        List<List<Integer>> ans = subsetsWithDupsAllPermutations(nums);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+", "+tupple.get(3)+" ] ");
		/*
				ans :
						[ 0 , 3 , 3, 3 ]
						[ 3 , 0 , 3, 3 ]
						[ 3 , 3 , 0, 3 ]
						[ 3 , 3 , 3, 0 ]
		 */

		ans = subsetsWithDupsAllPermutations(new int[]{1,2,3});

		System.out.println("The subsets with all permutations ");
		for(List<Integer> tupple: ans)
			System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
		/*
				ans is:
						[ 1 , 2 , 3 ]
						[ 1 , 3 , 2 ]
						[ 2 , 1 , 3 ]
						[ 2 , 3 , 1 ]
						[ 3 , 1 , 2 ]
						[ 3 , 2 , 1 ]
		 */

		ans = subsetsWithDupsAllPermutations(new int[]{1,1,2});

		System.out.println("The subsets with all permutations ");
		for(List<Integer> tupple: ans)
			System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
		/*
				ans is:
						[ 1 , 1 , 2 ]
						[ 1 , 2 , 1 ]
						[ 2 , 1 , 1 ]
		 */

		ans = subsetsWithDupsAllPermutations(new int[]{1,1,1});

		System.out.println("The subsets with all permutations ");
		for(List<Integer> tupple: ans)
			System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
		/*
				ans is:
						[ 1 , 1 , 1 ]
		 */

	}
}
