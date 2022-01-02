package in.sachinshinde.array;

//	https://leetcode.com/problems/remove-duplicates-from-sorted-array/

public class RemoveDuplicatesInPlace {

	private static int removeDuplicates(int[] nums) {
		int i = 0;
		for(int n: nums)
			if(i==0 || n > nums[i-1])
				nums[i++] = n;
		
		return i;
	}
	
	public static void main(String[] args) {
		int k = removeDuplicates(new int[] {0,0,0,0,1,1,2,3,3});
		System.out.println("\n"+k);
		
		k = removeDups(new int[] {0,0,0,0,1,1,2,3,3});
		System.out.println("\n"+k);
	}
	
	private static int removeDups(int[] nums) {
		if (nums.length == 0) 
            return 0;
        
        int i = 0;
        
        for (int j = 1; j < nums.length; j++)
            if (nums[j] != nums[i]) 
                nums[++i] = nums[j];
        
        return i + 1;
	}
}
