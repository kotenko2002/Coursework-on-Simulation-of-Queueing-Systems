package StateStorage;

public class StateStorage {
    private double processorsDelay;
    private final double minProcessorsDelay;
    private final double maxProcessorsDelay;

    public StateStorage(double minProcessorsDelay, double maxProcessorsDelay) {
        this.minProcessorsDelay = minProcessorsDelay;
        this.maxProcessorsDelay = maxProcessorsDelay;
    }

    public double getProcessorsDelay() {
        return  processorsDelay;
    }

    public void additionalResources(boolean activate) {
        processorsDelay = activate ? maxProcessorsDelay : minProcessorsDelay;
    }
}
