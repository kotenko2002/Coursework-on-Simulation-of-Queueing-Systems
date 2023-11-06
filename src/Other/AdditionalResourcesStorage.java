package Other;

public class AdditionalResourcesStorage {
    private double processorsDelay;
    private final double minDelay, maxDelay;
    private double totalUsageTime;
    private Double activatedTime;

    public AdditionalResourcesStorage(double minDelay, double maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;

        processorsDelay = maxDelay;

        totalUsageTime = 0 ;
        activatedTime = null;
    }

    public void additionalResources(boolean activate, double tCurrent) {
        processorsDelay = activate ? minDelay : maxDelay;

        if(activate && activatedTime != null) {
            totalUsageTime += (tCurrent - activatedTime);
        }
        activatedTime = activate ? tCurrent : null;
    }

    public double getProcessorsDelay() {
        return processorsDelay;
    }
    public double getUsageTime() {
        return totalUsageTime;
    }
}
