package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 28/10/15.
 */
public class FigT extends Figure {
    private FigureOrientation orientation = FigureOrientation.NORTH;


    public FigT(int x, int y) {
        super(x, y);

        color = 1;

        blocks[0] = new Block(Tetris.START_POINT_X - 1, Tetris.HEIGHT - 1, color);
        blocks[1] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 1, color);
        blocks[2] = new Block(Tetris.START_POINT_X + 1, Tetris.HEIGHT - 1, color);
        blocks[3] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT, color);
    }

    @Override
    public void move(int dx, int dy) {
        for (int i = 0; i < Tetris.MAX_BLOCKS; i++) {
            blocks[i].x += dx;
            blocks[i].y += dy;
        }
    }

    @Override
    public void rotate() {
        switch (orientation) {
            case NORTH:
                blocks[0].x = blocks[0].x + 1;
                blocks[0].y = blocks[0].y - 1;

                orientation = FigureOrientation.EAST;
                break;
            case EAST:
                blocks[3].x = blocks[3].x - 1;
                blocks[3].y = blocks[3].y - 1;

                orientation = FigureOrientation.SOUTH;
                break;
            case SOUTH:
                blocks[2].x = blocks[2].x - 1;
                blocks[2].y = blocks[2].y + 1;

                orientation = FigureOrientation.WEST;
                break;
            case WEST:
                blocks[0].x = blocks[0].x - 1;
                blocks[0].y = blocks[0].y + 1;

                blocks[2].x = blocks[2].x + 1;
                blocks[2].y = blocks[2].y - 1;

                blocks[3].x = blocks[3].x + 1;
                blocks[3].y = blocks[3].y + 1;

                orientation = FigureOrientation.NORTH;
                break;
        }

    }

    @Override
    public Block[] getBlocks() {
        return blocks;
    }

    @Override
    public int getColor() {
        return color;
    }
}
