# Студент: Соколіков Євгеній
# Практика з «Об’єктно-орієнтованого програмування»

# [24.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/tree/main/24.03.25p)

## Завдання 
### 1. Написати просту консольну програму

Ця консольна програма на Java демонструє вивід аргументів командної строки на екран.

### Опис
Програма містить клас `Main` з методом `main`, який виводить рядок на консоль.

<details>
<summary>Код Main.java</summary>
    
```java
class Main { // Оголошення класу з назвою Main
    public static void main(String[] args) { // Оголошення методу main, точка входу в програму; 
      // public: означає, що метод доступний ззовні класу; 
      // static: означає, що метод належить до самого класу, а не до конкретного об'єкта;
      // void: означає, що метод не повертає значення;
        System.out.println(" Це буде виведено на екран "); // Виведення рядка "Це буде виведено на екран" на консоль
    }
}
```
</details>

# [25.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/tree/main/25.03.25p)

## Завдання
### 1. Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень.

### Опис

Цей клас `CalculationData` призначений для зберігання параметрів і результатів обчислень.

<details>
<summary>Код CalculationData.java</summary>

```java
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
```
</details>

### 2. Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості.

### Опис

Цей клас `SerializationDemo` демонструє збереження та відновлення об'єкта в діалоговому режимі, використовуючи серіалізацію. Він також показує особливості використання transient полів.

<details>
<summary>Код SerializationDemo.java</summary>
    
```java
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
            saveState(data, "data.ser");

            // Відновлюємо об'єкт з файлу
            CalculationData restoredData = restoreState("data.ser");

            // Виводимо результат
            System.out.println("Після десеріалізації: " + restoredData.getTemporaryData());
            System.out.println("Результат обчислень: " + restoredData.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
</details>

### 3. Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації.

### Опис

Цей клас `CalculationTest` призначений для тестування коректності результатів обчислень та серіалізації/десеріалізації. Він використовує бібліотеку JUnit для організації тестів.

<details>
<summary>Код CalculationTest.java</summary>

```java
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
```
</details>

### 4. Виконати індивідуальне завдання згідно номеру в списку.
Індивідуальне завдання за номером по списку (19) - "Визначити мобільного оператора за заданим номером абонента." файл: Phone.java

### Опис

Цей клас `Phone` призначений для визначення мобільного оператора України за номером телефону. Він приймає номер у форматі +380XXXXXXXXX та виводить назву оператора.

<details>
<summary>Код Phone.java</summary>

```java
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
```
</details>

# [26.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/tree/main/26.03.25p)

## Завдання

### 1. Забезпечити розміщення результатів обчислень у колекції з можливістю збереження/відновлення.

### Опис
У цьому фрагменті коду результатами обчислень є об'єкти класу Item2d. Цей клас використовує два параметри: x та y, які представляють аргумент і результат обчислення відповідно. Об'єкти класу Item2d зберігаються в колекції ArrayList, що дозволяє зберігати кілька результатів обчислень. Для збереження та відновлення результатів використовується механізм серіалізації. Це дозволяє зберігати стан програми між сеансами роботи та відновлювати його при потребі.

<details>
<summary>Код Item2d.java</summary>

```java
package ex01;

import java.io.Serializable;

/**
 * Клас для зберігання пари значень (x, y).
 * Використовується для зберігання результатів обчислень.
 */
public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private double x; // Аргумент
    private double y; // Результат

    /**
     * Конструктор для ініціалізації значень.
     * @param x значення аргументу
     * @param y значення результату
     */
    public Item2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(x: %.2f, y: %.2f)", x, y);
    }
}
```
</details>

### 2. Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення рахунок додавання нових відображуваних класів.

### Опис
Шаблон проектування Factory Method використовується для створення об'єктів, що відповідають певному інтерфейсу. У даному випадку, клас ViewableTextResult є фабрикою для створення об'єктів класу ViewTextResult, які відображають результати обчислень у текстовому вигляді. Це дозволяє гнучко додавати нові способи відображення без зміни основної логіки програми. Клас ViewableTextResult надає метод getView(), який створює об'єкт ViewTextResult.

<details>
<summary>Код ViewableTextResult.java</summary>

```java
package ex02;

