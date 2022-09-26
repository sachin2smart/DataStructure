package in.sachinshinde.array;

//	https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

/*
 	Given a string s consisting only of characters a, b and c.

	Return the number of substrings containing at least 
		one occurrence of all these characters a, b and c.

 
	---------
	Example 1:
	---------
		Input: s = "abcabc"
		Output: 10
		Explanation: The substrings containing at least one occurrence of 
			the characters a, b and c are 
			"abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", 
			"cab", "cabc" and "abc" (again). 
	---------
	Example 2:
	---------
		Input: s = "aaacb"
		Output: 3
		Explanation: The substrings containing at least one occurrence of 
			the characters a, b and c are "aaacb", "aacb" and "acb". 
	---------
	Example 3:
	---------
		Input: s = "abc"
		Output: 1
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
	public int numberOfSubstrings(String s) {
        int charFoundAt[] = new int[]{0, 0, 0};
        int count = 0 , seqStartIndex = 0, n = s.length();
        
        for(int currIndex = 0; currIndex < n; currIndex++) {
        	charFoundAt[s.charAt(currIndex) - 'a']++;
            
            while(charFoundAt[0] > 0 && 	//	if 'a' found
            		charFoundAt[1] > 0 && 	//	if 'b' found
            		charFoundAt[2] > 0)		//	if 'c' found
                charFoundAt[s.charAt(seqStartIndex++) - 'a']--;
            
            count += seqStartIndex;
        }
        
        return count;
    }
	
    public int numberOfSubstrings2(String s) {
        int last[] = {-1, -1, -1}, count = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
            count += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        return count;
    }
	
	public static void main(String[] args) {
		NumberOfSubstringsContainingAllThreeCharacters n = 
				new NumberOfSubstringsContainingAllThreeCharacters();
		
		System.out.println(n.numberOfSubstrings("abcabc"));		//	10
		System.out.println(n.numberOfSubstrings2("abcabc"));	//	10
		

		System.out.println(n.numberOfSubstrings("aaacb"));		//	3
		System.out.println(n.numberOfSubstrings2("aaacb"));		//	3
		
		System.out.println(n.numberOfSubstrings("abc"));		//	1
		System.out.println(n.numberOfSubstrings2("abc"));		//	1
	}
}
