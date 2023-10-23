package Elements;
import FunRand.FunRand;

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
        super.outAct();
        tNext = tCurrent + getDelay();
        nextElement.inAct(0.0);
    }

    private double getDelay() {
        return FunRand.Norm(delayMean, delayDeviation);
    }
}