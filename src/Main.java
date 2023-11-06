import Elements.Create;
import Elements.Decoder;
import Elements.Element;
import Elements.Process;
import Other.AdditionalResourcesStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        //runTask(10000);
        //runTaskInLoop(10000, 1);
    }

    public static void runTask(int simulationTime) {
        Element.resetNextIdField();
        AdditionalResourcesStorage storage = new AdditionalResourcesStorage(4, 5);

        Create creator = new Create("CREATOR",6,3);
        Process processor1 = new Process("PROCESSOR1", storage);
        Process processor2 = new Process("PROCESSOR2", storage);
        Decoder decoder = new Decoder("DECODER", storage);

        creator.setNextElement(processor1);
        processor1.setNextElement(processor2);
        processor2.setNextElement(decoder);

        Model model = new Model(new ArrayList<>()
        {{
            add(creator);
            add(processor1);
            add(processor2);
            add(decoder);
        }}, true);
        model.simulate(simulationTime);

        int failures = decoder.getFailures();
        int quantity = decoder.getQuantity();
        double additionalResourcesUsageTime = decoder.getAdditionalResourcesUsageTime();

        System.out.println("Частота знищення пакетів: " + df.format(failures / (double)quantity) + ";");
        System.out.println("Частота підключення ресурсу: " + df.format(additionalResourcesUsageTime / (double)simulationTime) + ";");
    }

    public static void runTaskInLoop(int simulationTime, int iterations) {
        int avgFailures = 0;
        int avgQuantity = 0;
        double avgAdditionalResourcesUsageTime = 0;

        for(int i = 0; i < iterations; i++) {
            Element.resetNextIdField();

            AdditionalResourcesStorage storage = new AdditionalResourcesStorage(4, 5);

            Create creator = new Create("CREATOR",6,3);
            Process processor1 = new Process("PROCESSOR1", storage);
            Process processor2 = new Process("PROCESSOR2", storage);
            Decoder decoder = new Decoder("DECODER", storage);

            creator.setNextElement(processor1);
            processor1.setNextElement(processor2);
            processor2.setNextElement(decoder);

            Model model = new Model(new ArrayList<>()
            {{
                add(creator);
                add(processor1);
                add(processor2);
                add(decoder);
            }}, false);
            model.simulate(simulationTime);

            avgFailures += decoder.getFailures();
            avgQuantity += decoder.getQuantity();
            avgAdditionalResourcesUsageTime += decoder.getAdditionalResourcesUsageTime();
        }

        avgFailures = avgFailures / iterations;
        avgQuantity = avgQuantity / iterations;
        avgAdditionalResourcesUsageTime = avgAdditionalResourcesUsageTime / iterations;

        System.out.println("Частота знищення пакетів: " + df.format(avgFailures / (double)avgQuantity) + ";");
        System.out.println("Частота підключення ресурсу: " + df.format(avgAdditionalResourcesUsageTime / (double)simulationTime) + ";");
    }
}