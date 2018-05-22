package com.shizy;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerSolution {
	public static void main(String[] args) {
		Vector<Integer> slots = new Vector<>();
		int size = 4;
		// Object lock = new Object();
		
		Thread p = new Thread(new Producer(slots, size), "producer");
		Thread c = new Thread(new Consumer(slots, size), "consumer");
		
		p.start();
		c.start();
	}
}

class Producer implements Runnable {
	private final Vector<Integer> slots;
	private final int size;
	// private final Object lock;
	
	public Producer(Vector<Integer> slots, int size) {
		this.slots = slots;
		this.size = size;
		// this.lock = lock;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println("Produced: " + i);
			synchronized(slots) {
				while(slots.size() == size) {
					try {
						System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, size: " + slots.size());
						slots.wait();
					} catch (InterruptedException ex) {
		                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
		            }
				}
				
				// add i into vector
				slots.add(i);
				System.out.println(Thread.currentThread().getName() + " insert value: " + i + ", size: " + slots.size());
				slots.notifyAll();
			}
		}
	}
}

class Consumer implements Runnable {
	private final Vector<Integer> slots;
	private final int size;
	// private final Object lock;
	
	public Consumer(Vector<Integer> slots, int size) {
		this.slots = slots;
		this.size = size;
		// this.lock = lock;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(slots) {
				while(slots.isEmpty()) {
					try {
						System.out.println("Queue is empyt "  + Thread.currentThread().getName() + " is waiting, size: " + slots.size());
						slots.wait();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				
				int num = slots.remove(0);
				System.out.println("Consumed: " + num);
				slots.notifyAll();
				
				if (num == 9) break;
			}
		}
	}
}
