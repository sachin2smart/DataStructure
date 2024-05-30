package in.sachinshinde.stack;

import java.util.Stack;

//  https://leetcode.com/problems/remove-duplicate-letters/

/*
        Given a string s, remove duplicate letters so that every letter appears once and only once.
        You must make sure your result is the smallest in lexicographical order among all possible results.

        Example 1:
        ---------
        Input: s = "bcabc"
        Output: "abc"

        Example 2:
        ---------
        Input: s = "cbacdcbc"
        Output: "acdb"


        Constraints:
        ---------
            1 <= s.length <= 104
            s consists of lowercase English letters.
*/

public class RemoveDuplicateLetters {
    // https://leetcode.com/problems/remove-duplicate-letters/discuss/1859410/JavaC%2B%2B-DETAILED-%2B-VISUALLY-EXPLAINED-!!
    public String removeDuplicateLetters(String s) {
        if(s==null || s.length() == 0) {
            return null;
        }

        Stack<Integer> st = new Stack<>();  // answer will be stored in Stack
        boolean[] seen = new boolean[26];   // to keep track if we have seen the character
        int[] lastSeenPos = new int[26];    // to keep track of last index of character being seen

        for(int i=0; i<s.length(); i++) {
            lastSeenPos[s.charAt(i) - 'a'] = i;
        }

        for(int i=0; i<s.length(); i++) {
            int curr = s.charAt(i) - 'a';

            if(!seen[curr]) {
                //  when to mark unseen ? and why ?
                while (!st.isEmpty() && st.peek() > curr && lastSeenPos[st.peek()] > i) {
                    seen[st.pop()] = false; // mark as unseen
                }
                st.push(curr);
                seen[curr] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        System.out.println(removeDuplicateLetters.removeDuplicateLetters("cbacdcbc"));  // acdb
        System.out.println(removeDuplicateLetters.removeDuplicateLetters2("cbacdcbc"));  // acdb
    }

    public String removeDuplicateLetters2(String s) {
        if(s==null || s.length()==0) {
            return null;
        }

        Stack<Integer> st = new Stack<>();  // answer will be stored in Stack
        boolean[] seen = new boolean[26];   // to keep track if we have seen the character
        int[] lastSeenPos = new int[26];    // to keep track of last index of character being seen

        int n = s.length();
        char[] strChars = s.toCharArray();

        for(int i=0; i<n; i++) {
            lastSeenPos[strChars[i] - 'a'] = i;
        }

        for(int i=0; i<n; i++) {
            int curr = strChars[i] - 'a';

            if(!seen[curr]) {
                while (!st.isEmpty() && st.peek() > curr && lastSeenPos[st.peek()] > i) {
                    seen[st.pop()] = false;
                }
                st.push(curr);
                seen[curr] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.reverse().toString();
    }
}
