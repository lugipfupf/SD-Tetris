package tetris.gui;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;

    public Game(GUI gui) {
        this.gui = gui;
    }

    public void run() {
        gui.setVisible(true);
    }
}
