import Elements.Create;
import Elements.Decoder;
import Elements.Process;
import StateStorage.StateStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        task();
    }

    public static void task() {
        StateStorage storage = new StateStorage(4, 5);

        Create creator = new Create(6,3, "CREATOR");
        Process processor1 = new Process("PROCESSOR1", storage);
        Process processor2 = new Process("PROCESSOR2", storage);
        Decoder decoder = new Decoder("DECODER", storage);

        creator.setNextElement(processor1);
        processor1.setNextElement(processor2);
        processor2.setNextElement(decoder);

        Model model = new Model(new ArrayList<>()
        {
            {
            add(creator);
            add(processor1);
            add(processor2);
            add(decoder);
            }
        });
        model.simulate(10000);
    }
}