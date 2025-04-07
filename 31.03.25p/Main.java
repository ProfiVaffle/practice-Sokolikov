import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас програми для демонстрації обробки списку чисел.
 */
public class Main {

    private static List<Integer> numbers = new ArrayList<>();

    /**
     * Метод для пошуку максимального значення в списку чисел.
     * @param numbers список чисел
     * @return максимальне значення
     */
    public static int findMax(List<Integer> numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Метод для обчислення середнього значення в списку чисел.
     * @param numbers список чисел
     * @return середнє значення
     */
    public static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return numbers.isEmpty() ? 0 : (double) sum / numbers.size();
    }

    /**
     * Метод для пошуку мінімального значення в списку чисел.
     * @param numbers список чисел
     * @return мінімальне значення
     */
    public static int findMin(List<Integer> numbers) {
        int min = Integer.MAX_VALUE;
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Метод для генерації випадкових чисел.
     */
    public static void generateNumbers() {
        numbers.clear();
        for (int i = 0; i < 10; i++) {
            numbers.add((int) (Math.random() * 100 - 50)); // Генеруємо числа від -50 до 50
        }
        System.out.println("Згенеровано новий список чисел: " + numbers);
    }

    /**
     * Метод для зміни масштабу чисел у списку.
     * @param scaleFactor коефіцієнт масштабу
     */
    public static void changeScale(double scaleFactor) {
        for (int i = 0; i < numbers.size(); i++) {
            numbers.set(i, (int) (numbers.get(i) * scaleFactor));
        }
        System.out.println("Список чисел після зміни масштабу: " + numbers);
    }

    /**
     * Метод для виконання обчислень і виведення результатів.
     */
    public static void executeCalculations() {
        if (numbers.isEmpty()) {
            System.out.println("Список чисел порожній. Спочатку згенеруйте числа.");
            return;
        }
        int max = findMax(numbers);
        double avg = calculateAverage(numbers);
        int min = findMin(numbers);

        System.out.println("Результати обчислень:");
        System.out.println("Максимальне значення: " + max);
        System.out.println("Середнє значення: " + avg);
        System.out.println("Мінімальне значення: " + min);
    }

    /**
     * Головний метод програми.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Введіть команду: 'v'iew, 'g'enerate, 'c'hange, 'e'xecute, 'q'uit:");
        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "v":
                    System.out.println("Список чисел: " + numbers);
                    break;
                case "g":
                    generateNumbers();
                    break;
                case "c":
                    System.out.print("Введіть коефіцієнт масштабу: ");
                    double scaleFactor = scanner.nextDouble();
                    scanner.nextLine(); // Очищення буфера
                    changeScale(scaleFactor);
                    break;
                case "e":
                    executeCalculations();
                    break;
                case "q":
                    System.out.println("Вихід з програми.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        }
    }
}
