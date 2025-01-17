package in.sachinshinde.z_company.Robinhood;

import java.util.Arrays;

public class Result2 {

//    public static int minErrors(String errorString, int x, int y) {
//        int MOD = 1000000007;
//
//        // Count initial '0's and '1's
//        int count0 = 0, count1 = 0;
//        long errorsFor0 = 0, errorsFor1 = 0;
//
//        // Calculate errors if '!' is replaced by '0'
//        for (char ch : errorString.toCharArray()) {
//            if (ch == '0') {
//                errorsFor0 = (errorsFor0 + (long) count0 * y) % MOD;
//                count0++;
//            } else if (ch == '1') {
//                errorsFor0 = (errorsFor0 + (long) count0 * y) % MOD;
//                count1++;
//            } else if (ch == '!') {
//                errorsFor1 = (errorsFor1 + (long) count0 * y) % MOD; // Treat '!' as '1' here
//                errorsFor0 = (errorsFor0 + (long) count1 * x) % MOD; // Treat '!' as '0' here
//            }
//        }
//
//        return (int) Math.min(errorsFor0, errorsFor1) != 0 ? (int) Math.min(errorsFor0, errorsFor1) + x : 0;
//    }

    public static int getMinErrors1(String errorString, int x, int y) {
        // Case 1: Replace all '!' with '0' and calculate errors
        int errorsWhenZero = calculateErrors1(errorString.replace('!', '0'), x, y);

        // Case 2: Replace all '!' with '1' and calculate errors
        int errorsWhenOne = calculateErrors1(errorString.replace('!', '1'), x, y);

        // Return the minimum of both cases, modulo 10^9 + 7
        return Math.min(errorsWhenZero, errorsWhenOne) % MOD;
    }

    private static int calculateErrors1(String binaryString, int x, int y) {
        int countZero = 0; // To count zeros encountered
        int countOne = 0;  // To count ones encountered
        int errorsZero = 0;
        int errorsOne = 0;

        for (int i=0; i <binaryString.length()-1;) {
            if (binaryString.substring(i, i+2).equals("01")) {
                // Each '1' before this '0' makes a '10' subsequence

                countZero++;
            } else if (binaryString.substring(i, i+2).equals("10")) {
                // Each '0' before this '1' makes a '01' subsequence

                countOne++;
            }
            i=i+1;
        }
        errorsZero = (errorsZero + countZero * x) % MOD;
        errorsOne = (errorsOne + countOne * y) % MOD;
        return x*errorsZero + y*errorsOne;
    }

    public static void main(String[] args) {
//        System.out.println(getMinErrors("101!1", 2, 3));
        System.out.println(getMinErrors("!!!!!!!", 23, 47));
    }

    private static final int MOD = 1000000007;

    public static int getMinErrors2(String errorString, int x, int y) {
        // Case 1: Replace all '!' with '0' and calculate errors
        int errorsWhenZero = calculateErrors(errorString.replace('!', '0'), x, y);

        // Case 2: Replace all '!' with '1' and calculate errors
        int errorsWhenOne = calculateErrors(errorString.replace('!', '1'), x, y);

        // Return the minimum of both cases, modulo 10^9 + 7
        return Math.min(errorsWhenZero, errorsWhenOne) % MOD;
    }

    private static int calculateErrors(String binaryString, int x, int y) {
        int countZero = 0; // To count zeros encountered
        int countOne = 0;  // To count ones encountered
        int errors = 0;

        for (char ch : binaryString.toCharArray()) {
            if (ch == '0') {
                // Each '1' before this '0' makes a '10' subsequence
                errors = (errors + countOne * y) % MOD;
                countZero++;
            } else if (ch == '1') {
                // Each '0' before this '1' makes a '01' subsequence
                errors = (errors + countZero * x) % MOD;
                countOne++;
            }
        }
        return errors;
    }


        public static int getMinErrors(String errorString, int x, int y) {
            int n = errorString.length();
            if (n == 0) {
                return 0;
            }

            long[][] dp = new long[n + 1][n + 1];

            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], Long.MAX_VALUE);
            }
            dp[0][0] = 0;

            for (int i = 1; i <= n; i++) {
                if(i>1 && i<n-2 &&(errorString.charAt(i - 1) == '!' && errorString.charAt(i - 2) == '!') ) {
                    continue;
                }
                if (errorString.charAt(i - 1) == '0' || errorString.charAt(i - 1) == '!') {
                    for (int j = 0; j <= i; j++) {
                        if (dp[i - 1][j] < Long.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + (long) j * y);
                        }
                    }
                }
                if (errorString.charAt(i - 1) == '1' || errorString.charAt(i - 1) == '!') {
                    for (int j = 1; j <= i; j++) {
                        if (dp[i - 1][j - 1] < Long.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + (long) x * (i - j));
                        }
                    }
                }
            }

            long min = Long.MAX_VALUE;
            for (int i = 0; i <= n; i++) {
                min = Math.min(min, dp[n][i]);
            }

            return Long.MAX_VALUE == min ? 0 : (int) min;
        }
}
