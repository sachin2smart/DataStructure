package in.sachinshinde.array.twopointer;

import java.util.HashMap;

//  https://leetcode.com/problems/minimum-window-substring/

/*
        Given two strings s and t of lengths m and n respectively,
            return the minimum window substring of s such that every character in t
                (including duplicates) is included in the window.
            If there is no such substring, return the empty string "".

        The testcases will be generated such that the answer is unique.

        Example 1:
        ---------
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

        Example 2:
        ---------
        Input: s = "a", t = "a"
        Output: "a"
        Explanation: The entire string s is the minimum window.

        Example 3:
        ---------
        Input: s = "a", t = "aa"
        Output: ""
        Explanation: Both 'a's from t must be included in the window.
        Since the largest window of s only has one 'a', return empty string.


        Constraints:
        ------------
            m == s.length
            n == t.length
            1 <= m, n <= 105
            s and t consist of uppercase and lowercase English letters.
 */

public class MinimumWindowSubString {

    //sliding window
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char x : t.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int n = s.length();
        int matched = 0;    // what is the use of this variable ?
        int start = 0;
        int minLen = n + 1;
        int subStr = 0;

        for (int end = 0; end < n; end++) {
            char right = s.charAt(end);

            if (map.containsKey(right)) {
                map.put(right, map.get(right) - 1);
                if (map.get(right) == 0) {
                    matched++;
                }
            }

            while (matched == map.size()) {
                if (minLen > end - start + 1) {
                    minLen = end - start + 1;
                    subStr = start;
                }

                char deleted = s.charAt(start++);

                if (map.containsKey(deleted)) {
                    if (map.get(deleted) == 0) {
                        matched--;
                    }
                    map.put(deleted, map.get(deleted) + 1);
                }
            }
        }

        return minLen > n ? "" :
                s.substring(subStr, subStr + minLen);
    }

    public String minWindow2(String s, String t) {
        int[] charInT = new int[128];
        for (char c : t.toCharArray()) {
            charInT[c]++;
        }
        char[] sChar = s.toCharArray();
        int start = 0, end = 0, minStart = 0;
        int resLength = Integer.MAX_VALUE;
        int sLength = s.length();
        int tLength = t.length();
        int seenChars = 0;

        while (end < sLength) {
            char charInS = sChar[end];
            if (charInT[charInS] > 0) {
                seenChars++;
            }
            charInT[charInS]--;
            end++;

            while (seenChars == tLength) {
                if (end - start < resLength) {
                    resLength = end - start;
                    minStart = start;
                }
                char startChar = sChar[start];
                if (charInT[startChar] == 0) {
                    seenChars--;
                }
                charInT[startChar]++;
                start++;
            }
        }
        return resLength == Integer.MAX_VALUE ? "" :
                s.substring(minStart, minStart + resLength);
    }

    public static void main(String[] args) {
        MinimumWindowSubString string = new MinimumWindowSubString();
        System.out.println(string.minWindow("ADOBECODEBANC", "ABC"));   // BANC
        System.out.println(string.minWindow2("ADOBECODEBANC", "ABC"));   // BANC
    }
}
