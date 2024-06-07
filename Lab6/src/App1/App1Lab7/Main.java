package App1.App1Lab7;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock l1 = new ReentrantLock();
        Lock l2 = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("Routine");
            }
        });

        Thread1 fir1 = new Thread1(1, l1, l2, 4, 2, 4, 4, 6, barrier);
        Thread1 fir2 = new Thread1(2, l1, l2, 7, 3, 5, 5, 7, barrier);

        fir1.start();
        fir2.start();
    }
}
