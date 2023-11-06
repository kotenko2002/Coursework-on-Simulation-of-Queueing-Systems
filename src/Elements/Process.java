package Elements;

import Other.AdditionalResourcesStorage;
import Other.LanguagePackage;

import java.util.ArrayDeque;
import java.util.Queue;

public class Process extends Element {
    private final AdditionalResourcesStorage storage;
    private final Queue<LanguagePackage> queue;
    private LanguagePackage pack;
    private double meanLoad, meanQueue;

    public Process(String name, AdditionalResourcesStorage stateStorage) {
        super(name);
        this.storage = stateStorage;

        queue = new ArrayDeque<>();
        pack = null;
        meanLoad = meanQueue = 0.0;

        tNext = Double.MAX_VALUE;
    }

    @Override
    public void inAct(LanguagePackage pack) {
        if (this.pack == null) {
            this.pack = pack;
            tNext = tCurrent + getDelay();
        } else {
            queue.add(pack);
        }
    }

    @Override
    public void outAct() {
        quantity++;
        LanguagePackage processedPack = pack;

        if (!queue.isEmpty()) {
            this.pack = queue.poll();
            tNext = tCurrent + getDelay();
        } else {
            this.pack = null;
            tNext = Double.MAX_VALUE;
        }

        if(nextElement != null) {
            nextElement.inAct(processedPack);
        }
    }

    @Override
    public void doStatistics(double delta) {
        this.meanQueue = getMeanQueue() + queue.size() * delta;
        this.meanLoad = meanLoad + isProcessWorking() * delta;
    }
    public double getMeanLoad() {
        return meanLoad;
    }
    public double getMeanQueue() {
        return meanQueue;
    }

    private int isProcessWorking() {
        return pack != null ? 1 : 0;
    }
    private double getDelay() {
        return storage.getProcessorsDelay();
    }
}