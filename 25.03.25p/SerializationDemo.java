import java.io.*;

/**
 * Клас для демонстрації збереження та відновлення об'єкта.
 * Показує особливості використання transient полів.
 */
public class SerializationDemo {
    // Метод для збереження об'єкта у файл
    public static void saveState(CalculationData data, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data); // Зберігаємо об'єкт
            System.out.println("Об'єкт успішно збережено у файл: " + filePath);
        }
    }

    // Метод для відновлення об'єкта з файлу
    public static CalculationData restoreState(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не знайдено: " + filePath);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (CalculationData) ois.readObject(); // Читаємо об'єкт
        }
    }

    // Точка входу для перевірки роботи серіалізації
    public static void main(String[] args) {
        try {
            // Створюємо об'єкт CalculationData
            CalculationData data = new CalculationData(5, 10);
            data.setTemporaryData("Це тимчасові дані");
            System.out.println("До серіалізації: " + data.getTemporaryData());

            // Зберігаємо об'єкт у файл
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"));
            oos.writeObject(data);
            oos.close();

            // Відновлюємо об'єкт з файлу
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
            CalculationData restoredData = (CalculationData) ois.readObject();
            ois.close();

            // Виводимо результат
            System.out.println("Після десеріалізації: " + restoredData.getTemporaryData());
            System.out.println("Результат обчислень: " + restoredData.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
