package tetris.game;

/**
 * Created by highway on 19/11/15.
 */
public class Timer implements Runnable {
    private ActionHandler actionHandler;

    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
                actionHandler.moveDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
