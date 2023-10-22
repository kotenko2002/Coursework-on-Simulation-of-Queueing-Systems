package Elements;

public  class Element {
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

    public void inAct(double packageLifetime) {
    }
    public void outAct(){
        quantity++;
    }

    public void printInfo(){
        System.out.println(getName() +  ": { кількість: " + quantity + "; tnext: " + tNext + " }");
    }
    public void doStatistics(double delta){
    }

    public double gettNext() {
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
