package in.sachinshinde.array.medium;

import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/bulls-and-cows

/*
        You are playing the Bulls and Cows game with your friend.

        You write down a secret number and ask your friend to guess what the number is.
        When your friend makes a guess, you provide a hint with the following info:

        The number of "bulls", which are digits in the guess that are in the correct position.
        The number of "cows", which are digits in the guess that are in your secret number but
            are located in the wrong position.

        Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
        Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

        The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
        Note that both secret and guess may contain duplicate digits.

        Example 1:
        ---------
        Input: secret = "1807", guess = "7810"
        Output: "1A3B"
        Explanation: Bulls are connected with a '|' and cows are underlined:
        "1807"
          |
        "7810"

        Example 2:
        ---------
        Input: secret = "1123", guess = "0111"
        Output: "1A1B"
        Explanation: Bulls are connected with a '|' and cows are underlined:
        "1123"        "1123"
          |      or     |
        "0111"        "0111"
        Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.


        Constraints:
        -----------
            1 <= secret.length, guess.length <= 1000
            secret.length == guess.length
            secret and guess consist of digits only.
 */

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        StringBuilder secretWord = new StringBuilder(secret);
        StringBuilder guessWord = new StringBuilder(guess);
        Map<Character, Integer> dict = new HashMap<>();
        int numBulls = 0;
        int numCows = 0;

        for (int i = secret.length() - 1; i > -1; i--) {
            if (secretWord.charAt(i) == guessWord.charAt(i)) {
                numBulls++;
                secretWord.deleteCharAt(i);
                guessWord.deleteCharAt(i);
            }
            else {
                dict.put(secret.charAt(i), dict.getOrDefault(secret.charAt(i), 0) + 1);
            }
        }

        for (int i = 0; i < guessWord.length(); i++) {
            if(dict.containsKey(guessWord.charAt(i)) && dict.get(guessWord.charAt(i)) > 0) {
                numCows++;
                dict.put(guessWord.charAt(i), dict.get(guessWord.charAt(i)) - 1);
            }
        }

        return numBulls + "A" + numCows + "B";
    }

    public static void main(String[] args) {
        BullsAndCows bullsAndCows = new BullsAndCows();
        System.out.println(bullsAndCows.getHint("1807", "7810"));   //  1A3B
        System.out.println(bullsAndCows.getHint("1123" , "0111"));  //  1A1B

        System.out.println(bullsAndCows.getHint2("1807", "7810"));   //  1A3B
        System.out.println(bullsAndCows.getHint2("1123" , "0111"));  //  1A1B
    }

    public String getHint2(String secret, String guess) {
        int numBulls = 0;
        int numCows = 0;

        // Arrays to count occurrences of each digit in secret and guess
        int[] secretCounts = new int[10];
        int[] guessCounts = new int[10];

        // First pass: count bulls and record unmatched characters
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                numBulls++;
            }
            else {
                // Record the unmatched characters
                secretCounts[s - '0']++;
                guessCounts[g - '0']++;
            }
        }

        // Second pass: count cows
        for (int i = 0; i < 10; i++) {
            // The number of cows is the minimum number of unmatched characters
            // present in both secret and guess
            numCows += Math.min(secretCounts[i], guessCounts[i]);
        }

        // Construct the result in the required format
        return numBulls + "A" + numCows + "B";
    }

}
