package in.sachinshinde.design;

//	https://leetcode.com/problems/design-add-and-search-words-data-structure/

/*
	Design a data structure that supports adding new words and 
	finding if a string matches any previously added string.

	Implement the WordDictionary class:	
		- WordDictionary() 
				Initializes the object.
		- void addWord(word) 
				Adds word to the data structure, it can be matched later.
		- bool search(word) 
				Returns true if there is any string in the data structure 
				that matches word or false otherwise. 
		
		word may contain dots '.' where dots can be matched with any letter.
 */

/*
 	Example:
	-------
	Input
		["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
		[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
	Output
		[null,null,null,null,false,true,true,true]
		
	Explanation
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		wordDictionary.search("pad"); // return False
		wordDictionary.search("bad"); // return True
		wordDictionary.search(".ad"); // return True
		wordDictionary.search("b.."); // return True
 */

public class DesignAddAndSearchWordsDataStructure {

	class TrieNode {
		String word;
	    TrieNode[] children = new TrieNode[26];
	}
	
	private TrieNode root = new TrieNode();
	
    public void addWord(String word) {
        TrieNode currNode = root;
        for(char ch : word.toCharArray()) {
            if(currNode.children[ch - 'a'] == null) {
                currNode.children[ch - 'a'] = new TrieNode();
            }
            currNode = currNode.children[ch - 'a'];
        }
        currNode.word = word;
    }
    
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] word, int wl, TrieNode node) {
        if(wl == word.length) {
            return node.word != null && !node.word.equals("");
        }
        
        if(word[wl] != '.') {
            return node.children[word[wl] - 'a'] != null &&
                    match(word, wl + 1, node.children[word[wl] - 'a']);
        }
        else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(word, wl + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
	public static void main(String[] args) {
		DesignAddAndSearchWordsDataStructure ds = new DesignAddAndSearchWordsDataStructure();
		
		ds.addWord("bad");
		ds.addWord("dad");
		ds.addWord("mad");
		
		System.out.println(ds.search("pad")); // false
		System.out.println(ds.search("bad")); // true
		System.out.println(ds.search(".ad")); // true
		System.out.println(ds.search("b..")); // true
	}

}
