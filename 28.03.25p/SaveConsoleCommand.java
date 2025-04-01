package ex04;

import java.io.IOException;


/**
 * Консольна команда для збереження даних.
 */
public class SaveConsoleCommand implements ConsoleCommand {
    private View view;

    public SaveConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 's';
    }

    @Override
    public String toString() {
        return "'s'ave";
    }

    @Override
    public void execute() {
        System.out.println("Save current.");
        view.viewSave();
    }
}