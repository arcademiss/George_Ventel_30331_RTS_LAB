package App2.App2Lab7Semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread {
    int name, sleep, activity_min, activity_max, pr1, pr2;
    Semaphore s1, s2;
    CyclicBarrier barrier;
    
    Thread2(int name, CyclicBarrier barrier, Semaphore s1, Semaphore s2, int sleep, int activity_min, int activity_max, int pr1, int pr2) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.barrier = barrier;
        this.pr1 = pr1;
        this.pr2 = pr2;
    }
    
    public void run() {

        while (true) {
            System.out.println(this.getName() + " - STATE 1");

            if (name == 1) {
                try {
                    this.s1.acquire(this.pr1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " - STATE 2 and acquire s1");

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

                this.s1.release();
                System.out.println(this.getName() + " release s1");

            } else if (name == 2) {
                try {
                    this.s1.acquire(this.pr1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(this.getName() + " acquire s1");
                try {
                    this.s2.acquire(this.pr2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " acquire s2");

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

                this.s1.release();
                System.out.println(this.getName() + " release s1");
                this.s2.release();
                System.out.println(this.getName() + " release s2");
                
            } else if (name == 3) {
                try {
                    this.s2.acquire(this.pr2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " STATE 2 and acquire s2");

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

                this.s2.release();
                System.out.println(this.getName() + " release s2");
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
