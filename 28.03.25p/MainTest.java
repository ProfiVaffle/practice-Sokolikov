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