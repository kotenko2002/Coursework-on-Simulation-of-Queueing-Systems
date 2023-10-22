import Elements.Decoder;
import Elements.Element;
import Elements.Process;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Model {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ArrayList<Element> elements;
    private double tNext, tCurrent;
    private int event;
    //public double processorsDelay;

    public Model(ArrayList<Element> elements) {
        this.elements = elements;
        tNext = 0.0;
        tCurrent = tNext;
        event = 0;
    }

    public void simulate(double time) {
        while (tCurrent < time) {
            tNext = Double.MAX_VALUE;
            for (Element e : elements) {
                if (e.gettNext() < tNext) {
                    tNext = e.gettNext();
                    event = e.getId();
                }
            }

            System.out.println("\nНастав час для події в " + elements.get(event).getName() + ", час = " + df.format(tNext));

            for (Element e : elements) {
                e.doStatistics(tNext - tCurrent);
            }

            tCurrent = tNext;

            for (Element e : elements) {
                e.settCurrent(tCurrent);
            }

            elements.get(event).outAct();
            for (Element e : elements) {
                if (e.gettNext() == tCurrent) {
                    e.outAct();
                }
            }

            printInfo();
        }
        printResult();
    }
    public void printInfo() {
        for (Element e : elements) {
            e.printInfo();
        }
    }
    public void printResult() {
        System.out.println("\n-----------------------РЕЗУЛЬТАТ-----------------------");

        for (Element e : elements) {
            System.out.println(e.getName() + ": {");

            System.out.println("\tкількість: " + e.getQuantity() + ";");

            if (e instanceof Process) {
                Process p = (Process) e;
                System.out.println("\tсередня довжина черги: " + df.format(p.getMeanQueue() / tCurrent) + ";");
            } else if(e instanceof Decoder) {
                Decoder d = (Decoder) e;
                System.out.println("\tвідмови : " + new DecimalFormat("0.00").format(d.getFailurePercentage()) + "%");
            }
            System.out.println("}\n");
        }
    }
}