import ex01.Item2d;
import java.util.ArrayList;

/**
 * Клас для створення об'єкта ViewTextResult.
 */
public class ViewableTextResult {
    private ArrayList<Item2d> items;

    /**
     * Конструктор із передачею колекції результатів.
     * @param items Колекція результатів обчислень
     */
    public ViewableTextResult(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Створює об'єкт ViewTextResult.
     * @return Об'єкт ViewTextResult
     */
    public ViewTextResult getView() {
        return new ViewTextResult(items);
    }
}
```
</details>

### 3. Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.

### Опис
Інтерфейс Displayable визначає метод display(), який повинні реалізувати всі класи, що відображають результати обчислень. Клас ViewTextResult реалізує цей інтерфейс для відображення результатів у текстовому вигляді. Це дозволяє створити гнучку і розширювану систему відображення результатів, яку можна легко адаптувати для різних форматів виведення.

<details>
<summary>Код Displayable.java</summary>

```java
package ex02;

/**
 * Інтерфейс для відображення результатів обчислень.
 */
public interface Displayable {
    /**
     * Метод для відображення результатів у текстовому вигляді.
     */
    void display();
}
```
</details>

### 4. Реалізувати ці методи виведення результатів у текстовому вигляді.

### Опис
Клас ViewTextResult реалізує інтерфейс Displayable і забезпечує метод display(), який виводить результати обчислень у консоль у текстовому вигляді. Він перебирає всі елементи колекції items та виводить їх, використовуючи метод toString() класу Item2d.

<details>
<summary>Код ViewTextResult.java</summary>

```java
package ex02;

import ex01.Item2d;
import java.util.ArrayList;

/**
 * Клас для відображення результатів у текстовому вигляді.
 */
public class ViewTextResult implements Displayable {
    private ArrayList<Item2d> items; // Колекція результатів

    /**
     * Конструктор із передачею колекції результатів.
     * @param items Колекція результатів обчислень
     */
    public ViewTextResult(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Відображає результати у текстовому вигляді.
     */
    @Override
    public void display() {
        System.out.println("Результати обчислень (текстовий вигляд):");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }
}
```
</details>

### 5. Розробити та реалізувати інтерфейс для "фабрикуючого" методу.

### Опис
Метод getView() класу ViewableTextResult є фабрикуючим методом, який створює новий об'єкт ViewTextResult. Це дозволяє використовувати одну фабрику для створення об'єктів, що відповідають інтерфейсу Displayable. Фабричний метод спрощує зміну способу відображення результатів без необхідності змінювати код основної програми.

<details>
<summary>Код ViewableTextResult.java</summary>

```java
package ex02;

import ex01.Item2d;
import java.util.ArrayList;

/**
 * Клас для створення об'єкта ViewTextResult.
 */
public class ViewableTextResult {
    private ArrayList<Item2d> items;

    /**
     * Конструктор із передачею колекції результатів.
     * @param items Колекція результатів обчислень
     */
    public ViewableTextResult(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Створює об'єкт ViewTextResult.
     * @return Об'єкт ViewTextResult
     */
    public ViewTextResult getView() {
        return new ViewTextResult(items);
    }
}
```
</details>

# [27.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/tree/main/27.03.25p)

## Завдання
### 1. Використовуючи шаблон проектування Factory Method (Virtual Constructor), розширити ієрархію похідними класами, реалізують методи для подання результатів у вигляді текстової таблиці.

### Опис
Цей фрагмент коду демонструє використання шаблону проектування Factory Method для створення об'єктів, які реалізують відображення результатів у вигляді текстової таблиці. Клас `ViewTextTable` реалізує методи для відображення даних, включаючи заголовки, тіла таблиці та лінії для оформлення. Клас `ViewableTextTable` виступає як фабрика для створення об'єкта `ViewTextTable`, дозволяючи гнучко налаштовувати параметри відображення, наприклад, ширину стовпців таблиці.

<details>
<summary>Код View.java</summary>

```java
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

// ViewTextTable.java
package ex03;

import java.io.Serializable;
import java.util.Formatter;

/**
 * Клас для відображення результатів у вигляді текстової таблиці.
 */
public class ViewTextTable extends ViewResult {
    private static final int DEFAULT_WIDTH = 20;
    private int width;

