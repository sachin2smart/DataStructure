package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreatestCommonDivisorTraversal {

    public boolean canTraverseAllPairs(int[] nums) {
	List<List<Integer>> adjList = new ArrayList<>();
	Map<Integer, List<Integer>> factorsMap = new HashMap();
	
	int n = nums.length;
	
	for(int i=0; i<n; i++) {
	    adjList = new ArrayList<>();
	}
	
	for(int i=0; i<n; i++) {
	    mapAllPrimeFactors(i, nums, factorsMap);
	}
	
	return true;
    }
    
    private void mapAllPrimeFactors(int i, int[] nums, Map<Integer, List<Integer>> factorsMap) {
	
	
    }

    public static void main(String[] args) {
	
    }
}
