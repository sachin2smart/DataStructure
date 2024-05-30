package in.sachinshinde.array;

//  https://leetcode.ca/all/1119.html

/*
        Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it,
        and return the new string.

        Example 1:
        ---------
        Input: "leetcodeisacommunityforcoders"
        Output: "ltcdscmmntyfrcdrs"

        Example 2:
        ---------
        Input: "aeiou"
        Output: ""


        Note:
        ---------
            S consists of lowercase English letters only.
            1 <= S.length <= 1000
 */

public class RemoveVowelsFromAString {

    public String removeVowels(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                ans.append(c);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        RemoveVowelsFromAString string = new RemoveVowelsFromAString();
        System.out.println(string.removeVowels("leetcodeisacommunityforcoders"));    // "ltcdscmmntyfrcdrs"
        System.out.println(string.removeVowels("aeiou"));   // ""
    }
}
