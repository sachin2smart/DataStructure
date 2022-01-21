package in.sachinshinde.recursion;

//	https://leetcode.com/problems/decode-string/

/*
 * 	Decode String
 * 	Given an encoded string, return its decoded string.
	The encoding rule is: k[encoded_string], 
		where the encoded_string inside the square brackets is being repeated exactly k times. 
		Note that k is guaranteed to be a positive integer.

	You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.

	Furthermore, you may assume that the original data does not contain any digits 
	and that digits are only for those repeat numbers, k. 
	For example, there will not be input like 3a or 2[4].
 */

public class DecodeString {

    public static String decodeString(String s) {
    	int[] i = new int[]{0}; // maintains the index we're at through out the recursion. 
        return decode(s, i);
    }

	private static String decode(String s, int[] i) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        while(i[0] < s.length()){
            char ch = s.charAt(i[0]);
            i[0]++; 
            
            if(Character.isDigit(ch)){
                num = num * 10+ (ch -'0'); // if curr char is a number, store it
            }
            else if(ch == '['){
                String innerStr = decode(s, i); // start recursion to solve sub problem. Returns the string between [ and ].
                while(num != 0){ 
                    sb.append(innerStr); // multiply returned string with the num. 
                    num--; // num resets to 0.
                }
            }
            else if(ch == ']'){ // At this point, we're finishing up a recursive call. 
                break;  // break from the sub call and return innerString.
            }
            else{
                sb.append(ch); // appends any extra characters that do not need multiplying Ex: abc2[a], a2[b]a
            }
        }
        return sb.toString();
	}
    
    
    
}
