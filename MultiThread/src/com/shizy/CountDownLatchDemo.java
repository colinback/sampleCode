package com.shizy;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(3);
		Thread cacheService = new Thread(new Service("CacheService", 1000, latch));
		Thread alertService = new Thread(new Service("AlertService", 1000, latch));
		Thread validationService = new Thread(new Service("ValidationService", 1000, latch));
		
		cacheService.start();
		alertService.start();
		validationService.start();
		
		try {
			//main thread is waiting on CountDownLatch to finish
			latch.await();
			System.out.println("All services are up, application is starting now");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}

class Service implements Runnable {
	private final String name;
	private final int timeToStart;
	private final CountDownLatch latch;
	
	public Service(String name, int timeToStart, CountDownLatch latch) {
		this.name = name;
		this.timeToStart = timeToStart;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(timeToStart);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		System.out.println(name + "is Up");
		latch.countDown();
	}
}
