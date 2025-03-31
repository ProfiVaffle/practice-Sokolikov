import java.util.Scanner;

// Це програма для визначення мобільного оператора України за номером телефону
public class Phone {
    public static void main(String[] args) {
        // Створюємо об'єкт Scanner для зчитування даних від користувача
        Scanner scanner = new Scanner(System.in);

        // Просимо користувача ввести номер телефону
        System.out.print("Введіть номер телефону (у форматі +380XXXXXXXXX): ");
        String phoneNumber = scanner.nextLine(); // Зчитуємо номер телефону

        // Викликаємо метод для визначення оператора
        String operator = getOperator(phoneNumber);

        // Перевіряємо, чи вдалося визначити оператора
        if (operator != null) {
            // Якщо оператор знайдений, виводимо його назву
            System.out.println("Оператор: " + operator);
        } else {
            // Якщо оператор не знайдений або формат номера неправильний
            System.out.println("Невідомий оператор або неправильний формат номера.");
        }

        // Закриваємо Scanner, щоб уникнути витоку ресурсів
        scanner.close();
    }

    /**
     * Метод для визначення мобільного оператора за номером телефону.
     * @param phoneNumber Номер телефону у форматі +380XXXXXXXXX
     * @return Назва оператора або null, якщо формат неправильний
     */
    public static String getOperator(String phoneNumber) {
        // Перевіряємо, чи номер не є null і чи відповідає формату +380XXXXXXXXX
        if (phoneNumber == null || !phoneNumber.matches("\\+380\\d{9}")) {
            return null; // Якщо формат неправильний, повертаємо null
        }

        // Отримуємо код оператора (дві цифри після +380)
        String code = phoneNumber.substring(4, 6);

        // Використовуємо switch-case для визначення оператора
        switch (code) {
            case "50":
            case "66":
            case "95":
            case "99":
                return "Vodafone"; // Якщо код відповідає Vodafone
            case "67":
            case "68":
            case "96":
            case "97":
            case "98":
                return "Kyivstar"; // Якщо код відповідає Kyivstar
            case "63":
            case "73":
            case "93":
                return "Lifecell"; // Якщо код відповідає Lifecell
            default:
                return "Невідомий оператор"; // Якщо код не відповідає жодному оператору
        }
    }
}
