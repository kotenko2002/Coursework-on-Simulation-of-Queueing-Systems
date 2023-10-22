package Elements;

public class Decoder extends Element {
    public int failures;

    public Decoder(String name) {
        super(name);
        tNext = Double.MAX_VALUE;

        failures = 0;
    }

    @Override
    public void inAct(double packageLifetime) {
        quantity++;

        if(packageLifetime > 10) {
            failures++;
        }
    }

    public double getFailurePercentage() {
        return (failures / (double)quantity) * 100;
    }
}
