package in.sachinshinde.heap_priority_queue;

//  https://leetcode.com/problems/strong-password-checker/

/*
        A password is considered strong if the below conditions are all met:

        It has at least 6 characters and at most 20 characters.
        It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
        It does not contain three repeating characters in a row (i.e., "Baaabb0" is weak, but "Baaba0" is strong).
        Given a string password, return the minimum number of steps required to make password strong.
        if password is already strong, return 0.

        In one step, you can:
            Insert one character to password,
            Delete one character from password, or
            Replace one character of password with another character.


        Example 1:
        ---------
        Input: password = "a"
        Output: 5

        Example 2:
        ---------
        Input: password = "aA1"
        Output: 3

        Example 3:
        ---------
        Input: password = "1337C0d3"
        Output: 0


        Constraints:
        ---------
            1 <= password.length <= 50
            password consists of letters, digits, dot '.' or exclamation mark '!'.
 */

public class StrongPasswordChecker {

    public int strongPasswordChecker(String password) {
        int res = 0, a = 1, A = 1, d = 1;
        char[] charArray = password.toCharArray();
        int[] arr = new int[charArray.length];

        for (int i = 0; i < arr.length;) {
            if (Character.isLowerCase(charArray[i])) {
                a = 0;
            }

            if (Character.isUpperCase(charArray[i])) {
                A = 0;
            }

            if (Character.isDigit(charArray[i])) {
                d = 0;
            }

            int j = i;
            while (i < charArray.length && charArray[i] == charArray[j]){
                i++;
            }
            arr[j] = i - j;
        }

        int totalMissing = a + A + d;

        if (arr.length < 6) {
            res += totalMissing + Math.max(0, 6 - (arr.length + totalMissing));

        }
        else {
            int exceededLength = Math.max(arr.length - 20, 0), leftOver = 0;
            res += exceededLength;

            for (int k = 1; k < 3; k++) {
                for (int i = 0; i < arr.length && exceededLength > 0; i++) {
                    if (arr[i] >= 3 && arr[i] % 3 == (k - 1)) {
                        arr[i] -= Math.min(exceededLength, k);
                        exceededLength -= k;
                    }
                }
            }

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 3 && exceededLength > 0) {
                    int need = arr[i] - 2;
                    arr[i] -= exceededLength;
                    exceededLength -= need;
                }

                if (arr[i] >= 3) {
                    leftOver += arr[i] / 3;
                }
            }

            res += Math.max(totalMissing, leftOver);
        }

        return res;
    }


    public static void main(String[] args) {
        StrongPasswordChecker passwordChecker = new StrongPasswordChecker();
        System.out.println(passwordChecker.strongPasswordChecker("a"));         //  5
        System.out.println(passwordChecker.strongPasswordChecker("aA1"));       //  3
        System.out.println(passwordChecker.strongPasswordChecker("aA1bbA1"));   //  0
    }

}
