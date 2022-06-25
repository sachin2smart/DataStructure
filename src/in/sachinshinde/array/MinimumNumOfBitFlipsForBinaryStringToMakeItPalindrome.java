package in.sachinshinde.array;

//	https://www.geeksforgeeks.org/minimum-count-of-bit-flips-required-to-make-a-binary-string-palindromic/
/*
 	Given an integer N, the task is to find the minimum number of bits required to be flipped 
 		to convert the binary representation of N into a palindrome.

	Examples:
	
	Input: N = 12 
	Output: 2 
	Explanation: 
	Binary String representing 12 = “1100”. 
	To make “1100” a palindrome, convert the string to “0110”. 
	Therefore, minimum bits required to be flipped is 2.
 */

class MinimumNumOfBitFlipsForBinaryStringToMakeItPalindrome{

	private static boolean isBitSet(String str, int pos) {
		return str.charAt(pos-1)=='1';
	}
	
	private static int getNumOfBitFlips(String str) {
	  int bitFlipCount = 0;
	  int l = 1;
	  int r = str.length();
	
	  while (l < r) {
	      if(isBitSet(str,l) != isBitSet(str,r))
	    	  bitFlipCount++;
	     r--;
	     l++;
	  }
	  return bitFlipCount;
	}
	
	public static void main(String[] args) {
	  String str = "0100111";
	  System.out.println(getNumOfBitFlips(str));
	}
}