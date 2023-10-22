package Elements;

import FunRand.FunRand;

import java.util.Random;

public  class Element {
    protected String name;
    protected double tNext, tCurrent;
    protected double delay, delayMean, delayDeviation;
    private String distribution;
    private int quantity;
    protected Element nextElement;
    private static int nextId = 0;
    private int id;

    public Element() {
        tNext = 0.0;
        tCurrent = tNext;
        nextElement = null;
        id = nextId;
        nextId++;
    }

    public double getDelay() {
        switch (distribution.toLowerCase()) {
            case "exp":
                return FunRand.Exp(delay);
            case "norm":
                return FunRand.Norm(delayMean, delayDeviation);
            case "unif":
                return FunRand.Unif(delayMean - delayDeviation, delayMean + delayDeviation);
            default:
                return delay;
        }
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public void settCurrent(double tCurrent) {
        this.tCurrent = tCurrent;
    }
    public Element getNextElement() {
        return nextElement;
    }
    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }
    public void inAct() {
    }
    public void outAct(){
        quantity++;
    }
    public double gettNext() {
        return tNext;
    }
    public void settNext(double tNext) {
        this.tNext = tNext;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void printResult(){
        System.out.println("\tкількість: " + quantity + ";");
    }
    public void printInfo(){
        System.out.println(getName() +
                ": { кількість: " + quantity +
                "; tnext: " + tNext + " }");
    }
    public String getName() {
        return name;
    }
    public void doStatistics(double delta){
    }
}
