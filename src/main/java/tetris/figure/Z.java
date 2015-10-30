package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class Z extends Figure {
    public Z(int x, int y) {
        super(x, y);

        color = 6;

        blocks[0] = new Block(Tetris.START_POINT_X -1, Tetris.HEIGHT, color);
        blocks[1] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT, color);
        blocks[2] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 1, color);
        blocks[3] = new Block(Tetris.START_POINT_X + 1, Tetris.HEIGHT - 1, color);
    }

    @Override
    public void rotate() {
        switch (orientation) {
            case NORTH:
                blocks[0].x = blocks[0].x + 2;
                blocks[0].y = blocks[0].y + 1;

                blocks[3].y = blocks[3].y + 1;

                orientation = Orientation.EAST;
                break;
            case EAST:
                blocks[0].x = blocks[0].x - 2;
                blocks[0].y = blocks[0].y - 1;

                blocks[3].y = blocks[3].y - 1;

                orientation = Orientation.NORTH;
                break;
        }
    }
}
