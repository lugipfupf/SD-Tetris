package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class L extends Figure {
    public L(int x, int y) {
        super(x, y);

        color = 5;

        blocks[0] = new Block(x, y, color);
        blocks[1] = new Block(x, y - 1, color);
        blocks[2] = new Block(x, y - 2, color);
        blocks[3] = new Block(x + 1, y - 2, color);
    }

    @Override
    public void rotate() {
        System.out.println("Current: " + orientation);
        switch (orientation) {
            case NORTH:
                blocks[0].x = blocks[0].x + 1;
                blocks[0].y = blocks[0].y - 1;

                blocks[2].x = blocks[2].x - 1;
                blocks[2].y = blocks[2].y + 1;

                blocks[3].x = blocks[3].x - 2;

                orientation = Orientation.EAST;
                break;
            case EAST:
                blocks[0].x = blocks[0].x - 1;
                blocks[0].y = blocks[0].y - 1;

                blocks[2].x = blocks[2].x + 1;
                blocks[2].y = blocks[2].y + 1;

                blocks[3].y = blocks[3].y + 2;

                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                blocks[0].x = blocks[0].x - 1;
                blocks[0].y = blocks[0].y + 1;

                blocks[2].x = blocks[2].x + 1;
                blocks[2].y = blocks[2].y - 1;

                blocks[3].x = blocks[3].x + 2;

                orientation = Orientation.WEST;
                break;
            case WEST:
                blocks[0].x = blocks[0].x + 1;
                blocks[0].y = blocks[0].y + 1;

                blocks[2].x = blocks[2].x - 1;
                blocks[2].y = blocks[2].y - 1;

                blocks[3].y = blocks[3].y - 2;

                orientation = Orientation.NORTH;
                break;
        }
        System.out.println("Next: " + orientation);
    }
}
