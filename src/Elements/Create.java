package Elements;

public class Create extends Element {
    public Create(double delayMean, double delayDeviation, String name) {
        this.delayMean = delayMean;
        this.delayDeviation = delayDeviation;
        this.name = name;
        tNext = 0.0;
    }
    @Override
    public void outAct() {
        super.outAct();
        tNext = tCurrent + super.getDelay();
        nextElement.inAct();
    }
}