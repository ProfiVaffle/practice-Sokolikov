package ex04;

import java.io.Serializable;

/**
 * Клас для зберігання пари значень (x, y).
 */
public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private double x;
    private double y;

    public Item2d() {
        this.x = Math.random() * 100; // Випадкове значення для x
        this.y = Math.random() * 100; // Випадкове значення для y
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}