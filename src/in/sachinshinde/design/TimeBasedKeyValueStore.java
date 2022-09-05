package in.sachinshinde.design;

import java.util.HashMap;
import java.util.TreeMap;

//	https://leetcode.com/problems/time-based-key-value-store/

/*
 	Design a time-based key-value data structure that 
 		can store multiple values for the same key at different time stamps and 
 		retrieve the key's value at a certain timestamp.

	Implement the TimeMap class:
		- TimeMap() Initializes the object of the data structure.
		- void set(String key, String value, int timestamp) Stores the key key 
				with the value value at the given time timestamp.
		- String get(String key, int timestamp) Returns a value such that 
			set was called previously, with timestamp_prev <= timestamp. 
			If there are multiple such values, it returns the value associated
			 with the largest timestamp_prev. If there are no values, it returns "".
 */
	
public class TimeBasedKeyValueStore {
    HashMap<String, TreeMap<Integer, String>> keyTimeMap;
    
    public TimeBasedKeyValueStore() {
        keyTimeMap = new HashMap<String, TreeMap<Integer, String>>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!keyTimeMap.containsKey(key)) {
            keyTimeMap.put(key, new TreeMap<Integer, String>());
        }
        keyTimeMap.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!keyTimeMap.containsKey(key)) {
            return "";
        }
        Integer floorKey = keyTimeMap.get(key).floorKey(timestamp);
        if (floorKey != null) {
            return keyTimeMap.get(key).get(floorKey);
        }
        return "";
   }
   
   // Driver
    public static void main(String[] args) {
		TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
		store.set("foo", "bar", 1);
		System.out.println(store.get("foo", 1));	//	bar
		System.out.println(store.get("foo", 3));	//	bar
		store.set("foo", "bar2", 4);
		System.out.println(store.get("foo", 4));	//	bar2
		System.out.println(store.get("foo", 5)); 	//	bar2
	}
}

/*
	Input
	["TimeMap", "set", "get", "get", "set", "get", "get"]
	[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]

	Output
	[null, null, "bar", "bar", null, "bar2", "bar2"]
	
	Explanation
		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
		timeMap.get("foo", 1);         // return "bar"
		timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
		timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
		timeMap.get("foo", 4);         // return "bar2"
		timeMap.get("foo", 5);         // return "bar2"

Constraints:
	1 <= key.length, value.length <= 100
	key and value consist of lowercase English letters and digits.
	1 <= timestamp <= 107
	All the timestamps timestamp of set are strictly increasing.
	At most 2 * 105 calls will be made to set and get.

*/