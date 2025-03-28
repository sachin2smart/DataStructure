package in.sachinshinde.array;

//	https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/submissions/

/*
 	A string is good if there are no repeated characters.

	Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.

	Note that if there are multiple occurrences of the same substring, 
		every occurrence should be counted.

	A substring is a contiguous sequence of characters in a string.

 
	Example 1:
	---------
	Input: s = "xyzzaz"
	Output: 1
	Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
				The only good substring of length 3 is "xyz".
	
	Example 2:
	---------
		Input: s = "aababcabc"
		Output: 4
		Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
					The good substrings are "abc", "bca", "cab", and "abc".
 */

public class SubstringsOfSize3WithDistinctCharacters {
	public int countGoodSubstrings(String s) {
		int count = 0;
		
		for(int i = 1; i < s.length() - 1; i++)
			if(s.charAt(i-1) != s.charAt(i) && 
				s.charAt(i+1) != s.charAt(i) && 
				s.charAt(i+1) != s.charAt(i-1))
				count++;
		
		return count;
	}
	
	public int countGoodSubstrings2(String s) {
        if(s.length() < 3)
        	return 0;
        
        char a=s.charAt(0);
        char b=s.charAt(1);
        char c=s.charAt(2);
        
        int count=0;
        
        for(int i = 3; i <= s.length() - 1; i++) {
            if(a != b && b != c && c != a)
            	count++;
            
            a = b;
            b = c;
            c = s.charAt(i);
        }
        
        if(a != b && b != c && c != a)
        	count++;
        
        return count;
    }
	
	public static void main(String[] args) {
		SubstringsOfSize3WithDistinctCharacters str = 
				new SubstringsOfSize3WithDistinctCharacters();
		
		System.out.println(str.countGoodSubstrings("xyzzyzy"));		//	1
		System.out.println(str.countGoodSubstrings2("xyzzyzy"));	//	1
	}
}
