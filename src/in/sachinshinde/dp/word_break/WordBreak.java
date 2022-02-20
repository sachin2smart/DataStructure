package in.sachinshinde.dp.word_break;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
		dp[n] = true;
		
		for(int i = n-1; i >= 0; i--) {
			TrieNode currNode = root;
			int j = i;
			while(j<n) {
				currNode = currNode.children[s.charAt(j++)-'a'];
				if(currNode == null)
					break;
				if(currNode.isWord && dp[j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[0];
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
		
		String s = "iloveaaaaleetcode";
		List<String> wordList = Arrays.asList("i","love","leet", "code","a");
		
		System.out.println(wordBreak.wordBreak(s, wordList));
		System.out.println(wordBreak.wordBreak2(s, wordList));
		System.out.println(wordBreak.wordBreak3(s, wordList));
	}
	
	// memoization
    HashMap<String, Boolean> map = new HashMap<>();
    public boolean wordBreak2(String s, List<String> wordDict) {
        if(wordDict.contains(s)) 
        	return true;
        
        if(map.containsKey(s)) 
        	return map.get(s);
        
        for(int i=0;i<s.length();i++) {
            String prefix = s.substring(0,i+1);
            String suffix = s.substring(i+1);
            
            if(wordDict.contains(prefix) && wordBreak(suffix, wordDict)) {
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
