package in.sachinshinde.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
	
	//	using "Boyer-Moore Voting Algorithm", Time: O(N), Space: O(1)
	/*
	 	How it works ?
	 	---> 
	 		 - 	Maintain a count, which is 
	 		 		incremented whenever we see an instance of our current candidate for majority element and 
	 		 		decremented whenever we see anything else. 
	 		 -	Whenever count equals 0, 
	 		 		we effectively forget about everything in nums up to the current index and 
	 		 		consider the current number as the candidate for majority element. 
	 */
	
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for(int num : nums) {
            if(count == 0) {
                candidate = num;
            }
            
            if(candidate == num) 
            	count++;
            else
            	count--;
        }

        return candidate;
    }
    
    public static void main(String[] args) {
		MajorityElement majorityElement = new MajorityElement();
		System.out.println(majorityElement.majorityElement(new int[] {2,3,3}));	//	3
		System.out.println(majorityElement.majorityElement(new int[] {2,3,2}));	//	2
		System.out.println(majorityElement.majorityElement(new int[] {2,3,2,2}));	//	2
		
		System.out.println(majorityElement.majorityElement2(new int[] {2,3,3}));	//	3
		System.out.println(majorityElement.majorityElement2(new int[] {2,3,2}));	//	2
		System.out.println(majorityElement.majorityElement2(new int[] {2,3,2,2}));	//	2
		
		System.out.println(majorityElement.majorityElement3(new int[] {2,3,3}));	//	3
		System.out.println(majorityElement.majorityElement3(new int[] {2,3,2}));	//	2
		System.out.println(majorityElement.majorityElement3(new int[] {2,3,2,2}));	//	2
	}
    
    //	using sorting, Time: O(NlogN), Space: O(N)
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
    
    //	using HashMap, Time: O(N), Space: O(N)
	public int majorityElement3(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);
        
        Map.Entry<Integer, Integer> majorityEntry = null;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet())
            if(majorityEntry == null || entry.getValue() > majorityEntry.getValue())
                majorityEntry = entry;

        return majorityEntry.getKey();
    }
	
	private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for(int num : nums)
            if(!counts.containsKey(num))
                counts.put(num, 1);
            else
                counts.put(num, counts.get(num)+1);
        
        return counts;
    }
}
