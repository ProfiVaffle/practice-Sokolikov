package ex02;

import ex01.Item2d;
import java.util.ArrayList;

/**
 * Клас для створення об'єкта ViewTextResult.
 */
public class ViewableTextResult {
    private ArrayList<Item2d> items;

    /**
     * Конструктор із передачею колекції результатів.
     * @param items Колекція результатів обчислень
     */
    public ViewableTextResult(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Створює об'єкт ViewTextResult.
     * @return Об'єкт ViewTextResult
     */
    public ViewTextResult getView() {
        return new ViewTextResult(items);
    }
}
