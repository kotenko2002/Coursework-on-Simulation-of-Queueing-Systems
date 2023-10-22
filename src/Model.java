import Elements.Element;
import Elements.Process;

import java.util.ArrayList;

public class Model {
    private ArrayList<Element> elements;
    double tNext, tCurrent;
    int event;
    public double processorsDelay;

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

            System.out.println("\nНастав час для події в " + elements.get(event).getName() + ", час = " + tNext);

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
            e.printResult();

            if (e instanceof Process) {
                Process p = (Process) e;
                System.out.println("\tсередня довжина черги: " +
                        p.getMeanQueue() / tCurrent + ";"
                );
            }
            System.out.println("}\n");
        }
    }
}
