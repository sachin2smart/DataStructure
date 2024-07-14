package in.sachinshinde.backtrack;

//  https://leetcode.com/problems/beautiful-arrangement/

/*
        Suppose you have n integers labeled 1 through n.
        A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement
            if for every i (1 <= i <= n), either of the following is true:
                -   perm[i] is divisible by i.
                -   i is divisible by perm[i].

        Given an integer n, return the number of the beautiful arrangements that you can construct.

        Example 1:
        ---------
        Input: n = 2
        Output: 2
        Explanation:
        The first beautiful arrangement is [1,2]:
            - perm[1] = 1 is divisible by i = 1
            - perm[2] = 2 is divisible by i = 2
        The second beautiful arrangement is [2,1]:
            - perm[1] = 2 is divisible by i = 1
            - i = 2 is divisible by perm[2] = 1

        Example 2:
        ---------
        Input: n = 1
        Output: 1


        Constraints:
        ------------
            1 <= n <= 15
 */

public class BeautifulArrangement {

    public int countArrangement(int n) {
        int[] nums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }

        return recur(nums, 1);
    }

    private int recur(int[] nums, int curri) {
        if (curri == nums.length) {
            return 1;
        }
        int res = 0;

        for (int i = curri; i < nums.length; i++) {
            swap(nums, curri, i);

            if (nums[curri] % curri == 0 || curri % nums[curri] == 0) {
                res += recur(nums, curri + 1);
            }

            swap(nums, curri, i);
        }
        return res;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
        System.out.println(beautifulArrangement.countArrangement(2));   //  2
        System.out.println(beautifulArrangement.countArrangement(1));   //  1
        System.out.println(beautifulArrangement.countArrangement(15));   //  24679
    }

}
