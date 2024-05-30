package in.sachinshinde.z_basicsjava;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class Performance {

    public static void main(String[] args) {
        // ArrayDeque has better performance than Stack
        Deque<Integer> dq = new ArrayDeque<>();     // better
        Stack<Integer> st = new Stack<>();
        //------------
        // Reason 1:-
        //------------
        //      * ArrayDeque : Not Thread Safe
        //      * Stack      : Thread Safe
        //  To acquire and release a lock it requires relatively more time
        //------------
        // Reason 2:-
        //------------
        //      Stack : class       (only one class can be extended by a single class)
        //      Deque : interface   (any number of classes can be implemented by a single class)
        //------------
        // Reason 3:-
        //------------
        // One more reason to use Deque over Stack is Deque has the ability to use streams convert to list
        //  with keeping LIFO concept applied while Stack does not.

        Stack<Integer> stack = new Stack<>();
        Deque<Integer> deque = new ArrayDeque<>();

        stack.push(1);      // 1 is the top
        deque.push(1);        // 1 is the top
        stack.push(2);      // 2 is the top
        deque.push(2);        // 2 is the top

        List<Integer> list1 = stack.stream().collect(Collectors.toList());//[1,2]
        List<Integer> list2 = deque.stream().collect(Collectors.toList());//[2,1]

        List<Integer> arrayList1 = new ArrayList<>(stack);//[1,2]
        List<Integer> arrayList2 = new ArrayList<>(deque);//[2,1]


        //  If there are more than 10 elements, LinkedList works better
        Deque<Integer> linkedList = new LinkedList<>();

        // ConcurrentLinkedDeque : Better for multi-threaded environment
        Deque concurrentLinkedDeque = new ConcurrentLinkedDeque();
    }
}
