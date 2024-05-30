package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/happy-number/

/*
 	Write an algorithm to determine if a number n is happy.
        
        A happy number is a number defined by the following process:
        
        Starting with any positive integer, replace the number by the sum of the squares of its digits.
        Repeat the process until the number equals 1 (where it will stay), 
        or it loops endlessly in a cycle which does not include 1.
        Those numbers for which this process ends in 1 are happy.
        
        Return true if n is a happy number, and false if not.
        
        Example 1:
        ---------
        Input: n = 19
        Output: true
        Explanation:
        1^2 + 9^2 = 82
        8^2 + 2^2 = 68
        6^2 + 8^2 = 100
        1^2 + 0^2 + 0^2 = 1
        
        Example 2:
        ---------
        Input: n = 2
        Output: false
         
        
        Constraints:
        -----------
        	1 <= n <= 231 - 1
 */

public class HappyNumber {
    public boolean isHappy(int n) {
		int slow = n;
		int fast = n;

		do {
		  slow = squareOf(slow);
		  fast = squareOf(squareOf(fast));
		} while(slow != fast);

		return slow == 1;
    }
    
    private int squareOf(int n) {
		int res = 0;

		while(n>0) {
			int r = n%10;
			res += r*r;
			n = n/10;
		}

		return res;
    }
    
    public boolean isHappy_2(int n) {
		Set<Integer> set = new HashSet<Integer>();

		// while loops terminates when a duplicate value is being added into the set
		while(set.add(n)) {
			int square = squareOf(n);
			if(square == 1) {
				return true;
			}
			else {
				n = square;
			}
		}

		return false;
    }
    
    public boolean isHappy_3(int n) {
        int sum = n;
        while (sum != 1 && sum!=4 ) {
            int temp = sum;
            sum = 0;
            while (temp != 0 ) {
                int digit = temp % 10;
                sum += digit * digit;
                temp =temp/ 10;
            }
        }
        return sum == 1;
    }
    
    public static void main(String[] args) {
	HappyNumber number = new HappyNumber();
	System.out.println(number.isHappy(12));
	System.out.println(number.isHappy_2(12));
	System.out.println(number.isHappy_3(12));
    }
}
