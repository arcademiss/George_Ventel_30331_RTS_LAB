package App2;

import App1.Window;

import java.util.Observable;

public class App2Controller extends Observable implements Runnable {
    public int id;
    int processorLoad;
    App2View view;
    Thread t = new Thread();
    public int cc;

    App2Controller(int id, int priority, App2View view, int procLoad) {
        this.id = id;
        this.view = view;
        this.processorLoad = procLoad;
        this.t.setPriority(priority);
    }

    public int getId() {
        return id;
    }

    public int getCc() {
        return cc;
    }


    public void run() {
        int c = 0;

        while (c < 1000) {
            for (int j = 0; j < this.processorLoad; j++) {
                j++;
                j--;
            }
            c++;
            cc = c;
            setChanged();
            notifyObservers();
        }
    }
}
