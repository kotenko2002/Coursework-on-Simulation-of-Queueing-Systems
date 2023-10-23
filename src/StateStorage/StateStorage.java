package StateStorage;

public class StateStorage {
    private double processorsDelay;
    private final double minDelay;
    private final double maxDelay;
    private double additionalResourcesUsageTime;
    private Double additionalResourcesActivated;

    public StateStorage(double minDelay, double maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;

        additionalResourcesUsageTime = 0 ;
        additionalResourcesActivated = null;
    }

    public double getProcessorsDelay() {
        return  processorsDelay;
    }

    public double getAdditionalResourcesUsageTime() {
        return additionalResourcesUsageTime;
    }

    public void additionalResources(boolean activate, double tCurrent) {
        if(activate) {
            processorsDelay = minDelay;

            if(additionalResourcesActivated == null) {
                additionalResourcesActivated = tCurrent;
            }
        } else  {
            processorsDelay = maxDelay;

            if(additionalResourcesActivated != null) {
                additionalResourcesUsageTime += (tCurrent - additionalResourcesActivated);
            }
            additionalResourcesActivated = null;
        }
    }
}