    public ViewTextTable() {
        this.width = DEFAULT_WIDTH;
    }

    public ViewTextTable(int width) {
        this.width = width;
    }

    public int setWidth(int width) {
        return this.width = width;
    }

    public int getWidth() {
        return width;
    }

    private void outLine() {
        for (int i = width; i > 0; i--) {
            System.out.print('-');
        }
        System.out.println();
    }

    private void outHeader() {
        Formatter fmt = new Formatter();
        fmt.format("%s%d%s%2$d%s", "%", (width - 3) / 2, "s | %", "s\n");
        System.out.printf(fmt.toString(), "x ", "y ");
    }

    private void outBody() {
        Formatter fmt = new Formatter();
        fmt.format("%s%d%s%2$d%s", "%", (width - 3) / 2, ".0f | %", ".3f\n");
        for (Item2d item : getItems()) {
            System.out.printf(fmt.toString(), item.getX(), item.getY());
        }
    }

    @Override
    public void viewShow() {
        outHeader();
        outLine();
        outBody();
        outLine();
    }
}

/**
 * Клас для створення об'єкта ViewTextTable.
 */
class ViewableTextTable {
    public View getView() {
        return new ViewTextTable();
    }
}
```
</details>

### 2. Продемонструвати заміщення (перевизначення, overriding), поєднання (перевантаження, overloading), динамічне призначення методів (Пізнє зв'язування, поліморфізм, dynamic method dispatch).

### Опис
У цьому коді використовується кілька основних принципів ООП: перевизначення (overriding) методів, що дозволяє створити специфічну поведінку для кожного класу-нащадка, перевантаження (overloading) методів для підтримки різних варіантів виклику з різними параметрами, та поліморфізм для забезпечення можливості виклику методів через загальний інтерфейс, що дозволяє змінювати поведінку об'єктів у часі виконання.

<details>
<summary>Код ViewResult.java</summary>
    
```java
package ex03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Клас для роботи з результатами.
 */
public class ViewResult implements View {
    private static final String FNAME = "results.bin";
    private ArrayList<Item2d> items = new ArrayList<>();

    public ArrayList<Item2d> getItems() {
        return items;
    }

    @Override
    public void viewShow() {
        System.out.println("Current results:");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewInit() {
        items.clear();
        for (int i = 0; i < 5; i++) {
            items.add(new Item2d(Math.random() * 100, Math.random() * 100));
        }
    }

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FNAME))) {
            oos.writeObject(items);
            System.out.println("Results saved.");
        }
    }

    @Override
    public void viewRestore() throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FNAME))) {
            items = (ArrayList<Item2d>) ois.readObject();
            System.out.println("Results restored.");
        }
    }
}
```
</details>

### 3. Забезпечити діалоговий інтерфейс із користувачем.

### Опис
Ця частина коду реалізує простий консольний інтерфейс для взаємодії з користувачем. Взаємодія з користувачем здійснюється через введення команд, які відповідають за різні операції, такі як перегляд результатів, ініціалізація нових даних, збереження і відновлення результатів. Клас `Main` керує цілим процесом за допомогою циклічного меню, де користувач може вибирати потрібні операції.

<details>
<summary>Код Main.java</summary>

```java
package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас для запуску програми.
 * Використовує шаблон проектування Factory Method.
 * Відображає результати у вигляді текстової таблиці.
 */
public class Main {
    private View view; // Об'єкт для роботи з результатами

    /**
     * Конструктор для ініціалізації об'єкта View.
     * @param view Об'єкт, який реалізує інтерфейс View.
     */
    public Main(View view) {
        this.view = view;
    }

