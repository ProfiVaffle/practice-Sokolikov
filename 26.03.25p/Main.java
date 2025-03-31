package ex02;

import ex01.Item2d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Головний клас для запуску програми.
 */
public class Main {
    private ArrayList<Item2d> items; // Колекція результатів

    /**
     * Конструктор для ініціалізації колекції результатів.
     */
    public Main() {
        items = new ArrayList<>();
    }

    /**
     * Меню для взаємодії з користувачем.
     */
    protected void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            do {
                System.out.println("Enter command...");
                System.out.print("'q'uit, 'v'iew, 'g'enerate, 't'ext view: ");
                try {
                    s = in.readLine();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'v':
                    System.out.println("View current.");
                    viewCurrent();
                    break;
                case 'g':
                    System.out.println("Random generation.");
                    generateItems();
                    viewCurrent();
                    break;
                case 't':
                    System.out.println("Text view.");
                    ViewTextResult textView = new ViewableTextResult(items).getView();
                    textView.display();
                    break;
                default:
                    System.out.println("Wrong command.");
            }
        } while (s.charAt(0) != 'q');
    }

    /**
     * Генерує випадкові результати обчислень.
     */
    private void generateItems() {
        items.clear();
        for (int i = 0; i < 5; i++) {
            items.add(new Item2d(Math.random() * 100, Math.random() * 100));
        }
    }

    /**
     * Відображає поточні результати.
     */
    private void viewCurrent() {
        System.out.println("Поточні результати:");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }

    /**
     * Головний метод для запуску програми.
     * @param args Аргументи командного рядка
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
