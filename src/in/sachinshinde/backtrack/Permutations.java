package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//	https://leetcode.com/problems/permutations/

/*
 * 	Given an array nums of distinct integers, return all the possible permutations. 
 * 	You can return the answer in any order.
 */

/*
        Example 1:
        ---------
        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        Example 2:
        ---------
        Input: nums = [0,1]
        Output: [[0,1],[1,0]]

        Example 3:
        ---------
        Input: nums = [1]
        Output: [[1]]


        Constraints:
        ---------
            1 <= nums.length <= 6
            -10 <= nums[i] <= 10
            All the integers of nums are unique.
 */

public class Permutations {
		
	private static List<List<Integer>> subsetsWithAllPermutations(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void backtrack(int[] nums, List<Integer> currList, List<List<Integer>> subsets) {
        // search pruning
    	if(currList.size() == nums.length) {    //  * List ==> size() ;;;  * Array ==> length
    		subsets.add(new ArrayList<>(currList));
    	}
    	else {
            for (int num : nums) {
                // early termination
                if (currList.contains(num)) {
                    continue; // element already exists, skip
                }
                currList.add(num);  // set
                backtrack(nums, currList, subsets);     //  recursive backtracking
                currList.remove(currList.size() - 1); // unset (backtrack)
            }
    	}
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> ans = subsetsWithAllPermutations(nums);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");

        System.out.println("---------------------");
        Permutations permutations = new Permutations();
        ans = permutations.permute2(nums);

        for(List<Integer> tupple: ans) {
            System.out.println("[ " + tupple.get(0) + " , " + tupple.get(1) + " , " + tupple.get(2) + " ] ");
        }

    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generate(nums, 0, result);
        return result;
    }

    public void generate(int[] nums, int i, List<List<Integer>> result) {
        if (i == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num: nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, j , i);  //  change by swap
            generate(nums, i + 1, result);
            swap(nums, j, i);   //  revert to original by swapping again
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
