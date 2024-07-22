package in.sachinshinde.array.prefixsum;

/*
        You are given an array of positive integers nums of length n.

        A polygon is a closed plane figure that has at least 3 sides.
        The longest side of a polygon is smaller than the sum of its other sides.

        Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak
            where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak,
            then there always exists a polygon with k sides
                whose lengths are a1, a2, a3, ..., ak.

        The perimeter of a polygon is the sum of lengths of its sides.
        Return the largest possible perimeter of a polygon whose sides can be formed from nums, or
            -1 if it is not possible to create a polygon.


        Example 1:
        ----------
        Input: nums = [5,5,5]
        Output: 15
        Explanation: The only possible polygon that can be made from nums has 3 sides: 5, 5, and 5.
                    The perimeter is 5 + 5 + 5 = 15.

        Example 2:
        ----------
        Input: nums = [1,12,1,2,5,50,3]
        Output: 12
        Explanation: The polygon with the largest perimeter which can be made from nums has 5 sides: 1, 1, 2, 3, and 5.
        The perimeter is 1 + 1 + 2 + 3 + 5 = 12.
        We cannot have a polygon with either 12 or 50 as the longest side because it is not possible to include 2 or
            more smaller sides that have a greater sum than either of them.
        It can be shown that the largest possible perimeter is 12.

        Example 3:
        ----------
        Input: nums = [5,5,50]
        Output: -1
        Explanation: There is no possible way to form a polygon from nums,
            as a polygon has at least 3 sides and 50 > 5 + 5.


        Constraints:
        -----------
            3 <= n <= 105
            1 <= nums[i] <= 109

 */

import java.util.Arrays;

public class FindPolygonWithTheLargestPerimeter {

    //  Sorting Approach
    public long largestPerimeter(int[] nums) {
    /*
            50  : 1
            12  : 1
            5   : 1
            3   : 1
            2   : 1
            1   : 2

                50 : (12+5+3+2+1+1) = 50 : 24 (reject, since 24 is less than 50)
                12 : (5+3+2+1+1) = 50 : 12 (reject, since 12 is less than 50)
                5  : (3+2+1+1) = 5 : 7 (accepted, since 7 is greater than 5)
                        : ans = 5 + 7 = 12
        */


        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;

        for (int num : nums) {
            sum += num;
        }

        for(int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            sum -= num;
            if(sum > num) {
                return sum + num;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        FindPolygonWithTheLargestPerimeter poly = new FindPolygonWithTheLargestPerimeter();
        System.out.println(poly.largestPerimeter(new int[]{5, 5, 5}));              //  15
        System.out.println(poly.largestPerimeter(new int[]{1, 12, 1, 2, 5, 50, 3}));    //  12
        System.out.println(poly.largestPerimeter(new int[]{5, 5, 50}));             //  -1

        System.out.println(poly.largestPerimeter2(new int[]{5, 5, 5}));              //  15
        System.out.println(poly.largestPerimeter2(new int[]{1, 12, 1, 2, 5, 50, 3}));    //  12
        System.out.println(poly.largestPerimeter2(new int[]{5, 5, 50}));             //  -1
    }

    //  Recursion + Pruning
    public long largestPerimeter2(int[] nums) {
        long sum = 0, max = 0;
        int pos = 0;

        for (int num : nums) {
            sum += num;
            if (num > max) {
                max = num;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(max == nums[i]) {
                pos = i;
            }
        }

        if(sum == 0) {
            return -1;
        }

        if(sum - max > max){
            return sum;
        }
        else {
            nums[pos] = 0;                      //  pruning
            return largestPerimeter(nums);      //  recursion
        }
    }

}
