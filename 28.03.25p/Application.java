package ex04;

/**
 * Клас для запуску програми.
 * Використовує Singleton, щоб був лише один екземпляр.
 */
public class Application {
    // Єдиний екземпляр класу
    private static Application instance = new Application();

    // Приватний конструктор, щоб ніхто не створив ще один екземпляр
    private Application() {}

    /**
     * Метод для отримання єдиного екземпляра.
     * @return екземпляр класу Application.
     */
    public static Application getInstance() {
        return instance;
    }

    // Об'єкт для роботи з даними
    private View view = new ViewResult();

    // Меню для взаємодії з користувачем
    public Menu menu = new Menu();

    /**
     * Метод для запуску програми.
     */
    public void run() {
        // Додаємо команди до меню
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new UndoConsoleCommand(menu));
        // Запускаємо меню
        menu.execute();
    }
}