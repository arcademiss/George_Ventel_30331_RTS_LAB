package App3.App3Lab7;

import java.util.concurrent.CountDownLatch;

public class Thread1 extends Thread {
    Integer monitor1, monitor2;
    int sleep, activity_min, activity_max;
    CountDownLatch latch;

    public Thread1(Integer monitor1, Integer monitor2, int sleep, int activity_min, int activity_max, CountDownLatch latch) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.latch = latch;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        if (monitor1.equals(1) & monitor2.equals(1)) {
            try {
                Thread.sleep(Math.round(Math.random() * sleep) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
            for (int i = 0; i < k * 10000; i++) {
                i++;
                i--;
            }

            synchronized (monitor1) {
                synchronized (monitor2) {
                    monitor2.notify();
                }
                monitor1.notify();
            }

            System.out.println(this.getName() + " - STATE 3");

        }

        if (monitor1.equals(1) & monitor2.equals(0)) {
            synchronized (monitor1) {

                try {
                    monitor1.wait();
                    try {
                        Thread.sleep(Math.round(Math.random() * sleep) * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + " - STATE 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                int k1 = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
                for (int i = 0; i < k1 * 10000; i++) {
                    i++;
                    i--;
                }
            }
            System.out.println(this.getName() + " - STATE 3");

        }

        if (monitor1.equals(0) & monitor2.equals(1)) {

            synchronized (monitor2) {
                try {
                    monitor2.wait();
                    try {
                        Thread.sleep(Math.round(Math.random() * sleep) * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + " - STATE 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                int k = (int) Math.round(Math.random() * (this.activity_max - this.activity_min) + this.activity_min);
                for (int i = 0; i < k * 10000; i++) {
                    i++;
                    i--;
                }
            }
            System.out.println(this.getName() + " - STATE 3");
        }
        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 4");
    }
}
