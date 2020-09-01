package com.gfg.queue;

public class QueueByLL {

	QNode front;
	QNode rear;
	
	public QueueByLL() {
		this.front = null;
		this.rear = null;
	}

	public void enqueue(int data) {
		
		QNode newNode = new QNode(data);
		
		if(rear == null) {
			this.front = newNode;
			this.rear = newNode;
			return;
		}
		
		this.rear.next = newNode;
		this.rear = newNode;
	}

	public void dequeue() {
		
		if(this.front == null)
			return;
		
//		QNode nodeToRemove = this.front;
		this.front = this.front.next;
		
		if(this.front == null)
			this.rear = null;
	}
	
	boolean isEmpty() {
		return front == null ? true : false;
	}
	
	public static void main(String[] args) {
		
		QueueByLL queueByLL = new QueueByLL();
		
		if(!queueByLL.isEmpty()) {
			System.out.println(" Front Node : "+queueByLL.front.key);
			System.out.println(" Rear Node : "+queueByLL.rear.key);	
		}
		
			
//          F	R	
//		10--20--30
//		 X
		
		queueByLL.enqueue(10);
		queueByLL.enqueue(20);
		queueByLL.enqueue(30);
		queueByLL.dequeue();
		
		if(!queueByLL.isEmpty()) {
			System.out.println(" Front Node : "+queueByLL.front.key);
			System.out.println(" Rear Node : "+queueByLL.rear.key);	
		}	
//		    F           R
//		20--30--40--50--60
//		 X
		
		queueByLL.enqueue(40);
		queueByLL.enqueue(50);
		queueByLL.enqueue(60);
		queueByLL.dequeue();
		
		if(!queueByLL.isEmpty()) {
			System.out.println(" Front Node : "+queueByLL.front.key);
			System.out.println(" Rear Node : "+queueByLL.rear.key);	
		}
	}

}
