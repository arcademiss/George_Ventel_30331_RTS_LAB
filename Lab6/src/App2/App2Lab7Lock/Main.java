package App2.App2Lab7Lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String args[]) {
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
		public void run() {
			System.out.println("Routine");
		}
		});
		Lock l1 = new ReentrantLock();
		Lock l2 = new ReentrantLock();
		
		Thread1 fir1 = new Thread1(1, barrier,l1, l2,  4, 2, 4);
		Thread1 fir2 = new Thread1(2, barrier,l1, l2, 3, 3, 6);
		Thread1 fir3 = new Thread1(3, barrier,l2, l2, 5, 2, 5);
		
		fir1.start();
		fir2.start();
		fir3.start();
	}
}
