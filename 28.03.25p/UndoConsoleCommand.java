package ex04;

/**
 * Команда для скасування останньої операції.
 */
public class UndoConsoleCommand implements ConsoleCommand {
    private Menu menu;

    /**
     * Конструктор для створення команди.
     * @param menu меню, з яким працює команда.
     */
    public UndoConsoleCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public char getKey() {
        return 'u';
    }

    @Override
    public String toString() {
        return "'u'ndo";
    }

    @Override
    public void execute() {
        menu.undoLastCommand();
    }
}