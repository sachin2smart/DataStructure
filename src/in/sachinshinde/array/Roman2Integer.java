package in.sachinshinde.array;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/roman-to-integer/

/*
 	Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

	Symbol       Value
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000

	For example, 2 is written as II in Roman numeral, just two ones added together.
	12 is written as XII, which is simply X + II. 
	The number 27 is written as XXVII, which is XX + V + II.

	Roman numerals are usually written largest to smallest from left to right.
	However, the numeral for four is not IIII. 
	Instead, the number four is written as IV. 
	Because the one is before the five we subtract it making four. 
	The same principle applies to the number nine, which is written as IX. 
	
	There are six instances where subtraction is used:
	I can be placed before V (5) and X (10) to make 4 and 9. 
	X can be placed before L (50) and C (100) to make 40 and 90. 
	C can be placed before D (500) and M (1000) to make 400 and 900.
	
	Given a roman numeral, convert it to an integer.

	Example 1:
		Input: s = "III"
		Output: 3
		Explanation: III = 3.

	Example 2:
		Input: s = "LVIII"
		Output: 58
		Explanation: L = 50, V= 5, III = 3.

	Example 3:
		Input: s = "MCMXCIV"
		Output: 1994
		Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 */


public class Roman2Integer {

	public int romanToInt(String s) {
        if(s == null || s.length() == 0)
            return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        int sum = 0;
        int prev = map.get(s.charAt(0));
        int next = 0;

        for(int i=1; i < s.length(); i++) {
            next = map.get(s.charAt(i));

            if(prev < next)
                sum -= prev;
            else
                sum += prev;

            prev = next;	//	update prev, move left pointer to next number (Sliding Window)
        }

        sum += prev;	//	add the value of the last roman character, which is stored in "prev" pointer
        return sum;
    }
	
	public static void main(String[] args) {
		Roman2Integer roman2Integer = new Roman2Integer();
		System.out.println("MCMXCIV : "+roman2Integer.romanToInt("MCMXCIV"));	//	1994
		System.out.println("LVIII : "+roman2Integer.romanToInt("LVIII"));	//	58
		System.out.println("III : "+roman2Integer.romanToInt("III"));	//	3
		
		System.out.println("CD : "+roman2Integer.romanToInt("CD"));	//	400
		System.out.println("CCCC : "+roman2Integer.romanToInt("CCCC"));	//	400
		
//		-------
		
		System.out.println("MCMXCIV : "+roman2Integer.romanToInt2("MCMXCIV"));	//	1994
		System.out.println("LVIII : "+roman2Integer.romanToInt2("LVIII"));	//	58
		System.out.println("III : "+roman2Integer.romanToInt2("III"));	//	3
		
		System.out.println("CD : "+roman2Integer.romanToInt2("CD"));	//	400
		System.out.println("CCCC : "+roman2Integer.romanToInt2("CCCC"));	//	400
		
//		-------
		
		System.out.println("MCMXCIV : "+roman2Integer.romanToInt3("MCMXCIV"));	//	1994
		System.out.println("LVIII : "+roman2Integer.romanToInt3("LVIII"));	//	58
		System.out.println("III : "+roman2Integer.romanToInt3("III"));	//	3
		
		System.out.println("CD : "+roman2Integer.romanToInt3("CD"));	//	400
		System.out.println("CCCC : "+roman2Integer.romanToInt3("CCCC"));	//	400
	}
	
//	--------
	public int romanToInt2(String S) {
        int ans = 0, num = 0;
        for(int i = S.length()-1; i >= 0; i--) {
            switch(S.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if(4 * num < ans) 	//	why 4*num ?
            	ans -= num;
            else 
            	ans += num;
        }
        return ans;
    }
	
//	---------
	 private int getValue(char c) {
		 if(c=='I') 
			 return 1;
	     else if(c=='V')
	    	 return 5;
	     else if(c=='X') 
	    	 return 10;
	     else if(c=='L')
	    	 return 50;
	     else if(c=='C')
	    	 return 100;
	     else if(c=='D')
	    	 return 500;
	     else
	    	 return 1000;
	   }
	
	 public int romanToInt3(String s) {
		 int n = s.length(), res =0;
		 for(int i=0;i<n-1;i++){
	         int a = getValue(s.charAt(i));
	         int b = getValue(s.charAt(i+1));
	         if(a<b)
	             res-= a;
	         else
	             res+= a;
		 }
		 return res + getValue(s.charAt(n-1));
	 }
}