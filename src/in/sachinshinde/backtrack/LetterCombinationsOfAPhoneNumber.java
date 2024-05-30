package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/letter-combinations-of-a-phone-number/

/*
 	Given a string containing digits from 2-9 inclusive, 
 	return all possible letter combinations that the number could represent. 
 	Return the answer in any order.

	A mapping of digits to letters (just like on the telephone buttons) is given below. 
	Note that 1 does not map to any letters.
		1 = "",     2 = "abc", 3 = "def", 
        4 = "ghi",  5 = "jkl", 6 = "mno", 
        7 = "pqrs", 8 = "tuv", 9 = "wxyz"
 */
public class LetterCombinationsOfAPhoneNumber {
	public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        
        String[] keys = new String[] {"", "", "abc", "def", 
        							  "ghi", "jkl", "mno", 
        							  "pqrs", "tuv", "wxyz" };
        List<String> combinations = new ArrayList<>();
        backtrack(combinations, digits, "", keys);
        return combinations;
    }
    
    public void backtrack(List<String> combinations, String digits, 
    						String currCombination, String[] keys) {
        if (currCombination.length() == digits.length()) { 
        	combinations.add(currCombination); 
        	return; 
        }

        int currLen = currCombination.length();
        int currDigit = digits.charAt(currLen) - '0';   // need to know the position of chars in keys arrays

        for (char letter : keys[currDigit].toCharArray()) { // for each character from the keys array
            backtrack(combinations, digits, currCombination + letter, keys); // only 3'd param is updated
        }
    }
    
    public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber lc = new LetterCombinationsOfAPhoneNumber();
		System.out.println(lc.letterCombinations("23"));	
		//	["ad","ae","af","bd","be","bf","cd","ce","cf"]
	}
}
