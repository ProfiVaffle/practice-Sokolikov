package ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;

/**
 * Клас для тестування основної функціональності.
 */
public class MainTest {
    /**
     * Тестує ініціалізацію та збереження результатів.
     */
    @Test
    public void testInitAndSave() {
        ViewTextTable table = new ViewTextTable(15);
        table.viewInit();
        assertEquals(5, table.getItems().size());
        try {
            table.viewSave();
        } catch (IOException e) {
            assertTrue("Save failed: " + e.getMessage(), false);
        }
    }

    /**
     * Тестує відновлення результатів.
     */
    @Test
    public void testRestore() {
        ViewTextTable table = new ViewTextTable();
        try {
            table.viewRestore();
            assertEquals(5, table.getItems().size());
        } catch (Exception e) {
            assertTrue("Restore failed: " + e.getMessage(), false);
        }
    }
}