package in.sachinshinde.z_basicsjava;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class Performance {

    public static void main(String[] args) {
        Performance performance = new Performance();
        performance.checkArrayDequeAndStackPerformace();
        performance.checkArraysCollectionSortPerformance();
    }

    private void checkArrayDequeAndStackPerformace() {
        // ArrayDeque has better performance than Stack

        Deque<Integer> dq = new ArrayDeque<>();     // better
        Stack<Integer> st = new Stack<>();

        /*------------
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
         */

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
    private void checkArraysCollectionSortPerformance() {
        //  https://medium.com/@reetesh043/collection-sort-vs-arrays-sort-in-java-1528f83223c3

        /*
                Arrays.sort() is specifically designed for native arrays, while
                    Collections.sort() is more versatile and handles List implementations.

                Arrays.sort() has specialized implementations for primitive types,
                providing better performance compared to Collection.sort() for sorting arrays of primitive data types.

                Arrays.sort() might be slightly more performant for primitive arrays
                    due to its direct manipulation of memory.
         */

        //  *********************   Arrays.sort()    **********************************//
        //  Example 1
        int[] numbers = {5, 3, 8, 2, 1, 9, 4};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));   // Output: [1, 2, 3, 4, 5, 8, 9]

        //  Example 2
        String[] names = {"John", "Alice", "Bob", "David", "Eve"};
        Arrays.sort(names);
        System.out.println(Arrays.toString(names));  // Output: Sorted Array: [Alice, Bob, David, Eve, John]

        //  Variants
        Arrays.sort(numbers, 2, 5); // Sorts elements from index 2 to 4
        Arrays.sort(names, String::compareTo); // Sorts strings lexicographically
        Arrays.sort(names, (a, b) -> a.compareTo(b)); // Sorts strings lexicographically (same as above)
        Arrays.parallelSort(numbers);   //  uses the Fork/Join framework for parallelism; speeding up the sorting process for large arrays
        Arrays.parallelSort(numbers, 2, 5); // Sorts elements from index 2 to 4 in parallel

        //  *********************   Collections.sort()    **********************************//
        List<Integer> numbersList = new ArrayList<>(Arrays.asList(5, 3, 8, 2, 1, 9, 4));
        Collections.sort(numbersList);      //  sorting collections that implement the List interface
        System.out.println(numbersList);

        List<String> namesList = new ArrayList<>(Arrays.asList("John", "Alice", "Bob", "David", "Eve"));
        Collections.sort(namesList);    //  it uses modified merge sort; time complexity: O(nlogn)
        System.out.println(namesList);

        //  variants
        // Custom comparator to sort strings by their lengths in ascending order
        Comparator<String> lengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        Collections.sort(namesList, lengthComparator);  // Sorting the list using the custom comparator

        /*
                Takeaways:
                    [*] Use Arrays.sort() when performance is more important for primitive types
                    [*] Use Collections.sort() when flexibility and type safety are more important.
         */
    }
}
