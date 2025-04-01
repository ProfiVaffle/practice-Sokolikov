package ex04;

/**
 * Головний клас для запуску програми.
 * Тут все починається.
 */
public class Main {
    /**
     * Головний метод програми.
     * @param args аргументи командного рядка.
     */
    public static void main(String[] args) {
        // Отримуємо екземпляр програми і запускаємо її
        Application app = Application.getInstance();
        app.run();
    }
}
