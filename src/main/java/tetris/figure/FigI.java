package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class FigI extends Figure {
    public FigI(int x, int y) {
        super(x, y);

        color = 3;

        blocks[0] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT, color);
        blocks[1] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 1 , color);
        blocks[2] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 2, color);
        blocks[3] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 3, color);
    }

    @Override
    public void rotate() {

    }
}
