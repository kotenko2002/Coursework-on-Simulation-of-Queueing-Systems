package Elements;

import Other.LanguagePackage;

public class Element {
    protected String name;
    protected double tNext, tCurrent;
    protected int quantity;
    protected Element nextElement;
    private static int nextId = 0;
    private int id;

    public Element(String name) {
        this.name = name;
        tNext = 0.0;
        tCurrent = tNext;
        nextElement = null;
        id = nextId;
        nextId++;
    }

    public static void resetNextIdField() {
        nextId = 0;
    }

    public void inAct(LanguagePackage pack) {
    }
    public void outAct(){
    }

    public void printInfo() {
        System.out.println(getName() +  ": { кількість: " + quantity + "; tNext: " + tNext + " }");
    }
    public void doStatistics(double delta) {
    }

    public double getTNext() {
        return tNext;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }

    public void settCurrent(double tCurrent) {
        this.tCurrent = tCurrent;
    }
    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }
}
