package in.sachinshinde.z_basicsjava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Conversions {
	
	public static void main(String[] args) {

//		1. ArrayDeque to LinkedList Conversion
		Deque<Integer> ad = new ArrayDeque<Integer>();
		ad.add(1);
		ad.addFirst(0);
		ad.addLast(2);
		List<Integer> ll = new LinkedList<Integer>();
		ll = new LinkedList<Integer>(ad);
		System.out.println("\n 1. ArrayDeque to LinkedList Conversion");
		System.out.println("ArrayDeque: "+ad);	//[0, 1, 2]
		System.out.println("LinkedList: "+ll);	//[0, 1, 2]
		
//		2. ArrayDeque to ArrayList Conversion
		List<Integer> al = new ArrayList<Integer>();
		ad.remove();
		ad.removeFirst();
		ad.removeLast();
		ad.add(1);
		ad.addFirst(0);
		ad.addLast(2);
		al = new ArrayList<Integer>(ad);
		System.out.println("\n 2. ArrayDeque to ArrayList Conversion");
		System.out.println("ArrayDeque: "+ad);	//[0, 1, 2]
		System.out.println("ArrayList: "+al);	//[0, 1, 2]
		
		ad.retainAll(Arrays.asList(1));
		System.out.println(ad);	// [1]
		
//		3. Array to ArrayList conversion
		Integer[] arrInteger = new Integer[] {1,2,3};
		int[] arrInt = new int[] {1,2,3};
		List<Integer> al1 = new ArrayList<Integer>(Arrays.asList(arrInteger));
//		List<Integer> al11 = new ArrayList(Arrays.asList(arrInt)); // ** DO NOT Store int as Integer in List, use below 2 ways 
		List<Integer> al2 = new ArrayList<Integer>(Arrays.asList(Arrays.stream(arrInt).boxed().toArray(Integer[]::new)));
		List<Integer> al3 = new ArrayList<Integer>(Arrays.asList(IntStream.of(arrInt).boxed().toArray(Integer[]::new)));
		System.out.println("\n 3. Array to ArrayList conversion");
		System.out.println("Integer[] arrInteger: "+Arrays.toString(arrInteger));
		System.out.println("int[] arrInt: "+Arrays.toString(arrInt));
		System.out.println("ArrayList<Integer> al1: "+al1);
		System.out.println("ArrayList<int> al2: "+al2);
		System.out.println("ArrayList<int> al3: "+al3);

		
//		4. Array to LinkedList conversion
		Integer[] arrIntegerLL = new Integer[] {1,2,3};
		int[] arrIntLL = new int[] {1,2,3};
		List<Integer> ll1 = new LinkedList<Integer>(Arrays.asList(arrInteger));
//		List<Integer> ll11 = new LinkedList(Arrays.asList(arrInt)); // ** DO NOT Store int as Integer in List, use below 2 ways 
		List<Integer> ll2 = new LinkedList<Integer>(Arrays.asList(Arrays.stream(arrInt).boxed().toArray(Integer[]::new)));
		List<Integer> ll3 = new LinkedList<Integer>(Arrays.asList(IntStream.of(arrInt).boxed().toArray(Integer[]::new)));
		System.out.println("\n 4. Array to LinkedList conversion");
		System.out.println("Integer[] arrIntegerLL: "+Arrays.toString(arrIntegerLL));
		System.out.println("int[] arrIntLL: "+Arrays.toString(arrIntLL));
		System.out.println("LinkedList<Integer> ll1: "+ll1);
		System.out.println("LinkedList<int> ll2: "+ll2);
		System.out.println("LinkedList<int> ll3: "+ll3);
	}
	
	
}
	