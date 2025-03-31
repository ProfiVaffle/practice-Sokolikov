import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.*;
/**
 * Клас для тестування обчислень та серіалізації.
 */
public class CalculationTest {

    @Test
    public void testCalculation() {
        // Перевіряємо коректність обчислень
        CalculationData data = new CalculationData(3, 7);
        Calculator calculator = new Calculator(data);
        calculator.calculate();
        assertEquals(10, data.getResult(), 0.001); // Перевіряємо результат
    }

    @Test
    public void testSerialization() {
        try {
            CalculationData data = new CalculationData(4, 6);
            data.setTemporaryData("Тимчасові дані");

            // Серіалізація
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.ser"));
            oos.writeObject(data);
            oos.close();

            // Десеріалізація
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"));
            CalculationData restoredData = (CalculationData) ois.readObject();
            ois.close();

            // Перевіряємо, що дані відновлено
            assertEquals(data.getResult(), restoredData.getResult(), 0.001);
            assertNotNull(restoredData.getTemporaryData()); // Поле має бути відновлене
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
