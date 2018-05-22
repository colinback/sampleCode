package com.shizy;

public class StartVsRunCall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Task("start"));
		Thread t2 = new Thread(new Task("run"));
		
		t1.start();
		t2.run();
	}
	
	// 注意如果不是内部静态类, new Task编译无法通过
	private static class Task implements Runnable {
		private String caller;
		
		public Task(String caller) {
			this.caller = caller;
		}
		
		@Override
		public void run() {
			System.out.println("Caller: " + caller + ", Thread: " + Thread.currentThread().getName());
		}
	}
}
