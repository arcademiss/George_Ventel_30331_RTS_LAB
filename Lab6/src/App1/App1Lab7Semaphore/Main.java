package App1.App1Lab7Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("Routine");
            }
        });

        Thread2 fir1 = new Thread2(1, s1, s2, 4, 2, 4, 4, 6, barrier, 1, 1);
        Thread2 fir2 = new Thread2(2, s1, s2, 7, 3, 5, 5, 7, barrier, 1, 1);

        fir1.start();
        fir2.start();
    }
}
