package in.sachinshinde.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/camelcase-matching/

/*
 	Given an array of strings queries and a string pattern, 
 	return a boolean array answer where answer[i] is 
 		true if queries[i] matches pattern, and 
 		false otherwise.

	A query word queries[i] matches pattern 
		if you can insert lowercase English letters pattern 
		so that it equals the query. 
	You may insert each character at any position and 
		you may not insert any characters.

        Example 1:
        ----------
        Input:  queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], 
        	pattern = "FB"
        Output: [true,false,true,true,false]
        
        Explanation: "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
        "FootBall" can be generated like this "F" + "oot" + "B" + "all".
        "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
        
        Example 2:
        ---------
        Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
        Output: [true,false,true,false,false]
        Explanation: "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
        "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
        
        Example 3:
        ---------
        Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
        Output: [false,true,false,false,false]
        Explanation: "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
         
        
        Constraints:
        
        1 <= pattern.length, queries.length <= 100
        1 <= queries[i].length <= 100
        queries[i] and pattern consist of English letters.
 */


public class CamelcaseMatching {
    //	Method 1: Using Trie 
    public class TrieNode {
	private Map<Character, TrieNode> children = new HashMap<>();
	private boolean isEndOfWorld;
	
	public TrieNode() { }
	public TrieNode(char ch) { }
    }
    
    private TrieNode root;

    private void addWord(String pattern) {
	Map<Character, TrieNode> children = root.children;
        for(int i = 0; i < pattern.length(); i++) {
            char currChar = pattern.charAt(i);
            TrieNode currNode;
            if(children.containsKey(currChar)) {
        	currNode = children.get(currChar);
            } else { 
        	currNode = new TrieNode(currChar);
                children.put(currChar, currNode);
            }
            children = currNode.children;

            if(i == pattern.length() - 1) {
        	currNode.isEndOfWorld = true;
            }
        }
    }
    
    private Boolean isPatternMatches(String currQuery) {
	TrieNode currNode = root;
	for(char currChar : currQuery.toCharArray()) {
	    if(currNode.children.containsKey(currChar)) {
		currNode = currNode.children.get(currChar);
	    }
	    else if(currChar >= 'A' && currChar <= 'Z') {
		return false;
	    }
	}
	return currNode.isEndOfWorld;
    }
    
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        root = new TrieNode();
        addWord(pattern);
        for (String currQuery : queries) {
            res.add(isPatternMatches(currQuery));
        }
        return res;
    }

    
    // Method 2:
    public List<Boolean> camelMatch2(String[] queries, String pattern) {
	List<Boolean> res = new ArrayList<>();
	for (String query : queries)
	    res.add(isMatch(query, pattern));
	return res;
    }

    private boolean isMatch(String query, String pattern) {
	int i = 0;
	for (char currChar: query.toCharArray()) {
	    if (i < pattern.length() && currChar == pattern.charAt(i))
		i++;
	    else if (currChar >= 'A' && currChar <= 'Z')
		return false;
	}
	return i == pattern.length();
    }
	
    public static void main(String[] args) {
	CamelcaseMatching ccm = new CamelcaseMatching();
	System.out.println(ccm.camelMatch(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FB"));	//	[true,false,true,true,false]
	
	System.out.println(ccm.camelMatch2(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FB"));	//	[true,false,true,true,false]
	
	
	System.out.println(ccm.camelMatch(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FoBa")); //	[true, false, true, false, false]
	
	System.out.println(ccm.camelMatch2(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FoBa")); //	[true, false, true, false, false]
	
	System.out.println(ccm.camelMatch(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FoBaT")); //	[false, true, false, false, false]
	
	System.out.println(ccm.camelMatch2(new String[] {"FooBar","FooBarTest","FootBall",
		"FrameBuffer","ForceFeedBack"}, "FoBaT")); //	[false, true, false, false, false]
    }
}
