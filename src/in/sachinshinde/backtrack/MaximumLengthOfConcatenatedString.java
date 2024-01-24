package in.sachinshinde.backtrack;

import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/

/*
 	You are given an array of strings arr. 
 	A string s is formed by the concatenation of a subsequence of arr that has unique characters.

        Return the maximum possible length of s.
        
        A subsequence is an array that can be derived from another array by deleting some or no elements 
        	without changing the order of the remaining elements.
        
        
        Example 1:
        ---------
        Input: arr = ["un","iq","ue"]
        Output: 4
        Explanation: All the valid concatenations are:
                    - ""
                    - "un"
                    - "iq"
                    - "ue"
                    - "uniq" ("un" + "iq")
                    - "ique" ("iq" + "ue")
        Maximum length is 4.
        
        
        Example 2:
        ---------
        Input: arr = ["cha","r","act","ers"]
        Output: 6
        Explanation: Possible longest valid concatenations are 
        	"chaers" ("cha" + "ers") and 
        	"acters" ("act" + "ers").
        
        
        Example 3:
        ---------
        Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
        Output: 26
        Explanation: The only string in arr has all 26 characters.
         
        
        Constraints:
        -----------
            1 <= arr.length <= 16
            1 <= arr[i].length <= 26
            arr[i] contains only lowercase English letters.
 */

public class MaximumLengthOfConcatenatedString {
    public int maxLength(List<String> arr) {
        int[] maxLength = new int[1];
        maxUnique(arr, 0, "", maxLength);
        return maxLength[0];
    }
    
    private void maxUnique(List<String> arr, int start, String currStr, int[] maxLength) {
        if(start == arr.size() && containsUniqueChars(currStr) && currStr.length() > maxLength[0]) {
            maxLength[0] = currStr.length();
            return;
        }
        if(start == arr.size()) {
            return;
        }
        maxUnique(arr, start + 1, currStr, maxLength);
        maxUnique(arr, start + 1, currStr + arr.get(start), maxLength);           
    }
    
    private boolean containsUniqueChars(String currStr) {
        int[] counts = new int[26];
        for(char ch: currStr.toCharArray()) {
            if(counts[ch - 'a'] > 0) {
                return false;
            }
            counts[ch - 'a']++;
        }
        return true;
    }
    
    // -------------------------------------------------------------------------------------
    
    public int maxLength2(List<String> arr) {
        return backtrack(arr, 0, "");
    }
    
    private int backtrack(List<String> arr, int start, String currStr) {
        if(!containsUniqueChars(currStr)) {
            return 0;
        }

        int maxLength = currStr.length();
        
        for(int i=start; i<arr.size(); i++) {
            maxLength = Math.max(maxLength, backtrack(arr, i + 1, currStr + arr.get(i)));
        }
        
        return maxLength;
    }
    
    // -------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	MaximumLengthOfConcatenatedString string = new MaximumLengthOfConcatenatedString();
	System.out.println(string.maxLength(Arrays.asList("un","iq","ue")));		    // 4
	System.out.println(string.maxLength(Arrays.asList("cha","r","act","ers")));	    // 6
	System.out.println(string.maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));  // 26
	
	System.out.println(string.maxLength2(Arrays.asList("un","iq","ue")));		    // 4
	System.out.println(string.maxLength2(Arrays.asList("cha","r","act","ers")));	    // 6
	System.out.println(string.maxLength2(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));  // 26
    }
    
}
