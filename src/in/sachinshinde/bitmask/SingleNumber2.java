package in.sachinshinde.bitmask;

//	https://leetcode.com/problems/single-number-ii/

public class SingleNumber2 {

	private int singleNumber2(int A[]) {
		int ones = 0, twos = 0;
		for(int i = 0; i < A.length; i++){
			ones = (ones ^ A[i]) & ~twos;
		    twos = (twos ^ A[i]) & ~ones;
		}
		return ones;
	}
	
	public static void main(String[] args) {
		SingleNumber2 singleNumber2 = new SingleNumber2();
		System.out.println(singleNumber2.singleNumber(new int[] {1,1,1,3}));
		System.out.println(singleNumber2.singleNumber2(new int[] {1,1,1,3}));
	}
	
	private int singleNumber(int A[]) {
		int ones=0, twos=0;
	    int common_bit_mask;
	    for(int i=0; i<A.length;i++) {
			twos= twos |(ones & A[i]);
			ones= ones ^ A[i];
			common_bit_mask= ~(ones & twos);
			ones= ones & common_bit_mask;
			twos= twos & common_bit_mask;
		}
		return ones;
	}

}