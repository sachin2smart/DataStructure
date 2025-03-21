package in.sachinshinde.graph.dfs;

//  https://leetcode.com/problems/interleaving-string/

/*
        Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

        An interleaving of two strings s and t is a configuration
        where s and t are divided into n and m substrings respectively, such that:
                s = s1 + s2 + ... + sn
                t = t1 + t2 + ... + tm
                |n - m| <= 1

        The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
        Note: a + b is the concatenation of strings a and b.

        Example 1:
        ----------
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        Output: true
            Explanation: One way to obtain s3 is:
            Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
            Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
            Since s3 can be obtained by interleaving s1 and s2, we return true.

        Example 2:
        ----------
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        Output: false
            Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

        Example 3:
        ----------
        Input: s1 = "", s2 = "", s3 = ""
        Output: true


        Constraints:
        -----------
            0 <= s1.length, s2.length <= 100
            0 <= s3.length <= 200
            s1, s2, and s3 consist of lowercase English letters.

            Follow up: Could you solve it using only O(s2.length) additional memory space?
 */

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = s1.length(), n = s2.length();

        if(m + n != s3.length()) {
            return false;
        }

        return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
    }

    public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if(invalid[i][j]) {
            return false;
        }

        if(k == c3.length) {
            return true;
        }

        boolean valid = i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid) ||
                        j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);

        if(!valid) {
            invalid[i][j] = true;
        }

        return valid;
    }

    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();
        System.out.println(interleavingString.isInterleave("aabcc","dbbca","aadbbcbcac"));  //  true
        System.out.println(interleavingString.isInterleave("aabcc","dbbca","aadbbbaccc"));  //  false
        System.out.println(interleavingString.isInterleave("","",""));                      //  true
    }
}
