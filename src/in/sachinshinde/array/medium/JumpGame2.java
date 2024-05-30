package in.sachinshinde.array.medium;

//  https://leetcode.com/problems/jump-game-ii/

/*
        You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

        Each element nums[i] represents the maximum length of a forward jump from index i.
        In other words, if you are at nums[i], you can jump to any nums[i + j] where:
            0 <= j <= nums[i] and
            i + j < n

        Return the minimum number of jumps to reach nums[n - 1].
        The test cases are generated such that you can reach nums[n - 1].



        Example 1:
        ---------
        Input: nums = [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2.
        Jump 1 step from index 0 to 1, then 3 steps to the last index.

        Example 2:
        ---------
        Input: nums = [2,3,0,1,4]
        Output: 2

        Constraints:
        -----------
            1 <= nums.length <= 104
            0 <= nums[i] <= 1000
            It's guaranteed that you can reach nums[n - 1].
 */

public class JumpGame2 {

    public int jump3(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int curr = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == curr) {
                jumps++;
                curr = farthest;
            }
        }

        return jumps;
    }

    // This solution is better if the interviewer asked - do not modify the input array
    public int jump(int[] nums) {
        int n = nums.length;
        int destinationPos = n-1;
        int jumpTimes = 0;

        int currCoveragePos = 0;    // why this extra variable ? --> To indicate the max jump from the current position
        int lastJumpPos = 0;    // purpose of this variable ? --> To indicate till what position we were reached from last jump

        // Quick response if start index == destination index == 0
        if(n == 1) {
            return 0;
        }

        // Greedy strategy: extend coverage as long as possible with lazp jump
        for(int currPos = 0 ; currPos < n ; currPos++) {
            // extend coverage
            currCoveragePos = Math.max(currCoveragePos, currPos + nums[currPos]);
            if(currPos == lastJumpPos) {
                lastJumpPos = currCoveragePos;
                jumpTimes++;
                // check if we reached destination already
                if(currCoveragePos >= destinationPos) {
                    return jumpTimes;
                }
            }
        }

        return jumpTimes;
    }

    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();
        System.out.println(jumpGame2.jump(new int[]{2,3,1,1,4}));   // 2
        System.out.println(jumpGame2.jump(new int[]{2,3,0,1,4}));   // 2

        System.out.println(jumpGame2.jump2(new int[]{2,3,1,1,4}));   // 2
        System.out.println(jumpGame2.jump2(new int[]{2,3,0,1,4}));   // 2
    }

    // Simple
    public int jump2(int[] nums) {
        int n = nums.length;

        for(int i = 1; i < n; i++) {
            nums[i] = Math.max(nums[i] + i, nums[i-1]); // if the modification to the input array is allowed
        }

        int i = 0;
        int res = 0;

        while(i < n - 1) {
            res++;
            i = nums[i];
        }

        return res;
    }
}
