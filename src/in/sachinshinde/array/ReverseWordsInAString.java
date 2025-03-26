package in.sachinshinde.array;

//  https://leetcode.com/problems/reverse-words-in-a-string

/*

        Given an input string s, reverse the order of the words.

        A word is defined as a sequence of non-space characters.
        The words in s will be separated by at least one space.

        Return a string of the words in reverse order concatenated by a single space.

        Note that s may contain leading or trailing spaces or multiple spaces between two words.
        The returned string should only have a single space separating the words. Do not include any extra spaces.



        Example 1:
        ---------
            Input: s = "the sky is blue"
            Output: "blue is sky the"

        Example 2:
        ---------
            Input: s = "  hello world  "
            Output: "world hello"
            Explanation: Your reversed string should not contain leading or trailing spaces.

        Example 3:
        ---------
            Input: s = "a good   example"
            Output: "example good a"
            Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


        Constraints:
        -----------
            1 <= s.length <= 104
            s contains English letters (upper-case and lower-case), digits, and spaces ' '.
            There is at least one word in s.


        Follow-up: If the string data type is mutable in your language,
                    can you solve it in-place with O(1) extra space?

 */

public class ReverseWordsInAString {

    public String reverseWords(String s) {
        String[] strArr = s.trim().split("\\s+");
        StringBuilder res = new StringBuilder();
        for(int i= strArr.length - 1; i > 0; i--) {
            res.append(strArr[i]).append(" ");
        }
        return res + strArr[0];
    }

    public static void main(String[] args) {
        ReverseWordsInAString rev = new ReverseWordsInAString();
        String str = "the sky is blue";
        System.out.println(rev.reverseWords(str));
        System.out.println(rev.reverseWords2(str));

        String str2 = "  hello world ";
        System.out.println(rev.reverseWords(str2));
        System.out.println(rev.reverseWords2(str2));
    }

    public String reverseWords2(String s) {
        char[] charArr = s.toCharArray();
        int len = charArr.length;
        char[] result = new char[len];
        int currIndexInResult = 0;
        int end = len - 1;

        while (end >= 0) {
            while (end >= 0 && charArr[end] == ' ') {
                end--;
            }

            int start = end;

            while (start >= 0 && charArr[start] != ' ') {
                start--;
            }

            if (currIndexInResult > 0) {
                result[currIndexInResult++] = ' ';
            }

            for (int i = start + 1; i <= end; i++) {
                result[currIndexInResult++] = charArr[i];
            }

            end = start - 1;
        }

        return new String(result, 0, currIndexInResult).trim();
    }
}
