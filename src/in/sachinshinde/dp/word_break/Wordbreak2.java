package in.sachinshinde.dp.word_break;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/word-break-ii/

/*
 * 	Given a string s and a dictionary of strings wordDict, 
 * 		add spaces in s to construct a sentence where each word is a valid dictionary word. 
 * 	Return all such possible sentences in any order.
 * 
 * 	Note that the same word in the dictionary may be reused multiple times in the segmentation.	
 */

class TrieNode2{
	boolean isWord;
	TrieNode2[] children;
	String word;
	
	TrieNode2() {
		children = new TrieNode2[26];
	}
}

public class Wordbreak2 {
	
	public List<String> wordBreak(String s, List<String> wordDict) {
		if(s == null || s.length() == 0 || 
				wordDict == null || wordDict.size() == 0)
			return null;
		
		TrieNode2 root = buildTrie(wordDict);
		List<String> res = new ArrayList<>();
        List<String> current = new ArrayList<>();
        dfs(s, 0, root, root, current, res);
        return res;
    }

	private void dfs(String s, int index,TrieNode2 root, TrieNode2 node, List<String> current, List<String> res) {
		if(index == s.length()) {
            if(node == root) {
                res.add(String.join(" ", current));
            }
            return;
        }
		
        if(node == null) 
        	return;
        
        TrieNode2 childNode = node.children[s.charAt(index) - 'a'];
        
        if(childNode != null && childNode.isWord) {
            current.add(childNode.word);
            dfs(s, index + 1, root, root, current, res);
            current.remove(current.size() - 1);
        }
        
        dfs(s, index + 1, root, childNode, current, res);
	}

	private TrieNode2 buildTrie(List<String> wordDict) {
		TrieNode2 root = new TrieNode2();
		
		for(String word : wordDict) {
			TrieNode2 currNode = root;
	        for(int i = 0; i < word.length(); i++) {
	            int idx = word.charAt(i) - 'a';
	            if(currNode.children[idx] == null) 
	            	currNode.children[idx] = new TrieNode2();
	            currNode = currNode.children[idx];
	        }
	        currNode.isWord = true;
	        currNode.word = word;
        }
		return root;
	}
	
}

/*
	Example 1:
	Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
	Output: ["cats and dog","cat sand dog"]
	
	Example 2:
	Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
	Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
	Explanation: Note that you are allowed to reuse a dictionary word.
	
	Example 3:
	Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
	Output: []
	 
*/