    /**
     * Меню для взаємодії з користувачем.
     */
    protected void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            do {
                System.out.println("Enter command...");
                System.out.print("'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: ");
                try {
                    s = in.readLine();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'v':
                    System.out.println("View current.");
                    view.viewShow();
                    break;
                case 'g':
                    System.out.println("Random generation.");
                    view.viewInit();
                    view.viewShow();
                    break;
                case 's':
                    System.out.println("Save current.");
                    try {
                        view.viewSave();
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    try {
                        view.viewRestore();
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                default:
                    System.out.println("Wrong command.");
            }
        } while (s.charAt(0) != 'q');
    }

    /**
     * Головний метод для запуску програми.
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        Main main = new Main(new ViewableTextTable().getView());
        main.menu();
    }
}
```
</details>

### 4. Розробити клас для тестування основної функціональності.

### Опис 
У цьому фрагменті коду створюється тестовий клас `MainTest`, який використовується для перевірки основних функцій програми. Тести за допомогою бібліотеки JUnit перевіряють, чи правильно працюють методи класу `ViewResult`, такі як ініціалізація результатів, їх збереження та відновлення. Тести забезпечують коректність реалізації та взаємодії між класами.

<details>
<summary>Код MainTest.java</summary>

```java
// MainTest.java
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
```
</details>

# [28.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/tree/main/28.03.25p)

## Завдання
### 1. Реалізувати можливість скасування (undo) операцій (команд).

### Опис
Клас `UndoConsoleCommand` представляє команду для скасування останньої виконаної дії. Він інтегрується з головним меню програми (Menu), де зберігається історія виконаних команд. При активації (execute) команда викликає метод undoLastCommand(), що дозволяє користувачеві повернути попередній стан системи.

<details>
<summary>Код UndoConsoleCommand.java</summary>

```java
package ex04;

/**
 * Команда для скасування останньої операції.
 */
public class UndoConsoleCommand implements ConsoleCommand {
    private Menu menu;

    /**
     * Конструктор для створення команди.
     * @param menu меню, з яким працює команда.
     */
    public UndoConsoleCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public char getKey() {
        return 'u';
    }

    @Override
    public String toString() {
        return "'u'ndo";
    }

    @Override
    public void execute() {
        menu.undoLastCommand();
    }
}
```
</details>

### 2. Продемонструвати поняття "макрокоманда"

### Опис

`ConsoleCommand` розширює загальний інтерфейс Command, і саме через нього реалізується можливість створювати макрокоманди — команди, які складаються з кількох інших команд.

<details>
<summary>Код ConsoleCommand.java</summary>

```java
package ex04;

import ex04.Command;

/**
 * Інтерфейс для консольних команд.
 */
public interface ConsoleCommand extends Command {
    char getKey();
}
```
</details>

### 3. При розробці програми використовувати шаблон Singleton.

### Опис

Клас `Application` реалізовано за шаблоном Singleton — це гарантує, що у програмі існує лише один екземпляр цього класу. Такий підхід дозволяє централізовано керувати запуском і конфігурацією всієї програми.

<details>
<summary>Код Application.java</summary>

```java
package ex04;

/**
 * Клас для запуску програми.
 * Використовує Singleton, щоб був лише один екземпляр.
 */
public class Application {
    // Єдиний екземпляр класу
    private static Application instance = new Application();

    // Приватний конструктор, щоб ніхто не створив ще один екземпляр
    private Application() {}

    /**
     * Метод для отримання єдиного екземпляра.
     * @return екземпляр класу Application.
     */
    public static Application getInstance() {
        return instance;
    }

    // Об'єкт для роботи з даними
    private View view = new ViewResult();

    // Меню для взаємодії з користувачем
    public Menu menu = new Menu();

    /**
     * Метод для запуску програми.
     */
    public void run() {
        // Додаємо команди до меню
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new UndoConsoleCommand(menu));
        // Запускаємо меню
        menu.execute();
    }
}
```
</details>

### 4. Забезпечити діалоговий інтерфейс із користувачем.

### Опис

Інтерфейс `View` визначає набір методів для виведення, збереження, відновлення та ініціалізації результатів.

<details>
<summary>Код View.java</summary>

```java
package ex04;

/**
 * Інтерфейс для роботи з результатами.
 */
public interface View {
    void viewInit();
    void viewShow();
    void viewSave();
    void viewRestore();
}
```
</details>

### 5. Розробити клас для тестування функціональності програми.

### Опис

Для перевірки коректності роботи команд реалізовано клас MainTest. Він містить методи тестування ключових компонентів програми

<details>
<summary>Код MainTest.java</summary>

```java
package ex04;

/**
 * Клас для тестування програми.
 */
public class MainTest {
    /**
     * Тестує метод execute() у ChangeItemCommand.
     */
    public void testExecute() {
        ChangeItemCommand cmd = new ChangeItemCommand();
        cmd.setItem(new Item2d());
        double x, y, offset;
        for (int ctr = 0; ctr < 1000; ctr++) {
            cmd.getItem().setXY(x = Math.random() * 100.0, y = Math.random() * 100.0);
            cmd.setOffset(offset = Math.random() * 100.0);
            cmd.execute();
            assertEquals(x, cmd.getItem().getX(), .1e-10);
            assertEquals(y * offset, cmd.getItem().getY(), .1e-10);
        }
        System.out.println("testExecute пройшов успішно.");
    }

