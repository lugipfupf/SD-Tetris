package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class T extends Figure {

    public T(int x, int y) {
        super(x, y);

        color = 1;

        blocks[0] = new Block(x - 1, y - 1, color);
        blocks[1] = new Block(x, y - 1, color);
        blocks[2] = new Block(x + 1, y - 1, color);
        blocks[3] = new Block(x, y, color);
    }
}