package Elements;
import StateStorage.StateStorage;

public class Decoder extends Element {
    private final StateStorage stateStorage;
    private int failures;

    public Decoder(String name, StateStorage stateStorage) {
        super(name);
        this.stateStorage = stateStorage;
        tNext = Double.MAX_VALUE;
        failures = 0;
    }

    @Override
    public void inAct(double packageLifetime) {
        quantity++;

        if(packageLifetime > 10) {
            failures++;
        }

        stateStorage.additionalResources(getFailurePercentage() > 30, tCurrent);
    }

    public double getFailurePercentage() {
        return (failures / (double)quantity) * 100;
    }
    public double getAdditionalResourcesUsageTime() {
        return stateStorage.getAdditionalResourcesUsageTime();
    }
}
