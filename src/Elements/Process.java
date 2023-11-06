package Elements;

import Other.AdditionalResourcesStorage;
import Other.LanguagePackage;

import java.util.ArrayDeque;
import java.util.Queue;

public class Process extends Element {
    private final AdditionalResourcesStorage storage;
    private final Queue<LanguagePackage> queue;
    private double meanQueue;
    private boolean isAvailable;
    private LanguagePackage pack;

    public Process(String name, AdditionalResourcesStorage stateStorage) {
        super(name);
        this.storage = stateStorage;

        isAvailable= true;
        queue = new ArrayDeque<>();
        meanQueue = 0.0;
        tNext = Double.MAX_VALUE;
    }

    @Override
    public void inAct(LanguagePackage pack) {
        if (isAvailable) {
            isAvailable = false;

            this.pack = pack;
            tNext = tCurrent + getDelay();
        } else {
            queue.add(pack);
        }
    }
    @Override
    public void outAct() {
        quantity++;
        isAvailable = true;
        LanguagePackage processedPack = pack;
        tNext = Double.MAX_VALUE;

        if (!queue.isEmpty()) {
            isAvailable = false;

            this.pack = queue.poll();
            tNext = tCurrent + getDelay();
        }

        if(nextElement != null) {
            nextElement.inAct(processedPack);
        }
    }

    @Override
    public void doStatistics(double delta) {
        this.meanQueue = getMeanQueue() + queue.size() * delta;
    }
    public double getMeanQueue() {
        return meanQueue;
    }

    private double getDelay() {
        return storage.getProcessorsDelay();
    }
}