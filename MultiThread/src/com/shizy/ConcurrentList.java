package com.shizy;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentList {
	private static class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	
	private ReentrantLock lock = new ReentrantLock();
	private Node head = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentList cl = new ConcurrentList();
		cl.add(1);
		cl.add(2);
	}

	public void add(int x) {
		lock.lock();
		System.out.println("ReentrantLock lock");
		
		try {
			if (head == null)
				head = new Node(x);
			else {
				Node iter = head;
				while(iter.next != null) iter = iter.next;
				iter.next = new Node(x);
			}
		} finally {
			System.out.println("ReentrantLock release");
			lock.unlock();
		}
	}
}
