package ex03;

import java.io.IOException;

/**
 * Інтерфейс для відображення результатів.
 */
public interface View {
    void viewShow();
    void viewInit();
    void viewSave() throws IOException;
    void viewRestore() throws Exception;
}