package in.sachinshinde.design;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/implement-trie-prefix-tree/

/*
 * 	Trie:
 	----
 	A trie (pronounced as "try") or prefix tree is a tree data structure
 	 used to efficiently store and retrieve keys in a dataset of strings. 
 	 There are various applications of this data structure, 
 	 such as autocomplete and spellchecker.

	Implement the Trie class:
	------------------------
		- Trie() 
			Initializes the trie object.
		- void insert(String word) 
			Inserts the string word into the trie.
		- boolean search(String word) 
			Returns true if the string word is in the trie 
			(i.e., was inserted before), and false otherwise.
		- boolean startsWith(String prefix) 
			Returns true if there is a previously inserted string word 
			that has the prefix prefix, and false otherwise.
 */

public class TriePrefixTree {

	private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    
    public TriePrefixTree() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode getTrieNode = currNode.children.get(ch);
            if (getTrieNode == null) {
            	getTrieNode = new TrieNode();
                currNode.children.put(ch, getTrieNode);
            }
            currNode = getTrieNode;
        }
        currNode.endOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode getNode = currNode.children.get(ch);
            if (getNode == null) {
                return false;
            }
            currNode = getNode;
        }
        return currNode.endOfWord;
    }
	
    private boolean startsWith(String prefix) {
    	TrieNode currNode = root;
        for(char ch : prefix.toCharArray()) {
            if(!currNode.children.containsKey(ch)) {
                return false;
            }
            currNode = currNode.children.get(ch);
        }
        return true;
    }
    
    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode currNode, String word, int index) {
        if (index == word.length()) {
            if (!currNode.endOfWord) {
                return false;
            }
            currNode.endOfWord = false;
            return currNode.children.size() == 0;
        }
        
        char ch = word.charAt(index);
        TrieNode node = currNode.children.get(ch);
        if (node == null) {
            return false;
        }
        
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);
        if (shouldDeleteCurrentNode) {
        	currNode.children.remove(ch);
            return currNode.children.size() == 0;
        }
        return false;
    }
    
    public static void main(String[] args) {
		TriePrefixTree trie = new TriePrefixTree();
		trie.insert("apple");
		System.out.println(trie.search("apple"));   //  True
		System.out.println(trie.search("app"));     //  False
		System.out.println(trie.startsWith("app")); //  True
		trie.insert("app");
		System.out.println(trie.search("app"));     //  True
	}
}