package App2.App2Lab7Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String args[]) {
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
		public void run() {
			System.out.println("Routine");
		}
		});
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);
		
		Thread2 fir1 = new Thread2(1, barrier,s1, s2, 4, 2, 4, 1, 1);
		Thread2 fir2 = new Thread2(2, barrier,s1, s2, 3, 3, 6, 1, 1);
		Thread2 fir3 = new Thread2(3, barrier,s2, s2, 5, 2, 5, 1, 1);
		
		fir1.start();
		fir2.start();
		fir3.start();
	}
}
