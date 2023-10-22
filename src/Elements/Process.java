package Elements;

import java.util.ArrayDeque;
import java.util.Queue;

public class Process extends Element {
    private final Queue<Double> queue;
    private double meanQueue;
    private boolean isAvailable;
    private double packageLifetime;

    public Process(double delay, String name) {
        super(name);
        this.delay = delay;

        isAvailable= true;
        queue = new ArrayDeque<>();
        meanQueue = 0.0;
    }

    @Override
    public void inAct(double packageLifetime) {
        if (isAvailable) {
            isAvailable = false;

            double processingTime = getDelay();
            tNext = tCurrent + processingTime;
            this.packageLifetime = packageLifetime + processingTime;
        } else {
            queue.add(packageLifetime);
        }
    }
    @Override
    public void outAct() {
        super.outAct();
        tNext = Double.MAX_VALUE;
        isAvailable = true;

        double processedPackageLifetime = packageLifetime;
        if (!queue.isEmpty()) {
            isAvailable = false;

            double newPackageLifetime = queue.poll();
            double processingTime = getDelay();

            tNext = tCurrent + processingTime;
            this.packageLifetime = newPackageLifetime + processingTime;
        }

        if(nextElement != null) {
            nextElement.inAct(processedPackageLifetime);
        }
    }

    @Override
    public void doStatistics(double delta) {
        this.meanQueue = getMeanQueue() + queue.size() * delta;
    }
    public double getMeanQueue() {
        return meanQueue;
    }
}