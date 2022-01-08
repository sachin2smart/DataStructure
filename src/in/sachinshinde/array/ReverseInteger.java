package in.sachinshinde.array;

// https://leetcode.com/problems/reverse-integer/

/*
 *	Given a signed 32-bit integer x, return x with its digits reversed. 
 *	If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0. 
 *
	Input: x = 123
	Output: 321
	
	Input: x = -123
	Output: -321
	
	Input: x = 120
	Output: 21
 
 */

public class ReverseInteger {
	
	 public static int reverse(int x) {
        int result = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            
            if ((newResult - tail) / 10 != result)
                return 0;
            
            result = newResult;
            x = x / 10;
        }

        return result;
    }
	 
	public static void main(String[] args) {
		System.out.println(reverse(321));	//123
		System.out.println(reverse(-321));	//-123
		System.out.println(reverse(120));	//21
		System.out.println(reverse(-120));	//-21
		System.out.println(reverse(2147483645));	//0
	}
}
