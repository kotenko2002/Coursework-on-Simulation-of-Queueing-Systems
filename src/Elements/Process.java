package Elements;

public class Process extends Element {
    private int queue;
    private double meanQueue;
    private boolean isAvailable;

    public Process(double delay, String name) {
        this.delay = delay;
        this.name = name;

        isAvailable= true;
        queue = 0;
        meanQueue = 0.0;
    }

    @Override
    public void inAct() {
        if (isAvailable) {
            isAvailable = false;
            tNext = tCurrent + getDelay();
        } else {
            setQueue(queue + 1);
        }
    }
    @Override
    public void outAct() {
        super.outAct();
        tNext = Double.MAX_VALUE;
        isAvailable = true;

        if (queue > 0) {
            setQueue(queue - 1);
            isAvailable = false;
            tNext = tCurrent + getDelay();
        }

        if(nextElement != null) {
            super.getNextElement().inAct();
        }
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    @Override
    public void printInfo() {
        super.printInfo();
    }
    @Override
    public void doStatistics(double delta) {
        this.meanQueue = getMeanQueue() + queue * delta;
    }
    public double getMeanQueue() {
        return meanQueue;
    }
}