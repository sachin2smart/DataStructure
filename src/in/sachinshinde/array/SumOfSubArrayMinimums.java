package in.sachinshinde.array;

import java.util.Stack;

public class SumOfSubArrayMinimums {

    public int sumSubarrayMins(int[] arr) {
        final int MOD = 1000000007;
        Stack<Integer> st = new Stack<>();
        long sumOfMinimums = 0;

        for (int i = 0; i <= arr.length; i++) {
            while (!st.empty() && (i == arr.length || arr[st.peek()] >= arr[i])) {
                int mid = st.pop();
                int leftBoundary = st.empty() ? -1 : st.peek();

                long count = (long) (mid - leftBoundary) * (i - mid) % MOD;

                sumOfMinimums += (count * arr[mid]) % MOD;
                sumOfMinimums %= MOD;
            }
            st.push(i);
        }

        return (int) sumOfMinimums;
    }

    public int sumSubarrayMins2(int[] arr) {
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        long MOD = (long) 1e9 + 7;
        stack.push(-1);

        for (int i2 = 0; i2 < arr.length + 1; i2++) {
            int currVal = (i2 < arr.length) ? arr[i2] : 0;

            while(stack.peek() != -1 && currVal < arr[stack.peek()]) {
                int index = stack.pop();
                int i1 = stack.peek();
                int left = index - i1;
                int right = i2 - index;
                long add = (left * right * (long) arr[index]) % MOD;
                res += add ;
                res %= MOD;
            }

            stack.push(i2);

        }

        return (int) res;
    }

    public static void main(String[] args) {
        SumOfSubArrayMinimums sum = new SumOfSubArrayMinimums();
        System.out.println(sum.sumSubarrayMins(new int[]{3,1,2,4}));    //  17
        System.out.println(sum.sumSubarrayMins(new int[]{11,81,94,43,3}));    //  444
    }

}
