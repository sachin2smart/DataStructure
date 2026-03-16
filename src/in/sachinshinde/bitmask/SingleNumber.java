package in.sachinshinde.bitmask;

//	https://leetcode.com/problems/single-number/

public class SingleNumber {
  public static void main(String[] args) {
    SingleNumber singleNumber = new SingleNumber();
    System.out.println(singleNumber.singleNumber(new int[] {1, 1, 2}));
  }

  private int singleNumber(int[] nums) {
    int r = 0;
    for (int i : nums) {
      r = r ^ i;
    }
    return r;
  }
}
