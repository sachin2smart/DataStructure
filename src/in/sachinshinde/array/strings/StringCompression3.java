package in.sachinshinde.array.strings;

import java.util.Arrays;

//  https://leetcode.com/problems/string-compression-iii/

/*

    Given a string word, compress it using the following algorithm:

    Begin with an empty string comp. While word is not empty, use the following operation:
    Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
    Append the length of the prefix followed by c to comp.
    Return the string comp.



    Example 1:
    ---------
    Input: word = "abcde"
    Output: "1a1b1c1d1e"

    Explanation:
        Initially, comp = "".
        Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
        For each prefix, append "1" followed by the character to comp.

    Example 2:
    ---------
    Input: word = "aaaaaaaaaaaaaabb"
    Output: "9a5a2b"

    Explanation:

        Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and
        "bb" as the prefix in each operation.
        For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
        For prefix "aaaaa", append "5" followed by "a" to comp.
        For prefix "bb", append "2" followed by "b" to comp.


    Constraints:
    -----------
        1 <= word.length <= 2 * 105
        word consists only of lowercase English letters.

 */

public class StringCompression3 {

    public String compress(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int resArrLen = 0, currIndex = 0;

        char[] resArr = new char[n*2];
        while(currIndex < n) {
            char currChar = chars[currIndex];
            int count = 0;

            while(currIndex < n && chars[currIndex] == currChar && count < 9) {
                currIndex++;
                count++;
            }

            resArr[resArrLen++] = Integer.toString(count).charAt(0);
            resArr[resArrLen++] = currChar;
        }

        if(resArrLen < n * 2) {
            return String.valueOf(Arrays.copyOfRange(resArr, 0, resArrLen));
        }

        return String.valueOf(resArr);
    }


    public static void main(String[] args) {
        StringCompression3 stringCompression3 = new StringCompression3();
        System.out.println(stringCompression3.compress("abcde"));   //  1a1b1c1d1e
        System.out.println(stringCompression3.compress("aaaaaaaaaaaaaabb"));   //  9a5a2b

        System.out.println();

        System.out.println(stringCompression3.compressedString("abcde"));   //  1a1b1c1d1e
        System.out.println(stringCompression3.compressedString("aaaaaaaaaaaaaabb"));   //  9a5a2b
    }

    public String compressedString(String word) {
        StringBuilder comp = new StringBuilder();
        int n = word.length();

        //  initial
        int count = 1;
        char currChar = word.charAt(0);

        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == currChar && count < 9) {
                count++;
            }
            else {
                comp.append(count).append(currChar);    //  accumulate to the result
                //  reset
                currChar = word.charAt(i);
                count = 1;
            }
        }
        comp.append(count).append(currChar);    //  last entry to the result
        return comp.toString();
    }
}
