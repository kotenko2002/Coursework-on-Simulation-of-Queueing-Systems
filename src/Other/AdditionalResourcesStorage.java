package Other;

public class AdditionalResourcesStorage {
    private static final double FAILURE_PERCENTAGE_FOR_RESOURCE_ACTIVATION = 30;
    private double FAILURE_PERCENTAGE_FOR_RESOURCE_DEACTIVATION = 30;
    private double processorsDelay;
    private final double minDelay, maxDelay;
    private double totalUsageTime;
    private Double activatedTime;
    private int numberOfActivations, numberOfDeactivations;

    public AdditionalResourcesStorage(double minDelay, double maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;

        processorsDelay = maxDelay;

        totalUsageTime = 0;
        activatedTime = null;

        numberOfActivations = 0;
        numberOfDeactivations = 0;
    }
    public AdditionalResourcesStorage(double minDelay, double maxDelay, double PERCENTAGE_FOR_RESOURCE_DEACTIVATION) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
        this.FAILURE_PERCENTAGE_FOR_RESOURCE_DEACTIVATION = PERCENTAGE_FOR_RESOURCE_DEACTIVATION;
        processorsDelay = maxDelay;

        totalUsageTime = 0;
        activatedTime = null;

        numberOfActivations = 0;
        numberOfDeactivations = 0;
    }
    public void additionalResources(double failurePercentage, double tCurrent) {
        boolean activateResource = failurePercentage > FAILURE_PERCENTAGE_FOR_RESOURCE_ACTIVATION;
        boolean canBeDeactivated = failurePercentage <= FAILURE_PERCENTAGE_FOR_RESOURCE_DEACTIVATION;
        boolean resourceIsActivate = activatedTime != null;

        if(activateResource && !resourceIsActivate) {
            processorsDelay = minDelay;
            numberOfActivations++;
            activatedTime = tCurrent;
        }
        else if(!activateResource && resourceIsActivate && canBeDeactivated) {
            processorsDelay = maxDelay;
            numberOfDeactivations++;
            totalUsageTime += (tCurrent - activatedTime);
            activatedTime = null;
        }
        else if(resourceIsActivate) {
            totalUsageTime += (tCurrent - activatedTime);
            activatedTime = tCurrent;
        }
    }

    public double getProcessorsDelay() {
        return processorsDelay;
    }
    public double getUsageTime() {
        return totalUsageTime;
    }
    public int getNumberOfActivations() {
        return numberOfActivations;
    }
    public int getNumberOfDeactivations() {
        return numberOfDeactivations;
    }
}
