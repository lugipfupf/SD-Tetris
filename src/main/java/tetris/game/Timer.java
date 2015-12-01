package tetris.game;

import tetris.gui.ActionHandler;

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

                try {
                    actionHandler.moveDown();
                } catch (Exception e) {
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
