package ex04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Клас для роботи з командами.
 */
public class Menu implements Command {
    private List<ConsoleCommand> menu = new ArrayList<>();
    private Stack<Command> history = new Stack<>();

    public ConsoleCommand add(ConsoleCommand command) {
        menu.add(command);
        return command;
    }

    public void addToHistory(Command command) {
        history.push(command);
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            System.out.println("Скасовуємо останню команду...");
            command.execute();
        } else {
            System.out.println("Немає команд для скасування.");
        }
    }

    @Override
    public void execute() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        menu: while (true) {
            do {
                System.out.print(this);
                try {
                    s = in.readLine();
                } catch (IOException e) {
                    System.err.println("Помилка: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            char key = s.charAt(0);
            if (key == 'q') {
                System.out.println("Вихід.");
                break menu;
            }
            for (ConsoleCommand c : menu) {
                if (s.charAt(0) == c.getKey()) {
                    c.execute();
                    if (!(c instanceof UndoConsoleCommand)) {
                        addToHistory(c);
                    }
                    continue menu;
                }
            }
            System.out.println("Неправильна команда.");
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Введіть команду...\n");
        for (ConsoleCommand c : menu) {
            s.append(c).append(", ");
        }
        s.append("'q'uit: ");
        return s.toString();
    }
}