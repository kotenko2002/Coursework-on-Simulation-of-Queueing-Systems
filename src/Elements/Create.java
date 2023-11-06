package Elements;
import Other.LanguagePackage;

import java.util.Random;

public class Create extends Element {
    private double delayMean, delayDeviation;

    public Create(String name, double delayMean, double delayDeviation) {
        super(name);
        this.delayMean = delayMean;
        this.delayDeviation = delayDeviation;

        tNext = 0.0;
    }

    @Override
    public void outAct() {
        quantity++;
        tNext = tCurrent + getDelay();
        nextElement.inAct(new LanguagePackage(tCurrent));
    }

    private double getDelay() {
        double a = 0;
        Random r = new Random();
        while (a <= 0) a = delayMean + delayDeviation * r.nextGaussian();
        return a;
    }
}