    /**
     * Тестує основну функціональність ChangeConsoleCommand.
     */
    public void testChangeConsoleCommand() {
        ChangeConsoleCommand cmd = new ChangeConsoleCommand(new ViewResult());
        cmd.getView().viewInit();
        cmd.execute();
        assertEquals("'c'hange", cmd.toString());
        assertEquals('c', cmd.getKey());
        System.out.println("testChangeConsoleCommand пройшов успішно.");
    }

    private void assertEquals(double expected, double actual, double delta) {
        if (Math.abs(expected - actual) > delta) {
            throw new AssertionError("Очікувалося: " + expected + ", але отримано: " + actual);
        }
    }

    private void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Очікувалося: " + expected + ", але отримано: " + actual);
        }
    }

    private void assertEquals(char expected, char actual) {
        if (expected != actual) {
            throw new AssertionError("Очікувалося: " + expected + ", але отримано: " + actual);
        }
    }

    public static void main(String[] args) {
        MainTest test = new MainTest();
        test.testExecute();
        test.testChangeConsoleCommand();
    }
}
```
</details>

# [31.03.2025](https://github.com/ProfiVaffle/practice-Sokolikov/blob/main/31.03.25p)
## Завдання
### 1. Продемонструвати можливість паралельної обробки елементів колекції (пошук мінімуму, максимуму, обчислення середнього значення, відбір за критерієм, статистична обробка тощо).

### Опис

Клас `Main.java` містить основну логіку для виконання операцій з числами. Ось основні функції:

1) Генерація випадкових чисел

<details>
<summary>Приклад коду</summary>

```java
public static void generateNumbers() {
    // Генерація 10 випадкових чисел від -50 до 50
    numbers.clear();
    for (int i = 0; i < 10; i++) {
        numbers.add((int) (Math.random() * 100 - 50));
    }
    System.out.println("Згенеровано новий список чисел: " + numbers);
}
```
</details>

2) Пошук максимального значення

<details>
<summary>Приклад коду</summary>

```java
public static int findMax(List<Integer> numbers) {
    int max = Integer.MIN_VALUE;
    for (int num : numbers) {
        if (num > max) {
            max = num;
        }
    }
    return max;
}
```
</details>

3) Обчислення середнього значення

<details>
<summary>Приклад коду</summary>

```java
public static double calculateAverage(List<Integer> numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return numbers.isEmpty() ? 0 : (double) sum / numbers.size();
}

```
</details>


4) Пошук мінімального значення

<details>
<summary>Приклад коду</summary>

```java
public static int findMin(List<Integer> numbers) {
    int min = Integer.MAX_VALUE;
    for (int num : numbers) {
        if (num < min) {
            min = num;
        }
    }
    return min;
}
```
</details>

5) Керування командою
Програма дозволяє користувачеві взаємодіяти з системою через простий текстовий інтерфейс, де можна виконувати такі команди:

'`v`'iew — вивести поточний список чисел.

'`g`'enerate — згенерувати новий список чисел.

'`c`'hange — змінити масштаб чисел у списку.

'`e`'xecute — виконати обчислення (максимум, мінімум, середнє).

'`q`'uit — вихід з програми.

### 2. Управління чергою завдань (команд) реалізувати за допомогою шаблону Worker Thread.

### Опис

Управління завданнями (командами) в програмі може бути покращене через застосування шаблону Worker Thread, де кожна операція, така як генерація чисел або обчислення результатів, виконувалася б окремими потоками, що забезпечує паралельну обробку.

<details>
<summary>Код Main.java</summary>

```java
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
```
</details>
