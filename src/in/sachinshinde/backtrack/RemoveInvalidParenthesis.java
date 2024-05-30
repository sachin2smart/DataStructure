package in.sachinshinde.backtrack;

//  https://leetcode.com/problems/remove-invalid-parentheses

/*
        Given a string s that contains parentheses and letters,
        remove the minimum number of invalid parentheses to make the input string valid.

        Return a list of unique strings that are valid with the minimum number of removals.
        You may return the answer in any order.

        Example 1:
        ---------
        Input: s = "()())()"
        Output: ["(())()","()()()"]

        Example 2:
        ---------
        Input: s = "(a)())()"
        Output: ["(a())()","(a)()()"]

        Example 3:
        ---------
        Input: s = ")("
        Output: [""]


        Constraints:
        ------------
            1 <= s.length <= 25
            s consists of lowercase English letters and parentheses '(' and ')'.
            There will be at most 20 parentheses in s.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesis {
    Set<String> hs;
    int remCount;

    public List<String> removeInvalidParentheses(String s) {
        hs = new HashSet<>();
        remCount = Integer.MAX_VALUE;
        backtrack(s, 0, new StringBuilder(), 0, 0, 0);
        return new ArrayList<>(hs);
    }

    private void backtrack(String s, int currPos, StringBuilder sb,
                   int open, int close, int currCount) {
        // Search pruning
        if(currPos == s.length() && open == close) {
            if(currCount <= remCount) {
                if(currCount < remCount) {
                    hs = new HashSet<>();
                }
                hs.add(sb.toString());
                remCount = currCount;
            }
        }
        else {
            if(currPos >= s.length()) {
                return;
            }

            char currChar = s.charAt(currPos);

            if(currChar != '(' && currChar != ')') {
                // Char. This will always be part of valid string. Add it. advance currPos while backtracking
                sb.append(currChar);    // set
                backtrack(s, currPos + 1, sb, open, close, currCount);
                sb.setLength(sb.length() - 1);  // unset (backtrack)
            }
            else {
                // Choice 1: Don't choose this char, increment currCount while backtracking
                backtrack(s, currPos + 1, sb, open, close, currCount + 1);

                // Choice 2: Choose this char
                // Choice 2.1 : if char is '(', increment open count while backtracking
                if(currChar == '(') {
                    sb.append(currChar);    // set
                    backtrack(s, currPos + 1, sb, open + 1, close, currCount);
                    sb.setLength(sb.length() - 1);  // unset (backtrack)
                }
                else {  // Choice 2.2 : if char is ')', increment close count while backtracking
                    if(open > close) {
                        sb.append(currChar);    // set
                        backtrack(s, currPos + 1, sb, open, close + 1, currCount);
                        sb.setLength(sb.length() - 1);  // unset (backtrack)
                    }
                }
            }
        }
    }

    /*
            "4" time backtrack is being called:
                [1] : if the char is an alphabet  :--> (currPos++)
                [2] : if the char does not wish to take  :--> (currCount++)
                [3] : if the char is taken and it is '('  :--> (open++)
                [4] : if the char is taken and it is ')' if there are sufficient open brackets  :--> (close++)
     */

    public static void main(String[] args) {
        RemoveInvalidParenthesis parenthesis = new RemoveInvalidParenthesis();
        System.out.println(parenthesis.removeInvalidParentheses("()())()"));    // ["(())()","()()()"]
        System.out.println(parenthesis.removeInvalidParentheses("(a)())()"));   // ["(a())()","(a)()()"]
        System.out.println(parenthesis.removeInvalidParentheses(")("));         // [""]

        System.out.println(parenthesis.removeInvalidParentheses2("()())()"));    // ["(())()","()()()"]
        System.out.println(parenthesis.removeInvalidParentheses2("(a)())()"));   // ["(a())()","(a)()()"]
        System.out.println(parenthesis.removeInvalidParentheses2(")("));         // [""]
    }

    //  if the interviewer asked not to use too many function parameter and global variables,
    //  also if asked to reduce the number of calls for backtrack function
    //  before going for below solution ask the interviewer if it is fine to modify the input string s
    public List<String> removeInvalidParentheses2(String s) {
        int open = 0, close = 0;
        for(char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            }
            if (c == ')' && open == 0) {
                close++;
            }
            if (c == ')' && open > 0) {
                open--;
            }
        }
        List<String> res = new ArrayList<>();
        backtrack2(s, res, 0, open, close);
        if(res.isEmpty()) {
           res.add("");
        }
        return res;
    }

    private void backtrack2(String currString, List<String> res, int currPos, int open, int close) {
        // Search Pruning
        if(open == 0 && close == 0) {
            if(isValid(currString)) {
                res.add(currString);
            }
            return; // backtrack to previous valid state
        }
        for(int nextPos = currPos; nextPos < currString.length(); nextPos++) {
            // early termination
            if (nextPos > currPos && currString.charAt(nextPos) == currString.charAt(nextPos - 1)) {
                continue;
            }

            // nextString is the String excluding nextPos character
            // 1. String.subString(beginIndex, endIndex) :
            //      beginIndex – the beginning index, inclusive.
            //      endIndex – the ending index, exclusive.
            //  2. String.subString(beginIndex) :
            //      beginIndex – the beginning index, inclusive.
            String nextString = currString.substring(0, nextPos) + currString.substring(nextPos + 1);

            if(currString.charAt(nextPos) == '(' && open > 0) {
                backtrack2(nextString, res, nextPos, open - 1, close);
            }

            if(currString.charAt(nextPos) == ')' && close > 0) {
                backtrack2(nextString, res, nextPos, open, close - 1);
            }
        }
    }

    public boolean isValid(String currString) {
        int open = 0;
        for(char c : currString.toCharArray()) {
            if(c == '(') {
                open++;
            }
            if(c == ')') {
                if(open == 0) {
                    return false;
                }
                open--;
            }
        }
        return open == 0;
    }

    /*
            "2" times the backtrack2 is being called
                [1] when the next char is '('   :--> (open--)
                [2] when the next char is '('   :--> (close--)
     */
}
