package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsWithDups {
	
	private static List<List<Integer>> subsetsWithDupsAllPermutations(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(nums, new boolean[nums.length], new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void constructSubsets(int[] nums, boolean[] used, List<Integer> tempList, List<List<Integer>> subsets) {
    	if(tempList.size() == nums.length) {
    		subsets.add(new ArrayList<>(tempList));
    	}
    	else {
	        for(int i=0; i<nums.length; i++){		// starts with zero
	        	if(used[i] || i>0 &&  nums[i] == nums[i-1] && used[i-1]) 
	        		continue; // element already exists, skip
	        	used[i] = true;
	            tempList.add(nums[i]);
	            constructSubsets(nums, used, tempList, subsets);
	            used[i] = false;
	            tempList.remove(tempList.size()-1);
	        }
    	}
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {3,3,0,3};
        List<List<Integer>> ans = subsetsWithDupsAllPermutations(nums);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+", "+tupple.get(3)+" ] ");
    }
}
