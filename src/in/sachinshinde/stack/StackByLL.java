// Stack Operations: push, pop, top, isEmpty  : https://www.geeksforgeeks.org/implement-a-stack-using-singly-linked-list/
package in.sachinshinde.stack;

public class StackByLL {

	StackNode top;

	public int top() {
		if (!isEmpty())
			return top.key;
		return -1;
	}

	public boolean isEmpty() {
		if (top == null)
			return true;
		return false;
	}

	public int pop() {
		if (isEmpty())
			return -1;
		else {
			int data = top.key;
			top = top.next;
			return data;
		}
	}

	public void push(int data) {
		StackNode sNode = new StackNode(data);
		if (top == null)
			top = sNode;
		else {
			sNode.next = top;
			top = sNode;
		}
	}

	public static void main(String[] args) {
		StackByLL stackByLL = new StackByLL();
		
		stackByLL.push(10);
		stackByLL.push(20);
		stackByLL.push(30);
		
		System.out.println("Top Element :"+stackByLL.top());
		
		System.out.println("Pop Element :"+stackByLL.pop());
		
		System.out.println("Top Element :"+stackByLL.top());
		
		System.out.println("Is Stack Empty:"+stackByLL.isEmpty());
		
		stackByLL.pop();
		stackByLL.pop();
		
		System.out.println("Is Stack Empty:"+stackByLL.isEmpty());
		
		System.out.println("Pop Element :"+stackByLL.pop());
	}
}
