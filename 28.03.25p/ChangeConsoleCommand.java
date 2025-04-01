package ex04;

/**
 * Консольна команда для зміни елементів.
 */
public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand {
    private View view;

    public ChangeConsoleCommand(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'hange";
    }

    @Override
    public void execute() {
        System.out.println("Change item: scale factor " + Math.random());
        view.viewInit();
        for (Item2d item : ((ViewResult) view).items) {
            setItem(item);
            setOffset(Math.random() * 10);
            super.execute();
        }
        view.viewShow();
    }
}