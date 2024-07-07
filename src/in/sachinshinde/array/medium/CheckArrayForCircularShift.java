package in.sachinshinde.array.medium;

//  https://leetcode.com/discuss/interview-question/1419826/Robinhood-OA

/*

    For two arrays of the same length a and b let's say a is a cyclic shift of b
        if it's possible for a to become equal to b by
            moving 0 or more last elements to the beginning of the array,
            without changing the internal order.

    a = [ 1 2 3 4 5]
    b = [ 1 2 3 4 5]

    You are given an array of integers elements.
    Your task is to check whether elements is a cyclic shift of the identity array [1, 2,....,elements.length] or
    the reversed identity array [elements.length, elements.length-1,...., 2, 1].

    Return true if elements is a cyclic shift of the identity or reversed identity array,
        otherwise return false


    Example 1:
    ---------
    For elements = [1, 4, 2, 3], the output should be
    arrayShift (elements) = false

    Let's consider all the cyclic shifts of elements :
        Moving e elements from the end to the beginning, we get [1, 4, 2, 3]
        Moving 1 elements from the end to the beginning, we get [3, 1, 4, 2]
        Moving 2 elements from the end to the beginning, we get [2, 3, 1, 4]
        Moving 3 elements from the end to the beginning, we get [4, 2, 3, 1]
        None of these cyclic shifts equal the identity array [1, 2, 3, 4] or to the reversed identity array [4, 3, 2, 1].
        So the answer is false.

    Example 2:
    ---------
    For elements = [3, 4, 1, 2], the output should be
    array Shift (elements) = true

    If we move the last 2 elements of the given array from the end to the beginning, we get [1, 2, 3, 4],
    which is the identity array, so the answer is true

*/


public class CheckArrayForCircularShift {

    private boolean isMatch(int [] a, int [] b) {
        int element = a[0];
        for (int i = 0 ; i < b.length; i++) {
            if (element == b[i]) {
                if (isCircularMatch(a, b, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCircularMatch(int [] a, int [] b , int bIndex) {
        int aIndex = 0;
        while (aIndex < a.length) {
            if (a[aIndex] == b[bIndex % b.length]) {
                aIndex++;
                bIndex++;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckArrayForCircularShift arr = new CheckArrayForCircularShift();
        System.out.println(arr.isMatch(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));    // true
        System.out.println(arr.isMatch(new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3}));  //  false
        System.out.println(arr.isMatch(new int[]{1, 2, 3, 4}, new int[]{3, 4, 1, 2}));  // true

        System.out.println(arr.isCircularMatch(new int[]{1, 2, 3, 4, 5}));    // true
        System.out.println(arr.isCircularMatch(new int[]{1, 4, 2, 3}));  //  false
        System.out.println(arr.isCircularMatch(new int[]{3, 4, 1, 2}));  // true
    }

    private boolean isCircularMatch(int[] arr){
        int len = arr.length;
        int matchIndex = 0;
        for(int i = 0; i < len; i++) {
            int num = arr[i];
            if(num <= 0 || num > len)
                return false;

            if(num == 1) {
                matchIndex = i;
                break;
            }
        }
        return isInc(arr, matchIndex) || isDec(arr, matchIndex + 1 < arr.length ? matchIndex + 1 : 0);
    }

    private static boolean isInc(int[] arr, int i){
        for(int indValue = 1; indValue <= arr.length; indValue++){
            if(indValue != arr[i % arr.length]) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean isDec(int[] arr, int i){
        for(int indValue = arr.length; indValue >= 1; indValue--){
            if(indValue != arr[i % arr.length]) {
                return false;
            }
            i++;
        }
        return true;
    }
}
