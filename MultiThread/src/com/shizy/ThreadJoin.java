package com.shizy;

public class ThreadJoin {
	public static void main(String args[]) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " is Started");
		
		Thread t1 = new Thread("T1") {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + "is Started");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "is Completed");
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
		
		Thread t2 = new Thread("T2") {
			public void run(){
				try {
					t1.start();
					t1.join();
					
					System.out.println(Thread.currentThread().getName() + "is Started");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "is Completed");
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
		
		
		t2.start();
		t2.join();
		
		System.out.println(Thread.currentThread().getName() + " is Completed");
	}
}
