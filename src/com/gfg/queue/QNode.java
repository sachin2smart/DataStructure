package com.gfg.queue;

/*
	
	
	front                    rear
	 ____ ____ ____ ____ ____ ____
	|	 |	  |	   |    |    |    |____
	|____|____|____|____|____|____|    |
									   |
									 __|__
									   _
									   . 
    remove                     add
    
    
*/

public class QNode {
	
	int key;
	QNode next;
	
	public QNode(int key) {
		this.key = key;
		this.next = null;
	}
	
}


