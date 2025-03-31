package ex02;

import ex01.Item2d;
import java.util.ArrayList;

/**
 * Клас для відображення результатів у текстовому вигляді.
 */
public class ViewTextResult implements Displayable {
    private ArrayList<Item2d> items; // Колекція результатів

    /**
     * Конструктор із передачею колекції результатів.
     * @param items Колекція результатів обчислень
     */
    public ViewTextResult(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Відображає результати у текстовому вигляді.
     */
    @Override
    public void display() {
        System.out.println("Результати обчислень (текстовий вигляд):");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }
}
