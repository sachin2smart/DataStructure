package in.sachinshinde.z_java;

import in.sachinshinde.z_java.ProducerAndConsumer;

public class ConsumerProducerProblemSolution {

	public static void main(String ...args) {
		//Class which contain consumer method and producer
		ProducerAndConsumer producerAndConsumer=new ProducerAndConsumer();
		
		Thread producer=new Thread(new Runnable() {
			@Override
			public void run() {
			producerAndConsumer.producerCall();
			}
		});
		
		
		Thread consumer=new Thread(new Runnable() {
			@Override
			public void run() {
				 producerAndConsumer.consumerCall();
				
			}
		});
	
		// consumer and producer will start work
		producer.start();
		consumer.start();
		
		try {
			//here main Thread which main now will wait consumer and producer to complete their work
			producer.join();
			consumer.join();
		} catch (InterruptedException ed) {
			// TODO: handle exception
			ed.printStackTrace();
			
		}
		
			
	}
	
}
