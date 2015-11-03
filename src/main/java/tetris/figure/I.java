package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class I extends Figure {
    public I(int x, int y) {
        super(x, y);

        color = 3;

        blocks[0] = new Block(x, y, color);
        blocks[1] = new Block(x, y - 1 , color);
        blocks[2] = new Block(x, y - 2, color);
        blocks[3] = new Block(x, y - 3, color);
    }
}
