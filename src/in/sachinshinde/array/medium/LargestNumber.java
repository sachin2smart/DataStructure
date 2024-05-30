package in.sachinshinde.array.medium;

import java.util.Arrays;

//	https://leetcode.com/problems/largest-number/

/*
 	Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

        Since the result may be very large, so you need to return a string instead of an integer.
        
        Example 1:
        Input: nums = [10,2]
        Output: "210"
        
        Example 2:
        Input: nums = [3,30,34,5,9]
        Output: "9534330"
 */

public class LargestNumber {

	public String largestNumber(int[] nums) {
		if(nums == null || nums.length == 0) {
			return "";
		}

		// Convert int array to String array, so we can sort later on
		String[] stringArray = new String[nums.length];
		for(int i = 0; i < nums.length; i++) {
			stringArray[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(stringArray, (x,y) -> (y+x).compareTo(x+y));

		// If only a bunch of 0 in your int array
		if(stringArray[0].charAt(0) == '0') {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		for(String s: stringArray) {
			sb.append(s);
		}

		return sb.toString();
	}
    
    public String largestNumber2(int[] nums) {
        String[] s = new String[nums.length];         
        for(int i=0; i<nums.length; i++)  s[i] = String.valueOf(nums[i]);
        Arrays.sort(s, (a,b) -> (b + a).compareTo(a + b));
        return s[0].equals("0") ? "0" : String.join("",s);
    }
    
    public String largestNumber3(int[] nums) {
		String[] stringArray = Arrays.stream(nums)
			.mapToObj(String::valueOf)
			.toArray(String[]::new);

		Arrays.sort(stringArray,
			  (String s1, String s2) -> (s2+s1).compareTo(s1+s2));

		return Arrays
			.stream(stringArray)
			.reduce((x,y)-> x.equals("0") ? y : x+y)
			.get();
    }
    
    public static void main(String[] args) {
		LargestNumber largestNumber = new LargestNumber();

		System.out.println(largestNumber.largestNumber(new int[] {10,2}));	//	"210"
		System.out.println(largestNumber.largestNumber2(new int[] {10,2}));	//	"210"
		System.out.println(largestNumber.largestNumber3(new int[] {10,2}));	//	"210"

		System.out.println(largestNumber.largestNumber(new int[] {3,30,34,5,9}));	//	"9534330"
		System.out.println(largestNumber.largestNumber2(new int[] {3,30,34,5,9}));	//	"9534330"
		System.out.println(largestNumber.largestNumber3(new int[] {3,30,34,5,9}));	//	"9534330"
    }
}
