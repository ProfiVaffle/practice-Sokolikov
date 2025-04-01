package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас для запуску програми.
 * Використовує шаблон проектування Factory Method.
 * Відображає результати у вигляді текстової таблиці.
 */
public class Main {
    private View view; // Об'єкт для роботи з результатами

    /**
     * Конструктор для ініціалізації об'єкта View.
     * @param view Об'єкт, який реалізує інтерфейс View.
     */
    public Main(View view) {
        this.view = view;
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
                System.out.print("'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: ");
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
                    view.viewShow();
                    break;
                case 'g':
                    System.out.println("Random generation.");
                    view.viewInit();
                    view.viewShow();
                    break;
                case 's':
                    System.out.println("Save current.");
                    try {
                        view.viewSave();
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    try {
                        view.viewRestore();
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                default:
                    System.out.println("Wrong command.");
            }
        } while (s.charAt(0) != 'q');
    }

    /**
     * Головний метод для запуску програми.
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        Main main = new Main(new ViewableTextTable().getView());
        main.menu();
    }
}
