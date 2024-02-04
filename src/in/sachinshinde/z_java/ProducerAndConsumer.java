package in.sachinshinde.z_java;

import java.util.LinkedList;

class ProducerAndConsumer{
	
	//Create buffer stack where producer and consumer will send and received data.
	LinkedList<Integer> listStack=new LinkedList<Integer>();
	//Defined Buffer size 
	int bufferSize = 2;
	
	
	public void producerCall() {
		int producedData =1;
		try {
			
			while(true) {
			//Synchronized Block only one Thread can Enter
				synchronized (this) {
					// Producer will wait until buffer have not space to add more data and buffer size is only two as we already defined in #bufferSize 
					while(listStack.size() == bufferSize) {
						wait();
					}
					
					
					//If buffer have empty or space to add more data
					listStack.add(producedData++);
					
					System.out.println("Producer Prodced Data as "+producedData);
					Thread.sleep(3000); //Add Sleep for better understanding to log
					//notify to consumer that buffer have data now so consumer can consume the data
					notify(); //If you remove notify method so you will understand the consumer and producer problem

				}
			}
			
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void consumerCall() {
		
		try {
			while(true) {
				//get locked on current thread.
				//synchronized block for single thread can process consumer or producer only can process on buffer
				synchronized (this) {
					//Thread or Consumer will wait until producer not add data into buffer 
					while(listStack.size() == 0) {
						wait();
					}
					// if buffer have data it will remove first element from top
					// #removedData is only for reference to show in console
					int removedData=listStack.removeFirst();
					System.out.println("Consumer consumed the "+removedData);
					Thread.sleep(3000); //Add Sleep for better understanding to log
					
					// notify to producer thread that consumer consumed the data from buffer
					notify(); //If you remove notify method so you will understand the consumer and producer problem
					
				}
				
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}