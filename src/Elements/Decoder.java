package Elements;
import Storage.AdditionalResourcesStorage;

public class Decoder extends Element {
    private final AdditionalResourcesStorage storage;
    private int failures;

    public Decoder(String name, AdditionalResourcesStorage storage) {
        super(name);
        this.storage = storage;
        tNext = Double.MAX_VALUE;
        failures = 0;
    }

    @Override
    public void inAct(double packageLifetime) {
        quantity++;

        if(packageLifetime > 10) {
            failures++;
        }

        storage.additionalResources(getFailurePercentage() > 30, tCurrent);
    }

    public double getFailurePercentage() {
        return (failures / (double)quantity) * 100;
    }
    public double getAdditionalResourcesUsageTime() {
        return storage.getUsageTime();
    }
}
