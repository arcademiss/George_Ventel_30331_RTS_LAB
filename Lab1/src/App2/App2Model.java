package App2;

public class App2Model {
    private int noOfThreads;
    private int processorLoad;

    public App2Model(int noOfThreads, int processorLoad) {
        this.noOfThreads = noOfThreads;
        this.processorLoad = processorLoad;
    }

    public int getNoOfThreads() {
        return noOfThreads;
    }

    public void setNoOfThreads(int noOfThreads) {
        this.noOfThreads = noOfThreads;
    }

    public int getProcessorLoad() {
        return processorLoad;
    }

    public void setProcessorLoad(int processorLoad) {
        this.processorLoad = processorLoad;
    }
}
