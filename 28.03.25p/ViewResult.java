package ex04;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з результатами.
 */
public class ViewResult implements View {
    protected List<Item2d> items = new ArrayList<>();

    @Override
    public void viewInit() {
        items.clear();
        for (int i = 0; i < 5; i++) {
            Item2d item = new Item2d();
            item.setXY(Math.random() * 100, Math.random() * 100); // Випадкові значення
            items.add(item);
        }
    }

    @Override
    public void viewShow() {
        for (Item2d item : items) {
            System.out.println("Item: x = " + item.getX() + ", y = " + item.getY());
        }
    }

    @Override
    public void viewSave() {
        System.out.println("Результати збережено.");
    }

    @Override
    public void viewRestore() {
        System.out.println("Результати відновлено.");
    }
}