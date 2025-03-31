/**
 * Клас для виконання обчислень.
 * Використовує агрегування CalculationData.
 */
public class Calculator {
    private CalculationData data; // Посилання на об'єкт CalculationData

    /**
     * Конструктор приймає об'єкт CalculationData.
     * @param data об'єкт CalculationData
     */
    public Calculator(CalculationData data) {
        this.data = data;
    }

    /**
     * Метод для виконання обчислень (наприклад, додавання).
     */
    public void calculate() {
        double result = data.getParameter1() + data.getParameter2(); // Додаємо параметри
        data.setResult(result); // Зберігаємо результат
    }
}
