package ex04;

import ex04.Item2d;

/**
 * Команда для зміни елементів.
 */
public class ChangeItemCommand implements Command {
    private Item2d item;
    private double offset;

    public void setItem(Item2d item) {
        this.item = item;
    }

    public Item2d getItem() {
        return item;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getOffset() {
        return offset;
    }

    @Override
    public void execute() {
        if (item != null) {
            item.setXY(item.getX(), item.getY() * offset);
        }
    }
}