package com.shizy;

public class DeadLockDemo {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				method1();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				method2();
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
	public static void method1() {
		synchronized(String.class) {
			System.out.println("Aquired lock on String.class object");
			System.out.println("String Lock held " + Thread.holdsLock(String.class) +
					" by " + Thread.currentThread().getName());
			synchronized(Integer.class) {
				System.out.println("Aquired lock on Integer.class object");
				System.out.println("Integer Lock held " + Thread.holdsLock(Integer.class) +
						" by " + Thread.currentThread().getName());
			}
		}
	}
	
	public static void method2() {
		synchronized(Integer.class) {
			System.out.println("Aquired lock on Integer.class object");
			System.out.println("Integer Lock held " + Thread.holdsLock(Integer.class) +
					" by " + Thread.currentThread().getName());
			synchronized(String.class) {
				System.out.println("Aquired lock on String.class object");
				System.out.println("String Lock held " + Thread.holdsLock(String.class) +
						" by " + Thread.currentThread().getName());
			}
		}
	}
}
