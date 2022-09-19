package in.sachinshinde.dp.word_break;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//	https://leetcode.com/problems/word-break/

/*	
 	Given a string s and a dictionary of strings wordDict, 
 		return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 		
	Note that the same word in the dictionary may be reused multiple times in the segmentation.
*/

class TrieNode{
	boolean isWord;
	TrieNode[] children;
	
	TrieNode() {
		children = new TrieNode[26];
	}
}

public class WordBreak {
	//	Solution with TrieNode is the best solution 
	public boolean wordBreak(String s, List<String> wordDict) {
		if(s == null || s.length() == 0 || 
				wordDict == null || wordDict.size() == 0)
			return false;
		
		TrieNode root = buildTrie(wordDict);
		
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		
		dp[n] = true;	//	bottom-up approach (Memoization)
		
		for(int i = n-1; i >= 0; i--) {	//	from the last char to first char
			TrieNode currNode = root;
			int j = i;
			
			while(j < n) {	// from current i'th position to end of given string 
				currNode = currNode.children[s.charAt(j++) - 'a'];
		
				if(currNode == null)
					break;
				
				// below, the "&& dp[j]" checks all continuous words from previuos iterations
				// 	it means - we are memoizing the previous result for current condition 
				if(currNode.isWord && dp[j]) {	
					dp[i] = true;
					break;
				}
			}
		}
		
		return dp[0];	//	result should exists at the first element
	}

	private TrieNode buildTrie(List<String> wordDict) {
		TrieNode root = new TrieNode();
		for(String word: wordDict) {
			TrieNode currNode = root;
			for(char ch: word.toCharArray()) {
				if(currNode.children[ch - 'a'] == null)
					currNode.children[ch - 'a'] = new TrieNode();
				currNode = currNode.children[ch - 'a'];
			}
			currNode.isWord = true;
		}
		return root;
	}
	
	public static void main(String[] args) {
		WordBreak wordBreak = new WordBreak();
		
		String s = "love";
		List<String> wordList = Arrays.asList("l","o","v","e");
		//	Result : true
		System.out.println(wordBreak.wordBreak(s, wordList));
		System.out.println(wordBreak.wordBreak2(s, wordList));
		System.out.println(wordBreak.wordBreak3(s, wordList));
		
		s = "iloveaaaaleetcode";
		wordList = Arrays.asList("love","a","i","code","leet");
		//	Result : true
		System.out.println(wordBreak.wordBreak(s, wordList));
		System.out.println(wordBreak.wordBreak2(s, wordList));
		System.out.println(wordBreak.wordBreak3(s, wordList));
		
		s = "ialsolovegfg";
		wordList = Arrays.asList("love","a","i","code","leet");
		//	Result : false
		System.out.println(wordBreak.wordBreak(s, wordList));
		System.out.println(wordBreak.wordBreak2(s, wordList));
		System.out.println(wordBreak.wordBreak3(s, wordList));
	}
	
	// memoization
    HashMap<String, Boolean> map = new HashMap<>();	// we must not use "boolean" here, should be "Boolean"
    public boolean wordBreak2(String s, List<String> wordDict) {
        if(wordDict.contains(s)) 
        	return true;
        
        if(map.containsKey(s)) 
        	return map.get(s);
        
        for(int i = 0; i < s.length(); i++) {
            String prefix = s.substring(0, i + 1);
            String suffix = s.substring(i + 1);
            
            // prefix needs to be exists in wordDict
            // suffix to be checked in recursion 
            if(wordDict.contains(prefix) && 
            		wordBreak2(suffix, wordDict)) {
                map.put(s, true);
                return true;
            }
        }
        
        map.put(s, false);
        return false;
    }
    
    //	DP with 1D array
    public boolean wordBreak3(String s, List<String> wordDict) {
    	int n = s.length();
    	boolean dp[] = new boolean[n+1];
    	dp[0] = true;
    	for(int i=1; i<=n; i++) {
            for(int j=0; j<i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
