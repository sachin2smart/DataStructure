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
		List<String> finalList = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        dfs(s, 0, root, root, currentList, finalList);	// why to pass root twice?
        return finalList;
    }

	//	two nodes = to keep track ot root-node and current node traversed so far 
	private void dfs(String s, int index, TrieNode2 root, TrieNode2 currNode, 
			List<String> currentList, List<String> finalList) {
		
		if(index == s.length()) {
            if(currNode == root)
            	finalList.add(String.join(" ", currentList));
            return;
        }
		
        if(currNode == null) 
        	return;
        
        TrieNode2 childNode = currNode.children[s.charAt(index) - 'a'];
        
        if(childNode != null && childNode.isWord) {
        	currentList.add(childNode.word);
        	// since word found, go for searching next word from next index 
			// At this point currNode need to reset to root  
            dfs(s, index + 1, root, root, currentList, finalList);
            // If the next word not found from remaining chars, 
            //  remove above added word from the list
            currentList.remove(currentList.size() - 1);	
        }
        
        dfs(s, index + 1, root, childNode, currentList, finalList);
	}

	private TrieNode2 buildTrie(List<String> wordDict) {
		TrieNode2 root = new TrieNode2();
		
		for(String word : wordDict) {
			TrieNode2 currNode = root;
	        for(char ch: word.toCharArray()) {
				if(currNode.children[ch - 'a'] == null)
					currNode.children[ch - 'a'] = new TrieNode2();
				currNode = currNode.children[ch - 'a'];
			}
	        currNode.isWord = true;
	        currNode.word = word;
        }
		return root;
	}
	
	public static void main(String[] args) {
		Wordbreak2 wb2 = new Wordbreak2();
		
		System.out.println(wb2.wordBreak("catsanddog", 
				new ArrayList<String>(List.of(
						"cat","cats","and","sand","dog"))));
		
		System.out.println(wb2.wordBreak("pineapplepenapple", 
				new ArrayList<String>(List.of(
						"apple","pen","applepen","pine","pineapple"))));
		
		System.out.println(wb2.wordBreak("catanddog", 
				new ArrayList<String>(List.of(
						"cats","sand","and","dogs"))));
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
	Input: s = "catandog", wordDict = ["cats","dogs","sand","and"]
	Output: []
	 
*/