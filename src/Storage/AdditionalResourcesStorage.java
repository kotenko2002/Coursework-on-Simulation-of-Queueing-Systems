package Storage;

public class AdditionalResourcesStorage {
    private double processorsDelay;
    private final double minDelay, maxDelay;
    private double totalUsageTime;
    private Double activatedTime;

    public AdditionalResourcesStorage(double minDelay, double maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;

        totalUsageTime = 0 ;
        activatedTime = null;
    }

    public void additionalResources(boolean activate, double tCurrent) {
        if(activate) {
            processorsDelay = minDelay;

            if(activatedTime == null) {
                activatedTime = tCurrent;
            }
        } else  {
            processorsDelay = maxDelay;

            if(activatedTime != null) {
                totalUsageTime += (tCurrent - activatedTime);
            }
            activatedTime = null;
        }
    }

    public double getProcessorsDelay() {
        return  processorsDelay;
    }
    public double getUsageTime() {
        return totalUsageTime;
    }
}
