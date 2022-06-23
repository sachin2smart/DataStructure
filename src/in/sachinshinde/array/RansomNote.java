package in.sachinshinde.array;

//	https://leetcode.com/problems/ransom-note/


/*
  	Given two strings ransomNote and magazine, 
  		return true if ransomNote can be constructed by using the letters from magazine 
  		and false otherwise.

	Each letter in magazine can only be used once in ransomNote.	
*/

/*
 	Example 1:

	Input: ransomNote = "a", magazine = "b"
	Output: false
	Example 2:
	
	Input: ransomNote = "aa", magazine = "ab"
	Output: false
	Example 3:
	
	Input: ransomNote = "aa", magazine = "aab"
	Output: true
 */
	
public class RansomNote {
	
	public boolean canConstruct(String ransomNote, String magazine) {
        int[] alphabates = new int[26];
        for(char c: magazine.toCharArray()) 
        	alphabates[c-'a']++;
        for(char c: ransomNote.toCharArray()) 
        	if(--alphabates[c-'a'] < 0)
        		return false;
        return true;
    }
	
	public static void main(String[] args) {
		RansomNote ransomNote = new RansomNote();
		System.out.println(ransomNote.canConstruct("a", "b"));	//	false
		System.out.println(ransomNote.canConstruct("aa", "ab"));	//	false
		System.out.println(ransomNote.canConstruct("aa", "aab"));	//	true
	}
}
