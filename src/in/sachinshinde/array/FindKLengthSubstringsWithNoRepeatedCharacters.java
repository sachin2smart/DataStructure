package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

/*
	Given a string S, return the number of substrings of length K with no repeated characters.

	Example 1:
		
		Input: S = “havefunonleetcode”, K = 5
		Output: 6
		Explanation: There are 6 substrings they are:
					‘havef’,’avefu’,’vefun’,’efuno’,’etcod’,’tcode’.
	
	Example 2:
		Input: S = “home”, K = 5
		Output: 0
		Explanation:
			Notice K can be larger than the length of S. 
			In this case is not possible to find any substring.
	
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {
	public int numKLenSubstrNoRepeats(String s, int k) {
	    Set<Character> hs = new HashSet<>();
	    int count = 0; 
	    int j = 0;
	    
	    for(int i = 0; i < s.length(); i++) {
	      while(hs.contains(s.charAt(i)))
	    	  hs.remove(s.charAt(j++));	//	to maintain hs of k length
	      
	      hs.add(s.charAt(i));

	      if(i - j + 1 >= k)
	        count++;
	    }

	    return count;
	}
	
	public static void main(String[] args) {
		FindKLengthSubstringsWithNoRepeatedCharacters str = 
				new FindKLengthSubstringsWithNoRepeatedCharacters();
		
		System.out.println(str.numKLenSubstrNoRepeats("havefunonleetcode", 5));	//	6
		System.out.println(str.numKLenSubstrNoRepeats("home", 5));	//	0
	}
}
