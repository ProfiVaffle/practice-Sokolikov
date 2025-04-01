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

/**
 * Клас для зберігання пари значень (x, y).
 */
class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private double x;
    private double y;

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