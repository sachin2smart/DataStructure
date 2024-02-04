package in.sachinshinde.z_java;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test2 {

    public void printChars() {
	String str = "I am from Pune";
	Map<Character, Integer> hm = new HashMap<>();
	
	char[] charArray = str.toCharArray();
	
	int n = charArray.length;
	
	for(int i=0; i < n; i++) {
	    if(charArray[i] != ' ') {
		if(hm.containsKey(charArray[i])) {
		    hm.put(charArray[i], hm.get(charArray[i]) + 1);
		}
		else {
		    hm.put(charArray[i], 1);
		}
	    }
	}
	
	for(Map.Entry<Character, Integer> entry: hm.entrySet()) {
	    System.out.println(entry.getKey() +" : " + entry.getValue());
	}
	
    }
    
    public static void main(String[] args) {
	Test2 t = new Test2();
	t.printChars();
	
	String str = "Sachin";
	Pattern.compile(".").matcher(str).results().map(m -> m.group().toLowerCase())
		.collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()))
		.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
