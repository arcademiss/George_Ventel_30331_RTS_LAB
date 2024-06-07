package App1.App1Lab7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class Thread1 extends Thread{
    int name, sleep, activity_min1, activity_max1, activity_min2, activity_max2;
    Lock l1, l2;
    CyclicBarrier barrier;

    public Thread1(int name, Lock l1, Lock l2, int sleep, int activity_min1, int activity_max1, int activity_min2, int activity_max2, CyclicBarrier barrier) {
        this.name = name;
    	this.sleep = sleep;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;
        this.activity_min2 = activity_min2;
        this.activity_max2 = activity_max2;
        this.l1 = l1;
        this.l2 = l2;
        this.barrier = barrier;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            
            // delay for locations
    		int k1 = (int) Math.round(Math.random()*(activity_max1 - activity_min1) + activity_min1);
    		for (int i = 0; i < k1 * 100000; i++) {
    			i++; i--;
    		}

            if(this.name == 1){
                l1.lock();
                System.out.println(this.getName() + " - STATE 2 and acquire l1");
                
                // delay for locations
        		int k2 = (int) Math.round(Math.random()*(activity_max2 - activity_min2) + activity_min2);
        		for (int i = 0; i < k2 * 100000; i++) {
        			i++; i--;
        		}

                l2.lock();
                System.out.println(this.getName() + " - STATE 3 and acquire l2");
                try{
                    Thread.sleep(Math.round(Math.random() * this.sleep) *500);
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                }

                l2.unlock();
                System.out.println(this.getName() + " release l2");
                l1.unlock();
                System.out.println(this.getName() + " release l1");

            } else if (name == 2) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                l2.lock();
                // delay for locations
                System.out.println(this.getName() + " - STATE 2 and acquire l2");
                int k3 = (int) Math.round(Math.random() * (activity_max2 - activity_min2) + activity_min2);
                for (int i = 0; i < k3 * 10000; i++) {
                    i++;
                    i--;
                }

                l1.lock();
                System.out.println(this.getName() + " - STATE 3 and acquire l1");
                try {
                    Thread.sleep(Math.round(Math.random() * this.sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                l2.unlock();
                System.out.println(this.getName() + " release l2");
                l1.unlock();
                System.out.println(this.getName() + " release l1");
            }

            System.out.println(this.getName() + " - STATE 4");

            try{
                barrier.await();
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                e.printStackTrace();
            }

        }
    }
}

