package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class J extends Figure {
    public J(int x, int y) {
        super(x, y);

        color = 4;

        blocks[0] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT, color);
        blocks[1] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 1, color);
        blocks[2] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 2, color);
        blocks[3] = new Block(Tetris.START_POINT_X - 1, Tetris.HEIGHT - 2, color);
    }

    @Override
    public void rotate() {

    }
}
