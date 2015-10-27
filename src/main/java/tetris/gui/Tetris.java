package tetris.gui;

/**
 * Created by highway on 27/10/15.
 */
public class Tetris {
    public static void main(String[] args) {
        GUI gui = new GUI(10, 20);
        Game game = new Game(gui);
        game.run();
    }
}
