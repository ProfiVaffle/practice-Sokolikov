import java.io.*;

/**
 * Клас для зберігання параметрів і результатів обчислень.
 * Демонструє використання transient полів.
 */
public class CalculationData implements Serializable {
    private static final long serialVersionUID = 1L;

    private double parameter1; // Перший параметр
    private double parameter2; // Другий параметр
    private double result;     // Результат обчислень

    private transient String temporaryData; // Поле, яке не серіалізується
    private String backupTemporaryData;     // Поле для збереження transient даних

    /**
     * Конструктор для ініціалізації параметрів.
     * @param parameter1 перший параметр
     * @param parameter2 другий параметр
     */
    public CalculationData(double parameter1, double parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public double getParameter1() {
        return parameter1;
    }

    public double getParameter2() {
        return parameter2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getTemporaryData() {
        return temporaryData;
    }

    public void setTemporaryData(String temporaryData) {
        this.temporaryData = temporaryData;
    }

    /**
     * Метод для серіалізації об'єкта.
     * @param oos об'єкт ObjectOutputStream
     * @throws IOException якщо виникає помилка вводу/виводу
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Зберігаємо стандартні дані
        backupTemporaryData = temporaryData; // Зберігаємо transient дані
    }

    /**
     * Метод для десеріалізації об'єкта.
     * @param ois об'єкт ObjectInputStream
     * @throws IOException якщо виникає помилка вводу/виводу
     * @throws ClassNotFoundException якщо клас не знайдено
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Читаємо стандартні дані
        temporaryData = backupTemporaryData; // Відновлюємо transient дані
    }
}
