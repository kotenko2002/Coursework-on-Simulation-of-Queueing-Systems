package Elements;
import Other.AdditionalResourcesStorage;
import Other.LanguagePackage;

public class Decoder extends Element {
    public static final int LANGUAGE_PACKAGE_TIMEOUT = 10;
    private final AdditionalResourcesStorage storage;
    private int failures;

    public Decoder(String name, AdditionalResourcesStorage storage) {
        super(name);
        this.storage = storage;

        failures = 0;

        tNext = Double.MAX_VALUE;
    }

    @Override
    public void inAct(LanguagePackage pack) {
        quantity++;

        if(pack.getTimeSpentInSystem(tCurrent) > LANGUAGE_PACKAGE_TIMEOUT) {
            failures++;
        }

        storage.additionalResources(getFailurePercentage(), tCurrent);
    }

    public double getFailurePercentage() {
        return (failures / (double)quantity) * 100;
    }
    public double getAdditionalResourcesUsageTime() {
        return storage.getUsageTime();
    }
    public int getFailures() {
        return failures;
    }
}
