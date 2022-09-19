package in.sachinshinde.dp.grids;

//	https://leetcode.com/problems/word-search/

/*
 * 	Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * 	The word can be constructed from letters of sequentially adjacent cells,
 * 	 where adjacent cells are horizontally or vertically neighboring. 
 * 	The same letter cell may not be used more than once.
 */

public class WordSearch {
    
	public boolean exist(char[][] board, String word) {
		boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if((word.charAt(0) == board[i][j]) && 
                		isWordFound(board, i, j, word, 0, visited))
                    return true;
        
        return false;
    }
    
    private boolean isWordFound(char[][]board, int i, int j, 
    							String word, int wordIndex, 
    							boolean[][] visited) {
        if(wordIndex == word.length())
            return true;
        
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || 
        		board[i][j] != word.charAt(wordIndex) || visited[i][j])
            return false;
        
        visited[i][j] = true;
        
        if(isWordFound(board, i-1, j, word, wordIndex+1, visited) || 
           isWordFound(board, i+1, j, word, wordIndex+1, visited) ||
           isWordFound(board, i, j-1, word, wordIndex+1, visited) || 
           isWordFound(board, i, j+1, word, wordIndex+1, visited))
            	return true;
        
        visited[i][j] = false;
        
        return false;
    }
    
    public static void main(String[] args) {
		WordSearch wordSearch = new WordSearch();
		char[][] board = new char[][] {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
			};
		System.out.println(wordSearch.exist(board, "ABCCED"));	//	true
		System.out.println(wordSearch.exist(board, "ABCB"));	//	false
	}
}

/*

	Input: board = [
					["A","B","C","E"],
					["S","F","C","S"],
					["A","D","E","E"]
				]
		   word = "ABCCED"
	Output: true

------------------------------------------
	Input: board = [
					["A","B","C","E"],
					["S","F","C","S"],
					["A","D","E","E"]
				]
			word = "ABCB"
	Output: false
------------------------------------------
 */
