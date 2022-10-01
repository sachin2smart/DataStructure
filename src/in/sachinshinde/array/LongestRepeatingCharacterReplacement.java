package in.sachinshinde.array;

//	https://leetcode.com/problems/longest-repeating-character-replacement/

/*
 	You are given a string s and an integer k. 
 	You can choose any character of the string and 
 		change it to any other uppercase English character. 
 	You can perform this operation at most k times.

	Return the length of the longest substring containing the same letter 
		which you can get after performing the above operations.

	---------
        Example 1:
        ---------
            Input: 
            		s = "ABAB", 
            		k = 2
            
            Output: 4
            
            Explanation: Replace the two 'A's with two 'B's or vice versa.
        ---------
        Example 2:
        ---------
        Input: 
        	s = "AABABBA", 
        	k = 1
        Output: 4
        
        Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
        		The substring "BBBB" has the longest repeating letters, which is 4.

        		
        
        k = 2
        1. ABABAA = AAAAAA = 6
        2. ABBBAA = BBBBBA = 5
        3. ABCDAB = AAADAB = 3
        4. ABCABC = AAAABC = 4
                  = ABBBBC = 4
        
        K=3
        5. ABCABC = AAAAAC = 5
                  = ABBBBB = 5
        
 */

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
	int n = s.length();
	int[] charFreq = new int[26];

	int i = 0; // sliding window start position
	int maxFreq = 0, maxLength = 0;

	for (int j = 0; j < n; j++) {
	    maxFreq = Math.max(maxFreq, ++charFreq[s.charAt(j) - 'A']);
	    // if the num of characters are replaceable
	    while (j - i + 1 - maxFreq > k) {
		charFreq[s.charAt(i) - 'A']--;
		i++; // slide window to next
	    }
	    maxLength = Math.max(maxLength, j - i + 1);
	}
	return maxLength;
    }

    public static void main(String[] args) {
	LongestRepeatingCharacterReplacement chars = new LongestRepeatingCharacterReplacement();

	System.out.println(chars.characterReplacement("ABAB", 2)); // 4
	System.out.println(chars.characterReplacement("AABABBA", 1)); // 4
	System.out.println(chars.characterReplacement("ABABAA", 2)); // 6
	System.out.println(chars.characterReplacement("ABBBAA", 2)); // 5
	System.out.println(chars.characterReplacement("ABCDAB", 2)); // 3
	System.out.println(chars.characterReplacement("ABCABC", 2)); // 4
	System.out.println(chars.characterReplacement("ABCABC", 3)); // 5

	System.out.println(chars.characterReplacement2("ABAB", 2)); // 4
	System.out.println(chars.characterReplacement2("AABABBA", 1)); // 4
	System.out.println(chars.characterReplacement2("ABABAA", 2)); // 6
	System.out.println(chars.characterReplacement2("ABBBAA", 2)); // 5
	System.out.println(chars.characterReplacement2("ABCDAB", 2)); // 3
	System.out.println(chars.characterReplacement2("ABCABC", 2)); // 4
	System.out.println(chars.characterReplacement2("ABCABC", 3)); // 5
    }

    public int characterReplacement2(String s, int k) {
	// substring, two pointer
	// Special case
	if (s == null || s.length() == 0)
	    return 0;

	int len = s.length();
	if (len < 2)
	    return len;

	// two pointer to control the sliding window
	int left = 0, right = 0;
	// int array to maintain the frequency of chars in the slide window
	int[] freq = new int[26];
	int maxFreq = 0;
	int ans = 0;

	while (right < len) {
	    // Add s[right] in sliding window
	    int curFreq = ++freq[s.charAt(right) - 'A'];
	    maxFreq = Math.max(maxFreq, curFreq);
	    right++;

	    while (right - left > maxFreq + k) {
		freq[s.charAt(left) - 'A']--;
		left++;
	    }
	    ans = Math.max(right - left, ans);
	}

	return ans;
    }
}