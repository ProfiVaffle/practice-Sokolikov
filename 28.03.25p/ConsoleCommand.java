package ex04;

import ex04.Command;

/**
 * Інтерфейс для консольних команд.
 */
public interface ConsoleCommand extends Command {
    char getKey();
}