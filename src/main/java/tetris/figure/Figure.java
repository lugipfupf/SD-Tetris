package tetris.figure;

import tetris.gui.Tetris;

/**
 * Created by highway on 27/10/15.
 */
public class Figure {

    private Block[] blocks;
    private int color;
    private int x, y;

    private FigureOrientation orientation = FigureOrientation.EAST;


    public Figure(int x, int y) {
        blocks = new Block[Tetris.MAX_BLOCKS];

        this.x = x;
        this.y = y;
        color = 1;

        blocks[0] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 2, color);
        blocks[1] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 3, color);
        blocks[2] = new Block(Tetris.START_POINT_X, Tetris.HEIGHT - 4, color);
        blocks[3] = new Block(Tetris.START_POINT_X + 1, Tetris.HEIGHT - 3, color);
    }

    public void move(int dx, int dy) {
        for (int i = 0; i < Tetris.MAX_BLOCKS; i++) {
            blocks[i].x += dx;
            blocks[i].y += dy;
        }
    }

    public void rotate() {
        switch (orientation) {
            case NORTH:
                blocks[0].x = blocks[0].x - 1;

                blocks[1].x = blocks[1].x - 2;

                blocks[2].y = blocks[2].y - 1;

                orientation = FigureOrientation.EAST;
                break;
            case EAST:
                blocks[1].x = blocks[1].x + 1;
                blocks[1].y = blocks[1].y + 1;

                blocks[2].x = blocks[2].x + 2;
                blocks[2].y = blocks[2].y + 2;

                orientation = FigureOrientation.SOUTH;
                break;
            case SOUTH:
                blocks[0].x = blocks[0].x + 2;
                blocks[0].y = blocks[0].y - 2;

                blocks[1].x = blocks[1].x + 1;
                blocks[1].y = blocks[1].y - 1;

                orientation = FigureOrientation.WEST;
                break;
            case WEST:
                blocks[0].x = blocks[0].x - 1;
                blocks[0].y = blocks[0].y + 2;

                blocks[2].x = blocks[2].x - 2;
                blocks[2].y = blocks[2].y - 1;

                orientation = FigureOrientation.NORTH;
                break;
        }

    }

    public Block[] getBlocks() {
        return blocks;
    }

    public int getColor() {
        return color;
    }
}
