package in.sachinshinde.stack;

public class StackConstantTimeOperationAmazon {

    /*
    // Create an implementation of a stack with push, pop and min functions executing in constant time

Class Stack {

    StackNode top;
    //int[] dataList; // two-pointer + BinarySearch: nLogn
    //Queue<Integer> pq = new PriorityQueue<>((a,b) -> a-b); //meanHeap
    //Set<Integer> treeSet = new TreeSet<>(); // push, pop: logN

    public void push(int ele) {
        int minVal = Math.min(ele, top.getTop());
        StackNode sN = new StackNode(ele, minVal);
        if(top == null) {
            top = sN;
        }
        else {
            sN.next = top;
            top = sN;
        }
    }

    public int pop() {
        if(isEmpty) {
            return -1;
        }
        else {
            int ele = top.data;
            top = top.next;
            top.minVal = top.next.MinVal;
        }
    }

    public int min() {
        return top.minVal;
    }

}

class StackNode() {
    int data;
    int minVal;
    StackNode next;

    StackNode(int data, int minVal) {
        this.data = data;
        this.minVal = minVal;
        this.next = null;
    }

}
     */
}
