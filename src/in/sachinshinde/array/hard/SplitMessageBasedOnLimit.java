package in.sachinshinde.array.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//  https://leetcode.com/problems/split-message-based-on-limit/

/*
        You are given a string, message, and a positive integer, limit.
        You must split message into one or more parts based on limit.

        Each resulting part should have the suffix "<a/b>",
            where "b" is to be replaced with the total number of parts and
            "a" is to be replaced with the index of the part, starting from 1 and going up to b.

        Additionally, the length of each resulting part (including its suffix) should be equal to limit,
            except for the last part whose length can be at most limit.

        The resulting parts should be formed such that when their suffixes are removed and
            they are all concatenated in order, they should be equal to message.
        Also, the result should contain as few parts as possible.

        Return the parts message would be split into as an array of strings.
        If it is impossible to split message as required, return an empty array.

        Example 1:
        ---------
        Input: message = "this is really a very awesome message", limit = 9
        Output: ["thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"]
        Explanation:
        The first 9 parts take 3 characters each from the beginning of message.
        The next 5 parts take 2 characters each to finish splitting message.
        In this example, each part, including the last, has length 9.
        It can be shown it is not possible to split message into less than 14 parts.

        Example 2:
        ---------
        Input: message = "short message", limit = 15
        Output: ["short mess<1/2>","age<2/2>"]
        Explanation:
        Under the given constraints, the string can be split into two parts:
        - The first part comprises of the first 10 characters, and has a length 15.
        - The next part comprises of the last 3 characters, and has a length 8.


        Constraints:
        ------------
            1 <= message.length <= 10^4
            message consists only of lowercase English letters and ' '.
            1 <= limit <= 10^4
 */

public class SplitMessageBasedOnLimit {

    public String[] splitMessage1(String message, int limit) {
        int messageLength = message.length();
        int totalLength = 1;
        int partitionLength = 1;

        //  how to come across this loop ?
        //  - aim is to find the totalLength for the final result
        while((messageLength + partitionLength +
                (3 + getStringLength(totalLength)) * totalLength) > (limit * totalLength)) {
            if(3 + getStringLength(totalLength) * 2 >= limit) {
                return new String[0];
            }
            totalLength++;  // incremented by 1 everytime
            partitionLength += getStringLength(totalLength);
        }

        String[] result = new String[totalLength];
        int currIdx = 0;

        for(int i = 0; i < totalLength; i++) {
            String suffix = "<" + (i + 1) + "/" + totalLength +">";
            int nextIdx = currIdx + limit - suffix.length();
            String prefix = message.substring(currIdx, Math.min(nextIdx, message.length()));
            currIdx = nextIdx;
            result[i] = prefix + suffix;
        }

        return result;
    }

    private int getStringLength(int s){
        return Integer.toString(s).length();
    }

    public String[] splitMessage(String message, int limit) {
        //  Q1. What does this table represents ?
        int[] stgTable = {
                (limit - 5) * 9,
                (limit - 6) * 9 + (limit - 7) * 90,
                (limit - 7) * 9 + (limit - 8) * 90 + (limit - 9) * 900,
                (limit - 8) * 9 + (limit - 9) * 90 + (limit - 10) * 900 + (limit - 11) * 9000,
        };
        int stg = 0;
        while (stg < stgTable.length && stgTable[stg] < message.length()) {
            stg++;
        }
        if (stg == stgTable.length) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        int idx = 1, strIdx = 0;

        // how to come across the terminating condition in the second for loop ?
        for (int i = 0; i <= stg; i++) {
            int size = (limit - 5) - (stg + i);     // motivation behind this ?
            for (int j = 0; j < 9 * Math.pow(10, i) && strIdx < message.length(); j++) {
                list.add(message.substring(strIdx, Math.min(message.length(), strIdx + size)) + "<" + idx);
                strIdx += size;
                idx++;
            }
        }
        String[] res = list.toArray(new String[]{});
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] + "/" + (idx - 1) + ">";
        }
        return res;
    }

    public static void main(String[] args) {
        SplitMessageBasedOnLimit message = new SplitMessageBasedOnLimit();
        System.out.println(Arrays.toString(message.splitMessage("this is really a very awesome message", 9)));
        //  "thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"
        System.out.println(Arrays.toString(message.splitMessage1("this is really a very awesome message", 9)));
        //  "thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"


        System.out.println(Arrays.toString(message.splitMessage1("short message", 15)));    //  "short mess<1/2>", "age<2/2>"
        System.out.println(Arrays.toString(message.splitMessage("short message", 15)));     //  "short mess<1/2>", "age<2/2>"
    }
}