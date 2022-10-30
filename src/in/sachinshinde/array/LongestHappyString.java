package in.sachinshinde.array;

//	https://leetcode.com/problems/longest-happy-string/

/*
 * 	A string s is called happy if it satisfies the following conditions:

        s only contains the letters 'a', 'b', and 'c'.
        s does not contain any of "aaa", "bbb", or "ccc" as a substring.
        s contains at most a occurrences of the letter 'a'.
        s contains at most b occurrences of the letter 'b'.
        s contains at most c occurrences of the letter 'c'.
        
        Given three integers a, b, and c, return the longest possible happy string. 
        If there are multiple longest happy strings, return any of them. 
        If there is no such string, return the empty string "".
        
        A substring is a contiguous sequence of characters within a string.
 */

/*
 *	Example 1:
        Input: a = 1, b = 1, c = 7
        Output: "ccaccbcc"
        Explanation: "ccbccacc" would also be a correct answer.
        
        Example 2:
        Input: a = 7, b = 1, c = 0
        Output: "aabaa"
        Explanation: It is the only correct answer in this case.
 */

public class LongestHappyString {

    String generate(int a, int b, int c, String _a, String _b, String _c) {
	if (a < b)
	    return generate(b, a, c, _b, _a, _c);
	    
	if (b < c)
	    return generate(a, c, b, _a, _c, _b);
	    
	if (b == 0)
	    return _a.repeat(Math.min(2, a));
	    
	int a_repeated = Math.min(2, a);
	int b_repeated = a - a_repeated >= b ? 1 : 0;
	    
	return _a.repeat(a_repeated) + _b.repeat(b_repeated) +
	        generate(a - a_repeated, b - b_repeated, c, _a, _b, _c);    
    }
    
    public String longestDiverseString(int a, int b, int c) {
	return generate(a, b, c, "a", "b", "c");
    }
    
    public static void main(String[] args) {
	LongestHappyString string = new LongestHappyString();
	System.out.println(string.longestDiverseString(1, 1, 7));	//	ccaccbcc / ccbccacc	
	System.out.println(string.longestDiverseString(7, 1, 0));	//	aabaa
    }
    
    public String longestDiverseString2(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        
        int totalLength = a + b + c;
        int continuousA = 0, continuousB = 0, continuousC = 0;
        
        for(int i = 0; i < totalLength; i++) {
            if ((a >= b && a >= c && continuousA != 2) || (continuousB == 2 && a > 0) || (continuousC == 2 && a > 0))  {
                sb.append("a");
                a--;
                continuousA++;
                continuousB = 0;
                continuousC = 0;  
            } else if ((b >= a && b >= c && continuousB != 2) || (continuousA == 2 && b > 0) || (continuousC == 2 && b > 0)) {
                sb.append("b");
                b--;
                continuousB++;
                continuousA = 0;
                continuousC = 0;
            } else if ((c >= a && c >= b && continuousC != 2) || (continuousB == 2 && c > 0) || (continuousA == 2 && c > 0)) {
                sb.append("c");
                c--;
                continuousC++;
                continuousA = 0;
                continuousB = 0;  
            }
        }
        return sb.toString();
    }
}
