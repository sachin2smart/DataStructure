package in.sachinshinde.array;

//	Learn : https://www.interviewcake.com/concept/java/bit-shift
//	https://leetcode.com/problems/number-of-1-bits

/*
 	Write a function that takes an unsigned integer and
 	 returns the number of '1' bits it has (also known as the Hamming weight).
 	 
 	 Example 1:
		Input: n = 00000000000000000000000000001011
		Output: 3
		Explanation: The input binary string has a total of three '1' bits.

	Example 2:
		Input: n = 00000000000000000000000010000000
		Output: 1
		Explanation: The input binary string has a total of one '1' bit.

	Example 3:
		Input: n = 11111111111111111111111111111101
		Output: 31
		Explanation: The input binary string  has a total of thirty one '1' bits.
 */

public class NumberOf1Bits {
	public static int hammingWeight(int n) {
		//	Remember:  Integer type in Java is signed and there is no unsigned integer.
		int ones = 0;
    	while(n!=0) {	//	do not use "n>0" - because the input 2147483648 would correspond to -2147483648 in java
    		ones = ones + (n & 1);	//	If the last bit is one then once count gets incremented by 1
    		n = n>>>1;	//	Right shift by 1, prepend zero while shifting
    	}
    	return ones;
	}
	
	public static void main(String[] args) {
		System.out.println(NumberOf1Bits.hammingWeight(00000000000000000000000000001011));
		System.out.println(NumberOf1Bits.hammingWeight(00000000000000000000000010000000));
//		System.out.println(NumberOf1Bits.hammingWeight(11111111111111111111111111111101));
	}
}

/*
	Line# 9 Explanation:
		To count the 1's in the Integer representation we put 
		the input integer n in bit AND with 1 (that is represented as
		00000000000000000000000000000001, and if this operation result is 1,
		that means that the last bit of the input integer is 1. Thus we add it to the 1s count.

 */
