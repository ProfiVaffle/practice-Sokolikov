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