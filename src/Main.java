import Elements.Create;
import Elements.Process;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        task2();
    }

    public static void task2() {
        Create creator = new Create(6,3, "CREATOR");
        Process processor1 = new Process(5,  "PROCESSOR1");
        Process processor2 = new Process(5,  "PROCESSOR2");

        creator.setDistribution("norm");
        creator.setNextElement(processor1);

        processor1.setDistribution("exp");
        processor1.setNextElement(processor2);

        processor2.setDistribution("exp");

        Model model = new Model(new ArrayList<>(){{add(creator); add(processor1); add(processor2); }});
        model.simulate(10000);
    }
}