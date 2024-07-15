package in.sachinshinde.z_basicsjava;


import java.util.*;
import java.util.stream.Collectors;
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

//		5. Sort Arrays in Ascending order & descending order
		Integer[] arr = new Integer[]{3,2,4,0,1};
		System.out.println("Before sort : " + Arrays.toString(arr));
		Arrays.sort(arr); // this can be applied on int[] as well as Integer[]
		System.out.println("Ascending order : " + Arrays.toString(arr));
		Arrays.sort(arr, Collections.reverseOrder());	// this cannot be applied on int[]
		System.out.println("Ascending order : " + Arrays.toString(arr));

//		6. Convert int[] array into Integer[] array
		int[] data = {1,2,3,4,5,6,7,8,9,10};

		Integer[] data1 = Arrays.stream( data ).boxed().toArray( Integer[]::new );
		Integer[] data2 = IntStream.of( data ).boxed().toArray( Integer[]::new );

//		7. Convert int[] array into List
		List<Integer> data3  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
		List<Integer> data4 = IntStream.of( data ).boxed().collect( Collectors.toList() );


//		8. Convert Arrays into streams and perform operations on them
		int[] arrInts = new int[]{1,2,3,4,5,6,7,8,9};
		// get max from array
		System.out.println(Arrays.stream(arrInts).max().getAsInt());
		// get even numbers from array
		System.out.println(Arrays.toString(Arrays.stream(arrInts).filter(n->n%2==0).toArray()));
		System.out.println(Arrays.stream(arrInt).noneMatch(n->n<0));	// check all are +ve numbers
		System.out.println(Arrays.stream(arrInt).average().getAsDouble());	// get average

//		9. Sort the array of strings by length
		String[] fruits = { "apple", "orange", "banana", "pear", "kiwi" };
		Arrays.sort(fruits, Comparator.comparing(String::length));
		System.out.println(Arrays.toString(fruits));

//		10. Sort the array of strings lexicographically
		String[] nums = { "1", "10", "2", "25"};
		Arrays.sort(nums, (String s1, String s2) -> (s2+s1).compareTo(s1+s2));
		System.out.println(Arrays.toString(nums));	// [25, 2, 1, 10] the combination 252110 creates the max number

		String[] nums2 = { "1", "10", "2", "25"};
		Arrays.sort(nums2, (String s1, String s2) -> (s1+s2).compareTo(s2+s1));
		System.out.println(Arrays.toString(nums2));	// [10, 1, 2, 25] the combination 101225 creates the smallest number

//		11. Fill array with same value, copy another array into new array
		int[] oldArr = new int[4];
		System.out.println(Arrays.toString(oldArr));	//	[0, 0, 0, 0]
		Arrays.fill(oldArr, Integer.MAX_VALUE);
		System.out.println(Arrays.toString(oldArr));	// [2147483647, 2147483647, 2147483647, 2147483647]
		Arrays.fill(oldArr, -1);
		System.out.println(Arrays.toString(oldArr));	//	[-1, -1, -1, -1]

		int[] newArr = Arrays.copyOf(oldArr, 4);
		System.out.println(Arrays.toString(newArr));	//	[-1, -1, -1, -1]
		int[] newArr2 = Arrays.copyOf(oldArr, 2);
		System.out.println(Arrays.toString(newArr2));	//	[-1, -1]

//		12. Define  modulo 10^9 + 7
		final int MOD1 = (int) (1e9 + 7);
		final int MOD2 = 1000000007;
		final int MOD3 = 100_00_00_007;
		System.out.println(MOD1);
		System.out.println(MOD2);
		System.out.println(MOD3);

	}

}
	