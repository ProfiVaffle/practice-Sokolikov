package ex03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Клас для роботи з результатами.
 */
public class ViewResult implements View {
    private static final String FNAME = "results.bin";
    private ArrayList<Item2d> items = new ArrayList<>();

    public ArrayList<Item2d> getItems() {
        return items;
    }

    @Override
    public void viewShow() {
        System.out.println("Current results:");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewInit() {
        items.clear();
        for (int i = 0; i < 5; i++) {
            items.add(new Item2d(Math.random() * 100, Math.random() * 100));
        }
    }

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FNAME))) {
            oos.writeObject(items);
            System.out.println("Results saved.");
        }
    }

    @Override
    public void viewRestore() throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FNAME))) {
            items = (ArrayList<Item2d>) ois.readObject();
            System.out.println("Results restored.");
        }
    }
}