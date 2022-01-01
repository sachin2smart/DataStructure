package in.sachinshinde.linkedlist;

//	https://www.geeksforgeeks.org/flattening-a-linked-list/
//	https://github.com/striver79/SDESheet/blob/main/flatteningOfLinkedListJava
//	https://www.youtube.com/watch?v=ysytSSXpAI0&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=39


public class FlattenLinkedList {
	
	public ListNodeDown flatten(ListNodeDown root) {
		
		if(root == null || root.next == null) 
			return root;
		
		root.next = flatten(root.next);
		root = mergeTwoLists(root, root.next);
		
		return root;
	}
	
	private ListNodeDown mergeTwoLists(ListNodeDown a, ListNodeDown b) {
		
		ListNodeDown temp = new ListNodeDown(0);
		ListNodeDown res = temp;
		
		while(a != null && b != null) {
			if(a.data < b.data) {
				temp.down = a;
				temp = temp.down;
				a = a.down;
			}
			else {
				temp.down = b;
				temp = temp.down;
				b = b.down;
			}
		}
		
		if(a != null)
			temp.down = a;
		else
			temp.down = b;
		
		return res.down;
	}
	
}
