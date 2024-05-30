package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.List;

//	Letter Case Permutation
//	https://leetcode.com/problems/letter-case-permutation/

/*
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
	Return a list of all possible strings we could create. Return the output in any order.
 */

/*
 *	Example 1:
	---------
	Input: s = "a1b2"
	Output: ["a1b2","a1B2","A1b2","A1B2"] 
 */


//	Approach : DFS, Backtracking

public class LetterCasePermutation {

    public static List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, 0, s.toCharArray());
        return ans;
    }
    
    private static void backtrack(List<String> ans, int i, char[] charStr) {
        if(i==charStr.length) {
            ans.add(new String(charStr));
        }
        else {
            if(Character.isLetter(charStr[i])) { 
            	charStr[i] = Character.toUpperCase(charStr[i]); // in place update to uppercase
                backtrack(ans, i+1, charStr);
                
                charStr[i] = Character.toLowerCase(charStr[i]); // // in place update to lowercase
                backtrack(ans, i+1, charStr);
            }
            else {
                backtrack(ans, i + 1, charStr);     // No modification for number
            }
        }
    }
    
	public static void main(String[] args) {
		System.out.println(letterCasePermutation("a1b1"));
	}

}

/**  
a1b2   i=0, when it's at a, since it's a letter, we have two branches: a, A
/        \
a1b2       A1b2 i=1 when it's at 1, we only have 1 branch which is itself
|          |   
a1b2       A1b2 i=2 when it's at b, we have two branches: b, B
/  \        / \
a1b2 a1B2  A1b2 A1B2 i=3  when it's at 2, we only have one branch.
|    |     |     |
a1b2 a1B2  A1b2  A1B2 i=4 = S.length(). End recursion, add permutation to ans. 

During this process, we are changing the s char array itself
*/