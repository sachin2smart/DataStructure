package in.sachinshinde.hashmap;

//  https://leetcode.com/problems/snapshot-array/

/*
        Implement a SnapshotArray that supports the following interface:

        SnapshotArray(int length) initializes an array-like data structure with the given length.
        Initially, each element equals 0.
        void set(index, val) sets the element at the given index to be equal to val.
        int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
        int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id


        Example 1:
        -----------
        Input: ["SnapshotArray","set","snap","set","get"]
        [[3],[0,5],[],[0,6],[0,0]]
        Output: [null,null,0,null,5]
            Explanation:
            SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
            snapshotArr.set(0,5);  // Set array[0] = 5
            snapshotArr.snap();  // Take a snapshot, return snap_id = 0
            snapshotArr.set(0,6);
            snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5


        Constraints:
        -----------
            1 <= length <= 5 * 104
            0 <= index < length
            0 <= val <= 109
            0 <= snap_id < (the total number of times we call snap())
            At most 5 * 104 calls will be made to set, snap, and get.
 */

import java.util.*;

public class SnapshotArray {
    //  Using List of HashMap
    List<Map<Integer, Integer>> list;

    public SnapshotArray(int length) {
        list = new ArrayList<>();
        list.add(new HashMap<>());
    }

    public void set(int index, int val) {
        int snapId = list.size() - 1;   //  get the last snapId
        list.get(snapId).put(index, val);
    }

    public int snap() {
        list.add(new HashMap<>());  //  new map after snap
        return list.size() - 2;     //  why (-2) ? ==> since it is asked to return the same
    }

    public int get(int index, int snap_id) {
        for (int snap = snap_id; snap >= 0; snap--) {
            if (list.get(snap).containsKey(index)) {
                return list.get(snap).get(index);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0,5);
        System.out.println(snapshotArray.snap());   //  0
        snapshotArray.set(0,6);
        System.out.println(snapshotArray.get(0,0)); //  5
        System.out.println(snapshotArray.get(0,1)); //  6

        System.out.println();

        SnapshotArray2 snapshotArray2 = new SnapshotArray2(3);
        snapshotArray2.set(0,5);
        System.out.println(snapshotArray2.snap());   //  0
        snapshotArray2.set(0,6);
        System.out.println(snapshotArray2.get(0,0)); //  5
        System.out.println(snapshotArray2.get(0,1)); //  6

        System.out.println();

        SnapshotArray3 snapshotArray3 = new SnapshotArray3(3);
        snapshotArray3.set(0,5);
        System.out.println(snapshotArray3.snap());   //  0
        snapshotArray3.set(0,6);
        System.out.println(snapshotArray3.get(0,0)); //  5
        System.out.println(snapshotArray3.get(0,1)); //  6
    }

    //  Using List of TreeMap
    static class SnapshotArray2 {
        private int snapId;
        private final List<TreeMap<Integer, Integer>> snapshots;

        public SnapshotArray2(int length) {
            snapId = 0;
            snapshots = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                snapshots.add(new TreeMap<Integer, Integer>(){{put(0,0);}});
            }
        }

        public void set(int index, int val) {
            snapshots.get(index).put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            return snapshots.get(index).floorEntry(snap_id).getValue();
        }
    }

    //  Using TreeMap Array
    static class SnapshotArray3 {
        TreeMap<Integer, Integer>[] snapshots;
        int snapId = 0;

        public SnapshotArray3(int length) {
            snapshots = new TreeMap[length];
            for (int i = 0; i < length; i++) {
                snapshots[i] = new TreeMap<Integer, Integer>();
                snapshots[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            snapshots[index].put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            return snapshots[index].floorEntry(snap_id).getValue();
        }
    }
}
