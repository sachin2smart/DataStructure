package in.sachinshinde.misc.grids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/word-search-ii/

/*
 * 	Given an m x n board of characters and a list of strings words, return all words on the board.
 * 	Each word must be constructed from letters of sequentially adjacent cells, 
 * 		where adjacent cells are horizontally or vertically neighboring. 
 * 	The same letter cell may not be used more than once in a word.
 */

class TrieNode {
	String word;
    TrieNode[] children = new TrieNode[26];
}

public class WordSearch2 {

	public List<String> findWords(char[][] board, String[] words) {
	    TrieNode root = buildTrie(words);
	    List<String> res = new ArrayList<>();
	    
	    for(int i = 0; i < board.length; i++)
	        for(int j = 0; j < board[0].length; j++)
	            dfs(board, i, j, root, res);
	    
	    return res;
	}
	
	private void dfs(char[][] board, int i, int j, TrieNode currNode, List<String> res) {
	    char currChar = board[i][j];
	    
	    if(currChar == '#' || currNode.children[currChar - 'a'] == null) 
	    	return;
	    
	    currNode = currNode.children[currChar - 'a'];
	    
	    if(currNode.word != null) {
	        res.add(currNode.word);
	        currNode.word = null;
	    }
	    
	    int m = board.length - 1;
	    int n = board[0].length - 1;

	    board[i][j] = '#';
	    
	    if(i < m) dfs(board, i + 1, j, currNode, res); 
	    if(i > 0) dfs(board, i - 1, j, currNode, res);
	    if(j < n) dfs(board, i, j + 1, currNode, res);
	    if(j > 0) dfs(board, i, j - 1, currNode, res);
	    
	    board[i][j] = currChar;
	    
	}
	
	private TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String word: words) {
	        TrieNode currNode = root;
	        for (char ch : word.toCharArray()) {
	            if (currNode.children[ch - 'a'] == null) 
	            	currNode.children[ch - 'a'] = new TrieNode();
	            currNode = currNode.children[ch - 'a'];
	        }
	        currNode.word = word;
	    }
	    return root;
	}
	
	public static void main(String[] args) {
		WordSearch2 wordSearch2 = new WordSearch2();
		char[][] board = new char[][]{
			{'o','a','a','n'},
			{'e','t','a','e'},
			{'i','h','k','r'},
			{'i','f','l','v'}}; 
		String[] words = new String[] {"oath","pea","eat","rain"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[oath, eat]]
		
		board = new char[][] {
			{'a','a'},
			{'a','a'}
		};
		words = new String[] {"aaaaa"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[]]
		
		board = new char[][] {
			{'a','a'},
			{'a','a'}
		};
		words = new String[] {"aaaa","aa"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[aa, aaaa]]
		
		board = new char[][] {
			{'a','a'},
			{'a','a'}
		};
		words = new String[] {"a","aa"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[a, aa]]
		
		board = new char[][] {
			{'a'}
		};
		words = new String[] {"a","aa"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[a]]
		
		board = new char[][] {
			{'a'}
		};
		words = new String[] {};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[]]
		
		
		board = new char[][] {
			{}
		};
		words = new String[] {"a"};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[]]
		
		board = new char[][] {
			{}
		};
		words = new String[] {};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[]]
		
		board = new char[][] {};
		words = new String[] {};
		System.out.println(Arrays.asList(wordSearch2.findWords(board, words)));		//	[[]]
	}
	
}