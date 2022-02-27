package in.sachinshinde.misc;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.ca/all/293.html
//	https://leetcode.ca/2016-09-18-293-Flip-Game/


/*
 	You are playing the following Flip Game with your friend:
    Given a string that contains only these two characters: + and -,
    you and your friend take turns to flip two-consecutive "++" into "--".

	The game ends when a person can no longer make a move and therefore the other person will be the winner.
	
	Write a function to compute all possible states of the string after one valid move.
	
	For example, given s = "++++", after one move, it may become one of the following states:
	 [
	   "--++",
	   "+--+",
	   "++--"
	 ]
	
	If there is no valid move, return an empty list [].
 */

public class FlipGame {
	public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        char[] arr = s.toCharArray();

        // solution: restore after update 
        for(int i = 1; i < s.length(); i++) {
            if(arr[i] == '+' && arr[i - 1] == '+') {
                arr[i] = '-';
                arr[i - 1] = '-';
                result.add(String.valueOf(arr));
                arr[i] = '+';
                arr[i - 1] = '+';
            }
        }
        return result;
	}
	
	public static void main(String[] args) {
		FlipGame flipGame = new FlipGame();
		System.out.println(flipGame.generatePossibleNextMoves(new String("+-+-++")));	// +-+---
	}
}
