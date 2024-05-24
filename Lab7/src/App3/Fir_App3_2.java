package App3;

import java.util.concurrent.CountDownLatch;

public class Fir_App3_2 extends Thread {
    private final Object monitor;
    private final CountDownLatch latch;
    private final int transitionTime;
    private final int minActivity;
    private final int maxActivity;
    private final String name;

    public Fir_App3_2(Object monitor, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.monitor = monitor;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(name + " started execution.");
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + ": Token acquired.");
        try {
            Thread.sleep(transitionTime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // State 2
        System.out.println(name + ": Executing activity.");
        int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        System.out.println(name + ": Activity completed.");
        // State 3
        latch.countDown();
        System.out.println(name + ": Waiting for all threads to finish execution.");
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name + ": End of execution.");
    }
}
