package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class S extends Figure {
    public S(int x, int y) {
        super(x, y);

        color = 7;

        blocks[0] = new Block(x - 1, y -1, color);
        blocks[1] = new Block(x, y - 1, color);
        blocks[2] = new Block(x, y, color);
        blocks[3] = new Block(x + 1, y, color);
    }

    @Override
    public void rotate() {
        switch (orientation) {
            case NORTH:
                blocks[0].x = blocks[0].x + 2;
                blocks[0].y = blocks[0].y - 1;

                blocks[3].y = blocks[3].y - 1;

                orientation = Orientation.EAST;
                break;
            case EAST:
                blocks[0].x = blocks[0].x - 2;
                blocks[0].y = blocks[0].y + 1;

                blocks[3].y = blocks[3].y + 1;

                orientation = Orientation.NORTH;
                break;
        }
    }
}
