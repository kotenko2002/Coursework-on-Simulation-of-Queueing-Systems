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
        runTaskInLoop(10000, 1000);
        //verificationModel(1000,1000);

//        for (int i = 0; i < 20; i++) {
//            runTaskDiffPercentage(100000, 30);
//        }

        //runTaskTOoGetTimeOfSim(100000);
    }
    public static void runTaskTOoGetTimeOfSim(int simulationTime) {
        Element.resetNextIdField();
        AdditionalResourcesStorage storage = new AdditionalResourcesStorage(4, 5);

        Create creator = new Create("CREATOR",6,3);
        Process processor1 = new Process("PROCESSOR1", storage);
        Process processor2 = new Process("PROCESSOR2", storage);
        Decoder decoder = new Decoder("DECODER", storage);

        creator.setNextElement(processor1);
        processor1.setNextElement(processor2);
        processor2.setNextElement(decoder);

        ModelWithTimer model = new ModelWithTimer(new ArrayList<>()
        {{
            add(creator);
            add(processor1);
            add(processor2);
            add(decoder);
        }}, false, storage);
        model.simulate(simulationTime);

        double additionalResourcesUsageTime = decoder.getAdditionalResourcesUsageTime();

        System.out.println("Частота підключення ресурсу: " + df.format(additionalResourcesUsageTime / (double)simulationTime) + ";");
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

        int failures = decoder.getFailures(),
            quantity = decoder.getQuantity(),
            numberOfActivations = storage.getNumberOfActivations(),
            numberOfDeactivations = storage.getNumberOfDeactivations();
        double additionalResourcesUsageTime = decoder.getAdditionalResourcesUsageTime();

        System.out.println("Частота знищення пакетів: " + df.format(failures / (double)quantity) + ";");
        System.out.println("Частота підключення ресурсу: " + df.format(additionalResourcesUsageTime / (double)simulationTime) + ";");

        System.out.println("\nКількість ... ресурсу: ");
        System.out.println("\tпідключень: " + numberOfActivations);
        System.out.println("\tвідключень: " + numberOfDeactivations);
    }
    public static void runTaskDiffPercentage(int simulationTime, int percentage) {
        Element.resetNextIdField();
        AdditionalResourcesStorage storage = new AdditionalResourcesStorage(4, 5, percentage);

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

        int failures = decoder.getFailures(),
                quantity = decoder.getQuantity(),
                numberOfActivations = storage.getNumberOfActivations(),
                numberOfDeactivations = storage.getNumberOfDeactivations();
        double additionalResourcesUsageTime = decoder.getAdditionalResourcesUsageTime();

        System.out.println(numberOfActivations);
    }
    public static void runTaskInLoop(int simulationTime, int iterations) {
        int avgFailures = 0,
            avgQuantity = 0,
            avgNumberOfActivations = 0,
            avgNumberOfDeactivations = 0;
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
            avgNumberOfActivations += storage.getNumberOfActivations();
            avgNumberOfDeactivations += storage.getNumberOfDeactivations();
            avgAdditionalResourcesUsageTime += decoder.getAdditionalResourcesUsageTime();
        }

        avgFailures = avgFailures / iterations;
        avgQuantity = avgQuantity / iterations;
        avgNumberOfActivations = avgNumberOfActivations / iterations;
        avgNumberOfDeactivations = avgNumberOfDeactivations / iterations;
        avgAdditionalResourcesUsageTime = avgAdditionalResourcesUsageTime / iterations;

        System.out.println("Частота знищення пакетів: " + df.format(avgFailures / (double)avgQuantity) + ";");

        System.out.println("Частота підключення ресурсу: " + df.format(avgAdditionalResourcesUsageTime / (double)simulationTime) + ";");
        System.out.println("\tКількість підключень ресурсу: " + avgNumberOfActivations);
        System.out.println("\tКількість відключень ресурсу: " + avgNumberOfDeactivations);
    }
    public static void runTaskInLoopDiffPercentage(int simulationTime, int iterations, int percentage) {
        int avgFailures = 0,
                avgQuantity = 0,
                avgNumberOfActivations = 0,
                avgNumberOfDeactivations = 0;
        double avgAdditionalResourcesUsageTime = 0;

        for(int i = 0; i < iterations; i++) {
            Element.resetNextIdField();

            AdditionalResourcesStorage storage = new AdditionalResourcesStorage(4, 5, percentage);

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
            avgNumberOfActivations += storage.getNumberOfActivations();
            avgNumberOfDeactivations += storage.getNumberOfDeactivations();
            avgAdditionalResourcesUsageTime += decoder.getAdditionalResourcesUsageTime();
        }

        avgFailures = avgFailures / iterations;
        avgQuantity = avgQuantity / iterations;
        avgNumberOfActivations = avgNumberOfActivations / iterations;
        avgNumberOfDeactivations = avgNumberOfDeactivations / iterations;
        avgAdditionalResourcesUsageTime = avgAdditionalResourcesUsageTime / iterations;

        System.out.println("Частота знищення пакетів: " + df.format(avgFailures / (double)avgQuantity) + ";");

        System.out.println("Частота підключення ресурсу: " + df.format(avgAdditionalResourcesUsageTime / (double)simulationTime) + ";");
        System.out.println("\tКількість підключень ресурсу: " + avgNumberOfActivations);
        System.out.println("\tКількість відключень ресурсу: " + avgNumberOfDeactivations);
    }
    public static void verificationModel(int simulationTime, int iterations) {
        int avgIncomeInSystemPackagesAmount = 0,
            avgIncomeInDecoderPackagesAmount = 0,
            avgFailures = 0,
            avgNumberOfActivations = 0,
            avgNumberOfDeactivations = 0;
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

            avgIncomeInSystemPackagesAmount += creator.getQuantity();
            avgIncomeInDecoderPackagesAmount += decoder.getQuantity();
            avgFailures += decoder.getFailures();
            avgNumberOfActivations += storage.getNumberOfActivations();
            avgNumberOfDeactivations += storage.getNumberOfDeactivations();
            avgAdditionalResourcesUsageTime += decoder.getAdditionalResourcesUsageTime();

        }

        avgIncomeInSystemPackagesAmount = avgIncomeInSystemPackagesAmount / iterations;
        avgIncomeInDecoderPackagesAmount = avgIncomeInDecoderPackagesAmount / iterations;
        avgFailures = avgFailures / iterations;
        avgNumberOfActivations = avgNumberOfActivations / iterations;
        avgNumberOfDeactivations = avgNumberOfDeactivations / iterations;
        avgAdditionalResourcesUsageTime = avgAdditionalResourcesUsageTime / iterations;

        System.out.println("К-ть пакетів, що надійшли в систему: " + avgIncomeInSystemPackagesAmount);
        System.out.println("К-ть пакетів, що надійшли в декодер: " + avgIncomeInDecoderPackagesAmount);
        System.out.println("К-ть знищених пакетів: " + avgFailures);
        System.out.println("Час підключення ресурсу: " + df.format(avgAdditionalResourcesUsageTime));

        System.out.println("\nЧастота знищення пакетів: " + df.format(avgFailures / (double)avgIncomeInDecoderPackagesAmount) + ";");
        System.out.println("Частота підключення ресурсу: " + df.format(avgAdditionalResourcesUsageTime / (double)simulationTime) + ";");

        System.out.println("\nКількість ... ресурсу: ");
        System.out.println("\tпідключень: " + avgNumberOfActivations);
        System.out.println("\tвідключень: " + avgNumberOfDeactivations);

        System.out.println("\n" + avgIncomeInSystemPackagesAmount +
                "|" + avgIncomeInDecoderPackagesAmount +
                "|" + avgFailures +
                "|" + df.format(avgAdditionalResourcesUsageTime) +
                "|" + df.format(avgFailures / (double)avgIncomeInDecoderPackagesAmount) +
                "|" + df.format(avgAdditionalResourcesUsageTime / (double)simulationTime));
    }
}