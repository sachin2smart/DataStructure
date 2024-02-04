package in.sachinshinde.z_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {
	public int minimumOperations(int[] nums) {
        Arrays.sort(nums);
        int count = 0, i = 0;
        while(i < nums.length) {
            if(nums[i] <= 0) {
                i++;
                continue;
            }
            int dec = nums[i];
            for(int j = i; j < nums.length; j++) {
                nums[j] = nums[j] - dec;
            }
            count++;
        }
        return count;
    }
	
	public static void main(String[] args) {
//		Test1 test1 = new Test1();
//		System.out.println(test1.minimumOperations(new int[] {1,5,0,3,5}));
//		System.out.println(test1.minimumOperations(new int[] {0}));
//		System.out.println(test1.minimumOperations(new int[] {1}));
//		System.out.println(test1.minimumOperations(new int[] {1,1}));
		test2();
	}
	
	private static void test2() {
		List<String> al = new ArrayList<>();
		for(int i=0;i<10;i++) {
			al.add(0, "One");
			al.add(0, "Two");
			al.add(0, "Three");
			System.out.println(al);
		}
	}
}
