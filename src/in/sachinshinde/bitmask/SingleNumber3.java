package in.sachinshinde.bitmask;

import java.util.Arrays;

//	https://leetcode.com/problems/single-number-iii/

public class SingleNumber3 {
	 private int[] singleNumber(int[] nums) { 
        int diff = 0;
        
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        for (int num : nums) {
            diff = diff ^ num;
        }
        
        // Get its last set bit
        diff = diff & -diff;
        
        int[] res = {0, 0};
        
        // Pass 2 :
        for (int num : nums) {
            if ((num & diff) == 0) { // the bit is not set
                res[0] = res[0] ^ num;
            }
            else { // the bit is set
                res[1] = res[1] ^ num;
            }
        }
        return res;
    }
	 
	public static void main(String[] args) {
		SingleNumber3 singleNumber3 = new SingleNumber3();
		System.out.println(Arrays.toString(singleNumber3.singleNumber(new int[] {1,1,2,2,3,4})));
	}
}