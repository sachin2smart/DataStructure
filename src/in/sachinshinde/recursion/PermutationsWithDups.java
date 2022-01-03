package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsWithDups {
	
	private static List<List<Integer>> subsetsWithDupsAllPermutations(int[] nums, int n) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(0, nums, n,new boolean[nums.length], new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void constructSubsets(int start, int[] nums, int n,boolean[] used, List<Integer> tempList, List<List<Integer>> subsets) {
    	if(tempList.size() == n) {
    		subsets.add(new ArrayList<>(tempList));
    	}
    	else {
	        for(int i=0; i<n; i++){		// starts with zero
	        	if(used[i] || i>0 && tempList.contains(nums[i]) && used[i-1]) 
	        		continue; // element already exists, skip
	        	used[i] = true;
	            tempList.add(nums[i]);
	            constructSubsets(i+1, nums, n, used, tempList, subsets);
	            used[i] = false;
	            tempList.remove(tempList.size()-1);
	        }
    	}
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {1,1,2};
        List<List<Integer>> ans = subsetsWithDupsAllPermutations(nums, nums.length);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
    }
}
