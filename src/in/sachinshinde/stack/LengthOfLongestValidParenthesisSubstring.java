package in.sachinshinde.stack;

import java.util.Stack;

//  https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/

/*
        Given a string consisting of opening and closing parenthesis,
        find the length of the longest valid parenthesis substring.

        Examples:
        ---------
        Input: ((()
        Output : 2
        Explanation : ()

        Input: )()())
        Output : 4
        Explanation: ()()

        Input:  ()(()))))
        Output: 6
        Explanation:  ()(())
 */

public class LengthOfLongestValidParenthesisSubstring {

    public int findMaxLen(String str) {
        Stack<Integer> st = new Stack<>();
        int n = str.length();

        for(int i = 0; i < n; i++) {
            if(str.charAt(i) == '(') {
                st.push(i);
            }
            else {
                if (!st.isEmpty() && str.charAt(st.peek()) == '(') {
                    st.pop();
                }
                else {
                    st.push(i);
                }
            }
        }

        int res = 0;
        int lastPos = n;

        while (!st.isEmpty()) {
            int currPos = st.pop();
            res = Math.max(res, lastPos - currPos - 1);
            lastPos = currPos;
        }

        return Math.max(res, lastPos);
    }

    public static void main(String[] args) {
        LengthOfLongestValidParenthesisSubstring string = new LengthOfLongestValidParenthesisSubstring();
        System.out.println(string.findMaxLen("((()()"));    // 4
        System.out.println(string.findMaxLen("()(()))))")); // 6

        System.out.println(string.findMaxLen2("((()()"));    // 4
        System.out.println(string.findMaxLen2("()(()))))")); // 6
    }

    public int findMaxLen2(String str) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int n = str.length();
        int res = 0;

        for(int i = 0; i < n; i++) {
            if(str.charAt(i) == '(') {
                st.push(i);
            }
            else {
                if (!st.isEmpty()) {
                    st.pop();
                }
                if(!st.isEmpty()) {
                    res = Math.max(res, i - st.peek());
                }
                else {
                    st.push(i);
                }
            }
        }

        return res;
    }
}
