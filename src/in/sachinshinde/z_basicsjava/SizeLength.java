package in.sachinshinde.z_basicsjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SizeLength {
	
	public static void main(String[] args) {
		
		String s1 = "0";
		String s2 = s1;
		s1 = "1";
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s2.length());
		
		int a = 0;
		int b = a;
		a = 1;
		System.out.println(Integer.hashCode(a));
		System.out.println(Integer.hashCode(b));
		
		String strObject = new String("Java");
		String strLiteral = "Java";
		strObject = "c++";
		System.out.println(strObject);
		System.out.println(strLiteral);
		System.out.println(strObject.hashCode());
		System.out.println(strLiteral.hashCode());	
		
		String a1 = "Java"; 
		String b1 = "Java"; 
		System.out.println(a1 == b1); // True
		
		String a2 = "Java"; 
		String b2 = "Java"; 
		System.out.println(a2.equals(b2)); // True
		
		String c = new String("Java"); 
		String d = new String("Java"); 
		System.out.println(c == d); // False
		
		String c2 = new String("Java"); 
		String d2 = new String("Java"); 
		System.out.println(c2.equals(d2));	//True
		
		
		int[] intArray = new int[]{1,2,3,0};
		intArray[0] = 9;
		intArray[3] = 4;
		System.out.println(Arrays.toString(intArray));
		int intArrayLength = intArray.length;
		System.out.println(intArrayLength);
		
		List<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(1,2,3));
		arrayList.set(0, 9);
		arrayList.add(4);
		System.out.println(arrayList);
		int arrayListSize = arrayList.size();
		System.out.println(arrayListSize);
		
		List<Integer> linkedList = new LinkedList<Integer>(Arrays.asList(1,2,3));
		linkedList.set(0, 9);
		linkedList.add(4);
		System.out.println(linkedList);
		int linkedListSize = linkedList.size();
		System.out.println(linkedListSize);
		
		Map<Integer, Integer> intHashMap = new HashMap<Integer, Integer>(){{put(0,1);put(1,2);put(2,3);}};
		intHashMap.replace(0, 9);
		intHashMap.put(3, 4);
		System.out.println(intHashMap);
		int intHashMapSize = intHashMap.size();
		System.out.println(intHashMapSize);
		
		Map<Integer, Integer> intTreeMap = new TreeMap<Integer, Integer>(){{put(0,1);put(1,3);put(2,2);}};
		intTreeMap.replace(1, 2);
		intTreeMap.replace(2, 3);
		intTreeMap.put(3, 4);
		System.out.println(intTreeMap);
		int intTreeMapSize = intHashMap.size();
		System.out.println(intTreeMapSize);
		
		Set<Integer> hashSet = new HashSet<>();
		hashSet.add(1);
		hashSet.add(3);
		hashSet.add(2);
		System.out.println(hashSet);
		int hashSetSize = hashSet.size();
		System.out.println(hashSetSize);
		
		Set<Integer> treeSet = new TreeSet<>();
		treeSet.add(1);
		treeSet.add(3);
		treeSet.add(2);
		System.out.println(treeSet);
		int treeSetSize = treeSet.size();
		System.out.println(treeSetSize);
		
		char[] charArray = new char[] {'s', 'a', 'c', 'h', 'i', 'n'};
		System.out.println(charArray.length);
		
	}
}
