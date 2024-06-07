package App1.App1Lab7Semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread {
	 int name, sleep, activity_min1, activity_max1, activity_min2, activity_max2, pr1, pr2;
    private Semaphore s1;
    private Semaphore s2;
    private CyclicBarrier barrier;

    Thread2(int name, Semaphore s1, Semaphore s2, int sleep, int activity_min1, int activity_max1, int activity_min2, int activity_max2, CyclicBarrier barrier, int pr1, int pr2) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
        this.sleep = sleep;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;
        this.activity_min2 = activity_min2;
        this.activity_max2 = activity_max2;
        this.barrier = barrier;
        this.pr1 = pr1;
        this.pr2 = pr2;
    }

    public void run() {

        while (true) {
        	System.out.println(this.getName() + " - STATE 1");
            
            // delay for locations
    		int k1 = (int) Math.round(Math.random()*(activity_max1 - activity_min1) + activity_min1);
    		for (int i = 0; i < k1 * 100000; i++) {
    			i++; i--;
    		}
    		
            if (name == 1) {
                try {
                    this.s1.acquire(this.pr1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(this.getName() + " - STATE 2 and acquire s1");

                // delay for locations
        		int k2 = (int) Math.round(Math.random()*(activity_max2 - activity_min2) + activity_min2);
        		for (int i = 0; i < k2 * 100000; i++) {
        			i++; i--;
        		}

                try {
                    this.s2.acquire(this.pr2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(this.getName() + " - STATE 3 and acquire s2");
                
                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.s2.release();
                System.out.println(this.getName() + " release s2");
                this.s1.release();
                System.out.println(this.getName() + " release s1");

            } else if (name == 2) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                try {
                    this.s2.acquire(this.pr2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(this.getName() + " - STATE 2 and acquire s2");
                
                // delay for locations
        		int k2 = (int) Math.round(Math.random()*(activity_max2 - activity_min2) + activity_min2);
        		for (int i = 0; i < k2 * 100000; i++) {
        			i++; i--;
        		}

                try {
                    this.s1.acquire(this.pr1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(this.getName() + " - STATE 3 and acquire s1");
                
                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.s2.release();
                System.out.println(this.getName() + " release s2");
                this.s1.release();
                System.out.println(this.getName() + " release s1");
            }

            System.out.println(this.getName() + " - STATE 4");

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
