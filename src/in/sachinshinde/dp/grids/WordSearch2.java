package in.sachinshinde.array.matrix;

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
    TrieNode[] next = new TrieNode[26];
}

public class WordSearch2 {

	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<>();
	    TrieNode root = buildTrie(words);
	    for (int i = 0; i < board.length; i++)
	        for (int j = 0; j < board[0].length; j++)
	            dfs (board, i, j, root, res);
	    
	    return res;
	}
	
	private void dfs(char[][] board, int i, int j, TrieNode tn, List<String> res) {
	    char currChar = board[i][j];
	    if (currChar == '#' || tn.next[currChar - 'a'] == null) 
	    	return;
	    
	    tn = tn.next[currChar - 'a'];
	    
	    if (tn.word != null) {
	        res.add(tn.word);
	        tn.word = null;
	    }
	    
	    int m = board.length - 1;
	    int n = board[0].length - 1;

	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j , tn, res); 
	    if (j > 0) dfs(board, i, j - 1, tn, res);
	    if (i < m) dfs(board, i + 1, j, tn, res); 
	    if (j < n) dfs(board, i, j + 1, tn, res); 
	    board[i][j] = currChar;
	    
	}
	
	private TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String word : words) {
	        TrieNode currTrieNode = root;
	        for (char currChar : word.toCharArray()) {
	            int charIndex = currChar - 'a';
	            if (currTrieNode.next[charIndex] == null) 
	            	currTrieNode.next[charIndex] = new TrieNode();
	            currTrieNode = currTrieNode.next[charIndex];	//	TN points at the end of the word
	        }
	        currTrieNode.word = word;	// **Only store the word, do not store charIndex anywhere in TrieTree
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