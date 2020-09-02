package com.gfg.stack;

public class StackNode {

	int key;
	StackNode next;
	
	public StackNode(int data) {
		this.key = data;
		this.next = null;
	}
}


/* 
  
  Structure:
             ___
            |   |    top
            |___|
            |   |    top.next
            |___|
            |   |
            |___|
            |   |____
            |___|    |
                   __|__
                     _                   
                     .                             

*/