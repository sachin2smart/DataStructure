package in.sachinshinde.array.medium;

import java.util.Arrays;

//  https://leetcode.com/problems/minimum-number-of-keypresses/

/*
        You have a keypad with 9 buttons, numbered from 1 to 9, each mapped to lowercase English letters.

        You can choose which characters each button is matched to as long as:
            - All 26 lowercase English letters are mapped to.
            - Each character is mapped to by exactly 1 button.
            - Each button maps to at most 3 characters.
            - To type the first character matched to a button, you press the button once.
        To type the second character, you press the button twice, and so on.

        Given a string s, return the minimum number of keypresses needed to type s using your keypad.
        Note that the characters mapped to by each button, and the order they are mapped in cannot be changed.

        Example 1:
        ---------
        Input: s = "apple"
        Output: 5
        Explanation: One optimal way to setup your keypad is shown above.
            Type 'a' by pressing button 1 once.
            Type 'p' by pressing button 6 once.
            Type 'p' by pressing button 6 once.
            Type 'l' by pressing button 5 once.
            Type 'e' by pressing button 3 once.
            A total of 5 button presses are needed, so return 5.

        Example 2:
        ---------
        Input: s = "abcdefghijkl"
        Output: 15
        Explanation: One optimal way to setup your keypad is shown above.
            The letters 'a' to 'i' can each be typed by pressing a button once.
            Type 'j' by pressing button 1 twice.
            Type 'k' by pressing button 2 twice.
            Type 'l' by pressing button 3 twice.
            A total of 15 button presses are needed, so return 15.

        Constraints:
        -----------
            1 <= s.length <= 10^5
            s consists of lowercase English letters.
 */

public class MinimumNumberOfKeypresses {
    public int minimumKeyresses(String s) {
        int[] arr = new int[26];
        for(int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        Arrays.sort(arr);

        int min = 0;
        int count = 0;

        int numTimesPress = 1;

        for(int i = 25; i >= 0; i--) {
            if(arr[i] > 0) {
                min += arr[i] * numTimesPress;
                count++;
                if(count == 9) {
                    numTimesPress++;
                    count = 0;
                }

            }
        }

        return min;
    }

    public static void main(String[] args) {
        MinimumNumberOfKeypresses keys = new MinimumNumberOfKeypresses();
        System.out.println(keys.minimumKeyresses("apple"));
        System.out.println(keys.minimumKeyresses("abcdefghijkl"));
    }
}
