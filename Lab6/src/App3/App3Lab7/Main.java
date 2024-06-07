package App3.App3Lab7;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        Integer monitor1 = 1;
        Integer monitor2 = 1;
        CountDownLatch latch = new CountDownLatch(3);

        int x = 5;
        int y = 5;
        Thread1 fir1 = new Thread1(monitor1, monitor2, 7, 2, 3, latch);
        Thread1 fir2 = new Thread1(monitor1, 0, x, 3, 5, latch);
        Thread1 fir3 = new Thread1(0, monitor2, y, 4, 6, latch);
        
        fir3.start();
        fir2.start();
        fir1.start();

    }
}
