package App2.App2Lab7Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class Thread1 extends Thread {
    int name, sleep, activity_min, activity_max;
    Lock l1, l2;
    CyclicBarrier barrier;

    Thread1(int name, CyclicBarrier barrier, Lock l1, Lock l2, int sleep, int activity_min, int activity_max) {
        this.name = name;
        this.l1 = l1;
        this.l2 = l2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.barrier = barrier;
    }

    public void run() {

        while (true) {
            System.out.println(this.getName() + " - STATE 1");

            if (name == 1) {
                l1.lock();
                System.out.println(this.getName() + " - STATE 2 and acquire l1");
                
                int k = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
                for (int i = 0; i < k * 10000; i++) {
                    i++;
                    i--;
                }

                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(this.getName() + " - STATE 3");

                l1.unlock();
                System.out.println(this.getName() + " release l1");

            } else if (name == 2) {
                l1.lock();
                System.out.println(this.getName() + " acquire l1");
                l2.lock();
                System.out.println(this.getName() + " acquire l2");

                System.out.println(this.getName() + " - STATE 2");
                int k2 = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
                for (int i = 0; i < k2 * 10000; i++) {
                    i++;
                    i--;
                }

                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(this.getName() + " - STATE 3");

                l1.unlock();
                System.out.println(this.getName() + " release l1");
                l2.unlock();
                System.out.println(this.getName() + " release l2");

            } else if (name == 3) {
                l2.lock();
                System.out.println(this.getName() + " STATE 2 and acquire l2");

                int k3 = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
                for (int i = 0; i < k3 * 10000; i++) {
                    i++;
                    i--;
                }

                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(this.getName() + " - STATE 3");

                l2.unlock();
                System.out.println(this.getName() + " release l2");
            }

            System.out.println(this.getName() + " - STATE 4 - wait");

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
