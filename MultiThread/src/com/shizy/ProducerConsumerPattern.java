package com.shizy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerPattern {

	public static void main(String[] args) {
		// shared blocking queue
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
		
		// Producer & Consumer thread
		Thread producer = new Thread(new ProducerTask(bq), "producer");
		Thread consumer = new Thread(new ConsumerTask(bq), "consumer");
		
		// starting
		producer.start();
		consumer.start();
	}
}

class ProducerTask implements Runnable {
	private final BlockingQueue<Integer> sharedQueue;
	
	public ProducerTask(BlockingQueue<Integer> bq) {
		this.sharedQueue = bq;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			try {
				System.out.println("Produced: " + i);
				sharedQueue.put(i);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

class ConsumerTask implements Runnable {
	private final BlockingQueue<Integer> sharedQueue;
	
	public ConsumerTask(BlockingQueue<Integer> bq) {
		this.sharedQueue = bq;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("Consumed: " + sharedQueue.take());
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
