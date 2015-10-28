package tetris.gui;

/**
 * Created by highway on 27/10/15.
 */
public class Tetris {
    public static final int HEIGHT = 20;
    public static final int WIDTH  = 10;
    public static final int MAX_BLOCKS = 4;
    public static final int START_POINT_X = 5;

    public static void main(String[] args) {
        GUI gui = new GUI(WIDTH, HEIGHT);
        Game game = new Game(gui);
        game.run();
    }
}
