package in.sachinshinde.hashmap;

//  https://leetcode.com/problems/count-the-number-of-consistent-strings/

/*
        You are given a string allowed consisting of distinct characters and an array of strings words.
        A string is consistent if all characters in the string appear in the string allowed.

        Return the number of consistent strings in the array words.

        Example 1:
        ---------
        Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
        Output: 2
        Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.

        Example 2:
        ---------
        Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
        Output: 7
        Explanation: All strings are consistent.

        Example 3:
        ---------
        Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
        Output: 4
        Explanation: Strings "cc", "acd", "ac", and "d" are consistent.


        Constraints:
        ------------
            1 <= words.length <= 10^4
            1 <= allowed.length <= 26
            1 <= words[i].length <= 10
            The characters in allowed are distinct.
            words[i] and allowed contain only lowercase English letters.
 */

public class CountTheNumberOfConsistentStrings {

    public int countConsistentStrings(String allowed, String[] words) {
        int count = words.length;
        int[] store = new int[26];

        for (char c : allowed.toCharArray()){
            store[c - 'a']++;
        }

        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (store[c - 'a'] <= 0){
                    count--;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountTheNumberOfConsistentStrings countStrings = new CountTheNumberOfConsistentStrings();
        System.out.println(countStrings.countConsistentStrings("ab", new String[]{"ad","bd","aaab","baa","badab"}));    //  2
        System.out.println(countStrings.countConsistentStrings("abc", new String[]{"a","b","c","ab","ac","bc","abc"}));     //  7
        System.out.println(countStrings.countConsistentStrings("cad", new String[]{"cc","acd","b","ba","bac","bad","ac","d"}));     //  4
    }
}
