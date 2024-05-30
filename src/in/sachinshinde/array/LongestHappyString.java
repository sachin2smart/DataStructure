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
    
    public static void main(String[] args) {
        LongestHappyString string = new LongestHappyString();
        System.out.println(string.longestDiverseString(1, 1, 7));	//	ccaccbcc / ccbccacc
        System.out.println(string.longestDiverseString(7, 1, 0));	//	aabaa
    }
    
    public String longestDiverseString(int a, int b, int c) {
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
            }
            else if ((b >= a && b >= c && continuousB != 2) || (continuousA == 2 && b > 0) || (continuousC == 2 && b > 0)) {
                sb.append("b");
                b--;
                continuousB++;
                continuousA = 0;
                continuousC = 0;
            }
            else if ((c >= a && c >= b && continuousC != 2) || (continuousB == 2 && c > 0) || (continuousA == 2 && c > 0)) {
                sb.append("c");
                c--;
                continuousC++;
                continuousA = 0;
                continuousB = 0;  
            }
        }
        return sb.toString();
    }

    public String longestDiverseString_sameAsAbove(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int total = a + b + c;
        int sA = 0, sB = 0, sC = 0;

        for(int i=0; i<total; i++) {
            if((a >= b && a >= c && sA != 2) || (sB == 2 && a > 0) || (sC == 2 && a > 0)) {
                sb.append("a");
                a--; sA++;
                sB = 0; sC = 0;
            }
            else if((b >= c && b >= a && sB != 2) || (sA == 2 && b > 0) || (sC == 2&& b > 0) ){
                sb.append("b");
                b--; sB++;
                sA = 0; sC = 0;
            }
            else if((c >= b && c >= a && sC != 2) || (sA == 2 && c > 0) || (sB == 2 && c > 0) ){
                sb.append("c");
                c--; sC++;
                sA = 0; sB = 0;
            }
        }
        return sb.toString();
    }

}