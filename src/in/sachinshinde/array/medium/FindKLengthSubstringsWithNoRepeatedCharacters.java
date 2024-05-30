package in.sachinshinde.array.medium;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.ca/all/1100.html

/*
	Given a string S, return the number of substrings of length K with no repeated characters.

	Example 1:
		
		Input: S = �havefunonleetcode�, K = 5
		Output: 6
		Explanation: There are 6 substrings they are:
					�havef�,�avefu�,�vefun�,�efuno�,�etcod�,�tcode�.
	
	Example 2:
		Input: S = �home�, K = 5
		Output: 0
		Explanation:
			Notice K can be larger than the length of S. 
			In this case is not possible to find any substring.
	
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {
	public int numKLenSubstrNoRepeats(String s, int k) {
		if(s.length() < k) {
			return 0;
		}
	    Set<Character> hs = new HashSet<>();
	    int count = 0; 
	    int j = 0;
	    
	    for(int i = 0; i < s.length(); i++) {
	      while(hs.contains(s.charAt(i))) {
			  hs.remove(s.charAt(j++));    //	to maintain hs of k length
		  }
	      
	      hs.add(s.charAt(i));

	      if(i - j + 1 >= k) {
			  count++;
		  }
	    }

	    return count;
	}
	
	public static void main(String[] args) {
		FindKLengthSubstringsWithNoRepeatedCharacters str = 
				new FindKLengthSubstringsWithNoRepeatedCharacters();
		
		System.out.println(str.numKLenSubstrNoRepeats("havefunonleetcode", 5));	//	6
		System.out.println(str.numKLenSubstrNoRepeats("home", 5));	//	0
		System.out.println(str.numKLenSubstrNoRepeats("aaaaaaaaaa", 2));	//	0
		System.out.println(str.numKLenSubstrNoRepeats("ababababab", 2));	//	9

		System.out.println(str.numKLenSubstrNoRepeats2("havefunonleetcode", 5));	//	6
		System.out.println(str.numKLenSubstrNoRepeats2("home", 5));	//	0
		System.out.println(str.numKLenSubstrNoRepeats2("aaaaaaaaaa", 2));	//	0
		System.out.println(str.numKLenSubstrNoRepeats2("ababababab", 2));	//	9

	}

	//	https://leetcode.ca/2018-12-04-1100-Find-K-Length-Substrings-With-No-Repeated-Characters/
	public int numKLenSubstrNoRepeats2(String s, int k) {
		// initial checks
		if (k > s.length() || k > 26) {	// since lowercase english chars be at most 26 unique
			return 0;
		}

		int[] cnt = new int[128];	// why 128 ?
		int ans = 0;

		//	i=rightPointer, j=leftPointer, i-j+1 = windowSize
		for (int i = 0, j = 0; i < s.length(); i++) {
			cnt[s.charAt(i)]++;
			while (cnt[s.charAt(i)] > 1 || (i - j + 1) > k) {
				cnt[s.charAt(j++)]--;
			}
			ans += (i - j + 1) == k ? 1 : 0;	// if window size == k then increment ans
		}
		return ans;
	}
}
