package in.sachinshinde.array.binarysearch;

import java.util.HashSet;
import java.util.Set;

//  https://leetcode.ca/all/1062.html

/*
        Given a string S, find out the length of the longest repeating substring(s).
        Return 0 if no repeating substring exists.

        Example 1:
        ---------
        Input: "abcd"
        Output: 0
        Explanation: There is no repeating substring.

        Example 2:
        ---------
        Input: "abbaba"
        Output: 2
        Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.

        Example 3:
        ---------
        Input: "aabcaabdaab"
        Output: 3
        Explanation: The longest repeating substring is "aab", which occurs 3 times.

        Example 4:
        ---------
        Input: "aaaaa"
        Output: 4
        Explanation: The longest repeating substring is "aaaa", which occurs twice.

        Note:
        -----
            The string S consists of only lowercase English letters from 'a' - 'z'.
            1 <= S.length <= 1500
 */


public class LongestRepeatingSubstring {

    public int longestRepeatingSubstring(String s) {
        if(s.length() == 0) {
            return 0;
        }

        int low = 1;
        int high = s.length() - 1;

        int res = 0;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            Set<String> hs = new HashSet<>();
            boolean found = false;

            for(int i=0; i+mid <= s.length(); i++) {
                String subStr = s.substring(i, i+mid);
                if(hs.contains(subStr)) {
                    found = true;
                    break;
                }
                else {
                    hs.add(subStr);
                }
            }

            if(found) {
                res = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        LongestRepeatingSubstring string = new LongestRepeatingSubstring();
        System.out.println(string.longestRepeatingSubstring("abcd"));   // 0
        System.out.println(string.longestRepeatingSubstring("abbaba")); // 2
        System.out.println(string.longestRepeatingSubstring("aabcaabdaab"));    // 3
        System.out.println(string.longestRepeatingSubstring("aaaaa"));  // 4

    }

}
