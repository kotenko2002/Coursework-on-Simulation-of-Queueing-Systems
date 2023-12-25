import Elements.Decoder;
import Elements.Element;
import Elements.Process;
import Other.AdditionalResourcesStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ModelWithTimer {
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final AdditionalResourcesStorage storage;
    private ArrayList<Element> elements;
    private double tNext, tCurrent;
    private int event;
    private boolean printInfo;

    public ModelWithTimer(ArrayList<Element> elements, boolean printInfo, AdditionalResourcesStorage storage) {
        this.elements = elements;
        this.printInfo = printInfo;
        this.storage = storage;
        tNext = 0.0;
        tCurrent = tNext;
        event = 0;
    }

    public void simulate(double time) {
        int nextThreshold = 1000;
        while (tCurrent < time) {
            tNext = Double.MAX_VALUE;
            for (Element e : elements) {
                if (e.getTNext() < tNext) {
                    tNext = e.getTNext();
                    event = e.getId();
                }
            }

            if(printInfo) {
                System.out.println("\nНастав час для події в " + elements.get(event).getName() + ", час = " + df.format(tNext));
            }

            for (Element e : elements) {
                e.doStatistics(tNext - tCurrent);
            }

            tCurrent = tNext;

            // Проверка, превысил ли tCurrent следующий порог
            if (tCurrent >= nextThreshold) {
                System.out.println(df.format(storage.getUsageTime() / nextThreshold));
                nextThreshold += 500;
            }

            for (Element e : elements) {
                e.settCurrent(tCurrent);
            }

            elements.get(event).outAct();
            for (Element e : elements) {
                if (e.getTNext() == tCurrent) {
                    e.outAct();
                }
            }

            if(printInfo) printInfo();
        }

        if(printInfo) printResult();
    }
    public void printInfo() {
        for (Element e : elements) {
            e.printInfo();
        }
    }
    public void printResult() {
        System.out.println("\n------------------РЕЗУЛЬТАТ------------------");

        for (Element e : elements) {
            System.out.println(e.getName() + ": {");

            System.out.println("\tкількість: " + e.getQuantity() + ";");
            if (e instanceof Process) {
                Process p = (Process) e;
                System.out.println("\tсереднє навантаження: " + df.format(p.getMeanLoad() / tCurrent) + ";");
                System.out.println("\tсередня довжина черги: " + df.format(p.getMeanQueue() / tCurrent) + ";");
            } else if(e instanceof Decoder) {
                Decoder d = (Decoder) e;
                System.out.println("\tвідмови : " + new DecimalFormat("0.00").format(d.getFailurePercentage()) + "%;");
                System.out.println("\tчас роботи додаткових ресурсів: " + new DecimalFormat("0.00").format(d.getAdditionalResourcesUsageTime())+ ";");
            }

            System.out.println("}\n");
        }
    }
}