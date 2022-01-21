package in.sachinshinde.recursion;

//https://leetcode.com/problems/surrounded-regions/

//Surrounded Regions:
/*		Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
*		A region is captured by flipping all 'O's into 'X's in that surrounded region.
* 
* 		Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
* 		Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
* 		Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/


//Approach : Recursion

public class SurroundedRegions {

    public void captureRegion(char[][] board) {
        if(board.length==0 || board[0].length==0) 
        	return;
        
        if(board.length<3 || board[0].length<3)
        	return;

        int m=board.length;
        int n=board[0].length;
	
        firstLastColumnFlipper(board, m, n);
        
        firstLastRowFlipper(board, m, n);
        
        internalFlipper(board, m, n);
        
    }

	private void firstLastColumnFlipper(char[][] board, int m, int n) {
    	for(int i=0; i<m; i++) {
            if (board[i][0]=='O') 
            	starFlipper(board, i, 0);
            
            if(board[i][n-1]=='O') 
            	starFlipper(board, i, n-1);
        }
	}
	
    private void firstLastRowFlipper(char[][] board, int m, int n) {
    	for(int j=1; j<n-1; j++) {
            if(board[0][j]=='O') 
            	starFlipper(board, 0, j);
            
            if(board[m-1][j]=='O') 
            	starFlipper(board, m-1, j);
        }
	}

	private void starFlipper(char[][] board, int r, int c) {
        if(r<0 || c<0 || r>board.length-1 || c>board[0].length-1 || board[r][c]!='O') 
        	return;
        
        board[r][c] = '*';
        
        starFlipper(board, r + 1, c);
        starFlipper(board, r - 1, c);
        starFlipper(board, r, c + 1);
        starFlipper(board, r, c - 1);
    }
    
	private void internalFlipper(char[][] board, int m, int n) {
		for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j]=='O')
                	board[i][j] = 'X';
                
                if(board[i][j]=='*')
                	board[i][j] = 'O';
            }
        }
	}
}
