package App2;

public class Main {
    public static void main(String[] args) {
        App2Model model = new App2Model(6, 1000000);
        App2View view = new App2View(model.getNoOfThreads());
        for(int i = 0; i < model.getNoOfThreads(); i++) {
            App2Controller controller = new App2Controller(i, i+2, view, model.getProcessorLoad());
            controller.addObserver(view);
            Thread t = new Thread(controller);
            t.start();
        }
    }
}
